
package acme.entities;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Dashboard implements Serializable {

	private static final long	serialVersionUID	= 1L;

	//To list

	Integer						totalNotices;
	Integer						totalTechnologyRecords;
	Integer						totalToolRecords;
	Double						minimumMoneyIntervalActiveInquires;
	Double						maximumMoneyIntervalActiveInquires;
	Double						averageMoneyIntervalActiveInquires;
	Double						deviationMoneyIntervalActiveInquires;
	Double						minimumMoneyIntervalActiveOvertures;
	Double						maximumMoneyIntervalActiveOvertures;
	Double						averageMoneyIntervalActiveOvertures;
	Double						deviationMoneyIntervalActiveOvertures;

	//Charts
	//Integer						totalTechnologiesByTechnologySector;
	//	Integer						totalTechnologiesByScienceSector;
	//	Integer						totalTechnologiesByArtsSector;
	//	Integer						totalTechnologiesByBusinessSector;
	//	Integer						totalTechnologiesByHealthSector;
	//Integer[]					totalToolsByActivitySector;

	//Ratios

	//Double[]					openSourceTechnologiesVSclosedSourceTechnologies;
	//Double[]					openSourceToolsVSclosedSourceTools;

}
