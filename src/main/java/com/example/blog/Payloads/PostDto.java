package com.example.blog.Payloads;

import com.example.blog.Entities.Category;
import com.example.blog.Entities.Comment;
import com.example.blog.Entities.User;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class PostDto {

    private int postId;
    private String content;
    private String title;
    private String imageName;
    private Date postDate;
    private UserDto  user;
    private CategoryDto category;

    private Set<CommentDto> commentSet = new HashSet<>();

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public CategoryDto getCategory() {
        return category;
    }

    public void setCategory(CategoryDto category) {
        this.category = category;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public Set<CommentDto> getCommentSet() {
        return commentSet;
    }

    public void setCommentSet(Set<CommentDto> commentSet) {
        this.commentSet = commentSet;
    }
}
