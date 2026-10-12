# Health Check

## Overview
Reports the extension's JVM heap usage and confirms its arithmetic logic is still healthy.

## Request Details
- **Area**: Setup
- **Type**: CHANGE_SYSTEM
- **External API**: None — internal check only.
- **Ask Agent (MCP)**: Enabled (callable by the Krista AI agent)

## Input Parameters
None.

## Output Parameters
| Parameter | Type | Description |
|---|---|---|
| Is Healthy | Boolean | `true` if the internal arithmetic self-check passes. |
| Heap Used MB | Number | Current JVM heap usage in megabytes. |
| Details | Text | `"OK"`, or a short explanation if the self-check failed. |

## Example
**Input**: (none)
**Output**: Is Healthy=`true`, Heap Used MB=`128`, Details=`"OK"`

## Error Handling
- **Self-check failure** — if the internal arithmetic check fails, Is Healthy=`false` and Details
  explains why.
