@mobile
Feature: Buying a smartwatch at T-Mobile
  As a T-Mobile user
  I want to add a smartphone to my cart
  So i can buy it later

  Scenario: Adding a smartwatch without a subscription to your cart
    Given Open browser
    When Open the homePage
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
    When Back to homePage
    Then Amount of products in cart should be equal to one

