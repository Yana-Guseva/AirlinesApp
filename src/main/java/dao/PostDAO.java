package dao;

import com.yana.model.Post;
import controller.ConnectionPool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yana on 01.12.2015.
 */
public class PostDAO extends AbstractDAO<Post> {
    public PostDAO(ConnectionPool pool) {
        super(pool);
    }

    @Override
    public void add(Post post) {
        String sql = "INSERT INTO post VALUES (" + post.getPostId() + ", '" + post.getName() + "')";
        executeModifyQuery(sql);
    }

    @Override
    public void edit(Post post) {
        String sql = "UPDATE post SET name=" + post.getName() + "WHER id=" + post.getPostId();
        executeModifyQuery(sql);
    }

    @Override
    public void delete(Post post) {
        String sql = "DELETE * FROM post WHERE id=" + post.getPostId();
        executeModifyQuery(sql);
    }

    @Override
    public Post getItem(int id) {
        Post post = new Post();
        String sql = "SELECT * FROM post WHERE id=" + post.getPostId();
        Connection conn = pool.getConnection();
        try {
            ResultSet rs = conn.createStatement().executeQuery(sql);
            if (rs.next()) {
                post.setPostId(rs.getInt("id"));
                post.setName(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pool.freeConnection(conn);
        }
        return post;
    }

    @Override
    public ArrayList<Post> getAll() {
        ArrayList<Post> posts = new ArrayList<>();
        String sql = "SELECT * FROM post";
        Connection conn = pool.getConnection();
        try {
            ResultSet rs = conn.createStatement().executeQuery(sql);
            while (rs.next()) {
                Post post = new Post();
                post.setPostId(rs.getInt("id"));
                post.setName(rs.getString("name"));
                posts.add(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pool.freeConnection(conn);
        }
        return posts;
    }
}
