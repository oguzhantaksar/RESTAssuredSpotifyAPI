package Utils;

import com.github.javafaker.Faker;

public class FakerUtils {

    public static String generatePlaylistName() {
        Faker faker = new Faker();
        return "Playlist" + faker.regexify("A-Za-z0-9");
    }

    public static String generatePlaylistDescription() {
        Faker faker = new Faker();
        return "Description" + faker.regexify("A-Za-z0-9");
    }
}
