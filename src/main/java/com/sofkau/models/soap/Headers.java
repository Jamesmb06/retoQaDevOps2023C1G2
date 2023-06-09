package com.sofkau.models.soap;

import java.util.HashMap;
import java.util.Map;

public class Headers {
    private final Map<String, Object> headersCollection = new HashMap<>();

    public Map<String, Object> getHeadersCalculadoraSuma(){

        this.headersCollection.put("Content-Type", "text/xml;charset=UTF-8");
        this.headersCollection.put("SOAPAction", "http://tempuri.org/Add");
        return headersCollection;
    }

    public Map<String, Object> getHeadersConvertirDivisas(){

        this.headersCollection.put("Content-Type", "text/xml;charset=UTF-8");
        this.headersCollection.put("SOAPAction", "http://webservices.cloanto.com/currencyserver/ConvertToNum");
        return headersCollection;
    }
    public Map<String, Object> getHeadersZipCode(){
        this.headersCollection.put("Content-Type", "text/xml;charset=UTF-8");
        this.headersCollection.put("SOAPAction", "https://graphical.weather.gov/xml/DWMLgen/wsdl/ndfdXML.wsdl#LatLonListZipCode");
        return headersCollection;
    }
    public Map<String, Object> getHeadersNumerosDolares(){
        this.headersCollection.put("Content-Type", "text/xml;charset=UTF-8");
        this.headersCollection.put("SOAPAction", "");
        return headersCollection;
    }

    public Map<String, Object> getHeaders(String headers) {
        this.headersCollection.put("Content-Type", headers);
        return headersCollection;
    }

    public static Headers headers(){
        return new Headers();
    }

    public Map<String, Object> getHeadersCollection(){

        this.headersCollection.put("Content-Type", "text/xml;charset=UTF-8");
        this.headersCollection.put("SOAPAction", "");
        return headersCollection;
    }
    public Map<String, Object> getHeadersCollectionCalculadoraSubstract() {
        this.headersCollection.put("Content-Type", "text/xml;charset=UTF-8");
        this.headersCollection.put("SOAPAction", "http://tempuri.org/Subtract");
        return headersCollection;
    }

    public Map<String, Object> getHeadersCollectionCalculadora() {
        this.headersCollection.put("Content-Type", "text/xml;charset=UTF-8");
        this.headersCollection.put("SOAPAction", "http://tempuri.org/Multiply");
        return headersCollection;
    }
}

