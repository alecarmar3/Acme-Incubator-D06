
package acme.features.administrator.technologyRecord;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Configuration;
import acme.entities.TechnologyRecord;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorTechnologyRecordRepository extends AbstractRepository {

	@Query("select tr from TechnologyRecord tr where tr.id = ?1")
	TechnologyRecord findOneById(int id);

	@Query("select tr from TechnologyRecord tr")
	Collection<TechnologyRecord> findTechnologyRecords();

	@Query("select c from Configuration c")
	Collection<Configuration> findManyConfiguration();

}
