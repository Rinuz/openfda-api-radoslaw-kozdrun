package pl.kozdrun.openfdaapi.rest.api.openfda.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MetadataResult {

    private Integer skip;
    private Integer limit;
    private Integer total;
}