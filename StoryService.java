package com.lpu.Momento;

import java.util.List;

public interface StoryService {
	public Story createStory(Story story, Long userId) throws UserException;
	
	public List<Story> findStoryByUserId(Long userId) throws UserException, StoryException;

}
