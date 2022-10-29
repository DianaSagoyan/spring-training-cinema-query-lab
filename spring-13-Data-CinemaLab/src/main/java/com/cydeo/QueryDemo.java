package com.cydeo;

import com.cydeo.repository.AccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class QueryDemo implements CommandLineRunner {

    private final AccountRepository accountRepository;

    public QueryDemo(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Account Query: " + accountRepository.retrieveAccounts());
        System.out.println("Find By Age: " + accountRepository.findByAgeBefore(47));
        System.out.println("Find By Country or state: " + accountRepository.findByCountryOrState("United States", "Virginia"));
        System.out.println("Accounts where age is lower than: " + accountRepository.retrieveAccountsWhereAgeLowerThan(40));
        System.out.println("Age higher than: " + accountRepository.retrieveAgeHigherThan(25));
    }
}
