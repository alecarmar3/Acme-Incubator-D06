
package acme.features.anonymous.bulletin;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Bulletin;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousBulletinRepository extends AbstractRepository {

	@Query("select b from Bulletin b")
	Collection<Bulletin> findMany();

}
