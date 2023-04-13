package com.sofkau.utils;

public enum UrlResources {

    JSON_PLACEHOLDER_BASE_URL("https://jsonplaceholder.typicode.com"),
    ALBUMS_RESOURCE("/albums/%d"),
    TO_DO_RESOURCE("/users/1/todos?id=%d"),
    FAKE_REST_API_BASE_URL("https://fakerestapi.azurewebsites.net"),
    BOOKS_RESOURCE("/api/v1/Books/%d");

    private final String value;

    UrlResources(String value){
        this.value=value;
    }

    public String getValue(){
        return value;
    }
}
