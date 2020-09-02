
package acme.features.administrator.dashboard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorDashboardRepository extends AbstractRepository {

	@Query("select count(n) from Notice n")
	Integer countNotices();

	@Query("select count(tr) from TechnologyRecord tr")
	Integer countTechnologyRecords();

	@Query("select count(tr) from ToolRecord tr")
	Integer countToolRecords();

	@Query("select min((i.moneyMax.amount - i.moneyMin.amount)) from Inquiry i where (i.deadline > CURRENT_TIMESTAMP)")
	Double minInquiryMoneyInterval();

	@Query("select max((i.moneyMax.amount - i.moneyMin.amount)) from Inquiry i where (i.deadline > CURRENT_TIMESTAMP)")
	Double maxInquiryMoneyInterval();

	@Query("select avg((i.moneyMax.amount - i.moneyMin.amount)) from Inquiry i where (i.deadline > CURRENT_TIMESTAMP)")
	Double averageInquiryMoneyInterval();

	@Query("select stddev((i.moneyMax.amount - i.moneyMin.amount)) from Inquiry i where (i.deadline > CURRENT_TIMESTAMP)")
	Double deviationInquiryMoneyInterval();

	@Query("select min((o.moneyMax.amount - o.moneyMin.amount)) from Overture o where (o.deadline > CURRENT_TIMESTAMP)")
	Double minOvertureMoneyInterval();

	@Query("select max((o.moneyMax.amount - o.moneyMin.amount)) from Overture o where (o.deadline > CURRENT_TIMESTAMP)")
	Double maxOvertureMoneyInterval();

	@Query("select avg((o.moneyMax.amount - o.moneyMin.amount)) from Overture o where (o.deadline > CURRENT_TIMESTAMP)")
	Double averageOvertureMoneyInterval();

	@Query("select stddev((o.moneyMax.amount - o.moneyMin.amount)) from Overture o where (o.deadline > CURRENT_TIMESTAMP)")
	Double deviationOvertureMoneyInterval();

}
