package org.openmrs.module.additionalordertypes.web.resource;

import io.swagger.models.Model;
import io.swagger.models.ModelImpl;
import io.swagger.models.properties.IntegerProperty;
import io.swagger.models.properties.RefProperty;
import io.swagger.models.properties.StringProperty;
import org.openmrs.Order;
import org.openmrs.module.additionalordertypes.api.model.ProcedureOrder;
import org.openmrs.module.webservices.docs.swagger.core.property.EnumProperty;
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

@SubClassHandler(supportedClass = ProcedureOrder.class, supportedOpenmrsVersions = { "2.6.* - 9.*" })
public class ProcedureOrderSubclassHandler extends BaseDelegatingSubclassHandler<Order, ProcedureOrder> implements DelegatingSubclassHandler<Order, ProcedureOrder> {
	
	@Override
	public String getTypeName() {
		return "procedureorder";
	}
	
	@Override
	public PageableResult getAllByType(RequestContext context) throws ResourceDoesNotSupportOperationException {
		throw new ResourceDoesNotSupportOperationException();
	}
	
	@Override
	public ProcedureOrder newDelegate() {
		return new ProcedureOrder();
	}
	
	@PropertyGetter("display")
	public String getDisplay(ProcedureOrder order) {
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
		Representation conceptRepresentation = representation instanceof FullRepresentation ? Representation.FULL
		        : Representation.REF;
		description.addProperty("specimenSource", conceptRepresentation);
		description.addProperty("laterality");
		description.addProperty("clinicalHistory");
		description.addProperty("frequency", representation instanceof FullRepresentation ? Representation.DEFAULT
		        : Representation.REF);
		description.addProperty("numberOfRepeats");
		description.addProperty("specimenType", conceptRepresentation);
		description.addProperty("bodySite", conceptRepresentation);
		description.addProperty("relatedProcedure", conceptRepresentation);
		description.addProperty("category", conceptRepresentation);
		return description;
	}
	
	@Override
	public DelegatingResourceDescription getCreatableProperties() {
		DelegatingResourceDescription description = getResource().getCreatableProperties();
		description.addProperty("specimenSource");
		description.addProperty("laterality");
		description.addProperty("clinicalHistory");
		description.addProperty("frequency");
		description.addProperty("numberOfRepeats");
		description.addProperty("specimenType");
		description.addProperty("bodySite");
		description.addProperty("relatedProcedure");
		description.addProperty("category");
		return description;
	}
	
	@Override
	public Model getGETModel(Representation representation) {
		ModelImpl model = (ModelImpl) getResource().getGETModel(representation);
		String conceptModel = representation instanceof FullRepresentation ? "ConceptGet" : "ConceptGetRef";
		model.property("specimenSource", new RefProperty("#/definitions/" + conceptModel))
		        .property("laterality", new EnumProperty(ProcedureOrder.Laterality.class))
		        .property("clinicalHistory", new StringProperty())
		        .property("frequency", new RefProperty("#/definitions/OrderfrequencyGetRef"))
		        .property("numberOfRepeats", new IntegerProperty())
		        .property("specimenType", new RefProperty("#/definitions/" + conceptModel))
		        .property("bodySite", new RefProperty("#/definitions/" + conceptModel))
		        .property("relatedProcedure", new RefProperty("#/definitions/OrderGetRef"))
		        .property("category", new RefProperty("#/definitions/" + conceptModel));
		return model;
	}
	
	@Override
	public Model getCREATEModel(Representation representation) {
		ModelImpl model = (ModelImpl) getResource().getCREATEModel(representation);
		model.property("specimenSource", new StringProperty().example("uuid"))
		        .property("laterality", new EnumProperty(ProcedureOrder.Laterality.class))
		        .property("clinicalHistory", new StringProperty())
		        .property("frequency", new StringProperty().example("uuid"))
		        .property("numberOfRepeats", new IntegerProperty())
		        .property("specimenType", new StringProperty().example("uuid"))
		        .property("bodySite", new StringProperty().example("uuid"))
		        .property("relatedProcedure", new StringProperty().example("uuid"))
		        .property("category", new StringProperty().example("uuid"));
		return model;
	}
}
