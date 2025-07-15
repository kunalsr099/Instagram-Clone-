package com.lpu.Momento;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
	private final PostService postService;
    private final UserService userService;
    private final CommentService commentService;
	public CommentController(PostService postService, UserService userService, CommentService commentService) {
		this.postService = postService;
		this.userService = userService;
		this.commentService = commentService;
	}
	@PostMapping("/create/{postId}")
    public ResponseEntity<Comment> createComment(@RequestBody Comment comment,@PathVariable Long postId, @RequestHeader("Authorization") String token) throws UserException , PostException{
        User user = userService.findUserByToken(token);
        Comment createdCommnet = commentService.createComment(comment, user.getId(), postId);
        return new ResponseEntity<Comment>(createdCommnet, HttpStatus.OK);
    }
	@GetMapping("/{commentId}")
    public ResponseEntity<Comment> findCommentById(@PathVariable Long CommentId) throws CommentException {
        Comment comment = commentService.findCommentById(CommentId);
        return new ResponseEntity<Comment>(comment, HttpStatus.OK);
    }
	
	@PutMapping("/like{commentId}")
    public ResponseEntity<Comment> likeComment(@PathVariable Long commentId, @RequestHeader("Authorization") String token) throws  UserException, CommentException{
    User user = userService.findUserByToken(token);
    Comment comment = commentService.likeComment(commentId, user.getId());
    return new ResponseEntity<Comment>(comment, HttpStatus.OK);
    }
	
	@PutMapping("/unlike{commentId}")
    public ResponseEntity<Comment> unlikeComment(@PathVariable Long commentId, @RequestHeader("Authorization") String token) throws UserException, CommentException{
    User user = userService.findUserByToken(token);
    Comment comment = commentService.unLikeComment(commentId, user.getId());
    return new ResponseEntity<Comment>(comment, HttpStatus.OK);
    }
    
    

}
