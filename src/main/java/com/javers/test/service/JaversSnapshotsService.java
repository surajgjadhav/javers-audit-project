package com.javers.test.service;

import java.util.List;

import org.javers.core.Javers;
import org.javers.core.metamodel.object.CdoSnapshot;
import org.javers.repository.jql.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javers.test.vo.FilterVO;

/**
 * This class is used to get Javers Snapshots View
 * 
 * @author SURAJ
 *
 */
@Service
public class JaversSnapshotsService {

	private final Javers javers;

	@Autowired
	public JaversSnapshotsService(Javers javers) {
		this.javers = javers;
	}

	@Autowired
	JaversFilterService javersFliFilterService;

	/**
	 * This method is used to get snapshots of any object
	 * 
	 * @return
	 */
	public String getSnapshotsOnAnyObject() {
		QueryBuilder jqlQuery = QueryBuilder.anyDomainObject();
		List<CdoSnapshot> snapshots = javers.findSnapshots(jqlQuery.build());
		return javers.getJsonConverter().toJson(snapshots);
	}

	/**
	 * This method is used to get snapshots of particular class
	 * 
	 * @param cls
	 * @return
	 */
	public String getSnapshotsByClass(Class cls) {
		QueryBuilder jqlQuery = QueryBuilder.byClass(cls);
		List<CdoSnapshot> snapshots = javers.findSnapshots(jqlQuery.build());
		return javers.getJsonConverter().toJson(snapshots);
	}

	/**
	 * This method is used to get snapshots of particular entity
	 * 
	 * @param cls
	 * @param id
	 * @return
	 */
	public String getSnapshotsByEntity(Class cls, int id) {
		QueryBuilder jqlQuery = QueryBuilder.byInstanceId(id, cls);
		List<CdoSnapshot> snapshots = javers.findSnapshots(jqlQuery.build());
		return javers.getJsonConverter().toJson(snapshots);
	}

	/**
	 * This method is used to get snapshots on Value Object
	 * 
	 * @param cls
	 * @param id
	 * @param property
	 * @return
	 */
	public String getSnapshotsByValueObject(Class cls, int id, String property) {
		QueryBuilder jqlQuery = null;
		if (id > 0) {
			jqlQuery = QueryBuilder.byValueObjectId(id, cls, property);
		} else {
			jqlQuery = QueryBuilder.byValueObject(cls, property);
		}
		List<CdoSnapshot> snapshots = javers.findSnapshots(jqlQuery.build());
		return javers.getJsonConverter().toJson(snapshots);
	}

	/**
	 * This method is used to get snapshots of given class using filters
	 * 
	 * @param cls
	 * @param filterVO
	 * @return
	 */
	public String getSnapshotsByClassUsingFilter(Class cls, FilterVO filterVO) {
		QueryBuilder jqlQuery = QueryBuilder.byClass(cls);
		jqlQuery = javersFliFilterService.addFilters(jqlQuery, filterVO);
		List<CdoSnapshot> snapshots = javers.findSnapshots(jqlQuery.build());
		return javers.getJsonConverter().toJson(snapshots);
	}

	/**
	 * This method is used to get snapshots of given entity using filters
	 * 
	 * @param cls
	 * @param id
	 * @param filterVO
	 * @return
	 */
	public String getSnapshotsByEntityUsingFilter(Class cls, int id, FilterVO filterVO) {
		QueryBuilder jqlQuery = QueryBuilder.byInstanceId(id, cls);
		jqlQuery = javersFliFilterService.addFilters(jqlQuery, filterVO);
		List<CdoSnapshot> snapshots = javers.findSnapshots(jqlQuery.build());
		return javers.getJsonConverter().toJson(snapshots);
	}

}
