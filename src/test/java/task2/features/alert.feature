Feature: Alert

  In order to upload alerts
  As a user of marketalertum
  I want to be able to view a list of alerts

  Scenario: Alert layout
    Given I am an administrator of the website and I upload 3 alerts
    Given I am a user of marketalertum
    When I vew a list of alerts
    Then each alert should contain an icon
    And each alert should contain a heading
    And each alert should contain a description
    And each alert should contain an image
    And each alert should contain a price
    And each alert should contain a link to the original product website

  Scenario:  Alert limit
    Given I am an administrator of the website and I upload more than 5 alerts
    Given I am a user of marketalertum
    When I view a list of alerts I should see 5 alerts











