package org.openmrs.module.additionalordertypes.api.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;

import org.junit.Test;
import org.openmrs.Concept;
import org.openmrs.Order;

public class MedicalSupplyOrderTest {
	
	@Test
	public void copyShouldRetainMedicalSupplySpecificFields() {
		MedicalSupplyOrder order = new MedicalSupplyOrder();
		Concept quantityUnits = new Concept(1);
		order.setQuantity(2.5);
		order.setBrandName("Example brand");
		order.setQuantityUnits(quantityUnits);
		order.setMedicalSuppliesInventoryId(42);
		
		MedicalSupplyOrder copy = order.copy();
		
		assertNotSame(order, copy);
		assertEquals(Double.valueOf(2.5), copy.getQuantity());
		assertEquals("Example brand", copy.getBrandName());
		assertSame(quantityUnits, copy.getQuantityUnits());
		assertEquals(Integer.valueOf(42), copy.getMedicalSuppliesInventoryId());
	}
	
	@Test
	public void cloneForRevisionShouldBeAMedicalSupplyOrder() {
		MedicalSupplyOrder order = new MedicalSupplyOrder();
		
		MedicalSupplyOrder revision = order.cloneForRevision();
		
		assertEquals(Order.Action.REVISE, revision.getAction());
		assertSame(order, revision.getPreviousOrder());
	}
}
