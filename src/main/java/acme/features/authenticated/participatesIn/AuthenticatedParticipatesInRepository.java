
package acme.features.authenticated.participatesIn;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Forum;
import acme.entities.ParticipatesIn;
import acme.framework.entities.Authenticated;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedParticipatesInRepository extends AbstractRepository {

	@Query("select a from Authenticated a where a.id = ?1")
	Authenticated findOneAuthenticatedById(int authId);

	@Query("select pi from ParticipatesIn pi where pi.id = ?1")
	ParticipatesIn findOneById(int id);

	@Query("select f from Forum f where f.id = ?1")
	Forum findOneForumById(int id);

	@Query("select pi from ParticipatesIn pi where pi.forum.id = ?1")
	Collection<ParticipatesIn> findManyByForumId(int forumId);

	@Query("select f from Forum f where f.id = ?1")
	Forum findForumById(int forumId);

	@Query("select a from Authenticated a where NOT EXISTS(select pi from ParticipatesIn pi where (pi.participant.id = a.id AND pi.forum.id = ?1) OR pi.forum.owner.id = a.id)")
	Collection<Authenticated> findAllAuthenticated(int forumId);

}
