package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Employee {
    private final StringProperty name;
    private final StringProperty email;
    private final StringProperty cevoIssued;
    private final StringProperty dotExp;
    private final StringProperty palsExp;
    private final StringProperty aclsExp;
    private final StringProperty emsExp;
    private final StringProperty driversExp;
    private final StringProperty blsExp;
    private final StringProperty mvrExp;
    private final StringProperty licensureLevel;
    private final StringProperty nearestExpiration;

    public Employee(String name, String email, String cevoIssued, String dotExp, String palsExp, String aclsExp,
            String emsExp, String driversExp, String blsExp, String mvrExp, String licensureLevel,
            String nearestExpiration) {
        this.name = new SimpleStringProperty(name);
        this.email = new SimpleStringProperty(email);
        this.cevoIssued = new SimpleStringProperty(cevoIssued);
        this.dotExp = new SimpleStringProperty(dotExp);
        this.palsExp = new SimpleStringProperty(palsExp);
        this.aclsExp = new SimpleStringProperty(aclsExp);
        this.emsExp = new SimpleStringProperty(emsExp);
        this.driversExp = new SimpleStringProperty(driversExp);
        this.blsExp = new SimpleStringProperty(blsExp);
        this.mvrExp = new SimpleStringProperty(mvrExp);
        this.licensureLevel = new SimpleStringProperty(licensureLevel);
        this.nearestExpiration = new SimpleStringProperty(nearestExpiration);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public StringProperty emailProperty() {
        return email;
    }

    public StringProperty cevoIssuedProperty() {
        return cevoIssued;
    }

    public StringProperty dotExpProperty() {
        return dotExp;
    }

    public StringProperty palsExpProperty() {
        return palsExp;
    }

    public StringProperty aclsExpProperty() {
        return aclsExp;
    }

    public StringProperty emsExpProperty() {
        return emsExp;
    }

    public StringProperty driversExpProperty() {
        return driversExp;
    }

    public StringProperty blsExpProperty() {
        return blsExp;
    }

    public StringProperty mvrExpProperty() {
        return mvrExp;
    }

    public StringProperty licensureLevelProperty() {
        return licensureLevel;
    }

    public StringProperty nearestExpirationProperty() {
        return nearestExpiration;
    }

    public String getNearestExpiration() {
        return nearestExpiration.get();
    }

    public void setNearestExpiration(String nearestExpiration) {
        this.nearestExpiration.set(nearestExpiration);
    }
}