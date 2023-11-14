package Tests;

import Pojos.Error;
import Pojos.Playlist;
import Reusables.StatusCodeMsg;
import Reusables.WebApi.PlaylistApi;
import Utils.ConfigLoader;
import Utils.DataLoader;
import io.qameta.allure.*;
import io.restassured.response.Response;
import jdk.jfr.Description;
import org.testng.annotations.Test;

import static Reusables.CustomAssertions.assertError;
import static Reusables.CustomAssertions.assertStatusCode;
import static Utils.FakerUtils.generatePlaylistDescription;
import static Utils.FakerUtils.generatePlaylistName;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Epic("Spotify OAuth 2.0")
@Feature("Playlist API")
public class PlaylistTests
{
    ConfigLoader configLoader = ConfigLoader.getInstance();
    DataLoader dataLoader = DataLoader.getInstance();

    @Story("As a user i can create a playlist")
    @Link("https://example.org")
    @Link(name = "allure", type = "mylink")
    @TmsLink("12345")
    @Issue("1234567")
    @Description("This is the descpription for the case")
    @Test(description = "Should be able to create a playlist")
    public void shouldBeAbleToCreateAPlaylist(){

        Playlist requestPlaylist =playlistBuilder(generatePlaylistName(),generatePlaylistDescription(),false);
        Response response = PlaylistApi.post(requestPlaylist,configLoader.getUserId());
        assertStatusCode(response.getStatusCode(),201);
        Playlist responsePlaylist = response.as(Playlist.class);
        assertPlaylistEqual(responsePlaylist,requestPlaylist);

    }

    @Test
    public void shouldBeAbleGetAPlaylist(){


        Playlist requestPlaylist =playlistBuilder("New Playlist","New Description",false);
        Response response = PlaylistApi.get(dataLoader.getPlaylistIdGet());
        assertStatusCode(response.getStatusCode(), StatusCodeMsg.CODE_200.getCode());
        Playlist responsePlaylist = response.as(Playlist.class);
        assertPlaylistEqual(responsePlaylist,requestPlaylist);

    }

    @Test
    public void shouldBeAbleToUpdateAPlaylist(){

        Playlist requestPlaylist =playlistBuilder("New Playlist","New Description",false);
        Response response = PlaylistApi.update(requestPlaylist, dataLoader.getPlaylistIdUpdate());
        assertStatusCode(response.getStatusCode(),StatusCodeMsg.CODE_200.getCode());

    }
    @Story("As a user i can create a playlist")
    @Test
    public void shouldNotBeAbleToCreateAPlaylistWithoutName(){

        Playlist requestPlaylist =playlistBuilder("",generatePlaylistDescription(),false);
        Response response = PlaylistApi.post(requestPlaylist,configLoader.getUserId());
        assertStatusCode(response.statusCode(), 400);
        assertError(response.as(Error.class),StatusCodeMsg.CODE_400.getCode(),StatusCodeMsg.CODE_400.getMsg());

    }
    @Story("As a user i can create a playlist")
    @Test
    public void shouldNotBeAbleToCreateAPlaylistWithoutValidToken(){

        String invalidToken = "12345";
        Playlist requestPlaylist =playlistBuilder(generatePlaylistName(),generatePlaylistDescription(),false);
        Response response = PlaylistApi.post(requestPlaylist, configLoader.getUserId(),invalidToken);
        assertStatusCode(response.statusCode(), 401);
        assertError(response.as(Error.class),StatusCodeMsg.CODE_401.getCode(),StatusCodeMsg.CODE_401.getMsg());

    }

    @Step
    public Playlist playlistBuilder(String name, String description, Boolean _public) {

        return Playlist.builder()
                .name(name)
                .description(description)
                ._public(_public)
                .build();
    }

    @Step
    public void assertPlaylistEqual(Playlist responsePlaylist, Playlist requestPlaylist) {
        assertThat(responsePlaylist.getName(), equalTo(requestPlaylist.getName()));
        assertThat(responsePlaylist.get_public(), equalTo(requestPlaylist.get_public()));
    }

}