# EB Arithmetic — Implementation Plan

## Summary
A simple, self-contained Krista Global Catalog extension that performs basic arithmetic — addition
and subtraction — on two numbers supplied by the caller. It does not call any external system or
third-party API; both results are computed entirely inside the extension.

| | |
|---|---|
| Extension title | EB Arithmetic |
| Extension name (slug) | `eb-arithmetic` |
| Version | 1.0.0 |
| Package | `app.krista.extensions.ebplayground.ebtesting.ebarithmetic` |
| Ecosystem | EB Playground |
| Domain | EB Testing |
| Auth pattern | **NONE** |

## Authentication
The user explicitly selected **no authentication** (`answers.json` → `"authentication": "none"`),
and that matches the requirement — there is no external system to authenticate against, since both
operations are local computation. The extension will have **no secured connection fields** and
**no Setup/Authentication tab requirement**. (`Setup` still gets a minimal Test Connection /
Health Check that trivially succeeds, since every extension has a SetupArea, but there is nothing to
actually authenticate.)

## Area: Arithmetic

| Request | Inputs | Output | External endpoint | Reused or new |
|---|---|---|---|---|
| Add Two Numbers | `Number1` (Number, required), `Number2` (Number, required) | `Result` — scalar Number field, `Number1 + Number2` | N/A — no external call, computed locally | New |
| Subtract Two Numbers | `Number1` (Number, required), `Number2` (Number, required) | `Result` — scalar Number field, `Number1 - Number2` | N/A — no external call, computed locally | New |

No `@Entity` is defined for either request: per the scaffolding's field-types guidance, a single
numeric result is "a small ad-hoc result" (like a success flag or id), which takes a plain scalar
`@Field(type="Number")` output — not a domain object requiring an `Entity`.

## Entities
None. There is no domain object with a stable multi-field shape here — only a single computed
number per request.

## Capabilities
| Capability | Enabled | Why |
|---|---|---|
| Entity search | No | No searchable domain objects |
| Webhooks / WAIT_FOR_EVENT | No | No events to wait on |
| MCP (`tool = true`) | No | Not requested |
| Solutions tab | No | Not requested |
| File handling | No | No binary inputs/outputs |

## Domain reuse
`domain-catalog.json` reports `"found": false` — **EB Playground / EB Testing is a brand-new,
unpublished ecosystem/domain**. There is nothing published yet to check for overlap, so no reuse
question was needed and both requests are built fresh (vendor/provider-agnostic names — `Add Two
Numbers` / `Subtract Two Numbers` — consistent with the style future extensions in this domain could
reuse).

## Placeholder IDs — must replace before deploy
Because EB Playground / EB Testing has never been through the real global-catalog publish process
(`domain-catalog.json` → `found: false`), this build will mint **stable placeholder UUIDs** for:

- `@Domain` id (`domainId`)
- `ecosystemId`
- `ecosystemVersion`

These compile and run fine, but **must be swapped for the real registered values** before this
extension can bind into the live `EB Playground` / `EB Testing` ecosystem/domain in Krista Studio.
