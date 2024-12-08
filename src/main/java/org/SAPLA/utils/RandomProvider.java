package org.SAPLA.utils;

import org.json.JSONArray;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.*;


public class RandomProvider extends Random {
    private static RandomProvider _instance = null;
    private static String _seed;
    private static final String SEED_FILE = "seed.json";

    private RandomProvider() {
        super();
    }

    public static RandomProvider getInstance() {
        if (_instance == null) {
            // Generate a base-36 seed
            _seed = Long.toString(Instant.now().toEpochMilli(), 36);
            _instance = new RandomProvider();
            _instance.setSeed(Long.parseLong(_seed, 36));

            // Save the seed to a JSON file
            try {
                appendSeedToJsonFile(_seed);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return _instance;
    }
    public static List<String> generateRandomStrings() {
        // Utilisation d'un Set pour s'assurer que tous les messages soient différents
        Set<String> messages = new HashSet<>();
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        while (messages.size() < Constants.NB_MESSAGES_PER_INDIVIDUAL_AT_START * Constants.NB_INDIVIDUALS * Constants.NB_FACTIONS) {
            StringBuilder sb = new StringBuilder();

            for (int j = 0; j < Constants.MESSAGE_LENGTH; j++) {
                int randomIndex = RandomProvider.getInstance().nextInt(characters.length());
                sb.append(characters.charAt(randomIndex));
            }
            messages.add(sb.toString());
        }
        return new ArrayList<>(messages);
    }

    private static void appendSeedToJsonFile(String seed) throws IOException {
        JSONArray seedArray;

        try {
            // Read existing seeds from the JSON file if it exists
            seedArray = new JSONArray(new String(Files.readAllBytes(Paths.get("seed.json"))));
        } catch (IOException e) {
            // If the file doesn't exist or is invalid, create a new array
            seedArray = new JSONArray();
        }

        // Add the new seed to the array
        seedArray.put(seed);

        // Write the updated array back to the file
        try (FileWriter writer = new FileWriter(SEED_FILE)) {
            writer.write(seedArray.toString(4)); // Pretty print with indentation
        }
    }
}
