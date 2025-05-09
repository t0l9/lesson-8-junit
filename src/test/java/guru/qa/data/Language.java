package guru.qa.data;

public enum Language {
    RU("РУС"),
    EN("ENG"),
    QAZ("QAZ");

    public final String description;

    Language(String description) {
        this.description = description;
    }
}
