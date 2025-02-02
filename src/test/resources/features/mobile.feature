Feature: Buying a smartwatch at T-Mobile
  As a T-Mobile user
  I want to add a smartphone to my cart
  So I can buy it later

  Scenario: Adding a smartwatch without a subscription to your cart
    Given Open browser
    When Open the t-mobile website
    Then homePage: element logoImage is displayed
    When homePage: click on devicesDropdownMenuButton
    Then homePage: element smartwatchesItem is displayed
    When homePage: click on smartwatchesItem
    Then smartwatchesPage: element firstProduct is displayed
    When smartwatchesPage: click on firstProduct
    Then productPage: element productLabel is displayed
    And productPage: capture price from productPriceOnProductPage
    And productPage: capture price from feeOnProductPage
    When productPage: click on addToCartButton
    Then cartPage: element cartTitle is displayed
    And cartPage: capture price from productPriceOnCart
    And cartPage: capture price from feeOnCart
    And Verify that productPrice equals cartPrice



#  And pause for 5000 seconds







#    And pause for 100000 seconds
#    When Kliknij "Bez abonamentu" z kolumny "Smartwatche i opaski"
#    Then Widoczna lista smartfonów
#    When Kliknij w pierwszy element z listy
#    Then Widoczna strona produktu
#    When Dodaj produkt do koszyka
#    Then Widoczna strona "Twój koszyk" z kwotami "Cena na start" oraz "Rata miesięczna" zgadzającymi się z kwotami z poprzedniej strony
#    When Przejdź na stronę główną T-Mobile
#    Then Widoczna strona główna z ikoną koszyka w prawym górnym rogu z liczbą produktów w koszyku