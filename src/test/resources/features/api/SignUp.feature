Feature: User Signup API
  As a new user
  I want to sign up for the Gym Application
  So that I can create an account and track my workouts

  Scenario: Successful signup with valid data
    Given the API endpoint for user signup
    When I send a POST request with the following data:
      | fullName            | Yathish G           |
      | password            | $Password123        |
      | email               | yathish22@gmail.com |
      | target              | gain weight         |
      | preferableActivity  | yoga                |
    Then I should receive a 200 status code
    And the response should contain Successfull message with username
      | fullName | Yathish G |

  Scenario: Signup with short password
    Given the API endpoint for user signup
    When I send a POST request with the following data:
      | fullName            | Yathish G           |
      | password            | $Passw1             |
      | email               | yathish22@gmail.com |
      | target              | gain weight         |
      | preferableActivity  | yoga                |
    Then I should receive a 400 status code
    And the response should contain an error message "Password does not meet criteria"

  Scenario: Signup with password missing a symbol
    Given the API endpoint for user signup
    When I send a POST request with the following data:
      | fullName            | Yathish G           |
      | password            | Password123         |
      | email               | yathish22@gmail.com |
      | target              | gain weight         |
      | preferableActivity  | yoga                |
    Then I should receive a 400 status code
    And the response should contain an error message "Password does not meet criteria"

  Scenario: Signup with password missing a numeric character
    Given the API endpoint for user signup
    When I send a POST request with the following data:
      | fullName            | Yathish G           |
      | password            | $Password           |
      | email               | yathish22@gmail.com |
      | target              | gain weight         |
      | preferableActivity  | yoga                |
    Then I should receive a 400 status code
    And the response should contain an error message "Password does not meet criteria"

  Scenario: Signup with missing '@' in email
    Given the API endpoint for user signup
    When I send a POST request with the following data:
      | fullName            | Yathish G           |
      | password            | $Password123        |
      | email               | yathish22gmail.com  |
      | target              | gain weight         |
      | preferableActivity  | yoga                |
    Then I should receive a 400 status code
    And the response should contain an error message "Invalid email format"

  Scenario: Signup with missing domain in email
    Given the API endpoint for user signup
    When I send a POST request with the following data:
      | fullName            | Yathish G           |
      | password            | $Password123        |
      | email               | yathish22@.com      |
      | target              | gain weight         |
      | preferableActivity  | yoga                |
    Then I should receive a 400 status code
    And the response should contain an error message "Invalid email format"

  Scenario: Signup with missing TLD in email
    Given the API endpoint for user signup
    When I send a POST request with the following data:
      | fullName            | Yathish G           |
      | password            | $Password123        |
      | email               | yathish22@gmail     |
      | target              | gain weight         |
      | preferableActivity  | yoga                |
    Then I should receive a 400 status code
    And the response should contain an error message "Invalid email format"

  Scenario: Signup with missing fullName
    Given the API endpoint for user signup
    When I send a POST request with the following data:
      | fullName            |                     |
      | password            | $Password123        |
      | email               | yathish22@gmail.com |
      | target              | gain weight         |
      | preferableActivity  | yoga                |
    Then I should receive a 400 status code
    And the response should contain an error message "Full name is required"

  Scenario: Signup with missing target
    Given the API endpoint for user signup
    When I send a POST request with the following data:
      | fullName            | Yathish G           |
      | password            | $Password123        |
      | email               | yathish22@gmail.com |
      | target              |                     |
      | preferableActivity  | yoga                |
    Then I should receive a 400 status code
    And the response should contain an error message "Target is required"

  Scenario: Signup with missing preferableActivity
    Given the API endpoint for user signup
    When I send a POST request with the following data:
      | fullName            | Yathish G           |
      | password            | $Password123        |
      | email               | yathish22@gmail.com |
      | target              | gain weight         |
      | preferableActivity  |                     |
    Then I should receive a 201 status code
    And the response should contain Successfull message with username
      | fullName | Yathish G |