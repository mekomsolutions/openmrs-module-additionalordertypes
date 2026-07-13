package org.openmrs.module.additionalordertypes.web.resource;

import org.openmrs.DrugOrder;
import org.openmrs.Order;
import org.openmrs.OrderType;
import org.openmrs.ReferralOrder;
import org.openmrs.TestOrder;
import org.openmrs.api.OrderContext;
import org.openmrs.api.context.Context;
import org.openmrs.module.additionalordertypes.api.model.MedicalSupplyOrder;
import org.openmrs.module.additionalordertypes.api.model.ProcedureOrder;
import org.openmrs.module.webservices.rest.web.RestConstants;
import org.openmrs.module.webservices.rest.web.annotation.Resource;
import org.openmrs.module.webservices.rest.web.v1_0.resource.openmrs2_2.OrderResource2_2;

/**
 * Makes the OpenMRS 2.2 order REST resource available on OpenMRS 2.6 and supplies an order context
 * for the additional subclasses.
 */
@Resource(name = RestConstants.VERSION_1 + "/order", supportedClass = Order.class, order = 10, supportedOpenmrsVersions = { "2.6.* - 9.*" })
public class OrderResource2_6 extends OrderResource2_2 {
	
	public static final String PROCEDURE_ORDER_TYPE_UUID = "4237a01f-29c5-4167-9d8e-96d6e590aa33";
	
	public static final String MEDICAL_SUPPLY_ORDER_TYPE_UUID = "dab3ab30-2feb-48ec-b4af-8332a0831b49";
	
	@Override
	public Order save(Order order) {
		return Context.getOrderService().saveOrder(order, createOrderContext(order));
	}
	
	OrderContext createOrderContext(Order order) {
		OrderContext context = new OrderContext();
		OrderType orderType = Context.getOrderService().getOrderTypeByConcept(order.getConcept());
		
		if (orderType == null && order instanceof DrugOrder) {
			orderType = Context.getOrderService().getOrderTypeByUuid(OrderType.DRUG_ORDER_TYPE_UUID);
		} else if (orderType == null && order instanceof TestOrder) {
			orderType = Context.getOrderService().getOrderTypeByUuid(OrderType.TEST_ORDER_TYPE_UUID);
		} else if (orderType == null && order instanceof ReferralOrder) {
			orderType = Context.getOrderService().getOrderTypeByUuid(OrderType.REFERRAL_ORDER_TYPE_UUID);
		} else if (orderType == null && order instanceof ProcedureOrder) {
			orderType = Context.getOrderService().getOrderTypeByUuid(PROCEDURE_ORDER_TYPE_UUID);
		} else if (orderType == null && order instanceof MedicalSupplyOrder) {
			orderType = Context.getOrderService().getOrderTypeByUuid(MEDICAL_SUPPLY_ORDER_TYPE_UUID);
		}
		
		context.setOrderType(orderType);
		return context;
	}
}
