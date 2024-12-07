package org.SAPLA.RandomGeneration;

import org.SAPLA.utils.Constants;

import java.util.*;

public class RandomGeneration {
    public static List<String> generateRandomStrings() {
        // Utilisation d'un Set pour s'assurer que tous les messages soient différents
        Set<String> messages = new HashSet<>();
        Random random = new Random();
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        while (messages.size() < Constants.NB_MESSAGES_PER_INDIVIDUAL_AT_START * Constants.NB_INDIVIDUALS * Constants.NB_FACTIONS) {
            StringBuilder sb = new StringBuilder();

            for (int j = 0; j < Constants.MESSAGE_LENGTH; j++) {
                int randomIndex = random.nextInt(characters.length());
                sb.append(characters.charAt(randomIndex));
            }
            messages.add(sb.toString());
        }

        return new ArrayList<>(messages);
    }
}
