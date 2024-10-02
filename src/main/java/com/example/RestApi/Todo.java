package com.example.RestApi;

public class Todo {
    private int id;
    private boolean completed;
    private String title;
    private int userId;

    public Todo(int id, boolean completed, String title, int userId){
        this.id=id;
        this.completed=completed;
        this.title=title;
        this.userId=userId;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", completed=" + completed +
                ", title='" + title + '\'' +
                ", userId=" + userId +
                '}';
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
