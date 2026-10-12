# Add

## Overview
Adds two numbers together and returns the sum. Use this when a workflow needs the total of two
values.

## Request Details
- **Area**: Calculator
- **Type**: QUERY_SYSTEM
- **External API**: None — computed locally inside the extension.

## Input Parameters
| Parameter | Type | Required | Description |
|---|---|---|---|
| First Number | Number | Yes | The first operand. |
| Second Number | Number | Yes | The second operand. |

## Output Parameters
| Parameter | Type | Description |
|---|---|---|
| Result | Number | The sum of First Number and Second Number. |

## Example
**Input**: First Number=`4`, Second Number=`7`
**Output**: Result=`11`

## Error Handling
- **Missing or non-numeric input** — if First Number or Second Number is missing, or is not a
  finite number (e.g. not-a-number), the request fails with a message naming the field and what to
  provide instead.
