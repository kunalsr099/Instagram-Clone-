package com.lpu.Momento;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/story")
public class StoryController {

    private final StoryService storyService;
    private final UserService userService;

    public StoryController(StoryService storyService, UserService userService) {
        this.storyService = storyService;
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<Story> createStory(@RequestBody Story story,
                                             @RequestHeader("Authorization") String token) throws UserException {
        User user = userService.findUserByToken(token);
        Story createdStory = storyService.createStory(story, user.getId());
        return new ResponseEntity<>(createdStory, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Story>> findAllStoryByUserId(@PathVariable Long userId) throws UserException, StoryException {
        List<Story> stories = storyService.findStoryByUserId(userId);
        return new ResponseEntity<>(stories, HttpStatus.OK);
    }
}
