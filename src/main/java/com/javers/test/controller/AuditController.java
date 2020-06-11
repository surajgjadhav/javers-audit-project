package com.javers.test.controller;

import org.javers.core.Javers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javers.test.constants.ErrorConstants;
import com.javers.test.constants.JaversConstants;
import com.javers.test.service.JaversChangesService;
import com.javers.test.service.JaversShadowsService;
import com.javers.test.service.JaversSnapshotsService;
import com.javers.test.vo.FilterVO;

@RestController
@RequestMapping(value = "/audit")
public class AuditController {

	@Autowired
	JaversSnapshotsService javersSnapshotsService;

	@Autowired
	JaversShadowsService javersShadowsService;

	@Autowired
	JaversChangesService javersChangesService;

	/**
	 * This method is used to get snapshot,changes or shadows on any object
	 * 
	 * @param dataType
	 * @return
	 */
	@RequestMapping("/{dataType}")
	public String getQueryType(@PathVariable final String dataType) {
		String result;
		switch (dataType.toLowerCase()) {
		case JaversConstants.SNAPSHOTS:
			result = javersSnapshotsService.getSnapshotsOnAnyObject();
			break;
		case JaversConstants.CHANGES:
			result = javersChangesService.getChangesOnAnyObject();
			break;
		case JaversConstants.SHADOWS:
			result = javersShadowsService.getShadowsOnAnyObject();
			break;

		default:
			result = ErrorConstants.ERROR_OCCOURED;
			break;
		}
		return result;

	}

	/**
	 * This method is used to get snapshot,changes or shadows on any object using
	 * filters
	 * 
	 * @param dataType
	 * @param filterVO
	 * @return
	 */
	@RequestMapping(value = "/{dataType}/filter", method = RequestMethod.PUT)
	public String getQueryTypeWithFilter(@PathVariable final String dataType, @RequestBody FilterVO filterVO) {
		String result;
		switch (dataType.toLowerCase()) {
		case JaversConstants.SNAPSHOTS:
			result = javersSnapshotsService.getSnapshotsOnAnyObjectUsingFilter(filterVO);
			break;
		case JaversConstants.CHANGES:
			result = javersChangesService.getChangesOnAnyObjectUsingFilter(filterVO);
			break;
		case JaversConstants.SHADOWS:
			result = javersShadowsService.getShadowsOnAnyObject();
			break;

		default:
			result = ErrorConstants.ERROR_OCCOURED;
			break;
		}
		return result;
	}

	/**
	 * This method is used to get snapshot,changes or shadows on particular entity
	 * 
	 * @param dataType
	 * @param className
	 * @param id
	 * @return
	 * @throws ClassNotFoundException
	 */
	@RequestMapping("/{dataType}/entities/{className}/{id}")
	public String getEntityResult(@PathVariable final String dataType, @PathVariable final String className,
			@PathVariable final int id) throws ClassNotFoundException {
		Class cls = Class.forName(className);
		String result;
		switch (dataType.toLowerCase()) {
		case JaversConstants.SNAPSHOTS:
			result = javersSnapshotsService.getSnapshotsByEntity(cls, id);
			break;
		case JaversConstants.CHANGES:
			result = javersChangesService.getChangesByEntity(cls, id);
			break;
		case JaversConstants.SHADOWS:
			result = javersShadowsService.getShadowsByEntity(cls, id);
			break;

		default:
			result = ErrorConstants.ERROR_OCCOURED;
			break;
		}
		return result;

	}

	/**
	 * This method is used to get snapshot,changes or shadows on class
	 * 
	 * @param dataType
	 * @param className
	 * @return
	 * @throws ClassNotFoundException
	 */
	@RequestMapping("/{dataType}/class/{className}")
	public String getClassResult(@PathVariable final String dataType, @PathVariable final String className)
			throws ClassNotFoundException {
		Class cls = Class.forName(className);
		String result;
		switch (dataType.toLowerCase()) {
		case JaversConstants.SNAPSHOTS:
			result = javersSnapshotsService.getSnapshotsByClass(cls);
			break;
		case JaversConstants.CHANGES:
			result = javersChangesService.getChangesByClass(cls);
			break;
		case JaversConstants.SHADOWS:
			result = javersShadowsService.getShadowsByClass(cls);
			break;

		default:
			result = ErrorConstants.ERROR_OCCOURED;
			break;
		}
		return result;

	}

