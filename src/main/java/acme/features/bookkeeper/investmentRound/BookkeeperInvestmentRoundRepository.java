
package acme.features.bookkeeper.investmentRound;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.InvestmentRound;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface BookkeeperInvestmentRoundRepository extends AbstractRepository {

	@Query("select ir from InvestmentRound ir where ir.id = ?1")
	InvestmentRound findOneById(int id);

	@Query("select ir from InvestmentRound ir")
	Collection<InvestmentRound> findAllRounds();

	@Query("select ar.investmentRound from AccountingRecord ar where ar.bookkeeper.userAccount.id = ?1")
	Collection<InvestmentRound> findWrittenInvestmentRounds(int id);

}
