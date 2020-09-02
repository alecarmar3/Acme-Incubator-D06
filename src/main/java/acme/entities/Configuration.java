
package acme.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.Transient;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Configuration extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	@Length(max = 250)
	private String				spamWords;

	@Range(min = 0, max = 1)
	@Digits(integer = 1, fraction = 3)
	private Double				spamThreshold;

	@NotBlank
	@Length(max = 250)
	private String				activitySectors;


	@Transient
	public List<String> activitySectorsToList() {
		List<String> activitySectorsToList = new ArrayList<String>();

		activitySectorsToList = Arrays.asList(this.activitySectors.split(", "));

		return activitySectorsToList;
	}

	@Transient
	public boolean isSpam(final String text) {
		boolean isSpam = false;

		List<String> splitted = Arrays.asList(text.split("\\s|\\p{Punct}"));

		splitted = splitted.stream().filter(s -> s != "").collect(Collectors.toList());

		int n = splitted.size();
		int count = 0;

		String[] spam = this.spamWords.toLowerCase().split(",");

		String lowerCaseString = text.toLowerCase().replaceAll("\\s+{2,}", " ");

		for (String s : spam) {
			s = s.trim();
			count += org.apache.commons.lang3.StringUtils.countMatches(lowerCaseString, s);
			isSpam = count >= this.spamThreshold * n;
			if (isSpam) {
				break;
			}
		}

		return isSpam;
	}

}
