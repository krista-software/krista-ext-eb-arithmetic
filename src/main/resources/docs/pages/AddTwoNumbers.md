# Add Two Numbers

## Overview
Adds two numbers and returns their sum. Use this when a workflow or agent needs to combine two
numeric values, including negative numbers and decimals.

## Request Details
- **Area**: Calculator
- **Type**: QUERY_SYSTEM
- **External API**: N/A — computed locally, no external call.

## Input Parameters
| Parameter | Type | Required | Description |
|---|---|---|---|
| Number1 | Number | Yes | The first operand. |
| Number2 | Number | Yes | The second operand. |

## Output Parameters
| Parameter | Type | Description |
|---|---|---|
| Result | Number | The sum of Number1 and Number2. |

## Example
**Input**: Number1=`12`, Number2=`30.5`
**Output**: Result=`42.5`

## Error Handling
- **Missing Number1 or Number2** — the request returns a failure response naming the missing field
  (e.g. "Number1 is required. Please provide a numeric value for Number1."); supply both numbers and
  retry.
