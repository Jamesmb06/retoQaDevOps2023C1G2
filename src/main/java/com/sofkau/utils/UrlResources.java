package com.sofkau.utils;

public enum UrlResources {

    REQRES_BASE_URL_PUT("https://reqres.in/api/"),
    RESOURCES_PUT("users/"),
    SOAP_CAPITAL_BASE_URL("http://webservices.oorsprong.org/"),
    RESOURCE_CAPITAL("websamples.countryinfo/CountryInfoService.wso"),
    BODY_PATH("src/test/resources/soapXml/filecapital.xml"),
    ANIME_JINKAN_BASE_URL("https://api.jikan.moe/v4"),
    ANIME_JINKAN_RESOURCE("/anime/"),
    PLACE_HOLDER_BASE_URL("https://jsonplaceholder.typicode.com/"),
    PLACE_DELETE_POST("posts/"),
    BODY_PATH_ISO_NUMERO_CELULAR  ("src/test/resources/soapXml/filecodigoISOnumerocelular.xml"),
    BODY_PATH_ISO_BANDERA("src/test/resources/soapXml/filecodigoISObandera.xml");

    private final String value;

    UrlResources(String value){
        this.value=value;
    }

    public String getValue(){
        return value;
    }
}
