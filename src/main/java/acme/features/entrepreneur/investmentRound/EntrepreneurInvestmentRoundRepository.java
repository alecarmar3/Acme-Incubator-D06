
package acme.features.entrepreneur.investmentRound;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Configuration;
import acme.entities.InvestmentRound;
import acme.entities.roles.Entrepreneur;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EntrepreneurInvestmentRoundRepository extends AbstractRepository {

	@Query("select ir from InvestmentRound ir where ir.id = ?1")
	InvestmentRound findOneById(int id);

	@Query("select ir from InvestmentRound ir where ir.ticker = ?1")
	InvestmentRound findInvestmentRoundByTicker(String ticker);

	@Query("select ir from InvestmentRound ir where ir.entrepreneur.id = ?1")
	Collection<InvestmentRound> findMyInvestmentRounds(int id);

	@Query("select e.activitySector from Entrepreneur e where e.id = ?1")
	String findActivitySectorOfEntrepreneur(int id);

	@Query("select ir.ticker from InvestmentRound ir")
	Collection<String> findTickersInUse();

	@Query("select e from Entrepreneur e where e.id = ?1")
	Entrepreneur findEntrepreneurById(int authId);

	@Query("select a.investmentRound from Application a where a.investmentRound.id = ?1")
	Collection<InvestmentRound> findApplicationsForThisInvestmentRound(int id);

	@Query("select a.budget.amount from Activity a where a.investmentRound.id = ?1")
	Collection<Double> findBudgetsOfActivities(int id);

	@Query("select ir.finalMode from InvestmentRound ir where ir.id = ?1")
	Boolean findFinalMode(int id);

	@Query("select c from Configuration c")
	Collection<Configuration> findManyConfiguration();

}
