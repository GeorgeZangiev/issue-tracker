package com.elmojke.issuetracker.issue;

import com.elmojke.issuetracker.enums.BugPriority;
import com.elmojke.issuetracker.enums.BugStatus;
import com.elmojke.issuetracker.enums.IssueType;
import com.elmojke.issuetracker.enums.StoryStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Issue {

    @Id
    @SequenceGenerator(
            name = "issue_id_sequence",
            sequenceName = "issue_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "issue_id_sequence"
    )
    private Integer id;
    @Enumerated(EnumType.STRING)
    @NonNull
    private IssueType type;
    @NonNull
    private String title;
    @NonNull
    private String description;
    @NonNull
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date creationDate;
    private Integer assignedDeveloperId;
    private Integer estimatedPoint;
    @Enumerated(EnumType.STRING)
    private StoryStatus storyStatus;
    @Enumerated(EnumType.STRING)
    private BugPriority bugPriority;
    @Enumerated(EnumType.STRING)
    private BugStatus bugStatus;
    private Integer storyWeek;
}
