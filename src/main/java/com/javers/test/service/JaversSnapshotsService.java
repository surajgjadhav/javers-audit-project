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
	JaversFilterService javersFilterService;

	/**
	 * This method is used to get snapshots of all objects
	 * 
	 * @return
	 */
	public String getSnapshotsOnAnyObject() {
		QueryBuilder jqlQuery = QueryBuilder.anyDomainObject();
		List<CdoSnapshot> snapshots = javers.findSnapshots(jqlQuery.build());
		return javers.getJsonConverter().toJson(snapshots);
	}

	/**
	 * This method is used to get snapshots on all objects using filter
	 * 
	 * @param filterVO
	 * @return
	 */
	public String getSnapshotsOnAnyObjectUsingFilter(final FilterVO filterVO) {
		QueryBuilder jqlQuery = QueryBuilder.anyDomainObject();
		jqlQuery = javersFilterService.addFilters(jqlQuery, filterVO);
		List<CdoSnapshot> snapshots = javers.findSnapshots(jqlQuery.build());
		return javers.getJsonConverter().toJson(snapshots);
	}

	/**
	 * This method is used to get snapshots of particular class
	 * 
	 * @param cls
	 * @return
	 */
	public String getSnapshotsByClass(final Class cls) {
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
	public String getSnapshotsByEntity(final Class cls, final int id) {
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
	public String getSnapshotsByValueObject(final Class cls, final int id, final String property) {
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
	public String getSnapshotsByClassUsingFilter(final Class cls, final FilterVO filterVO) {
		QueryBuilder jqlQuery = QueryBuilder.byClass(cls);
		jqlQuery = javersFilterService.addFilters(jqlQuery, filterVO);
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
	public String getSnapshotsByEntityUsingFilter(final Class cls, final int id, final FilterVO filterVO) {
		QueryBuilder jqlQuery = QueryBuilder.byInstanceId(id, cls);
		jqlQuery = javersFilterService.addFilters(jqlQuery, filterVO);
		List<CdoSnapshot> snapshots = javers.findSnapshots(jqlQuery.build());
		return javers.getJsonConverter().toJson(snapshots);
	}

}
