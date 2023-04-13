package com.sofkau.utils;

public enum UrlResources {

    SOAP_CAPITAL_BASE_URL("http://webservices.oorsprong.org/"),
    RESOURCE_CAPITAL("websamples.countryinfo/CountryInfoService.wso"),
    BODY_PATH("src/test/resources/soapXml/filecapital.xml"),
    SOAP_CONTRIES("http://eaf.ema.europa.eu/"),
    RESOURCES_CONTRIES("eaf/services/EutctService?wsdl"),
    BODY_CONTRIES_PATH("src/test/resources/soapXml/filecontrieslist.xml"),
    BODY_OBJECT_PATH("src/test/resources/soapXml/fileobjectlist.xml"),
    BODY_SPECIES_PATH("src/test/resources/soapXml/filespecies.xml"),
    BODY_OBJECTPHARMACY_PATH("src/test/resources/soapXml/fileobjectspharmacy.xml");

    private final String value;

    UrlResources(String value){
        this.value=value;
    }

    public String getValue(){
        return value;
    }
}
