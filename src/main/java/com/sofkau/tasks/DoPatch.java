package com.sofkau.tasks;

import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Patch;
import net.serenitybdd.screenplay.rest.interactions.Post;

public class DoPatch implements Task {
    private String resource;
    private Object requestBody;


    public DoPatch withTheResource(String resource) {
        this.resource = resource;
        return this;
    }

    public DoPatch andTheRequestBody(Object requestBody) {
        this.requestBody = requestBody;
        return this;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Patch.to(resource)

                        .with(
                                requestSpecification -> requestSpecification.relaxedHTTPSValidation()
                                        .contentType(ContentType.JSON)
                                        .body(requestBody)
                        )
        );

    }

    public static DoPatch doPatch() { return new DoPatch();     }

}
