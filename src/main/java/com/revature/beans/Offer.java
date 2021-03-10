package com.revature.beans;

import java.sql.Date;

public class Offer {
	Integer id;
	private Float price;
	private Date date;
	private Bicycle bicycle;
	private User user;
	private OfferStatus offerStatus;
	private Boolean payed;
	
	
	
	
	public Offer() {
		
		id = 1;
		price = 25f;
		
		bicycle =new Bicycle() ;
		user = new User();
		offerStatus = new OfferStatus();
		payed=false;
	}
	
	
	public Boolean getPayed() {
		return payed;
	}


	public void setPayed(Boolean payed) {
		this.payed = payed;
	}


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Bicycle getBicycle() {
		return bicycle;
	}
	public void setBicycle(Bicycle bicycle) {
		this.bicycle = bicycle;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public OfferStatus getOfferStatus() {
		return offerStatus;
	}
	public void setOfferStatus(OfferStatus offerStatus) {
		this.offerStatus = offerStatus;
	}
	@Override
	public String toString() {
		return "Offer [id=" + id + ", price=" + price + ", date=" + date + ", bicycle=" + bicycle + ", user=" + user
				+ ", offerStatus=" + offerStatus + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bicycle == null) ? 0 : bicycle.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((offerStatus == null) ? 0 : offerStatus.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		Offer other = (Offer) obj;
		if (bicycle == null) {
			if (other.bicycle != null)
				return false;
		} else if (!bicycle.equals(other.bicycle))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (offerStatus == null) {
			if (other.offerStatus != null)
				return false;
		} else if (!offerStatus.equals(other.offerStatus))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
	
	
	
	
}
