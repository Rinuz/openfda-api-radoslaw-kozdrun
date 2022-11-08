package pl.kozdrun.openfdaapi.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class DrugDto {

    private String applicationNumber;
    private String sponsorName;
    private OpenFdaDto openFda;
    private List<SubmissionDto> submissions;
    private List<ProductDto> products;
}