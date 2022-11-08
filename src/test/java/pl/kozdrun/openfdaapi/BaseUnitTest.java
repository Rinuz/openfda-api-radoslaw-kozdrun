package pl.kozdrun.openfdaapi;

import org.apache.commons.lang3.RandomStringUtils;
import pl.kozdrun.openfdaapi.model.dto.DrugDto;
import pl.kozdrun.openfdaapi.model.dto.OpenFdaDto;

public abstract class BaseUnitTest {

    //TODO mock the rest of data

    protected DrugDto createDrugDto() {
        DrugDto drugDto = new DrugDto();
        drugDto.setApplicationNumber(RandomStringUtils.randomAlphanumeric(10));
        drugDto.setSponsorName(RandomStringUtils.randomAlphabetic(20));
        drugDto.setOpenFda(createOpenFdaDto());
        //TODO mock the rest of data
        return drugDto;
    }

    protected OpenFdaDto createOpenFdaDto() {
        OpenFdaDto openFdaDto = new OpenFdaDto();
        openFdaDto.setApplicationNumber(new String[]{RandomStringUtils.randomAlphanumeric(10)});
        return openFdaDto;
    }
}
