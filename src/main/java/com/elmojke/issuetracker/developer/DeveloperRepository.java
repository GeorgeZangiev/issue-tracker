package com.elmojke.issuetracker.developer;

import com.elmojke.issuetracker.developer.Developer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeveloperRepository extends JpaRepository<Developer, Integer> {
}
