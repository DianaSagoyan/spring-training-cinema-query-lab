package com.cydeo.repository;

import com.cydeo.entity.Account;
import com.cydeo.entity.Movie;
import com.cydeo.entity.Ticket;
import com.cydeo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    // ------------------- DERIVED QUERIES ------------------- //

    //Write a derived query to count how many tickets a user bought
    int countByUserAccountId(Long userId);


    //Write a derived query to list all tickets by specific email
    List<Ticket> findByUserAccount_Email(String email);


    //Write a derived query to count how many tickets are sold for a specific movie
    int countTicketsByMovieCinemaMovieName(String name);


    //Write a derived query to list all tickets between a range of dates
    List<Ticket> findByDateTimeBetween(LocalDateTime date1, LocalDateTime date2);


    // ------------------- JPQL QUERIES ------------------- //

    //Write a JPQL query that returns all tickets are bought from a specific user ;
    @Query("select t from Ticket t where t.userAccount.id = ?1")
    List<Ticket> retrieveUserTickets(Long id);


    //Write a JPQL query that returns all tickets between a range of dates
    @Query("select t from Ticket t where t.dateTime between ?1 and  ?2")
    List<Ticket> retrieveTicketsBetween(LocalDateTime date1, LocalDateTime date2);


    // ------------------- Native QUERIES ------------------- //

    //Write a native query to count the number of tickets a user bought
    @Query(nativeQuery = true, value = "select count(*) from ticket where user_account_id = ?1")
    int retrieveCountUserTickets(int id);


    //Write a native query to count the number of tickets a user bought in a specific range of dates
    @Query(nativeQuery = true, value = "select count(*) from ticket where user_account_id = ?1 date_time between ?2 and ?3")
    int retrieveNumberOfTicketsBetweenDates(int id, LocalDateTime date1, LocalDateTime date2);


    //Write a native query to distinct all tickets by movie name
    @Query(nativeQuery = true, value = "select distinct (m.name) from ticket t join movie-cinema mc on mc.id = t.movie_cinema_id" +
    "join movie m on m.id = mc.movie_id")
    List<String> retrieveDistinctName();


    //Write a native query to find all tickets by user email
    @Query(nativeQuery = true, value = "select * from ticket t join user_account u on t.user_account_id = u.id where u.email = ?1")
    List<Ticket> retrieveTicketByUserEmail(String email);


    //Write a native query that returns all tickets
    @Query(nativeQuery = true, value = "select * from ticket")
    List<Ticket> retrieveAllTickets();


    //Write a native query to list all tickets where a specific value should be containable in the username or account name or movie name
    @Query(value = "SELECT * FROM ticket t JOIN user_account ua ON t.user_account_id = ua.id " +
            "JOIN account_details ad ON ad.id = ua.account_details_id " +
            "JOIN movie_cinema mc ON mc.id = t.movie_cinema_id " +
            "JOIN movie m ON mc.movie_id = m.id " +
            "WHERE ua.username ILIKE concat('%', ?1, '%') " +
            "OR ad.name ILIKE concat('%', ?1, '%') " +
            "OR m.name ILIKE concat('%', ?1, '%')", nativeQuery = true)
    List<Ticket> retrieveAllBySearchCriteria(@Param("searchCriteria") String searchCriteria);


}
