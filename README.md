# OpenMRS Additional Order Types Module

This module adds two `ServiceOrder` subclasses to OpenMRS:

- `procedureorder`, backed by `ProcedureOrder`
- `medicalsupplyorder`, backed by `MedicalSupplyOrder`

It includes Hibernate mappings, Liquibase schema changes, and OpenMRS REST Web Services subclass handlers. Order type metadata (including concepts and privileges) remains deployment configuration. The REST fallback recognizes the established order type UUIDs `4237a01f-29c5-4167-9d8e-96d6e590aa33` for procedure orders and `dab3ab30-2feb-48ec-b4af-8332a0831b49` for medical supply orders.
