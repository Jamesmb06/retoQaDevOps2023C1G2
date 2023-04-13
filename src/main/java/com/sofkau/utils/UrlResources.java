package com.sofkau.utils;

public enum UrlResources {

    REQRES_BASE_URL("https://reqres.in/"),
    REGISTER_SUCCESSFUL_RESOURCE("api/register"),
    BASE_POKE_URL("https://pokeapi.co/"),
    POKEMON("api/v2/pokemon/"),
    BASE_JSON_URL("https://jsonplaceholder.typicode.com/"),
    DELETE_ALBUM("albums/"),
    PUT_POST_RESOURCE("posts/"),
    SOAP_DIVISA_BASE_URL("http://fx.currencysystem.com/"),
    RESOURCE_DIVISA("webservices/CurrencyServer4.asmx"),
    BODY_PATH1("src/test/resources/soapXml/filedivisas.xml"),
    SOAP_CALCULADORA_BASE_URL("http://www.dneonline.com/"),
    RESOURCE_CALCULADORA("calculator.asmx"),
    BODY_PATH2("src/test/resources/soapXml/filecalculadora.xml");



    private final String value;

    UrlResources(String value){
        this.value=value;
    }

    public String getValue(){
        return value;
    }
}
