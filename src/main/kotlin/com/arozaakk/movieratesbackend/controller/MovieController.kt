package com.arozaakk.movieratesbackend.controller

import com.arozaakk.movieratesbackend.auth.UserSession
import com.arozaakk.movieratesbackend.entity.Movie
import com.arozaakk.movieratesbackend.entity.Review
import com.arozaakk.movieratesbackend.enum.RoleEnum
import com.arozaakk.movieratesbackend.helper.AuthorizationHelper
import com.arozaakk.movieratesbackend.model.dto.MovieWithAvgRatingDTO
import com.arozaakk.movieratesbackend.model.web.*
import com.arozaakk.movieratesbackend.service.MovieService
import com.arozaakk.movieratesbackend.service.RatingService
import com.arozaakk.movieratesbackend.service.ReviewService
import io.swagger.v3.oas.annotations.Parameter
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/movies")
class MovieController(val movieService: MovieService,
                      val ratingService: RatingService,
                      val reviewService: ReviewService) {

    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getMovies(
        @RequestParam(required = false, defaultValue = "1") page: Int,
        @RequestParam(required = false, defaultValue = "10") pageSize: Int
    ): WebResponsePaginated<List<Movie>> {

        val paginatedMovies = movieService.getMovies(page, pageSize)
        return WebResponsePaginated(HttpStatus.OK.value(), HttpStatus.OK.name, paginatedMovies.second, paginatedMovies.first)
    }

    @GetMapping(value = ["/top-rated"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getTopRatedMovies(): WebResponse<List<MovieWithAvgRatingDTO>> {

        return WebResponse(HttpStatus.OK.value(), HttpStatus.OK.name, movieService.getTopRatedMovies())
    }

    @GetMapping(value = ["/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getMovie(@PathVariable id: Long): WebResponse<Movie> {
        return WebResponse(HttpStatus.OK.value(), HttpStatus.OK.name, movieService.getMovie(id))
    }

    @GetMapping(value = ["/{id}/reviews"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getMovieReviews(@PathVariable id: Long): WebResponse<List<Review>> {

        return WebResponse(HttpStatus.OK.value(), HttpStatus.OK.name, reviewService.getReviews(id))
    }

    @PostMapping(produces = [MediaType.APPLICATION_JSON_VALUE], consumes = [MediaType.APPLICATION_JSON_VALUE])
    @Parameter(name = "userSession", hidden = true)
    @ResponseStatus(HttpStatus.CREATED)
    fun addMovie(@RequestBody request: AddMovieWebRequest, userSession: UserSession): WebResponse<Movie> {

        AuthorizationHelper.validateRole(userSession, RoleEnum.ADMIN)
        return WebResponse(HttpStatus.CREATED.value(), HttpStatus.CREATED.name, movieService.addMovie(request, userSession.id))
    }

    @PostMapping(value = ["/{id}/rate"], produces = [MediaType.APPLICATION_JSON_VALUE], consumes = [MediaType.APPLICATION_JSON_VALUE])
    @Parameter(name = "userSession", hidden = true)
    @ResponseStatus(HttpStatus.CREATED)
    fun rateMovie(
        @PathVariable id: Long, @RequestBody request: AddRatingWebRequest,
        userSession: UserSession
    ): WebResponse<AddRatingWebResponse> {

        return WebResponse(HttpStatus.CREATED.value(), HttpStatus.CREATED.name, ratingService.addRating(request, id, userSession.id))
    }

    @PostMapping(value = ["/{id}/review"], produces = [MediaType.APPLICATION_JSON_VALUE], consumes = [MediaType.APPLICATION_JSON_VALUE])
    @Parameter(name = "userSession", hidden = true)
    @ResponseStatus(HttpStatus.CREATED)
    fun reviewMovie(
        @PathVariable id: Long, @RequestBody request: AddReviewWebRequest,
        userSession: UserSession
    ): WebResponse<AddReviewWebResponse> {

        return WebResponse(HttpStatus.CREATED.value(), HttpStatus.CREATED.name, reviewService.addReview(request, id, userSession.id))
    }
}