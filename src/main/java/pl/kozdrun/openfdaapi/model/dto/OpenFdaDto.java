package pl.kozdrun.openfdaapi.model.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OpenFdaDto {

    private String[] applicationNumber;
    private String[] brandName;
    private String[] genericName;
    private String[] manufacturerName;
    private String[] productNdc;
    private String[] productType;
    private String[] route;
    private String[] substanceName;
    private String[] rxcui;
    private String[] spl_id;
    private String[] splSetId;
    private String[] packageNdc;
    private String[] nui;
    private String[] pharmClassMoa;
    private String[] pharmClassEpc;
    private String[] unii;
}