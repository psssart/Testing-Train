package tdd_kata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Greeter {

    public String greet(String name) {
        if (isNullOrEmpty(name)) {
            return "Hello, my friend.";
        }

        name = name.trim();
        if (isShouting(name)) {
            return formatShoutedGreeting(name);
        }

        return formatNormalGreeting(name);
    }

    public String greet(String[] names) {
        if (names == null || names.length == 0 || allNamesAreInvalid(names)) {
            return "Hello, my friend.";
        }

        List<String> normalNames = new ArrayList<>();
        List<String> shoutedNames = new ArrayList<>();

        splitNames(names, normalNames, shoutedNames);

        String normalGreeting = generateNormalGreeting(normalNames);
        String shoutedGreeting = generateShoutedGreeting(shoutedNames);

        if (!normalGreeting.isEmpty() && !shoutedGreeting.isEmpty()) {
            return normalGreeting + " AND " + shoutedGreeting;
        }
        return normalGreeting + shoutedGreeting;
    }

    private boolean isNullOrEmpty(String name) {
        return name == null || name.trim().isEmpty() || name.equals("\\n") || name.equals("\\t");
    }

    private boolean allNamesAreInvalid(String[] names) {
        for (String name : names) {
            if (!isNullOrEmpty(name)) {
                return false;
            }
        }
        return true;
    }

    private boolean isShouting(String name) {
        return name.equals(name.toUpperCase()) && !name.equals(name.toLowerCase());
    }

    private String formatNormalGreeting(String name) {
        return String.format("Hello, %s.", name);
    }

    private String formatShoutedGreeting(String name) {
        return String.format("HELLO, %s!", name);
    }

    private void splitNames(String[] names, List<String> normalNames, List<String> shoutedNames) {
        for (String name : names) {
            if (isNullOrEmpty(name)) {
                continue;
            }

            name = name.trim();
            if (isEscapedName(name)) {
                normalNames.add(unescapeName(name));
            } else if (containsUnescapedComma(name)) {
                normalNames.addAll(Arrays.asList(splitByComma(name)));
            } else if (isShouting(name)) {
                shoutedNames.add(name);
            } else {
                normalNames.add(name);
            }
        }
    }

    private boolean isEscapedName(String name) {
        return name.startsWith("\"") && name.endsWith("\"");
    }

    private String unescapeName(String name) {
        return name.substring(1, name.length() - 1);
    }

    private boolean containsUnescapedComma(String name) {
        return !isEscapedName(name) && name.contains(",");
    }

    private String[] splitByComma(String name) {
        return name.split("\\s*,\\s*");
    }

    private String generateNormalGreeting(List<String> normalNames) {
        if (normalNames.isEmpty()) {
            return "";
        }

        if (normalNames.size() == 1) {
            return formatNormalGreeting(normalNames.get(0));
        } else if (normalNames.size() == 2) {
            return String.format("Hello, %s and %s.", normalNames.get(0), normalNames.get(1));
        } else {
            StringBuilder result = new StringBuilder("Hello, ");
            for (int i = 0; i < normalNames.size(); i++) {
                if (i == normalNames.size() - 1) {
                    result.append("and ").append(normalNames.get(i)).append(".");
                } else {
                    result.append(normalNames.get(i)).append(", ");
                }
            }
            return result.toString();
        }
    }

    private String generateShoutedGreeting(List<String> shoutedNames) {
        if (shoutedNames.isEmpty()) {
            return "";
        }

        if (shoutedNames.size() == 1) {
            return formatShoutedGreeting(shoutedNames.get(0));
        } else {
            StringBuilder result = new StringBuilder("HELLO, ");
            for (int i = 0; i < shoutedNames.size(); i++) {
                if (i == shoutedNames.size() - 1) {
                    result.append("AND ").append(shoutedNames.get(i)).append("!");
                } else {
                    result.append(shoutedNames.get(i)).append(", ");
                }
            }
            return result.toString();
        }
    }
}
