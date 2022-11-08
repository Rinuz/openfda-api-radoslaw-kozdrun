package pl.kozdrun.openfdaapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import pl.kozdrun.openfdaapi.exception.ApplicationException;
import pl.kozdrun.openfdaapi.model.dto.DrugDto;
import pl.kozdrun.openfdaapi.model.dto.mapper.DrugMapper;
import pl.kozdrun.openfdaapi.rest.api.openfda.model.FdaDrugApplication;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DrugsService {

    private final OpenFdaApiService openFdaApiService;
    private final DrugMapper drugMapper;

    public List<DrugDto> getDrugs(int page, int limit) {
        List<FdaDrugApplication> fdaDrugApplications = openFdaApiService.findDrugs(page, limit);
        return drugMapper.toDtos(fdaDrugApplications);
    }

    public DrugDto getDrugByApplicationNumber(@NonNull String applicationNumber) {
        Optional<FdaDrugApplication> drugOptional = openFdaApiService.findDrugByApplicationNumber(applicationNumber);

        FdaDrugApplication fdaDrugApplication = drugOptional.orElseThrow(() -> new ApplicationException(
                "Drug application not found in FDA for given application number: " + applicationNumber, HttpStatus.NOT_FOUND));
        return drugMapper.toDto(fdaDrugApplication);
    }

    public List<DrugDto> getDrugsByManufacturerAndBrand(@NonNull String manufacturerName, @Nullable String brandName, int page, int limit) {
        List<FdaDrugApplication> fdaDrugApplications = openFdaApiService.findDrugsByManufacturerAndBrand(manufacturerName, brandName, page, limit);
        return drugMapper.toDtos(fdaDrugApplications);
    }
}