package data;

public enum Language {
    Рус("Рус"),
    Eng("Eng");

    private final String displayName;

    Language(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}