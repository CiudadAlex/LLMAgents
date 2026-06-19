package org.leviatanplatform.llmagents.engine.domain;

public class NameAndDescription {

    private String name;
    private String description;

    public NameAndDescription(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
