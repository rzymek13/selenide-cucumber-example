package api;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;


import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Slf4j
public class CurrencyRatesSteps {
    private Response response;
    private List<CurrencyRate> rates;

    @Given("^Retrieve exchange rates$")
    public void retrieveExchangeRates() {
        log.info("Starting to retrieve exchange rates");
        response = RestAssured.get("http://api.nbp.pl/api/exchangerates/tables/A?format=json");
        Assert.assertEquals(200, response.getStatusCode());
        log.info("Response code " + response.getStatusCode());

        rates = response.jsonPath().getList("[0].rates").stream()
                .map(rate -> {
                    Map<String, Object> rateMap = (Map<String, Object>) rate;
                    return new CurrencyRate(
                            (String) rateMap.get("currency"),
                            (String) rateMap.get("code"),
                            ((Number) rateMap.get("mid")).doubleValue());
                })
                .collect(Collectors.toList());
    }
    @Then("^Show all data$")
    public void showAllData() {
        rates.forEach(rate -> log.info(rate.toString() + "\n"));
    }
    @Then("^show exchange rates for currency with code: (.+)$")
    public void showExchangeRatesForCurrencyWithCode(String currencyCode) {
        rates.stream()
                .filter(rate -> currencyCode.equals(rate.getCode()))
                .forEach(rate -> log.info( "Currency Code: " +rate.getCode() + " Rate: " + rate.getMid()));
    }

    @Then("^show exchange rates for currency with name: (.+)$")
    public void showExchangeRatesForCurrencyWithName(String currencyName) {

        rates.stream()
                .filter(rate -> currencyName.equals(rate.getCurrency()))
                .forEach(rate -> log.info( "Currency Code: " +rate.getCode() + " Rate: " + rate.getMid()));
    }

    @Then("^Show currencies with rate (less|more|equals) than (\\d+\\.\\d+)$")
    public void showCurrenciesWithRate(String option, double threshold) {
        switch(option){
            case "less":
                rates.stream()
                        .filter(rate -> rate.getMid() < threshold)
                        .forEach(rate -> log.info(rate.toString()));
                break;
            case "more":
                rates.stream()
                        .filter(rate -> rate.getMid() > threshold)
                        .forEach(rate -> log.info(rate.toString()));
                break;
            case "equals":
                rates.stream()
                        .filter(rate -> rate.getMid() == threshold)
                        .forEach(rate -> log.info(rate.toString()));
                break;
            default:
                throw new IllegalArgumentException("Invalid option: " + option);
        }
    }

}
