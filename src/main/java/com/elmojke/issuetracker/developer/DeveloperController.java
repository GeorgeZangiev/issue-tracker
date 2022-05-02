package com.elmojke.issuetracker.developer;

import com.elmojke.issuetracker.requests.DeveloperRegistrationRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/developers")
@AllArgsConstructor
public class DeveloperController {

    private final DeveloperService developerService;

    @GetMapping
    public List<Developer> getAllDevelopers() {
        log.info("getting all developers");
        return developerService.getAllDevelopers();
    }

    @PostMapping("/register-developer")
    public void registerDeveloper(@RequestBody DeveloperRegistrationRequest developerRegistrationRequest) {
        log.info("new developer registration {}", developerRegistrationRequest);
        developerService.registerDeveloper(developerRegistrationRequest);
    }

    @DeleteMapping(path = "{developerId}")
    public void deleteDeveloper(
            @PathVariable("developerId") Integer developerId) {
        log.info("deleting developer with id: {}", developerId);
        developerService.deleteDeveloper(developerId);
    }

    @PutMapping(path = "{developerId}")
    public void updateDeveloper(
            @PathVariable("developerId") Integer developerId,
            @RequestBody Developer developerDetails){
        log.info("updating developer with id: {}", developerId);
        developerService.updateDeveloper(developerId, developerDetails);
    }
}
