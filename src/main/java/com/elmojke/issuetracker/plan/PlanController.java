package com.elmojke.issuetracker.plan;

import com.elmojke.issuetracker.developer.DeveloperService;
import com.elmojke.issuetracker.issue.Issue;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/plan")
@AllArgsConstructor
public class PlanController {

    private final PlanService planService;

    @GetMapping
    public List<Issue> getPlan() {
        log.info("setting plan");
        return planService.getPlan();
    }

}
