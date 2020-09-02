
package acme.features.bookkeeper.accountingRecord;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.AccountingRecord;
import acme.entities.InvestmentRound;
import acme.entities.roles.Bookkeeper;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface BookkeeperAccountingRecordRepository extends AbstractRepository {

	@Query("select ar from AccountingRecord ar where ar.id = ?1")
	AccountingRecord findOneById(int id);

	@Query("select ar from AccountingRecord ar where ar.bookkeeper.id = ?1")
	Collection<AccountingRecord> findMyAccountingRecords(int id);

	@Query("select ir from InvestmentRound ir where ir.id = ?1")
	InvestmentRound findInvestmentRoundById(int id);

	@Query("select b from Bookkeeper b where b.id=?1")
	Bookkeeper findBookkeeperById(int id);

}
