
package acme.features.authenticated.forum;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Forum;
import acme.entities.InvestmentRound;
import acme.entities.Message;
import acme.framework.entities.Authenticated;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedForumRepository extends AbstractRepository {

	@Query("select f from Forum f where f.id = ?1")
	Forum findOneById(int id);

	@Query("select f from Forum f where f.investmentRound.id = ?1")
	Collection<Forum> findForumsOfInvestmentRound(int id);

	@Query("select a.investmentRound from Application a where a.status='ACCEPTED' AND a.investor.userAccount.id = ?1")
	Collection<InvestmentRound> findInvestorInvestmentRounds(int id);

	@Query("select ar.investmentRound from AccountingRecord ar where ar.bookkeeper.userAccount.id = ?1")
	Collection<InvestmentRound> findBookkeeperInvestmentRounds(int id);

	@Query("select ir from InvestmentRound ir where ir.entrepreneur.userAccount.id = ?1")
	Collection<InvestmentRound> findEntrepreneurInvestmentRounds(int id);

	@Query("select ir from InvestmentRound ir where ir.id = ?1")
	InvestmentRound getInvestmentRoundById(int id);

	@Query("select m from Message m where m.forum.id = ?1")
	Collection<Message> findManyMessagesByForumId(int jobId);

	@Query("select a from Authenticated a where a.id = ?1")
	Authenticated findAuthenticatedById(int authId);

	@Query("select pi.forum from ParticipatesIn pi where pi.participant.id = ?1")
	Collection<Forum> findManyByParticipantId(int id);

	@Query("select f from Forum f where f.owner.id = ?1")
	Collection<Forum> findForumsByOwner(int id);

}
