package com.cydeo;

import com.cydeo.repository.AccountRepository;
import com.cydeo.repository.CinemaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

@Component
public class QueryDemo implements CommandLineRunner {

    private final AccountRepository accountRepository;
    private final CinemaRepository cinemaRepository;

    public QueryDemo(AccountRepository accountRepository, CinemaRepository cinemaRepository) {
        this.accountRepository = accountRepository;
        this.cinemaRepository = cinemaRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("------------------Account---------------------");
        System.out.println("Account Query: " + accountRepository.retrieveAccounts());
        System.out.println("Find By Age: " + accountRepository.findByAgeBefore(47));
        System.out.println("Find By Country or state: " + accountRepository.findByCountryOrState("United States", "Virginia"));
        System.out.println("Accounts where age is lower than: " + accountRepository.retrieveAccountsWhereAgeLowerThan(40));
        System.out.println("Age higher than: " + accountRepository.retrieveAgeHigherThan(25));


        System.out.println("------------------Cinema---------------------");
        System.out.println("Cinema by country: " + cinemaRepository.findByLocation_Country("United States"));
//        System.out.println("Cinema name: " + cinemaRepository.retrieveCinemaWithId(BigInteger.valueOf(10))); //?ERROR
        System.out.println("Cinema by location: " + cinemaRepository.retrieveCinemaByLocationCountry("United States"));
        System.out.println("Select distinct: " + cinemaRepository.retrieveDistinct());
    }
}
