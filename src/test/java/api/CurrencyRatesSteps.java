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
        log.info("All exchange rates:\n");
        rates.forEach(rate -> log.info(rate.toString() + "\n"));
    }
    @Then("^show exchange rates for currency with code: (.+)$")
    public void showExchangeRatesForCurrencyWithCode(String currencyCode) {
        log.info("\n" + "Retrieved exchange rates for currency with code " + currencyCode + ":");
        rates.stream()
                .filter(rate -> currencyCode.equals(rate.getCode()))
                .forEach(rate -> log.info( "Currency Code: " +rate.getCode() + " Rate: " + rate.getMidValue()));
    }

    @Then("^show exchange rates for currency with name: (.+)$")
    public void showExchangeRatesForCurrencyWithName(String currencyName) {
        log.info("\n" + "Retrieved exchange rates for currency with name " + currencyName + ":");
        rates.stream()
                .filter(rate -> currencyName.equals(rate.getCurrency()))
                .forEach(rate -> log.info( "Currency Code: " +rate.getCode() + " Rate: " + rate.getMidValue()));
    }

    @Then("^Show currencies with rate (less|more|equals) than (\\d+\\.\\d+)$")
    public void showCurrenciesWithRate(String option, double threshold) {
        switch(option){
            case "less":
                log.info("\n" + "Retrieved exchange rates with rate less than " + threshold);
                rates.stream()
                        .filter(rate -> rate.getMidValue() < threshold)
                        .forEach(rate -> log.info(rate.toString()));
                break;
            case "more":
                log.info("\n" + "Retrieved exchange rates with rate more than " + threshold);
                rates.stream()
                        .filter(rate -> rate.getMidValue() > threshold)
                        .forEach(rate -> log.info(rate.toString()));
                break;
            case "equals":
                log.info("\n" + "Retrieved exchange rates with rate equal to " + threshold);
                rates.stream()
                        .filter(rate -> rate.getMidValue() == threshold)
                        .forEach(rate -> log.info(rate.toString()));
                break;
            default:
                throw new IllegalArgumentException("Invalid option: " + option);
        }
    }

}
