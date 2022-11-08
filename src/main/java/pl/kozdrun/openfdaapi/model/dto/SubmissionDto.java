package pl.kozdrun.openfdaapi.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubmissionDto {

    private String submissionType;
    private Integer submissionNumber;
    private String submissionStatus;
    private String submissionStatusDate;
    private String reviewPriority;
    private String submissionClassCode;
    private String submissionClassCodeDescription;
}