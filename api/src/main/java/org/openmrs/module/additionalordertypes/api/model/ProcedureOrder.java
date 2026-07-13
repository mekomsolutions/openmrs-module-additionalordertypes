package org.openmrs.module.additionalordertypes.api.model;

import org.openmrs.Concept;
import org.openmrs.ServiceOrder;

/**
 * An order for a clinical or diagnostic procedure.
 */
public class ProcedureOrder extends ServiceOrder {
	
	private static final long serialVersionUID = 1L;
	
	private Concept specimenType;
	
	private Concept bodySite;
	
	private Concept category;
	
	private ProcedureOrder relatedProcedure;
	
	@Override
	public ProcedureOrder copy() {
		ProcedureOrder target = new ProcedureOrder();
		copyHelper(target);
		copyProcedureOrderFields(target);
		return target;
	}
	
	@Override
	public ProcedureOrder cloneForDiscontinuing() {
		ProcedureOrder target = new ProcedureOrder();
		cloneForDiscontinuingHelper(target);
		return target;
	}
	
	@Override
	public ProcedureOrder cloneForRevision() {
		ProcedureOrder target = new ProcedureOrder();
		cloneForRevisionHelper(target);
		copyProcedureOrderFields(target);
		return target;
	}
	
	private void copyProcedureOrderFields(ProcedureOrder target) {
		target.setSpecimenType(getSpecimenType());
		target.setBodySite(getBodySite());
		target.setCategory(getCategory());
		target.setRelatedProcedure(getRelatedProcedure());
	}
	
	public Concept getSpecimenType() {
		return specimenType;
	}
	
	public void setSpecimenType(Concept specimenType) {
		this.specimenType = specimenType;
	}
	
	public Concept getBodySite() {
		return bodySite;
	}
	
	public void setBodySite(Concept bodySite) {
		this.bodySite = bodySite;
	}
	
	public Concept getCategory() {
		return category;
	}
	
	public void setCategory(Concept category) {
		this.category = category;
	}
	
	public ProcedureOrder getRelatedProcedure() {
		return relatedProcedure;
	}
	
	public void setRelatedProcedure(ProcedureOrder relatedProcedure) {
		this.relatedProcedure = relatedProcedure;
	}
}
