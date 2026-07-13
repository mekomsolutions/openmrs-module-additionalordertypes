package org.openmrs.module.additionalordertypes.api.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;

import org.junit.Test;
import org.openmrs.Concept;
import org.openmrs.Order;

public class ProcedureOrderTest {
	
	@Test
	public void copyShouldRetainProcedureSpecificFields() {
		ProcedureOrder order = new ProcedureOrder();
		Concept specimenType = new Concept(1);
		Concept bodySite = new Concept(2);
		Concept category = new Concept(3);
		ProcedureOrder relatedProcedure = new ProcedureOrder();
		order.setSpecimenType(specimenType);
		order.setBodySite(bodySite);
		order.setCategory(category);
		order.setRelatedProcedure(relatedProcedure);
		
		ProcedureOrder copy = order.copy();
		
		assertNotSame(order, copy);
		assertSame(specimenType, copy.getSpecimenType());
		assertSame(bodySite, copy.getBodySite());
		assertSame(category, copy.getCategory());
		assertSame(relatedProcedure, copy.getRelatedProcedure());
	}
	
	@Test
	public void cloneForRevisionShouldBeAProcedureOrder() {
		ProcedureOrder order = new ProcedureOrder();
		
		ProcedureOrder revision = order.cloneForRevision();
		
		assertEquals(Order.Action.REVISE, revision.getAction());
		assertSame(order, revision.getPreviousOrder());
	}
}
