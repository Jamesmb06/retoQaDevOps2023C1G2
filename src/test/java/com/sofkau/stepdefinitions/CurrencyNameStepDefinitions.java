package com.sofkau.stepdefinitions;

import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import java.nio.charset.StandardCharsets;
import static com.sofkau.models.soap.Headers.headers;
import static com.sofkau.questions.soap.ResponseSoap.responseSoap;
import static com.sofkau.tasks.DoPostSoap.doPostSoap;
import static com.sofkau.utils.ManageFile.readFile;
import static com.sofkau.utils.UrlResources.*;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.containsString;

public class CurrencyNameStepDefinitions extends ApiSetUp {

    String body;
    private static final Logger LOGGER = Logger.getLogger(CurrencyNameStepDefinitions.class);
    @Given("Dado que tengo acceso al servicio SOAP de Listar las monedas de cada pais")
    public void dadoQueTengoAccesoAlServicioSOAPDeListarLasMonedasDeCadaPais() {
        try {
            LOGGER.info("INICIA LA AUTOMATIZACION DE CURRENCY");
            setUp(SOAP_CAPITAL_BASE_URL.getValue());
        } catch (Exception e) {
            LOGGER.warn(e.getMessage());
            LOGGER.info(" fallo la configuracion inicial de currency test");
            Assertions.fail();
        }
    }

    @Given("que he ingresado el codigo ISO de un pais valido {string}")
    public void queHeIngresadoElCodigoISODeUnPaisValido(String code) {
        try {
            loadBodyCurrency(code);
            LOGGER.info("SE CARGA EL BODY DE CURRENCY");
        } catch (Exception e) {
            Assertions.fail();
            LOGGER.warn(e.getMessage());
            LOGGER.info(" fallo la carga del body de currency");
        }
    }

    @When("realizo la solicitud de las monedas correspondientes")
    public void realizoLaSolicitudDeLasMonedasCorrespondientes() {
        try {
            actor.attemptsTo(
                    doPostSoap()
                            .andTheResource(RESOURCE_CAPITAL.getValue())
                            .withTheHeaders(headers().getHeadersCollection())
                            .andTheBody(body)
            );
            LOGGER.info("Realiza la peticion a la API SOAP");
        } catch (Exception e) {
            Assertions.fail();
            LOGGER.warn(e.getMessage());
            LOGGER.info(" fallo al momento de realizar la peticion de get currency");
        }
    }

    @Then("obtengo un codigo de estado exitoso")
    public void obtengoUnCodigoDeEstadoExitoso() {
        try {
            LOGGER.info(new String(LastResponse.received().answeredBy(actor).asByteArray(), StandardCharsets.UTF_8));
            actor.should(
                    seeThatResponse("el codigo de respuesta de la API de currency es: " + HttpStatus.SC_OK,
                            response -> response.statusCode(HttpStatus.SC_OK))
            );
            LOGGER.info("CUMPLE CON LO ESPERADO DE CURRENCY");
        } catch (Exception e) {
            LOGGER.warn(e.getMessage());
            LOGGER.info("Error al realizar la comparacion de las monedas");
            Assertions.fail();
        }
    }

    @Then("El nombre de la moneda correspondiente {string}")
    public void elNombreDeLaMonedaCorrespondiente(String word) {
        try {
            LOGGER.info(new String(LastResponse.received().answeredBy(actor).asByteArray(), StandardCharsets.UTF_8));
            actor.should(
                    seeThat(" El nombre de la moneda es",
                            responseSoap(), containsString(word))
            );
            LOGGER.info("CUMPLE CON EL NOMBRE ESPERADO");
        } catch (Exception e) {
            LOGGER.warn(e.getMessage());
            LOGGER.info("Error al realizar la comparacion del nombre");
            Assertions.fail();
        }
    }

    private void loadBodyCurrency(String code) {
        body = readFile(BODY_CURRENCY_PATH.getValue());
        body = String.format(body, code);
    }
}
