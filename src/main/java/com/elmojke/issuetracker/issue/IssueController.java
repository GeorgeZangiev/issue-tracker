package com.elmojke.issuetracker.issue;

import com.elmojke.issuetracker.requests.BugRegistrationRequest;
import com.elmojke.issuetracker.requests.StoryRegistrationRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/issues")
@AllArgsConstructor
public class IssueController {

    private final IssueService issueService;

    @GetMapping
    public List<Issue> getAllIssues() {
        log.info("getting all issues");
        return issueService.getAllIssues();
    }

    @PostMapping("/register-story")
    public void registerStory(@RequestBody StoryRegistrationRequest storyRegistrationRequest) {
        log.info("new story registration {}", storyRegistrationRequest);
        issueService.registerStory(storyRegistrationRequest);
    }

    @PostMapping("/register-bug")
    public void registerBug(@RequestBody BugRegistrationRequest bugRegistrationRequest) {
        log.info("new bug registration {}", bugRegistrationRequest);
        issueService.registerBug(bugRegistrationRequest);
    }

    @DeleteMapping(path = "{issueId}")
    public void deleteIssue(
            @PathVariable("issueId") Integer issueId) {
        log.info("deleting issue with id: {}", issueId);
        issueService.deleteIssue(issueId);
    }

    @PutMapping(path = "/update-story/{issueId}")
    public void updateStory(
            @PathVariable("issueId") Integer issueId,
            @RequestBody Issue storyDetails){
        log.info("updating story with id: {}", issueId);
        issueService.updateStory(issueId, storyDetails);
    }

    @PutMapping(path = "/update-bug/{issueId}")
    public void updateBug(
            @PathVariable("issueId") Integer issueId,
            @RequestBody Issue bugDetails){
        log.info("updating bug with id: {}", issueId);
        issueService.updateBug(issueId, bugDetails);
    }
}
