package hu.daniel.hari.testthis.data.jpa.model.paymentclassification;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

import hu.daniel.hari.testthis.data.jpa.model.paymentclassification.hourly.JPATimeCard;

@Entity
public class HourlyJPAPaymentClassification extends JPAPaymentClassification {

	private int hourlyWage;

	@OneToMany(mappedBy="hourlyJPAPaymentClassification", orphanRemoval=true, cascade= {CascadeType.PERSIST, CascadeType.REMOVE})
	@PrimaryKeyJoinColumn
	private Set<JPATimeCard> jpaTimeCards = new HashSet<>();
	
	public HourlyJPAPaymentClassification() {}
	public HourlyJPAPaymentClassification(int hourlyWage) {
		this.hourlyWage = hourlyWage;
	}

	public int getHourlyWage() {
		return hourlyWage;
	}

	public void setHourlyWage(int hourlyWage) {
		this.hourlyWage = hourlyWage;
	}

	public void addJPATimeCard(JPATimeCard jpaTimeCard) {
		jpaTimeCard.connect(this);
		jpaTimeCards.add(jpaTimeCard);
	}

}
