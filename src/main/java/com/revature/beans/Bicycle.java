package com.revature.beans;

public class Bicycle {
	private Integer id;
	private String Reference;
	private String color;
	private Float size;
	private Float price;
	private String brand;
	private String description;
	//private Category category;
	//private Float maxSpeed;
	private Status status;
	
	
	public Bicycle() {
		id = 1;
		Reference = "";
		color = "";
		size = 0f;
		price = 0f;
		brand = "";
		description = "";
		//maxSpeed = 0f;
		status = new Status();
		
	}


	

	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getReference() {
		return Reference;
	}


	public void setReference(String reference) {
		Reference = reference;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getColor() {
		return color;
	}


	public void setColor(String color) {
		this.color = color;
	}


	public Float getSize() {
		return size;
	}


	public void setSize(Float size) {
		this.size = size;
	}


	public Float getPrice() {
		return price;
	}


	public void setPrice(Float price) {
		this.price = price;
	}


	public String getBrand() {
		return brand;
	}


	public void setBrand(String brand) {
		this.brand = brand;
	}


	public Status getStatus() {
		return status;
	}


	public void setStatus(Status status) {
		this.status = status;
	}
	
	
	@Override
	public String toString() {
		return "Bicycle [id=" + id + ", Reference=" + Reference + ", description=" + description + ", color=" + color
				+ ", size=" + size + ", price=" + price + ", brand=" + brand + ", status=" + status + "]";
	}




	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Reference == null) ? 0 : Reference.hashCode());
		result = prime * result + ((brand == null) ? 0 : brand.hashCode());
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((size == null) ? 0 : size.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		Bicycle other = (Bicycle) obj;
		if (Reference == null) {
			if (other.Reference != null)
				return false;
		} else if (!Reference.equals(other.Reference))
			return false;
		if (brand == null) {
			if (other.brand != null)
				return false;
		} else if (!brand.equals(other.brand))
			return false;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (size == null) {
			if (other.size != null)
				return false;
		} else if (!size.equals(other.size))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

	
	
	
}
