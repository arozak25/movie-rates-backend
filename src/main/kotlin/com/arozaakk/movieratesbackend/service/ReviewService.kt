package com.arozaakk.movieratesbackend.service

import com.arozaakk.movieratesbackend.entity.Rating
import com.arozaakk.movieratesbackend.entity.Review
import com.arozaakk.movieratesbackend.model.web.AddRatingWebRequest
import com.arozaakk.movieratesbackend.model.web.AddRatingWebResponse
import com.arozaakk.movieratesbackend.model.web.AddReviewWebRequest
import com.arozaakk.movieratesbackend.model.web.AddReviewWebResponse
import com.arozaakk.movieratesbackend.repository.MovieRepository
import com.arozaakk.movieratesbackend.repository.RatingRepository
import com.arozaakk.movieratesbackend.repository.ReviewRepository
import com.arozaakk.movieratesbackend.repository.UserRepository
import jakarta.validation.Valid
import jakarta.validation.ValidationException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.validation.annotation.Validated

@Validated
interface ReviewService {

    @Transactional
    fun addReview(@Valid request: AddReviewWebRequest, movieId: Long, userId: Long): AddReviewWebResponse

    @Transactional
    fun getReviews(movieId: Long): List<Review>

}

@Service
class ReviewServiceImpl(
    private val reviewRepository: ReviewRepository,
    private val movieRepository: MovieRepository,
    private val userRepository: UserRepository
): ReviewService {

    override fun addReview(request: AddReviewWebRequest, movieId: Long, userId: Long) : AddReviewWebResponse {
        val movie = movieRepository.findByIdOrNull(movieId)
            ?: throw ValidationException("Movie with id $movieId not found")

        val user = userRepository.findByIdOrNull(userId) ?: throw ValidationException("User with id $userId not found")

        if (reviewRepository.existsByUserAndMovie(user, movie))
            throw ValidationException("You already add a review, Thank you")

        val review = Review(movie = movie, user = user, reviewText = request.reviewText)
        reviewRepository.save(review)

        return AddReviewWebResponse(id = review.id, reviewText = review.reviewText, movie = movie)
    }

    override fun getReviews(movieId: Long): List<Review> {
        val movie = movieRepository.findByIdOrNull(movieId)
            ?: throw ValidationException("Movie with id $movieId not found")

        return reviewRepository.findAllByMovie(movie)
    }

}