package com.javers.test.service;

import org.javers.repository.jql.QueryBuilder;
import org.springframework.stereotype.Service;

import com.javers.test.vo.FilterVO;

/**
 * This class is used to set filter to Javers Query
 * 
 * @author SURAJ
 *
 */
@Service
public class JaversFilterService {

	/**
	 * This method is used to add filters to the JQL Query
	 * 
	 * @param jqlQuery
	 * @param filterVO
	 * @return
	 */
	public QueryBuilder addFilters(QueryBuilder jqlQuery, FilterVO filterVO) {
		if (filterVO.getLimit() > 0) {
			jqlQuery.limit(filterVO.getLimit());
		}
		if (!filterVO.getAuthor().isEmpty()) {
			jqlQuery.byAuthor(filterVO.getAuthor());
		}
		if (!filterVO.getChangedProperty().isEmpty()) {
			jqlQuery.withChangedProperty(filterVO.getChangedProperty());
		}
		if (null != filterVO.getFromDate()) {
			jqlQuery.from(filterVO.getFromDate());
		}
		if (null != filterVO.getToDate()) {
			jqlQuery.to(filterVO.getToDate());
		}
		return jqlQuery;
	}
}
