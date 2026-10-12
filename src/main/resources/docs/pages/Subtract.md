# Subtract

## Overview
Subtracts the second number from the first and returns the difference. Use this when a workflow
needs the gap between two values.

## Request Details
- **Area**: Calculator
- **Type**: QUERY_SYSTEM
- **External API**: None — computed locally inside the extension.

## Input Parameters
| Parameter | Type | Required | Description |
|---|---|---|---|
| First Number | Number | Yes | The number to subtract from. |
| Second Number | Number | Yes | The number to subtract. |

## Output Parameters
| Parameter | Type | Description |
|---|---|---|
| Result | Number | The result of First Number minus Second Number. |

## Example
**Input**: First Number=`10`, Second Number=`4`
**Output**: Result=`6`

## Error Handling
- **Missing or non-numeric input** — if First Number or Second Number is missing, or is not a
  finite number, the request fails with a message naming the field and what to provide instead.
- **Negative results are valid** — if Second Number is larger than First Number, Result is simply
  negative; this is not treated as an error.
