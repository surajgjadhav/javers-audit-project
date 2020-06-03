package com.javers.test.vo;

import java.time.LocalDate;

/**
 * This is the VO class for Filter
 * 
 * @author SURAJ
 *
 */
public class FilterVO {

	private int limit;
	private int skip;
	private String author;
	private String changedProperty;
	private LocalDate fromDate;
	private LocalDate toDate;

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getSkip() {
		return skip;
	}

	public void setSkip(int skip) {
		this.skip = skip;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getChangedProperty() {
		return changedProperty;
	}

	public void setChangedProperty(String changedProperty) {
		this.changedProperty = changedProperty;
	}

	public LocalDate getFromDate() {
		return fromDate;
	}

	public void setFromDate(LocalDate fromDate) {
		this.fromDate = fromDate;
	}

	public LocalDate getToDate() {
		return toDate;
	}

	public void setToDate(LocalDate toDate) {
		this.toDate = toDate;
	}

}
