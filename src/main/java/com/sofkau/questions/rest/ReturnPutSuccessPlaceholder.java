package com.sofkau.questions.rest;

import com.sofkau.models.rest.ResponseUpdateUser;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class ReturnPutSuccessPlaceholder implements Question<ResponseUpdateUser> {
    @Override
    public ResponseUpdateUser answeredBy(Actor actor) {
        return SerenityRest.lastResponse().as(ResponseUpdateUser.class);
    }

    public static ReturnPutSuccessPlaceholder returnUpdateJsonResponse(){
        return new ReturnPutSuccessPlaceholder();
    }
}
