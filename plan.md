# EB Arithmetic — Implementation Plan

## Summary
A sample, self-contained Krista Global Catalog extension that performs basic arithmetic
(addition, subtraction, multiplication, division) on two numbers. It calls no external
system — every request is computed locally inside the extension.

- **Title:** EB Arithmetic
- **Module/slug:** `eb-arithmetic`
- **Version:** 1.0.0
- **Package:** `app.krista.extensions.ebplayground.ebtesting.ebarithmetic`
- **Ecosystem:** EB Playground
- **Domain:** EB Testing

## Authentication
**None.** The user confirmed the extension needs no credentials (`authentication: "none"`),
and this holds up — there is no external API to authenticate against, so there will be no
secured connection fields and no Setup/Authentication tab requirement. `authPattern = NONE`.

## Domain reuse
`domain-catalog.json` reports `found: false` — **EB Playground / EB Testing is a brand-new
ecosystem and domain**, with no existing areas, entities, or requests to compare against.
Nothing was available to reuse, so every request below is built fresh ("new").

## Area: Calculator

| Request | Inputs | Output | External endpoint | Reused or new |
|---|---|---|---|---|
| Add | First Number (Number, required), Second Number (Number, required) | Result (Number) | N/A — local computation | New |
| Subtract | First Number (Number, required), Second Number (Number, required) | Result (Number) | N/A — local computation | New |
| Multiply | First Number (Number, required), Second Number (Number, required) | Result (Number) | N/A — local computation | New |
| Divide | First Number (Number, required), Second Number (Number, required) | Result (Number) | N/A — local computation | New |

All four requests are typed `QUERY_SYSTEM` (they read/compute, they don't change any
system state). Each returns a scalar `Result` (Number) output — no `@Entity` is needed since
a calculation result isn't a domain object with its own identity/fields (per the scaffolding
guidance: entities are for domain objects; a small ad-hoc result is a scalar field).

Divide additionally validates the second operand and raises a handled validation error
(mapped to a failed `ExtensionResponse`, not a crash) when it is zero.

## Entities
None. There are no domain objects in this extension — only scalar numeric results.

## Capabilities
| Capability | Enabled | Why |
|---|---|---|
| Entity search | No | No searchable domain objects |
| Webhooks / WAIT_FOR_EVENT | No | Nothing external pushes events |
| MCP (`tool = true`) | No | Not requested; can be added later per request if needed |
| Solutions tab | No | Not requested |
| File handling | No | No file inputs/outputs |

## Placeholder IDs — must replace before deploy
- **`@Domain` id**, **`ecosystemId`**, and **`ecosystemName`** for *EB Playground / EB Testing*
  will be generated as **stable placeholder UUIDs** (`placeholderIds: true`) because this
  ecosystem/domain has never been through the real global-catalog publish process
  (`domain-catalog.json` → `found: false`). These placeholders compile fine but **must be
  swapped for the real registered IDs** before this extension can bind/deploy in Krista.

## Tests
Pure-Java unit tests per request (happy path + edge cases), e.g.:
- `add_returnsSumOfTwoPositiveNumbers`, `add_returnsSumWithNegativeNumbers`, `add_returnsSumWithDecimalNumbers`
- `subtract_returnsDifferenceOfTwoPositiveNumbers`, `subtract_returnsDifferenceWithNegativeNumbers`, `subtract_returnsNegativeResultWhenSecondIsLarger`
- `multiply_returnsProductOfTwoPositiveNumbers`, `multiply_returnsZeroWhenEitherOperandIsZero`, `multiply_returnsProductWithNegativeNumbers`
- `divide_returnsQuotientOfTwoPositiveNumbers`, `divide_returnsDecimalResultWhenNotEvenlyDivisible`, `divide_rejectsDivisionByZeroWithValidationError`
