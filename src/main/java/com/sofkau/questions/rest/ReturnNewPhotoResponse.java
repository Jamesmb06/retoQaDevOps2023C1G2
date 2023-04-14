package com.sofkau.questions.rest;


import com.sofkau.models.rest.ResponseNewPhoto;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class ReturnNewPhotoResponse implements Question<ResponseNewPhoto> {
    @Override
    public ResponseNewPhoto answeredBy(Actor actor) {
        return SerenityRest.lastResponse().as(ResponseNewPhoto.class);
    }

    public static ReturnNewPhotoResponse returnNewPhotoResponse(){
        return new ReturnNewPhotoResponse();
    }
}
