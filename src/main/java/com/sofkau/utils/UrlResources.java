package com.sofkau.utils;

public enum UrlResources {

    REQRES_BASE_URL_PUT("https://reqres.in/api/"),
    RESOURCES_PUT("users/"),
    BASE_POKE_URL("https://pokeapi.co/"),
    RESOURCE_BAYAS("api/v2/berry/"),
    RESOURCE_JUEGO("api/v2/version/"),
    SOAP_CALCULADORA_BASE_URL("http://www.dneonline.com/"),
    RESOURCE_CALCULADORA_MULTIPLICAR("calculator.asmx"),
    BODY_MULTIPLICAR("src/test/resources/soapXml/filemultiplicar.xml");

    private final String value;

    UrlResources(String value){
        this.value=value;
    }

    public String getValue(){
        return value;
    }
}
