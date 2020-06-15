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
	JaversFilterService javersFilterService;

	/**
	 * This method is used to get Shadows of all Objects
	 * 
	 * @return
	 */
	public String getShadowsOnAnyObject() {
		QueryBuilder jqlQuery = QueryBuilder.anyDomainObject();
		List<Shadow<Object>> shadows = javers.findShadows(jqlQuery.build());
		return javers.getJsonConverter().toJson(shadows);
	}

	/**
	 * This method is used to get Shadows of all Objects using filter
	 * 
	 * @return
	 */
	public String getShadowsOnAnyObjectUsingFilter(final FilterVO filterVO) {
		QueryBuilder jqlQuery = QueryBuilder.anyDomainObject();
		jqlQuery = javersFilterService.addFilters(jqlQuery, filterVO);
		List<Shadow<Object>> shadows = javers.findShadows(jqlQuery.build());
		return javers.getJsonConverter().toJson(shadows);
	}
	
	/**
	 * This method is used to get shadows for given class
	 * 
	 * @param cls
	 * @return
	 */
	public String getShadowsByClass(final Class cls) {
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
	public String getShadowsByEntity(final Class cls, final int id) {
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
	public String getShadowsByValueObject(final Class cls, final int id, final String property) {
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
	public String getShadowsByClassUsingFilter(final Class cls, final FilterVO filterVO) {
		QueryBuilder jqlQuery = QueryBuilder.byClass(cls);
		jqlQuery = javersFilterService.addFilters(jqlQuery, filterVO);
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
	public String getShadowsByEntityUsingFilter(final Class cls, final int id, final FilterVO filterVO) {
		QueryBuilder jqlQuery = QueryBuilder.byInstanceId(id, cls);
		jqlQuery = javersFilterService.addFilters(jqlQuery, filterVO);
		List<Shadow<Object>> shadows = javers.findShadows(jqlQuery.build());
		return javers.getJsonConverter().toJson(shadows);
	}
}
