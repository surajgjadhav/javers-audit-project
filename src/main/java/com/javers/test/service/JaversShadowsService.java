package com.javers.test.service;

import java.util.List;

import org.javers.core.Javers;
import org.javers.repository.jql.QueryBuilder;
import org.javers.shadow.Shadow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javers.test.vo.FilterVO;

/**
 * This class is used to get Javers Shadow View
 * 
 * @author SURAJ
 *
 */
@Service
public class JaversShadowsService {

	private final Javers javers;

	@Autowired
	public JaversShadowsService(Javers javers) {
		this.javers = javers;
	}

	@Autowired
	JaversFilterService javersFliFilterService;

	/**
	 * This method is used to get Shadows of any Object
	 * 
	 * @return
	 */
	public String getShadowsOnAnyObject() {
		QueryBuilder jqlQuery = QueryBuilder.anyDomainObject();
		List<Shadow<Object>> shadows = javers.findShadows(jqlQuery.build());
		return javers.getJsonConverter().toJson(shadows);
	}

	/**
	 * This method is used to get shadows for given class
	 * 
	 * @param cls
	 * @return
	 */
	public String getShadowsByClass(Class cls) {
		QueryBuilder jqlQuery = QueryBuilder.byClass(cls);
		List<Shadow<Object>> shadows = javers.findShadows(jqlQuery.build());
		return javers.getJsonConverter().toJson(shadows);
	}

	/**
	 * This method is used to get shadows of particular entity
	 * 
	 * @param cls
	 * @param id
	 * @return
	 */
	public String getShadowsByEntity(Class cls, int id) {
		QueryBuilder jqlQuery = QueryBuilder.byInstanceId(id, cls);
		List<Shadow<Object>> shadows = javers.findShadows(jqlQuery.build());
		return javers.getJsonConverter().toJson(shadows);
	}

	/**
	 * This method is used to get shadows on Value Objects
	 * 
	 * @param cls
	 * @param id
	 * @param property
	 * @return
	 */
	public String getShadowsByValueObject(Class cls, int id, String property) {
		QueryBuilder jqlQuery = null;
		if (id > 0) {
			jqlQuery = QueryBuilder.byValueObjectId(id, cls, property);
		} else {
			jqlQuery = QueryBuilder.byValueObject(cls, property);
		}
		List<Shadow<Object>> shadows = javers.findShadows(jqlQuery.build());
		return javers.getJsonConverter().toJson(shadows);
	}

	/**
	 * This method is used to get shadows for given class using filter
	 * 
	 * @param cls
	 * @param filterVO
	 * @return
	 */
	public String getShadowsByClassUsingFilter(Class cls, FilterVO filterVO) {
		QueryBuilder jqlQuery = QueryBuilder.byClass(cls);
		jqlQuery = javersFliFilterService.addFilters(jqlQuery, filterVO);
		List<Shadow<Object>> shadows = javers.findShadows(jqlQuery.build());
		return javers.getJsonConverter().toJson(shadows);
	}

	/**
	 * This method is used to get shadows of particular entity using filters
	 * 
	 * @param cls
	 * @param id
	 * @param filterVO
	 * @return
	 */
	public String getShadowsByEntityUsingFilter(Class cls, int id, FilterVO filterVO) {
		QueryBuilder jqlQuery = QueryBuilder.byInstanceId(id, cls);
		jqlQuery = javersFliFilterService.addFilters(jqlQuery, filterVO);
		List<Shadow<Object>> shadows = javers.findShadows(jqlQuery.build());
		return javers.getJsonConverter().toJson(shadows);
	}
}
