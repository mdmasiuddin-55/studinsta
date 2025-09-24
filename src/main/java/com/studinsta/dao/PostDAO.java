package com.studinsta.dao;

import com.studinsta.model.Post;
import com.studinsta.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class PostDAO {

    public boolean savePost(Post post) {
        try (Connection con = DBConnection.getConnection()) {
            String query = "INSERT INTO posts (user_id, image_url, caption, created_at) VALUES (?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, post.getUserId());
            ps.setString(2, post.getImageUrl());
            ps.setString(3, post.getCaption());
            ps.setTimestamp(4, post.getCreatedAt());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
