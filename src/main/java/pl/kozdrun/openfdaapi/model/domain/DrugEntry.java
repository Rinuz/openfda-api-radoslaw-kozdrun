package pl.kozdrun.openfdaapi.model.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.annotation.Id;
import pl.kozdrun.openfdaapi.rest.api.openfda.model.FdaDrugApplication;
import pl.kozdrun.openfdaapi.rest.api.openfda.model.Product;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DrugEntry {

    @Id
    private String applicationNumber;
    private String manufacturerName;
    private String substanceName;
    private List<String> productNames;

    public static DrugEntry from(FdaDrugApplication drug) {
        DrugEntry drugEntry = new DrugEntry();
        drugEntry.setApplicationNumber(drug.getApplicationNumber());
        drugEntry.setManufacturerName(StringUtils.join(drug.getOpenFda().getManufacturerName(), ","));
        drugEntry.setSubstanceName(StringUtils.join(drug.getOpenFda().getSubstanceName(), ","));
        List<String> productNumbers = drug.getProducts().stream()
                .map(Product::getProductNumber)
                .collect(Collectors.toList());
        drugEntry.setProductNames(productNumbers);
        return drugEntry;
    }
}