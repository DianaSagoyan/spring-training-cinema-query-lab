package com.cydeo;

import com.cydeo.repository.AccountRepository;
import com.cydeo.repository.CinemaRepository;
import com.cydeo.repository.GenreRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class QueryDemo implements CommandLineRunner {

    private final AccountRepository accountRepository;
    private final CinemaRepository cinemaRepository;
    private final GenreRepository genreRepository;

    public QueryDemo(AccountRepository accountRepository, CinemaRepository cinemaRepository, GenreRepository genreRepository) {
        this.accountRepository = accountRepository;
        this.cinemaRepository = cinemaRepository;
        this.genreRepository = genreRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("------------------Account---------------------");
        System.out.println("Account Query: " + accountRepository.retrieveAccounts());
        System.out.println("Find By Age: " + accountRepository.findByAgeLessThanEqual(47));
        System.out.println("Find By Country or state: " + accountRepository.findByCountryOrState("United States", "Virginia"));
        System.out.println("Accounts where age is lower than: " + accountRepository.retrieveAccountsWhereAgeLowerThan(40));
        System.out.println("Age higher than: " + accountRepository.retrieveAgeHigherThan(25));


        System.out.println("------------------Cinema---------------------");
        System.out.println("Cinema by country: " + cinemaRepository.findByLocation_Country("United States"));
        System.out.println("Cinema name: " + cinemaRepository.retrieveCinemaWithId(10l));
        System.out.println("Cinema by location: " + cinemaRepository.retrieveCinemaByLocationCountry("United States"));
        System.out.println("Select distinct: " + cinemaRepository.retrieveDistinct());


        System.out.println("------------------Genre---------------------");
        System.out.println("All genres: " + genreRepository.retrieveAll());
        System.out.println("Genre where name contains: " + genreRepository.retrieveGenresByContainingName("rr"));
    }
}
