package com.example.entity;

public class Stuff {
    private int id;
    private String name;
    private String description;
    private int quantity;
    private int userId;

    public Stuff() {
    }

    public Stuff(int id) {
        this.id = id;
    }

    public Stuff(String name, String description, int quantity) {
        this.name = name;
        this.description = description;
        this.quantity = quantity;
    }

    public Stuff(int id, String name, String description, int quantity) {
        this(name, description, quantity);
        this.id = id;
    }


    public int getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Stuff{id = " + id +
                " name= " + name +
                " description= " + description +
                " quantity= " + quantity + " }";
    }
}
