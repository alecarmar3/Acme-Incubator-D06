
package acme.features.investor.application;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Application;
import acme.entities.InvestmentRound;
import acme.entities.roles.Investor;
import acme.framework.entities.UserRole;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InvestorApplicationRepository extends AbstractRepository {

	@Query("select ir from Application ir where ir.id = ?1")
	Application findOneById(int id);

	@Query("select a from Application a where a.investor.id = ?1")
	Collection<Application> findMyApplications(int id);

	@Query("select ir from InvestmentRound ir where ir.id = ?1")
	InvestmentRound findInvestmentRoundById(int id);

	@Query("select a from Application a where a.investmentRound.id=?1 AND a.investor.id=?2")
	Collection<Application> exists(int investmentRoundId, int id);

	@Query("select ur from UserRole ur where ur.userAccount.id = ?1")
	Collection<UserRole> findUserAccountRoles(int id);

	@Query("select i from Investor i where i.id=?1")
	Investor findInvestorById(int id);

	@Query("select a.ticker from Application a")
	Collection<String> findTickersInUse();

	@Query("select i.activitySector from Investor i where i.id = ?1")
	String findActivitySectorOfInvestor(int id);

}
