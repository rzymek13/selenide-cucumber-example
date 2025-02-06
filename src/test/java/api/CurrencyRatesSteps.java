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
    private static final String EXCHANGE_RATES_URL = "http://api.nbp.pl/api/exchangerates/tables/A?format=json";
    private Response response;
    private List<CurrencyRate> rates;

    @Given("^Retrieve exchange rates$")
    public void retrieveExchangeRates() {
        log.info("Starting to retrieve exchange rates");
        response = RestAssured.get(EXCHANGE_RATES_URL);
        Assert.assertEquals("Unexpected response status", 200, response.getStatusCode());
        log.info("Response code {}", response.getStatusCode());

        rates = parseRatesFromResponse(response);
    }

    private List<CurrencyRate> parseRatesFromResponse(Response response) {
        return response.jsonPath().getList("[0].rates").stream()
                .map(this::mapToCurrencyRate)
                .collect(Collectors.toList());
    }

    private CurrencyRate mapToCurrencyRate(Object rate) {
        Map<String, Object> rateMap = (Map<String, Object>) rate;
        return new CurrencyRate(
                (String) rateMap.get("currency"),
                (String) rateMap.get("code"),
                ((Number) rateMap.get("mid")).doubleValue());
    }

    @Then("^Show all data$")
    public void showAllData() {
        log.info("All exchange rates:");
        rates.forEach(rate -> log.info(rate.toString()));
    }

    @Then("^show exchange rates for currency with code: (.+)$")
    public void showExchangeRatesForCurrencyWithCode(String currencyCode) {
        log.info("Retrieved exchange rates for currency with code {}:", currencyCode);
        rates.stream()
                .filter(rate -> currencyCode.equals(rate.getCode()))
                .forEach(rate -> log.info("Currency Code: {} Rate: {}", rate.getCode(), rate.getMidValue()));
    }

    @Then("^show exchange rates for currency with name: (.+)$")
    public void showExchangeRatesForCurrencyWithName(String currencyName) {
        log.info("Retrieved exchange rates for currency with name {}:", currencyName);
        rates.stream()
                .filter(rate -> currencyName.equals(rate.getCurrency()))
                .forEach(rate -> log.info("Currency Code: {} Rate: {}", rate.getCode(), rate.getMidValue()));
    }

    @Then("^Show currencies with rate (less|more|equals) than (\\d+\\.\\d+)$")
    public void showCurrenciesWithRate(String option, double threshold) {
        log.info("Retrieved exchange rates with rate {} than {}", option, threshold);
        rates.stream()
                .filter(rate -> filterRatesByOption(rate, option, threshold))
                .forEach(rate -> log.info(rate.toString()));
    }

    private boolean filterRatesByOption(CurrencyRate rate, String option, double threshold) {
        switch (option) {
            case "less":
                return rate.getMidValue() < threshold;
            case "more":
                return rate.getMidValue() > threshold;
            case "equals":
                return rate.getMidValue() == threshold;
            default:
                throw new IllegalArgumentException("Invalid option: " + option);
        }
    }
}
