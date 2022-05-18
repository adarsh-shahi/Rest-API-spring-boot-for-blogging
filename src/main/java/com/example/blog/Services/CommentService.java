package com.example.blog.Services;

import com.example.blog.Payloads.CommentDto;

public interface CommentService {

    CommentDto createComment(Integer postID, CommentDto commentDto);
    void deleteComment(Integer commentId);

}
