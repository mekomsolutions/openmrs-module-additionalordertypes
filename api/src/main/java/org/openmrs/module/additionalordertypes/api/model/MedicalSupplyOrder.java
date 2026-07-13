package org.openmrs.module.additionalordertypes.api.model;

import org.openmrs.Concept;
import org.openmrs.ServiceOrder;

/**
 * An order for a consumable or other medical supply.
 */
public class MedicalSupplyOrder extends ServiceOrder {
	
	private static final long serialVersionUID = 1L;
	
	private Double quantity;
	
	private String brandName;
	
	private Concept quantityUnits;
	
	private Integer medicalSuppliesInventoryId;
	
	@Override
	public MedicalSupplyOrder copy() {
		MedicalSupplyOrder target = new MedicalSupplyOrder();
		copyHelper(target);
		copyMedicalSupplyOrderFields(target);
		return target;
	}
	
	@Override
	public MedicalSupplyOrder cloneForDiscontinuing() {
		MedicalSupplyOrder target = new MedicalSupplyOrder();
		cloneForDiscontinuingHelper(target);
		return target;
	}
	
	@Override
	public MedicalSupplyOrder cloneForRevision() {
		MedicalSupplyOrder target = new MedicalSupplyOrder();
		cloneForRevisionHelper(target);
		copyMedicalSupplyOrderFields(target);
		return target;
	}
	
	private void copyMedicalSupplyOrderFields(MedicalSupplyOrder target) {
		target.setQuantity(getQuantity());
		target.setBrandName(getBrandName());
		target.setQuantityUnits(getQuantityUnits());
		target.setMedicalSuppliesInventoryId(getMedicalSuppliesInventoryId());
	}
	
	public Double getQuantity() {
		return quantity;
	}
	
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
	
	public String getBrandName() {
		return brandName;
	}
	
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	
	public Concept getQuantityUnits() {
		return quantityUnits;
	}
	
	public void setQuantityUnits(Concept quantityUnits) {
		this.quantityUnits = quantityUnits;
	}
	
	public Integer getMedicalSuppliesInventoryId() {
		return medicalSuppliesInventoryId;
	}
	
	public void setMedicalSuppliesInventoryId(Integer medicalSuppliesInventoryId) {
		this.medicalSuppliesInventoryId = medicalSuppliesInventoryId;
	}
}
