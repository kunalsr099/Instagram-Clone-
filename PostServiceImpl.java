package com.lpu.Momento;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserService userService;
    private final UserRepository userRepository;

    public PostServiceImpl(PostRepository postRepository, UserService userService, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public Post createPost(Post post, Long userId) throws UserException {
        User user = userService.findUserById(userId);
        post.setUser(user);
        return postRepository.save(post);
    }

    @Override
    @Transactional
    public String deletePost(Long postId, Long userId) throws UserException, PostException {
        Post post = findPostById(postId);
        User user = userService.findUserById(userId);
        if (post.getUser().getId().equals(user.getId())) {
            postRepository.deleteById(postId);
            return "Post Deleted Successfully";
        }
        throw new PostException("You cannot delete other user's post");
    }

    @Override
    public List<Post> findPostByUserId(Long userId) throws UserException {
        List<Post> posts = postRepository.findByUserId(userId);
        if (posts.isEmpty()) {
            throw new UserException("This user does not have any post");
        }
        return posts;
    }

    @Override
    public Post findPostById(Long postId) throws PostException {
        return postRepository.findById(postId)
                .orElseThrow(() -> new PostException("Post not found with id: " + postId));
    }

    @Override
    public List<Post> findAllPostByUserIds(List<Long> userIds) throws UserException, PostException {
        List<Post> posts = postRepository.findAllPostByUserIds(userIds);
        if (posts.isEmpty()) {
            throw new PostException("No posts available");
        }
        return posts;
    }

    @Override
    @Transactional
    public String savedPost(Long postId, Long userId) throws UserException, PostException {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostException("Post not found with id: " + postId));
        User user = userService.findUserById(userId);
        if (user.getSavedPost().contains(post)) {
            throw new PostException("Post is already saved");
        }
        user.getSavedPost().add(post);
        userRepository.save(user);
        return "Post is Successfully saved";
    }

    @Override
    @Transactional
    public String unSavedPost(Long postId, Long userId) throws UserException, PostException {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostException("Post not found with id: " + postId));
        User user = userService.findUserById(userId);
        if (!user.getSavedPost().contains(post)) {
            throw new PostException("Post is not saved");
        }
        user.getSavedPost().remove(post);
        userRepository.save(user);
        return "Post is Successfully removed from saved posts";
    }

    @Override
    @Transactional
    public Post LikePost(Long postId, Long userId) throws UserException, PostException {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostException("Post not found with id: " + postId));
        User user = userService.findUserById(userId);
        post.getLikeByUsers().add(user);
        return postRepository.save(post);
    }

    @Override
    @Transactional
    public Post unLikePost(Long postId, Long userId) throws UserException, PostException {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostException("Post not found with id: " + postId));
        User user = userService.findUserById(userId);
        post.getLikeByUsers().remove(user);
        return postRepository.save(post);
    }
}
