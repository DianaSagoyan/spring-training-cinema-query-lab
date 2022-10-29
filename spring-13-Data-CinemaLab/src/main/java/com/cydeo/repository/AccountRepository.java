package com.cydeo.repository;

import com.cydeo.entity.Account;
import com.cydeo.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    // ------------------- DERIVED QUERIES ------------------- //

    //Write a derived query to list all accounts with a specific country or state
    List<Account> findByCountryOrState(String country, String state);

    //Write a derived query to list all accounts with age lower than or equal to a specific value
    List<Account> findByAgeBefore(int age);

    //Write a derived query to list all accounts with a specific role
    List<Account> findByRole(UserRole role);

    //Write a derived query to list all accounts between a range of ages
    List<Account> findByAgeBetween(int age1, int age2);

    //Write a derived query to list all accounts where the beginning of the address contains the keyword
    List<Account> findByAddressStartsWith(String pattern);

    //Write a derived query to sort the list of accounts with age
    List<Account> findAccountsByOrderByAge();

    // ------------------- JPQL QUERIES ------------------- //

    //Write a JPQL query that returns all accounts
    @Query("select a from Account a")
    List<Account> retrieveAccounts();

    //Write a JPQL query to list all admin accounts
    @Query("select a from Account a where a.role = 'Admin'")
    List<Account> retrieveAdminAccounts();

    //Write a JPQL query to sort all accounts with age
    @Query("select a from Account a order by a.age")
    List<Account> retrieveAccountsSortedByAge();


    // ------------------- Native QUERIES ------------------- //

    //Write a native query to read all accounts with an age lower than a specific value
    @Query(value = "select * from account_details where age < :age", nativeQuery = true)
    List<Account> retrieveAccountsWhereAgeLowerThan(@Param("age")int age);

    //Write a native query to read all accounts that a specific value can be containable in the name, address, country, state city
    @Query(nativeQuery = true, value = "select * from account_details where in (name, address, country, state, city) like :str")//?
    List<Account> retrieveAccountsContainableInNameAddressCountryState(@Param("str") String str);

    //Write a native query to read all accounts with an age higher than a specific value
    @Query(nativeQuery = true, value = "select * from account_details where age > :age")
    List<Account> retrieveAgeHigherThan(@Param("age") int age);


}
