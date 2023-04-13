package com.sofkau.stepdefinitions;

import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import static com.sofkau.models.soap.Headers.headers;
import static com.sofkau.questions.soap.ResponseSoap.responseSoap;
import static com.sofkau.tasks.DoPostSoap.doPostSoap;
import static com.sofkau.utils.ManageFile.readFile;
import static com.sofkau.utils.UrlResources.*;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.IsNull.notNullValue;

public class CambioDivisasStepDefinitions extends ApiSetUp {
    String body;
    private static final Logger LOGGER = Logger.getLogger(CambioDivisasStepDefinitions.class);

    @Given("que tengo acceso al servicio web de CurrencySystem para la conversion de divisas")
    public void queTengoAccesoAlServicioWebDeCurrencySystemParaLaConversionDeDivisas() {
        try {
            setUp(SOAP_DIVISA_BASE_URL.getValue());
            LOGGER.info("INICIA LA AUTOMATIZACION");
            loadBody();
        } catch (Exception e) {
            LOGGER.info("Fallo la configuracion inicial");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }

    @When("envio {int} {string} a {string} al servicio")
    public void envioAAlServicio(Integer cantidad, String divisaOrigen, String divisaDestino) {
        try {
            body = String.format(body, divisaOrigen, divisaDestino, cantidad);
            actor.attemptsTo(
                    doPostSoap()
                            .andTheResource(RESOURCE_DIVISA.getValue())
                            .withTheHeaders(headers().getHeadersConvertirDivisas())
                            .andTheBody(body)
            );
            LOGGER.info("Realiza la peticion");
        } catch (Exception e) {
            LOGGER.info("Fallo al momento de realizar la peticion");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }

    @Then("deberia recibir el resultado de la conversion no nulo {}")
    public void deberiaRecibirElResultadoDeLaConversionNoNulo(String arg0) {
        try {
            String convertedValue = valorActualDelXml();
            actor.should(
                    seeThatResponse("El codigo de respuesta es: " + HttpStatus.SC_OK,
                            response -> response.statusCode(HttpStatus.SC_OK)),
                    seeThat("El resultado de la conversion no es nulo",
                            responseSoap(), notNullValue()) // Actualizar la aserción para verificar que el resultado no sea nulo
            );
            LOGGER.info("El valor actual es: " + convertedValue);
            LOGGER.info("CUMPLE");
        } catch (Exception e) {
            LOGGER.info("Error al realizar la comparacion");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }

    @NotNull
    private String valorActualDelXml() throws SAXException, IOException, ParserConfigurationException {
        String responseString = LastResponse.received().answeredBy(actor).asString();
        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(responseString)));
        return doc.getElementsByTagName("ConvertToNumResult").item(0).getTextContent().trim();
    }

    private void loadBody() {
        body = readFile(BODY_PATH1.getValue());
    }

}
