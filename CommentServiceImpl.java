package com.lpu.Momento;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;


@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final PostService postService;
    private final UserService userService;
    private final UserRepository userRepository;

    public CommentServiceImpl(
        CommentRepository commentRepository,
        PostRepository postRepository,
        PostService postService,
        UserService userService,
        UserRepository userRepository
    ) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.postService = postService;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @Override
    
    public Comment createComment(Comment comment, Long userId, Long postId) throws UserException, PostException {
        User user = userService.findUserById(userId);
        Post post = postService.findPostById(postId);
         comment.setUser(user);
        comment.setCreatedAt(LocalDateTime.now());
        Comment createdComment = commentRepository.save(comment);
        
        post.getComments().add(createdComment);
        postRepository.save(post);

        return createdComment;
    }

    @Override
    public Comment findCommentById(Long commentId) throws CommentException {
        return commentRepository.findById(commentId)
            .orElseThrow(() -> new CommentException("Comment not found with id: " + commentId));
    }

    @Override
    public Comment likeComment(Long commentId, Long userId) throws UserException, CommentException {
        
        User user = userService.findUserById(userId);
        Comment comment = findCommentById(commentId);
       comment.getLikedByUsers().add(user);
        return commentRepository.save(comment);
    }

    @Override
    
    public Comment unLikeComment(Long commentId, long userId) throws UserException, CommentException {
        Comment comment = findCommentById(commentId);
        User user = userService.findUserById(userId);
         comment.getLikedByUsers().remove(user);
        return commentRepository.save(comment);
    }

//	@Override
//	public String deleteComment(Long commentId, Long userId) throws UserException, CommentException {
//		Comment comment = commentRepository.findById(commentId)
//		        .orElseThrow(() -> new CommentException("Comment not found"));
//
//		    if (!comment.getUser().getId().equals(userId)) {
//		        throw new CommentException("Not authorized to delete this comment");
//		    }
//
//		    commentRepository.deleteById(commentId);
//		    return "Comment deleted successfully";
//	}
}
