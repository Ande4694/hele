package com.example.api.Bootstrap;

import com.example.api.Repository.CategoryRepository;
import com.example.api.Model.Cars;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Spring er startet");

        Cars toyota = new Cars();
        toyota.setColor("puprle");
        toyota.setName("yaris");

        Cars porsche = new Cars();
        porsche.setColor("Sort");
        porsche.setName("hans");

        Cars bmw = new Cars();
        bmw.setColor("blue");
        bmw.setName("bimmer");


        categoryRepository.save(toyota);
        categoryRepository.save(porsche);
        categoryRepository.save(bmw);
        System.out.println("Gemt antal objekter: " + categoryRepository.count());

    }
}

