package com.sofkau.utils;

public enum UrlResources {

    SOAP_CAMEL_CASE_BASE_URL("https://www.dataaccess.com/webservicesserver/"),
    RESOURCE_CAMEL_CASE("TextCasing.wso"),
    BODY_CAMEL_CASE_PATH("src/test/resources/soapXml/filecamelcase.xml"),
    JSONPLACE_BASE_URL("https://jsonplaceholder.typicode.com/"),
    POSTS_BY_ID_RESOURCE("posts/"),
    COMMENTS_BY_POST("comments?postId="),
    RICK_AND_MORTY_BASE_URL("https://rickandmortyapi.com/api"),
    RESOURCE_RICKANDMORTY("/character/"),
    SOAP_CAPITAL_BASE_URL("http://webservices.oorsprong.org/"),
    RESOURCE_CAPITAL("websamples.countryinfo/CountryInfoService.wso"),
    SOAP_CONTRIES("http://eaf.ema.europa.eu/"),
    RESOURCES_CONTRIES("eaf/services/EutctService?wsdl"),
    BODY_CONTRIES_PATH("src/test/resources/soapXml/filecontrieslist.xml"),
    BODY_OBJECT_PATH("src/test/resources/soapXml/fileobjectlist.xml"),
    BODY_SPECIES_PATH("src/test/resources/soapXml/filespecies.xml"),
    BODY_OBJECTPHARMACY_PATH("src/test/resources/soapXml/fileobjectspharmacy.xml"),
    RESOURCES_PUT_JSONPLACEHOLDER("comments/"),
    DUMMYJSON_BASE_URL_GET("https://dummyjson.com/"),
    RESOURCES_GET_DUMMYJSON("comments"),
    SOAP_ZIPCODE_BASE_URL("https://graphical.weather.gov:443/"),
    RESOURCE_ZIPCODE("xml/SOAP_server/ndfdXMLserver.php"),
    BODY_ZIP_PATH("src/test/resources/soapXml/filezipcode.xml"),
    SOAP_NUMEROS_DOLARES_BASE_URL("https://www.dataaccess.com/"),
    RESOURCE_NUMEROS_DOLARES("webservicesserver/numberconversion.wso"),
    BODY_NUMEROS_DOLARES_PATH("src/test/resources/soapXml/fileNumerosDolars.xml"),
    SOAP_CALCULADORA_BASE_URL("http://www.dneonline.com/"),
    BODY_RESTA_PATH("src/test/resources/soapXml/fileresta.xml"),
    RESOURCE_CALCULADORA("calculator.asmx"),
    BASE_POKE_URL("https://pokeapi.co/"),
    GENERATION1_RESOURCE("api/v2/generation/"),
    PHOTO_POST_RESOURCE("/photos"),
    JSONPLACEHOLDER_BASE_URL("https://jsonplaceholder.typicode.com"),
    BODY_CURRENCY_PATH("src/test/resources/soapXml/filecurrency.xml"),
    RESOURCES_POST("api/register/"),
    RESOURCE_NUMBER_CONVERSION("/webservicesserver/numberconversion.wso"),
    SOAP_NUMBER_CONVERSION_BASE_URL("https://www.dataaccess.com"),
    BODY_PATH_NUMBER_CONVERSION("src/test/resources/soapXml/filenumberconversionletters.xml"),
    BASE_JSON_URL("https://jsonplaceholder.typicode.com/"),
    POST_JSON_URL("posts/"),
    PUT_JSON_URL("photos/"),
    REQRES_BASE_URL("https://reqres.in/"),
    POKEMON("api/v2/pokemon/"),
    PUT_POST_RESOURCE("posts/"),
    SOAP_DIVISA_BASE_URL("http://fx.currencysystem.com/"),
    RESOURCE_DIVISA("webservices/CurrencyServer4.asmx"),
    BODY_PATH1("src/test/resources/soapXml/filedivisas.xml"),
    RESOURCES_PUT("api/users/"),
    RESOURCE_BAYAS("api/v2/berry/"),
    RESOURCE_JUEGO("api/v2/version/"),
    RESOURCE_CALCULADORA_MULTIPLICAR("calculator.asmx"),
    BODY_MULTIPLICAR("src/test/resources/soapXml/filemultiplicar.xml"),
    BODY_PATH2("src/test/resources/soapXml/filecalculadora.xml");

    private final String value;

    UrlResources(String value){
        this.value=value;
    }

    public String getValue(){
        return value;
    }
}
