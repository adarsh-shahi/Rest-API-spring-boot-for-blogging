package com.example.blog.Services.Implementation;

import com.example.blog.Entities.Comment;
import com.example.blog.Entities.Post;
import com.example.blog.Exceptions.ResourceNotFoundException;
import com.example.blog.Payloads.CommentDto;
import com.example.blog.Repositories.CommentRepository;
import com.example.blog.Repositories.PostRepository;
import com.example.blog.Services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto createComment(Integer postID, CommentDto commentDto) {
        Post post = postRepository.findById(postID).orElseThrow(() -> new ResourceNotFoundException("post", "id", postID));

        Comment comment = modelMapper.map(commentDto, Comment.class);
        comment.setPost(post);
        Comment savedComment = commentRepository.save(comment);

        return modelMapper.map(savedComment, CommentDto.class);

    }

    @Override
    public void deleteComment(Integer commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("comment", "id", commentId));
        commentRepository.delete(comment);

    }
}
