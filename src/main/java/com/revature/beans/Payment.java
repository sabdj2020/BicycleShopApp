package com.revature.beans;

import java.sql.Date;

public class Payment {
	private Integer id;
	private Float amount;
	private Float remainingPayment;
	Offer o = new Offer();
	private Boolean payed;
	private Integer weeksRemaining;
	
	
	public Payment() {
		id = 1;
		amount = 0f;
		remainingPayment = 0f;
		Offer o = new Offer();
		weeksRemaining=0;
	}
	
	


	public Boolean getPayed() {
		return payed;
	}




	public void setPayed(Boolean payed) {
		this.payed = payed;
	}




	public Integer getWeeksRemaining() {
		return weeksRemaining;
	}




	public void setWeeksRemaining(Integer weeksRemaining) {
		this.weeksRemaining = weeksRemaining;
	}




	public Integer getId() {
		return id;
	}




	public void setId(Integer id) {
		this.id = id;
	}




	public Float getAmount() {
		return amount;
	}




	public void setAmount(Float amount) {
		this.amount = amount;
	}




	public Float getRemainingPayment() {
		return remainingPayment;
	}




	public void setRemainingPayment(Float remainingPayment) {
		this.remainingPayment = remainingPayment;
	}




	public Offer getO() {
		return o;
	}




	public void setO(Offer o) {
		this.o = o;
	}




	@Override
	public String toString() {
		return "Payment [id=" + id + ", amount=" + amount + ", remainingPayment=" + remainingPayment + ", o=" + o + "]";
	}




	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((o == null) ? 0 : o.hashCode());
		result = prime * result + ((remainingPayment == null) ? 0 : remainingPayment.hashCode());
		return result;
	}




	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Payment other = (Payment) obj;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (o == null) {
			if (other.o != null)
				return false;
		} else if (!o.equals(other.o))
			return false;
		if (remainingPayment == null) {
			if (other.remainingPayment != null)
				return false;
		} else if (!remainingPayment.equals(other.remainingPayment))
			return false;
		return true;
	}
	
	
	
}
