
package acme.features.authenticated.message;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Configuration;
import acme.entities.Forum;
import acme.entities.Message;
import acme.framework.entities.Authenticated;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedMessageRepository extends AbstractRepository {

	@Query("select ir from Message ir where ir.id = ?1")
	Message findOneById(int id);

	@Query("select m from Message m where m.forum.id = ?1")
	Collection<Message> findMessagesOfForum(int id);

	@Query("select a from Authenticated a where a.id = ?1")
	Authenticated findAuthenticatedById(int authId);

	@Query("select f from Forum f where f.id = ?1")
	Forum findOneForumById(int id);

	@Query("select c from Configuration c")
	Collection<Configuration> findManyConfiguration();

}
