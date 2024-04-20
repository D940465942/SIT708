package com.example.taskmanager;

public class Task {
    private long id;
    private String title;
    private String description;
    private String due_date;

    public Task(long id, String title, String description, String due_date) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.due_date = due_date;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDueDate() {
        return due_date;
    }

    public void setDueDate(String due_date) {
        this.due_date = due_date;
    }
}