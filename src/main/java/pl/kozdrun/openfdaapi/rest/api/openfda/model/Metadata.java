package pl.kozdrun.openfdaapi.rest.api.openfda.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Metadata {

    private String disclaimer;
    private String terms;
    private String license;
    @JsonProperty("last_updated")
    private LocalDate lastUpdated;
    private MetadataResult result;
}