package com.lpu.Momento;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;
    private final UserService userService;

    public PostController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<Post> createPost(@RequestBody Post post, @RequestHeader("Authorization") String token) throws UserException {
        User user = userService.findUserByToken(token);
        Post createdPost = postService.createPost(post, user.getId());
        return new ResponseEntity<>(createdPost, HttpStatus.OK);
    }

    @GetMapping("/all/{userId}")
    public ResponseEntity<List<Post>> findPostByUserId(@PathVariable Long userId) throws UserException {
        List<Post> posts = postService.findPostByUserId(userId);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }
    
    @GetMapping("/following/{userIds}")
    public ResponseEntity<List<Post>> findAllPostByUserIds(@PathVariable List<Long> userIds) throws UserException, PostException {
        List<Post> posts = postService.findAllPostByUserIds(userIds);
        return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
    }
    
    @GetMapping("/{postId}")
    public ResponseEntity<Post> findPostById(@PathVariable Long postId) throws PostException {
        Post post = postService.findPostById(postId);
        return new ResponseEntity<Post>(post, HttpStatus.OK);
    }
    
    @PutMapping("/like{postId}")
    public ResponseEntity<Post> likePost(@PathVariable Long postId, @RequestHeader("Authorization") String token) throws PostException, UserException{
    User user = userService.findUserByToken(token);
    Post post = postService.LikePost(postId, user.getId());
    return new ResponseEntity<Post>(post, HttpStatus.OK);
    }
    
    @PutMapping("/unlike{postId}")
    public ResponseEntity<Post> unlikePost(@PathVariable Long postId, @RequestHeader("Authorization") String token) throws PostException, UserException{
    User user = userService.findUserByToken(token);
    Post post = postService.unLikePost(postId, user.getId());
    return new ResponseEntity<Post>(post, HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{postId}")
    public ResponseEntity<MessageResponse> deletePost(@PathVariable Long postId, @RequestHeader("Authorization") String token) throws PostException, UserException{
        User user = userService.findUserByToken(token);
        String message = postService.deletePost(postId, user.getId());
        MessageResponse res = new MessageResponse(message);
        return new ResponseEntity<MessageResponse>(res, HttpStatus.ACCEPTED);
        }
    
    @PutMapping("/save{postId}")
    public ResponseEntity<MessageResponse> savePost(@PathVariable Long postId, @RequestHeader("Authorization") String token) throws PostException, UserException{ 
    	User user = userService.findUserByToken(token);
        String message = postService.savedPost(postId, user.getId());
        MessageResponse res = new MessageResponse(message);
        return new ResponseEntity<MessageResponse>(res, HttpStatus.ACCEPTED);
    }
    
    @PutMapping("/unsave{postId}")
    public ResponseEntity<MessageResponse> unSavePost(@PathVariable Long postId, @RequestHeader("Authorization") String token) throws PostException, UserException{ 
    	User user = userService.findUserByToken(token);
        String message = postService.unSavedPost(postId, user.getId());
        MessageResponse res = new MessageResponse(message);
        return new ResponseEntity<MessageResponse>(res, HttpStatus.ACCEPTED);
    }


    
    

}
