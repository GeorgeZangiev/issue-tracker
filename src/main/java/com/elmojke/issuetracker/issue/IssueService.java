package com.elmojke.issuetracker.issue;

import com.elmojke.issuetracker.developer.DeveloperRepository;
import com.elmojke.issuetracker.exception.DeveloperNotFoundException;
import com.elmojke.issuetracker.exception.IssueNotFoundException;
import com.elmojke.issuetracker.requests.BugRegistrationRequest;
import com.elmojke.issuetracker.requests.StoryRegistrationRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class IssueService {

    private final DeveloperRepository developerRepository;
    private final IssueRepository issueRepository;

    public List<Issue> getAllIssues() {
        return issueRepository.findAll();
    }

    public void registerStory(StoryRegistrationRequest request) {
        Issue story = Issue.builder()
                .type(request.type())
                .title(request.title())
                .description(request.description())
                .creationDate(request.creationDate())
                .storyStatus(request.storyStatus())
                .build();
        issueRepository.save(story);
    }

    public void registerBug(BugRegistrationRequest request) {
        if(!developerRepository.existsById(request.assignedDeveloperId())){
            throw new DeveloperNotFoundException("Developer with id " + request.assignedDeveloperId() + " does not exist");
        }
        Issue bug = Issue.builder()
                .type(request.type())
                .title(request.title())
                .description(request.description())
                .creationDate(request.creationDate())
                .assignedDeveloperId(request.assignedDeveloperId())
                .bugPriority(request.bugPriority())
                .bugStatus(request.bugStatus())
                .build();
        issueRepository.save(bug);
    }

    public void deleteIssue(Integer issueId) {
        if(!issueRepository.existsById(issueId)) {
            throw new IssueNotFoundException(
                    "Issue with id " + issueId + " does not exist");
        }
        else issueRepository.deleteById(issueId);
    }

    public ResponseEntity<Issue> updateStory(Integer issueId, Issue storyDetails) {
        Issue story = issueRepository.findById(issueId)
                .orElseThrow(() -> new IssueNotFoundException("Story with id " + issueId + " does not exist"));
        story.setType(storyDetails.getType());
        story.setTitle(storyDetails.getTitle());
        story.setDescription(storyDetails.getDescription());
        story.setCreationDate(storyDetails.getCreationDate());
        story.setAssignedDeveloperId(storyDetails.getAssignedDeveloperId());
        story.setEstimatedPoint(storyDetails.getEstimatedPoint());
        story.setStoryStatus(storyDetails.getStoryStatus());
        story.setStoryWeek(storyDetails.getStoryWeek());
        Issue updatedStory = issueRepository.save(story);
        return ResponseEntity.ok(updatedStory);
    }

    public ResponseEntity<Issue> updateBug(Integer issueId, Issue bugDetails) {
        if (!developerRepository.existsById(bugDetails.getAssignedDeveloperId())){
            throw new DeveloperNotFoundException("Developer with id "
                    + bugDetails.getAssignedDeveloperId() + " does not exist");
        }
        Issue bug = issueRepository.findById(issueId)
                .orElseThrow(() -> new IssueNotFoundException("Story with id " + issueId + " does not exist"));
        bug.setType(bugDetails.getType());
        bug.setTitle(bugDetails.getTitle());
        bug.setDescription(bugDetails.getDescription());
        bug.setCreationDate(bugDetails.getCreationDate());
        bug.setAssignedDeveloperId(bugDetails.getAssignedDeveloperId());
        bug.setBugPriority(bugDetails.getBugPriority());
        bug.setBugStatus(bugDetails.getBugStatus());
        Issue updatedBug = issueRepository.save(bug);
        return ResponseEntity.ok(updatedBug);
    }
}
