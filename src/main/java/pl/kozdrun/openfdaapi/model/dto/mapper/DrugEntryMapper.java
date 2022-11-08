package pl.kozdrun.openfdaapi.model.dto.mapper;

import org.mapstruct.Mapper;
import pl.kozdrun.openfdaapi.model.domain.DrugEntry;
import pl.kozdrun.openfdaapi.model.dto.DrugEntryDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DrugEntryMapper {

    List<DrugEntryDto> toDtos(List<DrugEntry> entities);

    DrugEntryDto toDto(DrugEntry entity);
}