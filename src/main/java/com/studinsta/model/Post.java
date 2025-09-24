package com.studinsta.model;

import java.sql.Timestamp;

public class Post {
    private int id;
    private int userId;
    private String imageUrl;
    private Timestamp createdAt;

    public Post() {}

    public Post(int id, int userId, String imageUrl, Timestamp createdAt) {
        this.id = id;
        this.userId = userId;
        this.imageUrl = imageUrl;
        this.createdAt = createdAt;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }
}
