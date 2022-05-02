package com.elmojke.issuetracker.issue;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IssueRepository extends JpaRepository<Issue, Integer> {
    @Query(value = "SELECT * FROM ISSUE i WHERE i.TYPE = 'STORY' AND i.ASSIGNED_DEVELOPER_ID IS NULL", nativeQuery = true)
    List<Issue> getUnassignedStories();

    @Query(value = "SELECT COUNT (ASSIGNED_DEVELOPER_ID) FROM ISSUE WHERE ASSIGNED_DEVELOPER_ID = ?", nativeQuery = true)
    Integer getNumOfTaskForDeveloper(@Param("?") Integer id);
}
