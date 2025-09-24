package com.studinsta.dao;

import com.studinsta.model.Comment;
import com.studinsta.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class CommentDAO {

    public boolean saveComment(Comment comment) {
        try (Connection con = DBConnection.getConnection()) {
            String query = "INSERT INTO comments (post_id, user_id, content, created_at) VALUES (?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, comment.getPostId());
            ps.setInt(2, comment.getUserId());
            ps.setString(3, comment.getContent());
            ps.setTimestamp(4, comment.getCreatedAt());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
