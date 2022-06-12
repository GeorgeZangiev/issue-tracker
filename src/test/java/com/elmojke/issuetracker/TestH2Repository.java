package com.elmojke.issuetracker;

import com.elmojke.issuetracker.developer.Developer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestH2Repository extends JpaRepository<Developer, Integer> {
}
