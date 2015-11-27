package com.yana.model;

/**
 * Created by Yana on 27.11.2015.
 */
public class Post {
    private int postId;
    private String name;

    public Post() {}

    public Post(int postId, String name) {
        this.postId = postId;
        this.name = name;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