	/**
	 * This method is used to get snapshot,changes or shadows for particular value
	 * changes
	 * 
	 * @param dataType
	 * @param className
	 * @param property
	 * @param id
	 * @return
	 * @throws ClassNotFoundException
	 */
	@RequestMapping("/{dataType}/valueObject/{className}/{property}")
	public String getValuObjectResult(@PathVariable final String dataType, @PathVariable final String className,
			@PathVariable final String property, @RequestParam(name = "id", defaultValue = "0") final int id)
			throws ClassNotFoundException {
		Class cls = Class.forName(className);
		String result;
		switch (dataType.toLowerCase()) {
		case JaversConstants.SNAPSHOTS:
			result = javersSnapshotsService.getSnapshotsByValueObject(cls, id, property);
			break;
		case JaversConstants.CHANGES:
			result = javersChangesService.getChangesByValueObject(cls, id, property);
			break;
		case JaversConstants.SHADOWS:
			result = javersShadowsService.getShadowsByValueObject(cls, id, property);
			break;

		default:
			result = ErrorConstants.ERROR_OCCOURED;
			break;
		}
		return result;

	}

	/**
	 * This method is used to get snapshot,changes or shadows on class using filters
	 * 
	 * @param dataType
	 * @param className
	 * @param filterVO
	 * @return
	 * @throws ClassNotFoundException
	 */
	@RequestMapping(value = "/{dataType}/class/{className}/filter", method = RequestMethod.PUT)
	public String getClassResultUsingFilter(@PathVariable final String dataType, @PathVariable final String className,
			@RequestBody FilterVO filterVO) throws ClassNotFoundException {
		Class cls = Class.forName(className);
		String result;
		switch (dataType.toLowerCase()) {
		case JaversConstants.SNAPSHOTS:
			result = javersSnapshotsService.getSnapshotsByClassUsingFilter(cls, filterVO);
			break;
		case JaversConstants.CHANGES:
			result = javersChangesService.getChangesByClassUsingFilter(cls, filterVO);
			break;
		case JaversConstants.SHADOWS:
			result = javersShadowsService.getShadowsByClassUsingFilter(cls, filterVO);
			break;

		default:
			result = ErrorConstants.ERROR_OCCOURED;
			break;
		}
		return result;

	}

	/**
	 * This method is used to get snapshot,changes or shadows on particular entity
	 * with filters
	 * 
	 * @param dataType
	 * @param className
	 * @param id
	 * @param filterVO
	 * @return
	 * @throws ClassNotFoundException
	 */
	@RequestMapping(value = "/{dataType}/entities/{className}/{id}/filter", method = RequestMethod.PUT)
	public String getEntityResultUsingFilter(@PathVariable final String dataType, @PathVariable final String className,
			@PathVariable final int id, @RequestBody FilterVO filterVO) throws ClassNotFoundException {
		Class cls = Class.forName(className);
		String result;
		switch (dataType.toLowerCase()) {
		case JaversConstants.SNAPSHOTS:
			result = javersSnapshotsService.getSnapshotsByEntityUsingFilter(cls, id, filterVO);
			break;
		case JaversConstants.CHANGES:
			result = javersChangesService.getChangesByEntityUsingFilter(cls, id, filterVO);
			break;
		case JaversConstants.SHADOWS:
			result = javersShadowsService.getShadowsByEntityUsingFilter(cls, id, filterVO);
			break;

		default:
			result = ErrorConstants.ERROR_OCCOURED;
			break;
		}
		return result;

	}

}
