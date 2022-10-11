import clients.RestClient;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import static java.nio.file.Files.list;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AvatarTest {

    private final RestClient restClient = new RestClient();
    private final String avatarBasePath = System.getProperty("user.dir") + File.separator + "Avatars" + File.separator;

    @ParameterizedTest
    @Order(1)
    @ValueSource(strings = {"Avatar1.png", "Avatar2.jpg","Avatar3.jpg"})
    public void ClientCanUploadMultipleAvatars(String avatarFileName) {
        String avatarFinalPath = avatarBasePath + avatarFileName;
        Assertions.assertTrue(restClient.postForAvatar(avatarFinalPath));
    }

    @Test
    @Order(2)
    public void clientCanUploadDirectoryOfAvatars() {
        try {
            List<Path> avatarFilesList;
            avatarFilesList = list(Paths.get(avatarBasePath)).collect(Collectors.toList());
            for (Path avatarFinalPath: avatarFilesList) {
                Assertions.assertTrue(restClient.postForAvatar(avatarFinalPath.toString()));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
