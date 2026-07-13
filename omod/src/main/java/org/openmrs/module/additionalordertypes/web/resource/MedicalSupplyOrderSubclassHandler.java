package org.openmrs.module.additionalordertypes.web.resource;

import io.swagger.models.Model;
import io.swagger.models.ModelImpl;
import io.swagger.models.properties.DoubleProperty;
import io.swagger.models.properties.IntegerProperty;
import io.swagger.models.properties.RefProperty;
import io.swagger.models.properties.StringProperty;
import org.openmrs.Order;
import org.openmrs.module.additionalordertypes.api.model.MedicalSupplyOrder;
import org.openmrs.module.webservices.rest.web.RequestContext;
import org.openmrs.module.webservices.rest.web.annotation.PropertyGetter;
import org.openmrs.module.webservices.rest.web.annotation.SubClassHandler;
import org.openmrs.module.webservices.rest.web.representation.CustomRepresentation;
import org.openmrs.module.webservices.rest.web.representation.DefaultRepresentation;
import org.openmrs.module.webservices.rest.web.representation.FullRepresentation;
import org.openmrs.module.webservices.rest.web.representation.Representation;
import org.openmrs.module.webservices.rest.web.resource.api.PageableResult;
import org.openmrs.module.webservices.rest.web.resource.impl.BaseDelegatingSubclassHandler;
import org.openmrs.module.webservices.rest.web.resource.impl.DelegatingResourceDescription;
import org.openmrs.module.webservices.rest.web.resource.impl.DelegatingSubclassHandler;
import org.openmrs.module.webservices.rest.web.response.ResourceDoesNotSupportOperationException;

@SubClassHandler(supportedClass = MedicalSupplyOrder.class, supportedOpenmrsVersions = { "2.6.* - 9.*" })
public class MedicalSupplyOrderSubclassHandler extends BaseDelegatingSubclassHandler<Order, MedicalSupplyOrder> implements DelegatingSubclassHandler<Order, MedicalSupplyOrder> {
	
	@Override
	public String getTypeName() {
		return "medicalsupplyorder";
	}
	
	@Override
	public PageableResult getAllByType(RequestContext context) throws ResourceDoesNotSupportOperationException {
		throw new ResourceDoesNotSupportOperationException();
	}
	
	@Override
	public MedicalSupplyOrder newDelegate() {
		return new MedicalSupplyOrder();
	}
	
	@PropertyGetter("display")
	public String getDisplay(MedicalSupplyOrder order) {
		return ((OrderResource2_6) getResource()).getDisplayString(order);
	}
	
	@Override
	public DelegatingResourceDescription getRepresentationDescription(Representation representation) {
		if (representation instanceof CustomRepresentation) {
			return null;
		}
		if (!(representation instanceof DefaultRepresentation) && !(representation instanceof FullRepresentation)) {
			return null;
		}
		
		DelegatingResourceDescription description = getResource().getRepresentationDescription(representation);
		description.addProperty("quantity");
		description.addProperty("medicalSuppliesInventoryId");
		description.addProperty("brandName");
		description.addProperty("quantityUnits", representation instanceof FullRepresentation ? Representation.DEFAULT
		        : Representation.REF);
		return description;
	}
	
	@Override
	public DelegatingResourceDescription getCreatableProperties() {
		DelegatingResourceDescription description = getResource().getCreatableProperties();
		description.addProperty("quantity");
		description.addProperty("medicalSuppliesInventoryId");
		description.addProperty("brandName");
		description.addProperty("quantityUnits");
		return description;
	}
	
	@Override
	public Model getGETModel(Representation representation) {
		ModelImpl model = (ModelImpl) getResource().getGETModel(representation);
		model.property("medicalSuppliesInventoryId", new IntegerProperty()).property("quantity", new DoubleProperty())
		        .property("brandName", new StringProperty())
		        .property("quantityUnits", new RefProperty("#/definitions/ConceptGetRef"));
		return model;
	}
	
	@Override
	public Model getCREATEModel(Representation representation) {
		ModelImpl model = (ModelImpl) getResource().getCREATEModel(representation);
		model.property("medicalSuppliesInventoryId", new IntegerProperty()).property("quantity", new DoubleProperty())
		        .property("brandName", new StringProperty()).property("quantityUnits", new StringProperty().example("uuid"));
		return model;
	}
}
