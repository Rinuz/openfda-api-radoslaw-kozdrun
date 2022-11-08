package pl.kozdrun.openfdaapi.rest.api.openfda.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class FdaDrugsResponse {

    private Metadata metadata;
    private List<FdaDrugApplication> results = new ArrayList<>();
}
