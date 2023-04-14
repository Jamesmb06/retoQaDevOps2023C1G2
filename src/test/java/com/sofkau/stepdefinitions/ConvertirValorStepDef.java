package com.sofkau.stepdefinitions;

import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.sofkau.models.soap.Headers.headers;
import static com.sofkau.questions.soap.ResponseSoap.responseSoap;
import static com.sofkau.tasks.DoPostSoap.doPostSoap;
import static com.sofkau.utils.Constantes.*;
import static com.sofkau.utils.ManageFile.readFile;
import static com.sofkau.utils.UrlResources.*;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.Matchers.*;

public class ConvertirValorStepDef extends ApiSetUp {
    private final Logger log = LoggerFactory.getLogger(ConvertirValorStepDef.class);

    @Given("Tengo acceso al servidor de la API de Xignite")
    public void tengoAccesoAlServidorDeLaAPIDeXignite() {
        try {
            log.info("Iniciando escenario");
            setUp(XIGNITE_BASE_URL.getValue());
        } catch (Exception e) {
            log.error("Setup erroneo");
            log.error(e.getMessage());
            log.error(String.valueOf(e.getCause()));
            Assertions.fail();
        }
    }

    @When("Trato de convertir de la divisa {string}, a la divisa {string} la cantidad de {string}")
    public void tratoDeConvertirDeLaDivisaALaDivisaLaCantidadDe(String from, String to, String amount) {
        try {
            log.info("Corriendo seleccion");
            actor.attemptsTo(
                    doPostSoap()
                            .withTheHeaders(headers()
                                    .getHeaders(String.format("%s;%s;%s",
                                            TYPE.getValue(),
                                            CODE.getValue(),
                                            ACTION_CONVERT_VALUE.getValue())))
                            .andTheResource(GLOBAL_CURRENCIES_RESOURCE.getValue())
                            .andTheBody(String.format(readFile(BODY_CONVERT_VALUE.getValue()),
                                    TOKEN.getValue(),
                                    from,
                                    to,
                                    amount))
            );
        } catch (Exception e) {
            log.error("ERROR");
            log.error(e.getMessage());
            log.error(String.valueOf(e.getCause()));
            Assertions.fail();
        }
    }

    @Then("Se vera un codigo de respuesta {int}")
    public void seVeraUnCodigoDeRespuesta(Integer code) {
        try {
            actor.should(
                    seeThatResponse("Codigo de estado",
                            response -> response.statusCode(code))
            );
            log.info("Primera asercion pasada");
        } catch (Exception e) {
            log.error("Test fallido");
            log.error(e.getMessage());
            log.error(String.valueOf(e.getCause()));
            Assertions.fail();
        }
    }

    @And("Vere el resultado {string}")
    public void vereElResultado(String result) {
        try {
            actor.should(
                    seeThat("Convertir resultado",
                            responseSoap(), notNullValue())
            );
            log.info("Segunda asercion pasada");
        } catch (Exception e) {
            log.error("Test fallido");
            log.error(e.getMessage());
            log.error(String.valueOf(e.getCause()));
            Assertions.fail();
        } finally {
            log.info("Test completado");
        }
    }
}