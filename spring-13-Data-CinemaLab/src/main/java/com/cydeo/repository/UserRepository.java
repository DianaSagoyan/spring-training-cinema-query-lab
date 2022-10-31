package com.cydeo.repository;

import com.cydeo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // ------------------- DERIVED QUERIES ------------------- //

    //Write a derived query to read a user with an email?
    Optional<User> findByEmail(String email); // ?


    //Write a derived query to read a user with a username?
    Optional<User> findByUsername(String username);


    //Write a derived query to list all users that contain a specific name?
    List<User> findByAccountNameContaining(String name);


    //Write a derived query to list all users that contain a specific name in the ignore case mode?
    List<User> findByAccountNameContainingIgnoreCase(String name);


    //Write a derived query to list all users with an age greater than a specified age?
    List<User> findByAccountAgeGreaterThan(int age);


    // ------------------- JPQL QUERIES ------------------- //

    //Write a JPQL query that returns a user read by email?
    @Query("select u from User u where u.email = ?1")
    User retrieveUserByEmail(String email);


    //Write a JPQL query that returns a user read by username?
    @Query("select u from User u where u.username = ?1")
    User retrieveUserByUserName(String name);


    //Write a JPQL query that returns all users?
    @Query("select u from User u")
    List<User> retrieveAllUsers();


    // ------------------- Native QUERIES ------------------- //

    //Write a native query that returns all users that contain a specific name?
    @Query(nativeQuery = true, value = "select * from user_account u " +
            "join account_details ad on ad.id = u.acciunt_details_id where ad.name like concat('%',?1, '%' ) ")
    List<User> retrieveUserWithName(String name);


    //Write a native query that returns all users?
    @Query(nativeQuery = true, value = "select * from user_account")
    List<User> retrieveUsers();


    //Write a native query that returns all users in the range of ages?
    @Query(nativeQuery = true, value = "select * from user_account u join account_details a on a.id = u.account_details_id where a.age between ?1 and ?2")
    List<User> retrieveUserInAgeRange(int age1, int age2);


    //Write a native query to read a user by email?
    @Query(nativeQuery = true, value = "select * from user_account where email = ?1")
    List<User> retrieveUserByUsingEmail(String email);


}
