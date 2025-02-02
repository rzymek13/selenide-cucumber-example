Feature: Currency Rates

  Scenario: Kursy walut
    Given Retrieve exchange rates
  Then Show all data
  Then show exchange rates for currency with code: USD
    Then show exchange rates for currency with name: bat (Tajlandia)
    Then Show currencies with rate more than 2.0
