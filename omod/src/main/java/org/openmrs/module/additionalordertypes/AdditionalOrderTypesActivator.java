package org.openmrs.module.additionalordertypes;

import org.openmrs.module.BaseModuleActivator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * OpenMRS lifecycle callbacks for the Additional Order Types module.
 */
public class AdditionalOrderTypesActivator extends BaseModuleActivator {
	
	private static final Logger log = LoggerFactory.getLogger(AdditionalOrderTypesActivator.class);
	
	@Override
	public void started() {
		log.info("Additional Order Types module loaded with procedure and medical supply order support");
	}
}
