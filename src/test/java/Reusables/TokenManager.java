package Reusables;

import Utils.ConfigLoader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.time.Instant;
import java.util.HashMap;

import static Reusables.RestResource.postAccount;
import static Reusables.SpecBuilder.getResponseSpec;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.responseSpecification;

public class TokenManager {


    private static String access_token;
    private static Instant expiry_time;

    public synchronized static String getToken() {

        try {
            if (access_token == null || Instant.now().isAfter(expiry_time)){
                System.out.println("Renewing token...");
                Response response = renewToken();
                access_token = response.path("access_token");
                int expiryDurationInSeconds = response.path("expires_in");
                expiry_time = Instant.now().plusSeconds(expiryDurationInSeconds - 300);
            } else {
                System.out.println("Token is good to use");
            }

        }
        catch (Exception e){
            throw new RuntimeException("Failed to get token");
        }
        return access_token;
    }

    public static Response renewToken() {

        ConfigLoader configLoader = ConfigLoader.getInstance();

        HashMap<String, String> formParams = new HashMap<String,String>();

        formParams.put("client_id",configLoader.getCliendId());
        formParams.put("client_secret",configLoader.getClientSecret());
        formParams.put("refresh_token",configLoader.getRefreshToken());
        formParams.put("grant_type",configLoader.getGrantType());

        Response response = postAccount(formParams);

        if (response.statusCode() != 200) {
            throw new RuntimeException("Renew Token Failed...");
        }

    return response;

    }
}
