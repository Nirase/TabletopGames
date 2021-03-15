package games.terraformingmars.rules;

import games.terraformingmars.TMGameState;
import games.terraformingmars.TMTypes;

public interface Requirement {
    /*
     2 cases implemented:
        - counter: global parameter / player resource / player production min or max;
        - minimum N tags on cards played by player
     */
    boolean testCondition(TMGameState gs);

    static Requirement stringToRequirement(String s) {
        String[] split = s.split(":");
        // First is counter
        if (split[0].contains("tag")) {
            // Format: tag-tag1-tag2:min1-min2
            split[0] = split[0].replace("tag-", "");
            String[] tagDef = split[0].split("-");
            String[] minDef = split[1].split("-");
            TMTypes.Tag[] tags = new TMTypes.Tag[tagDef.length];
            int[] minValues = new int[tagDef.length];
            for (int i = 0; i < tagDef.length; i++) {
                tags[i] = TMTypes.Tag.valueOf(tagDef[i]);
                minValues[i] = Integer.parseInt(minDef[i]);
            }
            return new TagRequirement(tags, minValues);
        } else {
            return new CounterRequirement(split[0], Integer.parseInt(split[1]), split[2].equalsIgnoreCase("max"));
        }
    }
}
