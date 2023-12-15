package enums;

import java.util.Arrays;

public enum StudioGroupType {

    FUNC_STUDIO("פונקציונלי", new String[]{"פונקציונלי", "פונקציונאלי", "tabata", "תחנות", "hit", "hiit"}),
    PILATES_STUDIO("פילאטיס", new String[]{"פילאטיס"}),
    SHAPE_STUDIO("עיצוב", new String[]{"בריאותית", "זומבה", "עיצוב"});

    private final String title;
    private final String[] myArray;

    StudioGroupType(String groupTitle, String[] strings) {
        title = groupTitle;
        myArray = strings;
    }

    public static String getGroupTitle(String value) {
        for (StudioGroupType group : StudioGroupType.values()) {
            if (Arrays.stream(group.myArray).anyMatch(str -> value.toLowerCase().contains(str.toLowerCase()))) {
                return group.title;
            }
        }

        return value;
    }
}
