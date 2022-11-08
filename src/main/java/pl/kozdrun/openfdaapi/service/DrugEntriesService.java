package pl.kozdrun.openfdaapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import pl.kozdrun.openfdaapi.exception.ApplicationException;
import pl.kozdrun.openfdaapi.model.domain.DrugEntry;
import pl.kozdrun.openfdaapi.model.dto.DrugEntryDto;
import pl.kozdrun.openfdaapi.model.dto.mapper.DrugEntryMapper;
import pl.kozdrun.openfdaapi.repository.DrugEntryRepository;
import pl.kozdrun.openfdaapi.rest.api.openfda.model.FdaDrugApplication;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DrugEntriesService {

    private final DrugEntryRepository drugEntryRepository;
    private final DrugEntryMapper drugEntryMapper;
    private final OpenFdaApiService openFdaApiService;

    public DrugEntryDto createDrugEntry(@NonNull String applicationNumber) {
        Optional<FdaDrugApplication> drugOptional = openFdaApiService.findDrugByApplicationNumber(applicationNumber);
        FdaDrugApplication drug = drugOptional.orElseThrow(() -> new ApplicationException(
                "Cannot create drug entry because drug application not found in FDA for given application number: "
                        + applicationNumber, HttpStatus.NOT_FOUND));

        DrugEntry drugEntry = DrugEntry.from(drug);
        drugEntryRepository.save(drugEntry);
        return drugEntryMapper.toDto(drugEntry);
    }

    public List<DrugEntryDto> getDrugEntries(int page, int limit) {
        Page<DrugEntry> searchPage = drugEntryRepository.findAll(PageRequest.of(page, limit));
        List<DrugEntry> drugEntries = searchPage.get().collect(Collectors.toList());
        return drugEntryMapper.toDtos(drugEntries);
    }

    public DrugEntryDto getDrugEntryByApplicationNumber(@NonNull String applicationNumber) {
        Optional<DrugEntry> drugEntryOptional = drugEntryRepository.findById(applicationNumber);
        DrugEntry drugEntry = drugEntryOptional.orElseThrow(() -> new ApplicationException(
                "Drug entry not found for given application number: " + applicationNumber, HttpStatus.NOT_FOUND));
        return drugEntryMapper.toDto(drugEntry);
    }
}