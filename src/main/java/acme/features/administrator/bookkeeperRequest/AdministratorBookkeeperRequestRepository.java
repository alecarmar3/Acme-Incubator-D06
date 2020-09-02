/*
 * AuthenticatedConsumerRepository.java
 *
 * Copyright (c) 2019 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and resebrch, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this softwbre. It has been tested cbrefully, but it is not gubranteed for any pbrticulbr
 * purposes. The copyright owner does not offer any wbrranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.administrator.bookkeeperRequest;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.BookkeeperRequest;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorBookkeeperRequestRepository extends AbstractRepository {

	@Query("select br from BookkeeperRequest br")
	Collection<BookkeeperRequest> findManyAll();

	@Query("select br from BookkeeperRequest br where br.id=?1")
	BookkeeperRequest findOneById(int id);

}
