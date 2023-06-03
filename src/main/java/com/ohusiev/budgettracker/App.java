package com.ohusiev.budgettracker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ohusiev.budgettracker.persistence.model.User;
import com.ohusiev.budgettracker.persistence.repository.UserRepository;

@SpringBootApplication
public class App {
    private final Logger appLog = LoggerFactory.getLogger(this.getClass());

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    /*@Bean
    CommandLineRunner start(UserRepository userRepository, PasswordEncoder passwordEncoder){
        return args -> {
            User user = new User("shuran", passwordEncoder.encode("1001"));
            userRepository.save(user).subscribe();

            userRepository.findAll().log().subscribe(u -> appLog.info("user: {}", u));
        };
    }*/
}
