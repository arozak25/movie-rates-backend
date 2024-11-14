package com.arozaakk.movieratesbackend.service

import com.arozaakk.movieratesbackend.entity.Rating
import com.arozaakk.movieratesbackend.model.web.AddRatingWebRequest
import com.arozaakk.movieratesbackend.model.web.AddRatingWebResponse
import com.arozaakk.movieratesbackend.repository.MovieRepository
import com.arozaakk.movieratesbackend.repository.RatingRepository
import com.arozaakk.movieratesbackend.repository.UserRepository
import jakarta.validation.ValidationException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface RatingService {

    @Transactional
    fun addRating(request: AddRatingWebRequest, movieId: Long, userId: Long): AddRatingWebResponse

}

@Service
class RatingServiceImpl(
    private val ratingRepository: RatingRepository,
    private val movieRepository: MovieRepository,
    private val userRepository: UserRepository
): RatingService {

    override fun addRating(request: AddRatingWebRequest, movieId: Long, userId: Long) : AddRatingWebResponse {
        val movie = movieRepository.findByIdOrNull(movieId)
            ?: throw ValidationException("Movie with id $movieId not found")

        val user = userRepository.findByIdOrNull(userId) ?: throw ValidationException("User with id $userId not found")

        if (ratingRepository.existsByUserAndMovie(user, movie))
            throw ValidationException("You already add a rating, Thank you")

        val rating = Rating(movie = movie, user = user, rating = request.rating)
        ratingRepository.save(rating)

        return AddRatingWebResponse(id = rating.id, rating = rating.rating, movie = movie)
    }

}