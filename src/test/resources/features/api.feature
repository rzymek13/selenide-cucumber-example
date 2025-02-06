@nbp
Feature: Currency Rates

  Scenario: Kursy walut
    Given Retrieve exchange rates
    Then Show all data
    Then show exchange rates for currency with code: USD
    Then show exchange rates for currency with name: bat (Tajlandia)
    Then Show currencies with rate more than 5.0
    Then Show currencies with rate less than 3.0
