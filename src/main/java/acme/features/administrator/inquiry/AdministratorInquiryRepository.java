
package acme.features.administrator.inquiry;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Inquiry;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorInquiryRepository extends AbstractRepository {

	@Query("select i from Inquiry i where i.id = ?1")
	Inquiry findOneById(int id);

	@Query("select i from Inquiry i")
	Collection<Inquiry> findInquiries();

}
