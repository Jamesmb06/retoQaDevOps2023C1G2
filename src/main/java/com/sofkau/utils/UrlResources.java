package com.sofkau.utils;

public enum UrlResources {

    REQRES_BASE_URL_PUT("https://reqres.in/api/"),
    RESOURCES_PUT("users/"),
    SOAP_CAPITAL_BASE_URL("http://webservices.oorsprong.org/"),
    RESOURCE_CAPITAL("websamples.countryinfo/CountryInfoService.wso"),
    BODY_PATH("src/test/resources/soapXml/filecapital.xml"),

    BASE_POKE_URL("https://pokeapi.co/"),
    GENERATION1_RESOURCE("api/v2/generation/"),
    PHOTO_POST_RESOURCE("/photos"),
    JSONPLACEHOLDER_BASE_URL("https://jsonplaceholder.typicode.com");

    private final String value;

    UrlResources(String value){
        this.value=value;
    }

    public String getValue(){
        return value;
    }
}
