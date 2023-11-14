package Reusables;

import Pojos.Playlist;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;

import static Reusables.Route.API;
import static Reusables.Route.TOKEN;
import static Reusables.SpecBuilder.*;
import static io.restassured.RestAssured.given;

public class RestResource {

    public static  Response postAccount(HashMap<String,String> formParams) {
        return given(getAccountRequestSpec())
        .formParams(formParams)
        .when().post(API+TOKEN)
        .then().spec(getResponseSpec())
        .extract().response();
    }

    public static Response post(String path,String token,Object requestBody) {

        return given().spec(getRequestSpec()).
                body(requestBody).
                auth().oauth2(token).
                when().post(path).
                then().
                spec(getResponseSpec()).
                extract().
                response();
    }

    public static Response get(String path, String token) {

        return given().spec(getRequestSpec()).
                auth().oauth2(token).
                when().get(path).
                then().
                spec(getResponseSpec()).
                extract().
                response();
    }

    public static Response update(String path,String token,Object requestBody) {

        return given().spec(getRequestSpec()).
                body(requestBody).
                auth().oauth2(token).
                when().put(path).
                then().
                spec(getResponseSpec()).
                extract().
                response();

    }

}
