package com.codelab.basics;

public class DataModel {

    private int id;
    private String name;
    private int powerLevel;
    private String description;
    private int accessCount;

    public DataModel(int id, String name, int powerLevel, String description, int accessCount) {
        this.id = id;
        this.name = name;
        this.powerLevel = powerLevel;
        this.description = description;
        this.accessCount = accessCount;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPowerLevel() {
        return powerLevel;
    }

    public String getDescription() {
        return description;
    }

    public int getAccessCount() {
        return accessCount;
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", powerLevel=" + powerLevel +
                ", description='" + description + '\'' +
                ", accessCount=" + accessCount +
                '}';
    }
}