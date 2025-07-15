package com.lpu.Momento;

public interface CommentService {
	public Comment createComment(Comment comment, Long userId , Long postId) throws UserException, PostException;
	
	public Comment findCommentById(Long commentId) throws CommentException;
	public Comment likeComment(Long commentId, Long userId) throws UserException, CommentException;
	public Comment unLikeComment(Long commentId, long userId) throws UserException, CommentException;
//	String deleteComment(Long commentId, Long userId) throws UserException, CommentException;


}
