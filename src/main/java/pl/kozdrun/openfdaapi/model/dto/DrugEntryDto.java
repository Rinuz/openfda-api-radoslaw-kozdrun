package pl.kozdrun.openfdaapi.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class DrugEntryDto {

    private String applicationNumber;
    private String manufacturerName;
    private String substanceName;
    private List<String> productNames;
}