package com.elmojke.issuetracker.requests;

import com.elmojke.issuetracker.enums.IssueType;
import com.elmojke.issuetracker.enums.IssueStatus;

import java.util.Date;

public record StoryRegistrationRequest(IssueType type, String title, String description,
                                       Date creationDate, IssueStatus issueStatus) {
}
