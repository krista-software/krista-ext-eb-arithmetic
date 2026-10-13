# Subtract Two Numbers

## Overview
Subtracts the second number from the first and returns the difference. Use this when a workflow or
agent needs the difference between two numeric values, including when the result is negative.

## Request Details
- **Area**: Calculator
- **Type**: QUERY_SYSTEM
- **External API**: N/A — computed locally, no external call.

## Input Parameters
| Parameter | Type | Required | Description |
|---|---|---|---|
| Number1 | Number | Yes | The number to subtract from (minuend). |
| Number2 | Number | Yes | The number being subtracted (subtrahend). |

## Output Parameters
| Parameter | Type | Description |
|---|---|---|
| Result | Number | The result of Number1 minus Number2. |

## Example
**Input**: Number1=`10`, Number2=`15`
**Output**: Result=`-5`

## Error Handling
- **Missing Number1 or Number2** — the request returns a failure response naming the missing field
  (e.g. "Number2 is required. Please provide a numeric value for Number2."); supply both numbers and
  retry.
