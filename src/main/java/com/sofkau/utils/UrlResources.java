package com.sofkau.utils;

public enum UrlResources {
    SOAP_CAMEL_CASE_BASE_URL("https://www.dataaccess.com/webservicesserver/"),
    RESOURCE_CAMEL_CASE("TextCasing.wso"),
    BODY_CAMEL_CASE_PATH("src/test/resources/soapXml/filecamelcase.xml"),
    JSONPLACE_BASE_URL("https://jsonplaceholder.typicode.com/"),
    POSTS_BY_ID_RESOURCE("posts/"),
    COMMENTS_BY_POST("comments?postId="),
    RICK_AND_MORTY_BASE_URL("https://rickandmortyapi.com/api"),
    RESOURCE_RICKANDMORTY("/character/");

    private final String value;

    UrlResources(String value){
        this.value=value;
    }

    public String getValue(){
        return value;
    }
}
