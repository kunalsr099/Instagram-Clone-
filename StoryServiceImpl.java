package com.lpu.Momento;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class StoryServiceImpl implements StoryService{
	private final StoryRepository storyRepository;
	private final UserService userService;
	

	public StoryServiceImpl(StoryRepository storyRepository, UserService userService) {
		this.storyRepository = storyRepository;
		this.userService = userService;
	}

	@Override
	public Story createStory(Story story, Long userId) throws UserException {
		User user = userService.findUserById(userId);
		story.setUser(user);
		story.setTimestamp(LocalDateTime.now());
		user.getStories().add(story);
		return storyRepository.save(story);
	}

	@Override
	public List<Story> findStoryByUserId(Long userId) throws UserException, StoryException {
		User user = userService.findUserById(userId);
		List<Story> stories = storyRepository.findAllStoryByUserId(userId);
		return stories;
	}

}
