package com.sofkau.questions.rest;

import com.sofkau.models.rest.ResponseUpdateUserReqres;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class ReturnUpdateJsonResponseReqres implements Question<ResponseUpdateUserReqres> {
    @Override
    public ResponseUpdateUserReqres answeredBy(Actor actor) {
        return SerenityRest.lastResponse().as(ResponseUpdateUserReqres.class);
    }

    public static ReturnUpdateJsonResponseReqres returnUpdateJsonResponseReqres(){
        return new ReturnUpdateJsonResponseReqres();
    }
}
