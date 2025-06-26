Feature: Greeter program interpolates names into a greeting.

  Scenario: Greeter interpolates a single compound name
    Given the input is "John Paul the Third"
    When the greet method is called
    Then the output should be "Hello, John Paul the Third."

  # Requirement 1: Basic name interpolation
  Scenario: Greeter interpolates a single name
    Given the input is "Alice"
    When the greet method is called
    Then the output should be "Hello, Alice."

  Scenario: Single name with leading and trailing whitespace
    Given the input is "  Alice  "
    When the greet method is called
    Then the output should be "Hello, Alice."

  Scenario: Single name with special characters
    Given the input is "@lice"
    When the greet method is called
    Then the output should be "Hello, @lice."

  Scenario: Single name with numbers
    Given the input is "A1ice"
    When the greet method is called
    Then the output should be "Hello, A1ice."

  Scenario: Single name with Estonian letters
    Given the input is "Ülo Määtälöö"
    When the greet method is called
    Then the output should be "Hello, Ülo Määtälöö."

  Scenario: Single name with a hyphen
    Given the input is "Andres-Envelop"
    When the greet method is called
    Then the output should be "Hello, Andres-Envelop."

  # Requirement 2: Handling null and empty input
  Scenario: Greeter handles null input
    Given the input is null
    When the greet method is called
    Then the output should be "Hello, my friend."

  Scenario: Greeter handles empty input
    Given the input is ""
    When the greet method is called
    Then the output should be "Hello, my friend."

  Scenario: Greeter handles whitespace-only input
    Given the input is "  "
    When the greet method is called
    Then the output should be "Hello, my friend."

  Scenario: Greeter handles tab characters as input
    Given the input is "\t"
    When the greet method is called
    Then the output should be "Hello, my friend."

  Scenario: Greeter handles newline characters as input
    Given the input is "\n"
    When the greet method is called
    Then the output should be "Hello, my friend."

  # Requirement 3: Handling shouting
  Scenario: Greeter handles shouting with uppercase name
    Given the input is "JERRY"
    When the greet method is called
    Then the output should be "HELLO, JERRY!"

  Scenario: Shouting with leading and trailing whitespace
    Given the input is "  BOB  "
    When the greet method is called
    Then the output should be "HELLO, BOB!"

  # Requirement 4: Handling two names
  Scenario: Greeter interpolates two names
    Given the input array is '["Jill", "Jane"]'
    When the greet method is called
    Then the output should be "Hello, Jill and Jane."

  # Requirement 5: Handling multiple names
  Scenario: Greeter interpolates three or more names with an Oxford comma
    Given the input array is '["Amy", "Brian", "Charlotte"]'
    When the greet method is called
    Then the output should be "Hello, Amy, Brian, and Charlotte."

  # Requirement 6: Mixed normal and shouted names
  Scenario: Greeter handles mixed normal and shouted names
    Given the input array is '["Amy", "BRIAN", "Charlotte"]'
    When the greet method is called
    Then the output should be "Hello, Amy and Charlotte. AND HELLO, BRIAN!"

  Scenario: Duplicate names in the input
    Given the input array is '["Anna", "Anna", "BOB"]'
    When the greet method is called
    Then the output should be "Hello, Anna and Anna. AND HELLO, BOB!"

  # Requirement 7: Handling names with commas
  Scenario: Greeter handles names with embedded commas
    Given the input array is '["Bob", "Charlie, Dianne"]'
    When the greet method is called
    Then the output should be "Hello, Bob, Charlie, and Dianne."

  # Requirement 8: Handling escaped commas
  Scenario: Greeter handles escaped commas in names
    Given the input array with escaped commas
    When the greet method is called
    Then the output should be "Hello, Bob and Charlie, Dianne."

  # Edge cases
  Scenario: Empty array input
    Given the input array is '[]'
    When the greet method is called
    Then the output should be "Hello, my friend."

  Scenario: Array containing only null values
    Given the input array is [null, null]
    When the greet method is called
    Then the output should be "Hello, my friend."

  Scenario: Array containing empty strings
    Given the input array is '["", ""]'
    When the greet method is called
    Then the output should be "Hello, my friend."

  Scenario: Array with mixed valid and invalid entries
    Given the input array is ["Anna", null, ""]
    When the greet method is called
    Then the output should be "Hello, Anna."

  Scenario: Array with leading and trailing whitespace in entries
    Given the input array is '["  Anna  ", "  Bob, Charlie  "]'
    When the greet method is called
    Then the output should be "Hello, Anna, Bob, and Charlie."

  Scenario: Array with only shouted names
    Given the input array is '["ANNA", "BOB", "CHARLIE"]'
    When the greet method is called
    Then the output should be "HELLO, ANNA, BOB, AND CHARLIE!"

  Scenario: Combination of normal, shouted, and escaped comma names
    Given the input array is '["Anna", "BOB", "\"Charlie, Dianne\"", "Eve"]'
    When the greet method is called
    Then the output should be "Hello, Anna, Charlie, Dianne, and Eve. AND HELLO, BOB!"
