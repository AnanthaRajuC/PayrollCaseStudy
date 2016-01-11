package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification;

import java.util.Collection;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.DateInterval;

public abstract class HourlyPaymentClassification extends PaymentClassification {
	// Business rule constants 
	// (can be refactored to configurable later whenever business requests it) 
	public static final int DAILY_NORMAL_HOURS = 8;
	public static final double OVERTIME_WAGE_MULTIPLIER = 1.5d;

	public abstract void setHourlyWage(int hourlyWage);
	public abstract int getHourlyWage();

	public abstract void addTimeCard(TimeCard timeCard);
	public abstract Collection<TimeCard> getTimeCardsIn(DateInterval payInterval);

	@Override
	public <T> T accept(PaymentClassificationVisitor<T> visitor) {
		return visitor.visit(this);
	}
	
	@Override
	public int calculateAmount(DateInterval payInterval) {
		return getTimeCardsIn(payInterval).stream()
				.mapToInt(timeCard -> calculateAmount(timeCard))
				.sum();
	}

	private int calculateAmount(TimeCard timeCard) {
		SeparatedHours hours = SeparatedHours.of(timeCard.getWorkingHourQty());
		return calculateNormalTimeAmount(hours.normalHours) + calculateOvertimeAmount(hours.overTimeHours);
	}
	
	private int calculateNormalTimeAmount(int normalHours) {
		return normalHours * getHourlyWage();
	}

	private int calculateOvertimeAmount(int overTimeHours) {
		return (int)(overTimeHours * (getHourlyWage() * OVERTIME_WAGE_MULTIPLIER));
	}
	//TODO: Overengineered!
	private static class SeparatedHours {
		public final int normalHours;
		public final int overTimeHours;
		public SeparatedHours(int normalHours, int overTimeHours) {
			this.normalHours = normalHours;
			this.overTimeHours = overTimeHours;
		}
		public static SeparatedHours of(int workingHourQty) {
			if(workingHourQty <= DAILY_NORMAL_HOURS) {
				return new SeparatedHours(workingHourQty, 0);
			} else {
				return new SeparatedHours(DAILY_NORMAL_HOURS, workingHourQty - DAILY_NORMAL_HOURS);
			}
		}
	}

}
