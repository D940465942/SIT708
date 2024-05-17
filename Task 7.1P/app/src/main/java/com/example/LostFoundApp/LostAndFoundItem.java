package com.example.LostFoundApp;

public class LostAndFoundItem {
    private final int id;
    private final String postType;
    private final String name;
    private final String phone;
    private final String description;
    private final String date;
    private final String location;

    public LostAndFoundItem(int id, String postType, String name, String phone, String description, String date, String location) {
        this.id = id;
        this.postType = postType;
        this.name = name;
        this.phone = phone;
        this.description = description;
        this.date = date;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public String getPostType() {
        return postType;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public String getLocation() {
        return location;
    }
}
