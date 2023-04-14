package com.sofkau.questions.rest;


import com.sofkau.models.rest.ResponseRegistro;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class ReturnNewPhotoResponse implements Question<ResponseRegistro> {
    @Override
    public ResponseRegistro answeredBy(Actor actor) {
        return SerenityRest.lastResponse().as(ResponseRegistro.class);
    }

    public static ReturnNewPhotoResponse returnNewPhotoResponse(){
        return new ReturnNewPhotoResponse();
    }
}
