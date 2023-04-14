package com.sofkau.questions.rest;

import com.sofkau.models.rest.User;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class ReturnUpdateJsonResponseReqres implements Question<User> {
    @Override
    public User answeredBy(Actor actor) {
        return SerenityRest.lastResponse().as(User.class);
    }

    public static ReturnUpdateJsonResponseReqres returnUpdateJsonResponseReqres(){
        return new ReturnUpdateJsonResponseReqres();
}
}