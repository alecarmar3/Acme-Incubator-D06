
package acme.features.authenticated.investmentRound;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Application;
import acme.entities.InvestmentRound;
import acme.entities.roles.Bookkeeper;
import acme.framework.entities.UserRole;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedInvestmentRoundRepository extends AbstractRepository {

	@Query("select ir from InvestmentRound ir where ir.id = ?1")
	InvestmentRound findOneById(int id);

	@Query("select ir from InvestmentRound ir where ir.finalMode = true")
	Collection<InvestmentRound> findActiveInvestmentRounds();

	@Query("select a.investmentRound from Application a where a.status='ACCEPTED' AND a.investor.userAccount.id = ?1")
	Collection<InvestmentRound> findInvestorInvestmentRounds(int id);

	@Query("select ar.investmentRound from AccountingRecord ar where ar.bookkeeper.userAccount.id = ?1")
	Collection<InvestmentRound> findBookkeeperInvestmentRounds(int id);

	@Query("select ir from InvestmentRound ir where ir.entrepreneur.userAccount.id = ?1")
	Collection<InvestmentRound> findEntrepreneurInvestmentRounds(int id);

	@Query("select a from Application a where a.investmentRound.id = ?1 AND a.investor.userAccount.id = ?2")
	Collection<Application> exists(int investmentRoundId, int id);

	@Query("select ur from UserRole ur where ur.userAccount.id = ?1")
	Collection<UserRole> findUserAccountRoles(int id);

	@Query("select b from Bookkeeper b where b.userAccount.id = ?1")
	Bookkeeper getBookkeeperByAccountId(int id);
}
