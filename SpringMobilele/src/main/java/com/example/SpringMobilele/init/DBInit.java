package com.example.SpringMobilele.init;

import com.example.SpringMobilele.repository.BrandRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DBInit implements CommandLineRunner {


    public DBInit(BrandRepository brandRepository) {
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
