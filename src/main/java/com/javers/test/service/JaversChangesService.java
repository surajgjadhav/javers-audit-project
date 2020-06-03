package com.javers.test.service;

import java.util.List;

import org.javers.core.Javers;
import org.javers.core.diff.Change;
import org.javers.repository.jql.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javers.test.vo.FilterVO;

/**
 * This class is used to get Javers Changes View
 * 
 * @author SURAJ
 *
 */
@Service
public class JaversChangesService {

	private final Javers javers;

	@Autowired
	public JaversChangesService(Javers javers) {
		this.javers = javers;
	}

	@Autowired
	JaversFilterService javersFliFilterService;

	/**
	 * This method is used to get changes on any object
	 * 
	 * @return
	 */
	public String getChangesOnAnyObject() {
		QueryBuilder jqlQuery = QueryBuilder.anyDomainObject();
		List<Change> changes = javers.findChanges(jqlQuery.build());
		return javers.getJsonConverter().toJson(changes);
	}

	/**
	 * This method is used to get changes for given class
	 * 
	 * @param cls
	 * @return
	 */
	public String getChangesByClass(Class cls) {
		QueryBuilder jqlQuery = QueryBuilder.byClass(cls);
		List<Change> changes = javers.findChanges(jqlQuery.build());
		return javers.getJsonConverter().toJson(changes);
	}

	/**
	 * This method is used to get changes for given entity
	 * 
	 * @param cls
	 * @param id
	 * @return
	 */
	public String getChangesByEntity(Class cls, int id) {
		QueryBuilder jqlQuery = QueryBuilder.byInstanceId(id, cls);
		List<Change> changes = javers.findChanges(jqlQuery.build());
		return javers.getJsonConverter().toJson(changes);
	}

	/**
	 * This method is used to get changes on value object
	 * 
	 * @param cls
	 * @param id
	 * @param property
	 * @return
	 */
	public String getChangesByValueObject(Class cls, int id, String property) {
		QueryBuilder jqlQuery = null;
		if (id > 0) {
			jqlQuery = QueryBuilder.byValueObjectId(id, cls, property);
		} else {
			jqlQuery = QueryBuilder.byValueObject(cls, property);
		}
		List<Change> changes = javers.findChanges(jqlQuery.build());
		return javers.getJsonConverter().toJson(changes);
	}

	/**
	 * This method is used to get changes on class by using filters
	 * 
	 * @param cls
	 * @param filterVO
	 * @return
	 */
	public String getChangesByClassUsingFilter(Class cls, FilterVO filterVO) {
		QueryBuilder jqlQuery = QueryBuilder.byClass(cls);
		jqlQuery = javersFliFilterService.addFilters(jqlQuery, filterVO);
		List<Change> changes = javers.findChanges(jqlQuery.build());
		return javers.getJsonConverter().toJson(changes);
	}

	/**
	 * This method is used to get changes on entity using filter
	 * 
	 * @param cls
	 * @param id
	 * @param filterVO
	 * @return
	 */
	public String getChangesByEntityUsingFilter(Class cls, int id, FilterVO filterVO) {
		QueryBuilder jqlQuery = QueryBuilder.byInstanceId(id, cls);
		jqlQuery = javersFliFilterService.addFilters(jqlQuery, filterVO);
		List<Change> changes = javers.findChanges(jqlQuery.build());
		return javers.getJsonConverter().toJson(changes);
	}
}
