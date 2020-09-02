
package acme.components;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Banner;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface BannerRepository extends AbstractRepository {

	@Query("select count(b) from Banner b")
	int countBanners();

	@Query("select b from Banner b")
	List<Banner> findManyBanners(PageRequest pageRequest);

	@Query("select b from Banner b")
	List<Banner> findBanners();

	default Boolean isValidBanner(final Banner banner) {

		Boolean validBanner = false;

		LocalDate now;
		now = LocalDate.now();

		String[] shortExpDate;
		Integer year, month;
		shortExpDate = banner.getExpirationDate().split("/");
		month = Integer.parseInt(shortExpDate[0]);
		year = Integer.parseInt(shortExpDate[1]);

		boolean validYear = year >= now.getYear() - 2000;
		boolean validMonth = month >= now.getMonthValue();
		boolean monthInRange = month >= 1 && month <= 12;

		if (validYear && year == now.getYear() - 2000) {
			validBanner = validMonth && monthInRange;
			return validBanner;
		} else if (validYear) {
			validBanner = monthInRange;
			return validBanner;
		} else {
			return validBanner;
		}
	}

	default Banner findRandomBanner() {
		Banner result;
		int bannerCount, bannerIndex;
		ThreadLocalRandom random;
		PageRequest page;
		List<Banner> list;

		bannerCount = this.countBanners();
		random = ThreadLocalRandom.current();
		bannerIndex = random.nextInt(0, bannerCount);

		page = PageRequest.of(bannerIndex, 1);
		list = this.findManyBanners(page);
		result = list.isEmpty() ? null : list.get(0);

		return result;
	}

	//.stream().filter(x -> Integer.parseInt(x.getExpirationDate().split("/")[0]) >= now.getMonthValue() && Integer.parseInt(x.getExpirationDate().split("/")[1]) >= now.getYear() - 2000
	//&& Integer.parseInt(x.getExpirationDate().split("/")[0]) >= 1 && Integer.parseInt(x.getExpirationDate().split("/")[0]) <= 12).collect(Collectors.toList());
}
