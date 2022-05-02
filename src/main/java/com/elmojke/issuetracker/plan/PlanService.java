package com.elmojke.issuetracker.plan;

import com.elmojke.issuetracker.developer.Developer;
import com.elmojke.issuetracker.developer.DeveloperRepository;
import com.elmojke.issuetracker.enums.IssueStatus;
import com.elmojke.issuetracker.issue.Issue;
import com.elmojke.issuetracker.issue.IssueRepository;
import com.elmojke.issuetracker.issue.IssueService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

@Service
@AllArgsConstructor
public class PlanService {

    private final DeveloperRepository developerRepository;
    private final IssueRepository issueRepository;
    private final IssueService issueService;

    public List<Issue> getPlan() {
        List<Issue> unassignedStories = issueRepository.getUnassignedStories();
        List<Developer> developers = developerRepository.findAll();
        HashMap<Integer, Integer> tasks = new HashMap<>();
        for (Developer developer: developers){
            tasks.put(developer.getId(), issueRepository.getNumOfTaskForDeveloper(developer.getId()));
        }
        int freeDeveloperId = 1;
        for (Issue unassignedStory : unassignedStories) {
            Random random = new Random();
            int randomEstimatedPoint = random.nextInt(100) + 1;
            Issue assignedStory = Issue.builder()
                    .type(unassignedStory.getType())
                    .title(unassignedStory.getTitle())
                    .description(unassignedStory.getDescription())
                    .creationDate(unassignedStory.getCreationDate())
                    .assignedDeveloperId(freeDeveloperId)
                    .estimatedPoint(randomEstimatedPoint)
                    .issueStatus(IssueStatus.ESTIMATED)
                    .storyWeek((tasks.get(freeDeveloperId) / 10) + 1)
                    .build();
            issueService.updateStory(unassignedStory.getId(), assignedStory);
            tasks.put(freeDeveloperId, tasks.get(freeDeveloperId) + 1);
            if (tasks.get(freeDeveloperId) > tasks.get(developers.size()))
                freeDeveloperId++;
            else freeDeveloperId--;
        }
        return issueRepository.findAll();
    }
}

