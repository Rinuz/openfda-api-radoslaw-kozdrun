package pl.kozdrun.openfdaapi.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import pl.kozdrun.openfdaapi.rest.api.openfda.OpenFdaApiClient;
import pl.kozdrun.openfdaapi.rest.api.openfda.model.FdaDrugApplication;
import pl.kozdrun.openfdaapi.rest.api.openfda.model.FdaDrugsResponse;

import java.util.List;
import java.util.Optional;

import static pl.kozdrun.openfdaapi.rest.api.openfda.OpenFdaApiClient.*;

@Component
@RequiredArgsConstructor
public class OpenFdaApiService {

    private static final int SINGLE_RESULT_PAGE = 0;
    private static final int SINGLE_RESULT_LIMIT = 1;

    private final OpenFdaApiClient openFdaApiClient;

    public List<FdaDrugApplication> findDrugs(int page, int limit) {
        FdaDrugsResponse fdaDrugsResponse = openFdaApiClient.findDrugs(page, limit);
        return fdaDrugsResponse.getResults();
    }

    public Optional<FdaDrugApplication> findDrugByApplicationNumber(String applicationNumber) {
        FdaDrugsResponse fdaDrugsResponse = openFdaApiClient.findDrugs(SINGLE_RESULT_PAGE, SINGLE_RESULT_LIMIT,
                Pair.of(APPLICATION_NUMBER_PARAM, applicationNumber));
        return Optional.ofNullable(CollectionUtils.firstElement(fdaDrugsResponse.getResults()));
    }

    public List<FdaDrugApplication> findDrugsByManufacturerAndBrand(String manufacturerName, String brandName, int page, int limit) {
        FdaDrugsResponse fdaDrugsResponse = openFdaApiClient.findDrugs(page, limit,
                Pair.of(MANUFACTURER_NAME_PARAM, manufacturerName),
                Pair.of(BRAND_NAME_PARAM, brandName)
        );
        return fdaDrugsResponse.getResults();
    }
}