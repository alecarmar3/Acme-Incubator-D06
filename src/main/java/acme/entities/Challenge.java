
package acme.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Length;

import acme.framework.datatypes.Money;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(indexes = {
	@Index(columnList = "deadline")
})
public class Challenge extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	@Length(max = 250)
	private String				title;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date				deadline;

	@Temporal(TemporalType.TIMESTAMP)
	@Past
	private Date				updateDate;

	@NotBlank
	@Length(max = 250)
	private String				description;

	@NotBlank
	@Length(max = 250)
	private String				expertGoal;

	@NotBlank
	@Length(max = 250)
	private String				averageGoal;

	@NotBlank
	@Length(max = 250)
	private String				rookieGoal;

	@NotNull
	@Valid
	private Money				expertReward;

	@NotNull
	@Valid
	private Money				averageReward;

	@NotNull
	@Valid
	private Money				rookieReward;

}
