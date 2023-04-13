package com.sofkau.setup;

import io.restassured.RestAssured;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import org.apache.log4j.PropertyConfigurator;

import static com.sofkau.utils.Log4jValues.LOG4J_PROPERTY_PATH;

public class ApiSetUp {

    protected Actor actor = new Actor("team2");

    private static final String BASE_URI = "https://jsonplaceholder.typicode.com";

    protected void setUp(String urlBase){
        setUpLog4j();
        actorCallAnApi(urlBase);
    }
    private void actorCallAnApi(String urlBase){

        actor.can(CallAnApi.at(urlBase));
    }

    private void setUpLog4j(){

        PropertyConfigurator.configure(LOG4J_PROPERTY_PATH.getValue());
    }
    private void setUpBases(){

        RestAssured.baseURI = BASE_URI;
    }
}
