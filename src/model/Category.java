package model;

import java.io.Serializable;

public class Category implements Serializable {
	
	private static final long serialVersionUID = 4954230693278879624L;

	private String description;
	private int catid ;
	
	

	public Category(String description , int catid) {
		setDescription(description );
		setCatid(catid);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getCatid() {
		return catid;
	}

	public void setCatid(int catid) {
		this.catid = catid;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
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
		Category other = (Category) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		return true;
	}

}
