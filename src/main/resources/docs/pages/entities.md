# Entities

EB Arithmetic has **no entities**. Every request returns a single scalar `Result` (Number) field —
an arithmetic result isn't a domain object with its own identity or fields, so no `@Entity` class
was needed (see each request's page under [Supported Requests](pages/supported-requests.md) for its
exact output field).
