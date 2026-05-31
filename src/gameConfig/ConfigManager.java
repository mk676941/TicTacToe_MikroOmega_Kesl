package gameConfig;
import gameBoard.GamePresets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * config management class
 * used for saving and loading config from a JSON file
 * contains methods for saving and loading data to or from a JSON file
 * @author Matej Kesl
 */
public class ConfigManager {
    private static final String filePath = "config.json";

    /**
     * method used for saving game config into an existing JSON file
     * @param config - game config to save
     */
    public static void save(GameConfig config) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(config);

        try {
            Files.write(Paths.get(filePath),
                    json.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * mathod used for loading game config for a JSON file
     * method used for creating a default config JSON file
     * @return gameConfig.GameConfig - game config from a JSON file
     */
    public static GameConfig load() {
        Gson gson = new Gson();

        //creating a default config
        if (!Files.exists(Path.of(filePath))) {
            GameConfig defualt = new GameConfig(GamePresets.DEFAULT, "Player 1", "Player 2", "#000000", "#000000");
            save(defualt);
            return defualt;
        }

        //loading config data
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath))) {
            return gson.fromJson(reader, GameConfig.class);
        } catch (Exception e) {
            throw  new RuntimeException(e);
        }
    }

}