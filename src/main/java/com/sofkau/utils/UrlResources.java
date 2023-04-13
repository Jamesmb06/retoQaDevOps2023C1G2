package com.sofkau.utils;

public enum UrlResources {

    REQRES_BASE_URL_PUT("https://reqres.in/api/"),
    RESOURCES_PUT("users/"),
    SOAP_CAPITAL_BASE_URL("http://webservices.oorsprong.org/"),
    RESOURCE_CAPITAL("websamples.countryinfo/CountryInfoService.wso"),
    BODY_PATH("src/test/resources/soapXml/filecapital.xml"),
    JSONPLACEHOLDER_BASE_URL_PUT("https://jsonplaceholder.typicode.com/"),
    RESOURCES_PUT_JSONPLACEHOLDER("comments/"),
    DUMMYJSON_BASE_URL_GET("https://dummyjson.com/"),
    RESOURCES_GET_DUMMYJSON("comments"),
    SOAP_ZIPCODE_BASE_URL("https://graphical.weather.gov:443/"),
    RESOURCE_ZIPCODE("xml/SOAP_server/ndfdXMLserver.php"),
    BODY_ZIP_PATH("src/test/resources/soapXml/filezipcode.xml"),
    SOAP_NUMEROS_DOLARES_BASE_URL("https://www.dataaccess.com/"),
    RESOURCE_NUMEROS_DOLARES("webservicesserver/numberconversion.wso"),
    BODY_NUMEROS_DOLARES_PATH("src/test/resources/soapXml/fileNumerosDolars.xml");

    private final String value;

    UrlResources(String value){
        this.value=value;
    }

    public String getValue(){
        return value;
    }
}
