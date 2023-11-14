package Reusables.WebApi;

import Pojos.Playlist;
import Reusables.RestResource;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static Reusables.Route.PLAYLISTS;
import static Reusables.Route.USERS;
import static Reusables.SpecBuilder.getRequestSpec;
import static Reusables.SpecBuilder.getResponseSpec;
import static Reusables.TokenManager.getToken;
import static Reusables.TokenManager.renewToken;
import static io.restassured.RestAssured.given;

public class PlaylistApi {

    @Step
    public static Response post(Playlist requestPlaylist, String user) {

        return RestResource.post(USERS+"/"+user+PLAYLISTS,getToken(), requestPlaylist);

    }

    public static Response post(Playlist requestPlaylist, String user, String invalidToken) {

        return RestResource.post(USERS+"/"+user+PLAYLISTS, invalidToken, requestPlaylist);

    }


    public static Response get(String playlistId) {

        return RestResource.get(PLAYLISTS+"/"+playlistId, getToken());

    }

    public static Response update(Playlist requestPlaylist, String playlistId) {

        return RestResource.update(PLAYLISTS+"/"+playlistId,getToken(), requestPlaylist);


    }

}
