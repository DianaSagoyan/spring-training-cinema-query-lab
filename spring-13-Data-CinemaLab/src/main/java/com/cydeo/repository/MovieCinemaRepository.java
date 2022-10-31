package com.cydeo.repository;

import com.cydeo.entity.Movie;
import com.cydeo.entity.MovieCinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MovieCinemaRepository extends JpaRepository<MovieCinema, Long> {

    // ------------------- DERIVED QUERIES ------------------- //

    //Write a derived query to read movie cinema with id
    List<MovieCinema> findById();  // ?


    //Write a derived query to count all movie cinemas with a specific cinema id
    int countMovieCinemaByCinema_Id(int id);  // ?


    //Write a derived query to count all movie cinemas with a specific movie id
    int countMovieCinemaByMovie_Id(int id);  // ?

    //Write a derived query to list all movie cinemas with higher than a specific date
    List<MovieCinema> findByDateTimeAfter(LocalDateTime date);


    //Write a derived query to find the top 3 expensive movies
    List<MovieCinema> findTop3ByMovie_Price(int price);


    //Write a derived query to list all movie cinemas that contain a specific movie name
    List<MovieCinema> findByMovieNameContaining(String pattern);


    //Write a derived query to list all movie cinemas in a specific location name
    List<MovieCinema> findByCinema_Location(String location);


    // ------------------- JPQL QUERIES ------------------- //

    //Write a JPQL query to list all movie cinemas with higher than a specific date
    @Query("select m from MovieCinema m where m.dateTime > ?1")
    List<MovieCinema> retrieveMovieAfterDate(LocalDateTime date);


    // ------------------- Native QUERIES ------------------- //

    //Write a native query to count all movie cinemas by cinema id
    @Query(nativeQuery = true, value = "select count(*) from movie_cinema where id = ?1")
    int retrieveCountCinemaById(Long id);

    //Write a native query that returns all movie cinemas by location name
    @Query(nativeQuery = true, value = "select * from movie_cinema where location = ?1")
    List<MovieCinema> retrieveCountCinemaByLocation(String Location);


}
