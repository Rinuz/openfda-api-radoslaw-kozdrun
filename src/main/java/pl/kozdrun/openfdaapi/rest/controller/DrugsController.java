package pl.kozdrun.openfdaapi.rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pl.kozdrun.openfdaapi.model.dto.DrugDto;
import pl.kozdrun.openfdaapi.model.dto.DrugEntryDto;
import pl.kozdrun.openfdaapi.service.DrugEntriesService;
import pl.kozdrun.openfdaapi.service.DrugsService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "api/v1/drugs")
@RequiredArgsConstructor
public class DrugsController {

    private static final String DEFAULT_PAGE = "0";
    private static final String DEFAULT_LIMIT = "100";

    private final DrugsService drugsService;
    private final DrugEntriesService drugEntriesService;

    @Operation(summary = "Get all drug record applications in FDA")
    @GetMapping
    public List<DrugDto> getDrugs(
            @RequestParam(required = false, defaultValue = DEFAULT_PAGE) int page,
            @RequestParam(required = false, defaultValue = DEFAULT_LIMIT) int limit) {
        return drugsService.getDrugs(page, limit);
    }

    @Operation(summary = "Get drug record application in FDA by given application number")
    @GetMapping("{applicationNumber}")
    public DrugDto getDrug(
            @PathVariable("applicationNumber") String applicationNumber) {
        return drugsService.getDrugByApplicationNumber(applicationNumber);
    }

    @Operation(summary = "Search drug record applications in FDA by parameters")
    @GetMapping("search")
    public List<DrugDto> searchDrugs(
            @RequestParam String manufacturerName,
            @RequestParam(required = false) String brandName,
            @RequestParam(required = false, defaultValue = DEFAULT_PAGE) int page,
            @RequestParam(required = false, defaultValue = DEFAULT_LIMIT) int limit) {
        return drugsService.getDrugsByManufacturerAndBrand(manufacturerName, brandName, page, limit);
    }

    @Operation(summary = "Store drug record application entry for given application number")
    @PostMapping("entries/{applicationNumber}")
    public DrugEntryDto createDrugEntry(
            @PathVariable("applicationNumber") String applicationNumber) {
        return drugEntriesService.createDrugEntry(applicationNumber);
    }

    @Operation(summary = "Get all stored drug record applications entries")
    @GetMapping("entries")
    public List<DrugEntryDto> getDrugEntries(
            @RequestParam(required = false, defaultValue = DEFAULT_PAGE) int page,
            @RequestParam(required = false, defaultValue = DEFAULT_LIMIT) int limit) {
        return drugEntriesService.getDrugEntries(page, limit);
    }

    @Operation(summary = "Get stored drug record application entry by given application number")
    @GetMapping("entries/{applicationNumber}")
    public DrugEntryDto getDrugEntry(
            @PathVariable("applicationNumber") String applicationNumber) {
        return drugEntriesService.getDrugEntryByApplicationNumber(applicationNumber);
    }
}