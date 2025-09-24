package com.studinsta.model;

import java.sql.Timestamp;

public class Post {
    private int id;
    private int userId;
    private String imageUrl;
    private String caption;
    private Timestamp createdAt;

    public Post() {}

    // Constructor used in PostServlet
    public Post(int userId, String imageUrl, String caption) {
        this.userId = userId;
        this.imageUrl = imageUrl;
        this.caption = caption;
        this.createdAt = new Timestamp(System.currentTimeMillis());
    }

    public Post(int id, int userId, String imageUrl, String caption, Timestamp createdAt) {
        this.id = id;
        this.userId = userId;
        this.imageUrl = imageUrl;
        this.caption = caption;
        this.createdAt = createdAt;
    }

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public String getCaption() { return caption; }
    public void setCaption(String caption) { this.caption = caption; }

    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }
}
