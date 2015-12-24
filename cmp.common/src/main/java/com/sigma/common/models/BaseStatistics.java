package com.sigma.common.models;

public class BaseStatistics  implements java.io.Serializable{
	

	private int totalCount;

	private int totalPeopleCount;
	
	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getTotalPeopleCount() {
		return totalPeopleCount;
	}

	public void setTotalPeopleCount(int totalPeopleCount) {
		this.totalPeopleCount = totalPeopleCount;
	}


}
