package com.sofkau.models.rest;

import com.github.javafaker.Faker;
import lombok.Data;

@Data
public class Book {
    private static final Faker faker = new Faker();
    private int id;
    private String title;
    private String description = faker.harryPotter().quote();
    private int pageCount;
    private String excerpt = faker.backToTheFuture().quote();
    private String publishDate = String.format("%d-%02d-%02d",
            faker.number().numberBetween(2000, 2023),
            faker.number().numberBetween(1, 12),
            faker.number().numberBetween(1, 28)
    );
}