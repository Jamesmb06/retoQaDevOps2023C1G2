package com.sofkau.utils;

public enum UrlResources {

    REQRES_BASE_URL("https://reqres.in/"),
    REGISTER_SUCCESSFUL_RESOURCE("api/register"),
    POKE_API_URL("https://pokeapi.co/api/v2/"),
    POKEMON("pokemon/"),
    BASE_JSON_URL("https://jsonplaceholder.typicode.com/"),
    DELETE_ALBUM("albums/"),
    PUT_POST_RESOURCE("posts/");

    private final String value;

    UrlResources(String value){
        this.value=value;
    }

    public String getValue(){
        return value;
    }
}
