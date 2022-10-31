package com.cydeo.repository;

import com.cydeo.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    // ------------------- DERIVED QUERIES ------------------- //

    //Write a derived query to read a movie with a name
    List<Movie> findByName(String name);  //?


    //Write a derived query to list all movies between a range of prices
    List<Movie> findByPriceBetween(int price1, int price2);


    //Write a derived query to list all movies where duration exists in the specific list of duration
    List<Movie> findByDurationExists(int duration);


    //Write a derived query to list all movies with higher than a specific release date
    List<Movie> findByReleaseDateAfter(LocalDateTime date);


    //Write a derived query to list all movies with a specific state and type
    List<Movie> findByStateAndType(String state, String type);


    // ------------------- JPQL QUERIES ------------------- //

    //Write a JPQL query to list all movies between a range of prices
    @Query("select m from Movie m where m.price between ?1 and ?2")
    List<Movie> retrieveBetweenPriceRange(int price1, int price2);


    //Write a JPQL query that returns all movie names
    @Query("select m.name from Movie m")
    List<String> retrieveMovieNames();


    // ------------------- Native QUERIES ------------------- //

    //Write a native query that returns a movie by name
    @Query(nativeQuery = true, value = "select * from movie where name = ?1")
    Movie retrieveMovieByName(String name);


    //Write a native query that return the list of movies in a specific range of prices
    @Query(nativeQuery = true, value = "select * from movie where price between ?1 and ?2")
    List<Movie> retrieveMoviesBetweenPrices(int price1, int price2);


    //Write a native query to return all movies where duration exists in the range of duration
    @Query(nativeQuery = true, value = "select * from movie where duration = ?1") //?
    List<Movie> retrieveMoviesWhereDurationExists(int duration);


    //Write a native query to list the top 5 most expensive movies
    @Query(nativeQuery = true, value = "select top 5 from movie order by price ")
    List<Movie> retrieveTop5ExpensiveMovies();


}
