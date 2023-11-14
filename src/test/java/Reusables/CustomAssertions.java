package Reusables;

import Pojos.Error;
import io.qameta.allure.Step;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CustomAssertions {

    @Step
    public static void assertStatusCode(int actualStatusCode, int expectedStatusCode) {
        assertThat(actualStatusCode, equalTo(expectedStatusCode));
    }

    @Step
    public static void assertError (Error responseError, int statusCode, String errorMessage) {

        assertThat(responseError.getError().getMessage(), equalTo(errorMessage));
        assertThat(responseError.getError().getStatus(), equalTo(statusCode));

    }
}
