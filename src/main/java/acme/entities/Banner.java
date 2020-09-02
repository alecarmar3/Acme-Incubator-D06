
package acme.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Banner extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	@URL
	@Length(max = 250)
	private String				picture;

	@NotBlank
	@Length(max = 250)
	private String				slogan;

	@Temporal(TemporalType.TIMESTAMP)
	@Past
	private Date				updateDate;

	@NotBlank
	@URL
	@Length(max = 250)
	private String				targetUrl;

	@NotBlank
	@CreditCardNumber
	private String				creditCardNumber; //number

	@NotBlank
	@Length(max = 250)
	private String				holderName;

	@NotBlank
	@Length(max = 250)
	private String				brand;

	@Pattern(regexp = "^\\d{2}\\/\\d{2}$", message = "{default.banner.error.expiration-date-pattern}")
	@NotBlank
	private String				expirationDate;

	@Pattern(regexp = "^[0-9]{3}$", message = "{default.banner.error.cvv-pattern}")
	@NotBlank
	private String				cvv;

}
