package com.sofkau.questions.rest;

import com.sofkau.models.rest.Response;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class ReturnPutSuccessPlaceholder implements Question<Response> {
    @Override
    public Response answeredBy(Actor actor) {

        return SerenityRest.lastResponse().as(Response.class);
    }

    public static ReturnPutSuccessPlaceholder returnPutSuccessPlaceholder(){
        return new ReturnPutSuccessPlaceholder();
    }
}
