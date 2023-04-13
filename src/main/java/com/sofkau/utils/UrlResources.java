package com.sofkau.utils;

public enum UrlResources {

    REQRES_BASE_URL_PUT("https://reqres.in/api/"),
    RESOURCES_PUT("users/"),
    RESOURCES_POST("register/"),
    SOAP_CAPITAL_BASE_URL("http://webservices.oorsprong.org/"),
    RESOURCE_CAPITAL("websamples.countryinfo/CountryInfoService.wso"),
    BODY_PATH("src/test/resources/soapXml/filecapital.xml"),
    RESOURCE_NUMBER_CONVERSION("/webservicesserver/numberconversion.wso"),
    SOAP_NUMBER_CONVERSION_BASE_URL("https://www.dataaccess.com"),
    BODY_PATH_NUMBER_CONVERSION("src/test/resources/soapXml/filenumberconversionletters.xml"),
    BASE_JSON_URL("https://jsonplaceholder.typicode.com/"),
    POST_JSON_URL("posts/"),
    PUT_JSON_URL("photos/");

    private final String value;

    UrlResources(String value){
        this.value=value;
    }

    public String getValue(){
        return value;
    }
}
