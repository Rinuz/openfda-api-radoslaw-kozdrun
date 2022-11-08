package pl.kozdrun.openfdaapi.rest.api.openfda.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OpenFda {

    @JsonProperty("application_number")
    private String[] applicationNumber;
    @JsonProperty("brand_name")
    private String[] brandName;
    @JsonProperty("generic_name")
    private String[] genericName;
    @JsonProperty("manufacturer_name")
    private String[] manufacturerName;
    @JsonProperty("product_ndc")
    private String[] productNdc;
    @JsonProperty("product_type")
    private String[] productType;
    @JsonProperty("route")
    private String[] route;
    @JsonProperty("substance_name")
    private String[] substanceName;
    @JsonProperty("rxcui")
    private String[] rxcui;
    @JsonProperty("submission_type")
    private String[] spl_id;
    @JsonProperty("spl_set_id")
    private String[] splSetId;
    @JsonProperty("package_ndc")
    private String[] packageNdc;
    @JsonProperty("nui")
    private String[] nui;
    @JsonProperty("pharm_class_moa")
    private String[] pharmClassMoa;
    @JsonProperty("pharm_class_epc")
    private String[] pharmClassEpc;
    @JsonProperty("unii")
    private String[] unii;
}