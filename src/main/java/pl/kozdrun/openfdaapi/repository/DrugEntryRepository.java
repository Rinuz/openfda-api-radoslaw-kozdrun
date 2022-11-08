package pl.kozdrun.openfdaapi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.kozdrun.openfdaapi.model.domain.DrugEntry;

public interface DrugEntryRepository extends MongoRepository<DrugEntry, String> {
}
