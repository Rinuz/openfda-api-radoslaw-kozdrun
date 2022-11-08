package pl.kozdrun.openfdaapi.model.dto.mapper;

import org.mapstruct.Mapper;
import pl.kozdrun.openfdaapi.model.dto.DrugDto;
import pl.kozdrun.openfdaapi.rest.api.openfda.model.FdaDrugApplication;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DrugMapper {

    List<DrugDto> toDtos(List<FdaDrugApplication> fdaDrugs);

    DrugDto toDto(FdaDrugApplication fdaDrug);
}