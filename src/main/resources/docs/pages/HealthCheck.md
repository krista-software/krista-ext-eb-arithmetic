# Health Check

## Overview
Reports the extension's JVM health (heap usage) so an operator can confirm the extension is running
normally.

## Request Details
- **Area**: Setup
- **Type**: CHANGE_SYSTEM
- **External API**: N/A — computed locally, no external call.

## Output Parameters
| Parameter | Type | Description |
|---|---|---|
| Is Healthy | Boolean | `true` when the extension is running normally. |
| Heap Used MB | Number | Current JVM heap usage, in megabytes. |
| Details | Text | Additional status detail. |

## Example
**Output**: Is Healthy=`true`, Heap Used MB=`128`, Details=`OK`

## Error Handling
- This request reports JVM memory statistics only; it has no external failure path.
