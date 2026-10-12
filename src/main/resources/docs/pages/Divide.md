# Divide

## Overview
Divides the first number by the second and returns the quotient. Use this when a workflow needs to
split or rate-compare two values.

## Request Details
- **Area**: Calculator
- **Type**: QUERY_SYSTEM
- **External API**: None — computed locally inside the extension.

## Input Parameters
| Parameter | Type | Required | Description |
|---|---|---|---|
| First Number | Number | Yes | The dividend. |
| Second Number | Number | Yes | The divisor. Must not be zero. |

## Output Parameters
| Parameter | Type | Description |
|---|---|---|
| Result | Number | The quotient of First Number divided by Second Number. |

## Example
**Input**: First Number=`9`, Second Number=`2`
**Output**: Result=`4.5`

## Error Handling
- **Second Number is zero** — the request fails with "Second Number must not be zero. Division by
  zero is undefined. Provide a non-zero divisor, e.g. 2." instead of returning an infinite or
  undefined result.
- **Missing or non-numeric input** — if First Number or Second Number is missing, or is not a
  finite number, the request fails with a message naming the field and what to provide instead.
