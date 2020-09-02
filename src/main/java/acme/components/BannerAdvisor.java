
package acme.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import acme.entities.Banner;

@ControllerAdvice
public class BannerAdvisor {

	@Autowired
	private BannerRepository repository;


	@ModelAttribute("banner")
	public Banner getBanner() {

		Banner result = this.repository.findRandomBanner();

		if (!this.repository.isValidBanner(result)) {
			return this.getBanner();
		} else {
			return result;
		}
	}
}
