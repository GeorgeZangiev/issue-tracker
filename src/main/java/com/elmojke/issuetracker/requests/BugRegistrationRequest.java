package com.elmojke.issuetracker.requests;

import com.elmojke.issuetracker.enums.BugPriority;
import com.elmojke.issuetracker.enums.BugStatus;
import com.elmojke.issuetracker.enums.IssueType;

import java.util.Date;


public record BugRegistrationRequest(IssueType type, String title, String description,
                                     Date creationDate, Integer assignedDeveloperId,
                                     BugPriority bugPriority, BugStatus bugStatus) {
}
