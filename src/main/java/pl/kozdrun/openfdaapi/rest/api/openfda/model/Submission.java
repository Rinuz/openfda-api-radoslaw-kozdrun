package pl.kozdrun.openfdaapi.rest.api.openfda.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Submission {

    @JsonProperty("submission_type")
    private String submissionType;
    @JsonProperty("submission_number")
    private Integer submissionNumber;
    @JsonProperty("submission_status")
    private String submissionStatus;
    @JsonProperty("submission_status_date")
    private String submissionStatusDate;
    @JsonProperty("review_priority")
    private String reviewPriority;
    @JsonProperty("submission_class_code")
    private String submissionClassCode;
    @JsonProperty("submission_class_code_description")
    private String submissionClassCodeDescription;
}