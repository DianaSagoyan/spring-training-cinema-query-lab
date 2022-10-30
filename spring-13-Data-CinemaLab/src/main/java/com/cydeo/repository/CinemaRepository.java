package com.cydeo.repository;

import com.cydeo.entity.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface CinemaRepository extends JpaRepository<Cinema, Long> {

    // ------------------- DERIVED QUERIES ------------------- //

    //Write a derived query to get cinema with a specific name
    List<Cinema> findByName(String name);


    //Write a derived query to read sorted the top 3 cinemas that contains a specific sponsored name
    List<Cinema> findTop3BySponsoredNameContainsOrderBySponsoredName(String name);

    //Write a derived query to list all cinemas in a specific country
    List<Cinema> findByLocation_Country(String country);

    //Write a derived query to list all cinemas with a specific name or sponsored name
    List<Cinema> findByNameOrSponsoredName(String name, String sponsoredName);


    // ------------------- JPQL QUERIES ------------------- //

    //Write a JPQL query to read the cinema name with a specific id
    @Query("select c.name from Cinema c where c.id = ?1") //?    ERROR
    String retrieveCinemaWithId(BigInteger id);


    // ------------------- Native QUERIES ------------------- //

    //Write a native query to read all cinemas by location country
    @Query(nativeQuery = true, value = "select * from cinema right join location l on l.country = ?1")
    List<Cinema> retrieveCinemaByLocationCountry(String country);


    //Write a native query to read all cinemas by name or sponsored name contains a specific pattern
    @Query(nativeQuery = true, value = "select * from cinema where name contains ?1 or where sponsored_name contains ?1")
    List<Cinema> retrieveCinemaWithPattern(String pattern);

    //Write a native query to sort all cinemas by name
    @Query(nativeQuery = true, value = "select * from cinema order by name")
    List<Cinema> sortByName();


    //Write a native query to distinct all cinemas by sponsored name
    @Query(nativeQuery = true, value = "select distinct sponsored_name from cinema ") //  ?
    List<String> retrieveDistinct();

}
