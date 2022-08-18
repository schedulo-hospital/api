# Schedulo - hospitals

```mermaid
erDiagram
Organization }o--|| Department : contains
Department }o--|| User : has

Organization }o--|| OrganizationUserRoles : contains
User }o--|| OrganizationUserRoles : has
User }o--|| Shift : has
User }o--|| Availability : has

```
