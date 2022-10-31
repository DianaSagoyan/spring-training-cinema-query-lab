package com.cydeo.repository;

import com.cydeo.entity.Account;
import com.cydeo.entity.Movie;
import com.cydeo.entity.Ticket;
import com.cydeo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    // ------------------- DERIVED QUERIES ------------------- //

    //Re-look and go through each query

    //Write a derived query to count how many tickets a user bought
    int findByUserAccount(); //?


    //Write a derived query to list all tickets by specific email
    List<Ticket> findByUserAccount_Email(String email);


    //Write a derived query to count how many tickets are sold for a specific movie
    int countTicketsByMovie_Name(String name); //?


    //Write a derived query to list all tickets between a range of dates
    List<Movie> findByDateTimeBetween(LocalDateTime date1, LocalDateTime date2);


    // ------------------- JPQL QUERIES ------------------- //

    //Write a JPQL query that returns all tickets are bought from a specific user ;
    @Query("select t from Ticket t where t.id = ?1")
    List<Ticket> retrieveUserTickets(Long id);  //?


    //Write a JPQL query that returns all tickets between a range of dates
    @Query("select t from Ticket t where t.dateTime between ?1 and  ?2")
    List<Ticket> retrieveTicketsBetween(LocalDateTime date1, LocalDateTime date2);


    // ------------------- Native QUERIES ------------------- //

    //Write a native query to count the number of tickets a user bought
    @Query(nativeQuery = true, value = "select count(*) from ticket where user_account.id = ?1")
    int retrieveCountUserTickets(int id);


    //Write a native query to count the number of tickets a user bought in a specific range of dates
    @Query(nativeQuery = true, value = "select (*) from ticket where date_time between ?1 and ?2")
    int retrieveNumberOfTicketsBetweenDates(LocalDateTime date1, LocalDateTime date2);


    //Write a native query to distinct all tickets by movie name
    @Query(nativeQuery = true, value = "select distinct m.name from ticket join movie-cinema m ")
    List<Ticket> retrieveDistinctName();


    //Write a native query to find all tickets by user email
    @Query(nativeQuery = true, value = "select * from ticket join user_account u where u.email = ?1")
    List<Ticket> retrieveTicketByUserEmail(String email);


    //Write a native query that returns all tickets
    @Query(nativeQuery = true, value = "select * from ticket")
    List<Ticket> retrieveAllTickets();


    //Write a native query to list all tickets where a specific value should be containable in the username or account name or movie name
    @Query(nativeQuery = true, value = "select * from ticket join user_account u where ?1 in(u.username)") // ?
    List<Ticket> retrieveTicketValueContainedInUserNameOrAccountNameOrMovieName(String value);


}
