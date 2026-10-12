# Multiply

## Overview
Multiplies two numbers together and returns the product. Use this when a workflow needs to scale
one value by another.

## Request Details
- **Area**: Calculator
- **Type**: QUERY_SYSTEM
- **External API**: None — computed locally inside the extension.

## Input Parameters
| Parameter | Type | Required | Description |
|---|---|---|---|
| First Number | Number | Yes | The first factor. |
| Second Number | Number | Yes | The second factor. |

## Output Parameters
| Parameter | Type | Description |
|---|---|---|
| Result | Number | The product of First Number and Second Number. |

## Example
**Input**: First Number=`6`, Second Number=`7`
**Output**: Result=`42`

## Error Handling
- **Missing or non-numeric input** — if First Number or Second Number is missing, or is not a
  finite number, the request fails with a message naming the field and what to provide instead.
