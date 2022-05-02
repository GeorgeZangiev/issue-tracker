package com.elmojke.issuetracker.developer;

import com.elmojke.issuetracker.requests.DeveloperRegistrationRequest;
import com.elmojke.issuetracker.exception.DeveloperNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DeveloperService {

    private final DeveloperRepository developerRepository;

    public List<Developer> getAllDevelopers() {
        return developerRepository.findAll();
    }

    public void registerDeveloper(DeveloperRegistrationRequest request) {
        Developer developer = Developer.builder()
                .name(request.name())
                .build();
        // todo: check if name has not been taken if name has to be unique
        developerRepository.save(developer);
    }

    public void deleteDeveloper(Integer developerId) {
        if(!developerRepository.existsById(developerId)) {
            throw new DeveloperNotFoundException(
                    "Developer with id " + developerId + " does not exist");
        }
        else developerRepository.deleteById(developerId);
    }

    public ResponseEntity<Developer> updateDeveloper(Integer developerId, Developer developerDetails) {
        Developer developer = developerRepository.findById(developerId)
                .orElseThrow(() -> new DeveloperNotFoundException("Developer with id " + developerId + " does not exist"));
        developer.setName(developerDetails.getName());
        Developer updatedDeveloper = developerRepository.save(developer);
        return ResponseEntity.ok(updatedDeveloper);
    }
}
