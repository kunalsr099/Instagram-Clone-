package com.lpu.Momento;

import java.util.List;

public interface PostService {

    Post createPost(Post post, Long userId) throws UserException;

    String deletePost(Long postId, Long userId) throws UserException, PostException;

    List<Post> findPostByUserId(Long userId) throws UserException;

    Post findPostById(Long postId) throws PostException;

    List<Post> findAllPostByUserIds(List<Long> userIds) throws UserException, PostException;

    String savedPost(Long postId, Long userId) throws UserException, PostException;

    String unSavedPost(Long postId, Long userId) throws UserException, PostException;

    Post LikePost(Long postId, Long userId) throws UserException, PostException;

    Post unLikePost(Long postId, Long userId) throws UserException, PostException;
}
