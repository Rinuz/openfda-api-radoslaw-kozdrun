package pl.kozdrun.openfdaapi.rest.api.openfda.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class FdaDrugApplication {

    @JsonProperty("application_number")
    private String applicationNumber;
    @JsonProperty("sponsor_name")
    private String sponsorName;
    @JsonProperty("openfda")
    private OpenFda openFda;
    private List<Submission> submissions = new ArrayList<>();
    private List<Product> products = new ArrayList<>();
}