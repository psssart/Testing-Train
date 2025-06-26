package tdd_kata;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.assertj.core.api.Assertions;

import java.util.Arrays;

public class GreeterStepDefinitions {

    private Greeter greeter;
    private String input;
    private String[] inputArr;
    private String output;

    @Before
    public void setTheGreeter() {
        greeter = new Greeter();
    }

    @Given("the input is {string}")
    public void theInputIs(String input) {
        this.input = input.equals("null") ? null : input.trim();
    }

    @Given("the input array is {string}")
    public void theInputArrayIs(String inputArray) {
        if (inputArray.equals("[]")) {
            this.inputArr = new String[0];
        } else {
            // Make an array
            this.inputArr = Arrays.stream(inputArray.replaceAll("[\\[\\]\"]", "").split(","))
                    .map(String::trim)
                    .toArray(String[]::new);
        }
    }

    @Given("the input array with escaped commas")
    public void theInputArrayWithEscapedCommas() {
        this.inputArr = new String[]{"Bob", "\"Charlie, Dianne\""};
    }

    @Given("the input is null")
    public void theInputIsNull() {
        this.input = null;
    }

    @Given("the input array is [null, null]")
    public void theInputArrayIsNullNull() {
        this.inputArr = new String[]{null, null};
    }

    @Given("the input array is [{string}, null, {string}]")
    public void theInputArrayIsNull(String string, String string2) {
        this.inputArr = new String[]{string, null, string2};
    }

    @When("the greet method is called")
    public void theGreetMethodIsCalled() {
        if (inputArr != null) {
            output = greeter.greet(inputArr);
        } else {
            output = greeter.greet(input);
        }
    }

    @Then("the output should be {string}")
    public void theOutputShouldBe(String expectedOutput) {
        Assertions.assertThat(output).isEqualTo(expectedOutput);
    }
}
