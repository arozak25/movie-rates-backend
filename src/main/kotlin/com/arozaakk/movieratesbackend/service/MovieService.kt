package com.arozaakk.movieratesbackend.service

import com.arozaakk.movieratesbackend.entity.Movie
import com.arozaakk.movieratesbackend.entity.MovieTag
import com.arozaakk.movieratesbackend.entity.Tag
import com.arozaakk.movieratesbackend.model.dto.MovieWithAvgRatingDTO
import com.arozaakk.movieratesbackend.model.web.AddMovieWebRequest
import com.arozaakk.movieratesbackend.model.web.Pagination
import com.arozaakk.movieratesbackend.repository.MovieRepository
import com.arozaakk.movieratesbackend.repository.MovieTagRepository
import com.arozaakk.movieratesbackend.repository.TagRepository
import com.arozaakk.movieratesbackend.repository.UserRepository
import jakarta.persistence.EntityManager
import jakarta.persistence.Query
import jakarta.transaction.Transactional
import jakarta.validation.ValidationException
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.validation.annotation.Validated


@Validated
interface MovieService {

    @Transactional
    fun addMovie(request: AddMovieWebRequest, userId: Long): Movie

    fun getMovies(page: Int, pageSize: Int): Pair<List<Movie>, Pagination>

    fun getMovie(id: Long): Movie

    fun getTopRatedMovies(): List<MovieWithAvgRatingDTO>
}

@Service
class MovieServiceImpl(
    private val movieRepository: MovieRepository,
    private val userRepository: UserRepository,
    private val tagRepository: TagRepository,
    private val movieTagRepository: MovieTagRepository,
    private val entityManager: EntityManager
): MovieService {

    override fun addMovie(request: AddMovieWebRequest, userId: Long): Movie {
        if (!userRepository.existsById(userId))
            throw ValidationException("User with id $userId doesn't exist")

        val movie = Movie(
            title = request.title,
            description = request.description,
            releaseDate = request.releaseDate,
            director = request.director,
            genre = request.genre,
            createdBy = userId
        )

        movieRepository.save(movie)

        for (tagName in request.tags) {
            var tag = tagRepository.findFirstByName(tagName.lowercase())

            // Check if tag exists, otherwise create a new one
            if (tag != null) {
                val movieTag = MovieTag(movieId = movie.id, tagId = tag.id, createdBy = userId)
                movieTagRepository.save(movieTag)
            } else {
                tag = Tag(name = tagName.lowercase(), createdBy = userId)
                tag = tagRepository.save(tag)

                val movieTag = MovieTag(movieId = movie.id, tagId = tag.id, createdBy = userId)
                movieTagRepository.save(movieTag)
            }
        }

        return movie

    }

    override fun getMovies(page: Int, pageSize: Int): Pair<List<Movie>, Pagination> {
        if (page == 0 || pageSize == 0)
            throw ValidationException("page or pageSize must not be zero")

        val pageable = PageRequest.of(page.minus(1), pageSize)
        val moviePage = movieRepository.findAll(pageable)

        // Calculate total pages (totalData / pageSize)
        val totalData = moviePage.totalElements.toInt()
        val totalPage = if (totalData % pageSize == 0) {
            totalData / pageSize
        } else {
            totalData / pageSize + 1
        }

        val pagination = Pagination(
            totalData = totalData,
            totalPage = totalPage,
            page = page
        )

        return Pair(moviePage.content, pagination)
    }

    override fun getMovie(id: Long): Movie {
        return movieRepository.findByIdOrNull(id) ?: throw ValidationException("Movie with id $id doesn't exist")
    }

    override fun getTopRatedMovies(): List<MovieWithAvgRatingDTO> {
        val jpql = """
            SELECT new com.arozaakk.movieratesbackend.model.dto.MovieWithAvgRatingDTO(
                r.movie.id, 
                r.movie.title, 
                AVG(r.rating)
            )
            FROM Rating r
            GROUP BY r.movie.id
            ORDER BY AVG(r.rating) DESC
        """

        val query: Query = entityManager.createQuery(jpql)
        query.maxResults = 10

        return query.resultList as List<MovieWithAvgRatingDTO>
    }

}