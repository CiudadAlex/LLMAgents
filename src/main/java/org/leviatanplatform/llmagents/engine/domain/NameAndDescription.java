package org.leviatanplatform.llmagents.engine.domain;

public class NameAndDescription {

    private String name;
    private String description;

    public NameAndDescription() {
    }

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

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "name: " + name + "\ndescription: " + description;
    }
}
