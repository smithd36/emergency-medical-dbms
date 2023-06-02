package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DetailEmployee {
    private final StringProperty name;
    private final StringProperty licensureLevel;
    private final StringProperty nearestExpiration;

    public DetailEmployee(String name, String licensureLevel, String nearestExpiration) {
        this.name = new SimpleStringProperty(name);
        this.licensureLevel = new SimpleStringProperty(licensureLevel);
        this.nearestExpiration = new SimpleStringProperty(nearestExpiration);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public StringProperty licensureLevelProperty() {
        return licensureLevel;
    }

    public StringProperty nearestExpirationProperty() {
        return nearestExpiration;
    }
}
