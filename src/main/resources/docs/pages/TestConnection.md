# Test Connection

## Overview
Verifies the extension is loaded and responding inside Krista. Since EB Arithmetic has no external
system to reach, this always succeeds once the extension is installed.

## Request Details
- **Area**: Setup
- **Type**: QUERY_SYSTEM
- **External API**: N/A — computed locally, no external call.

## Output Parameters
| Parameter | Type | Description |
|---|---|---|
| Success | Boolean | Always `true` once the extension is loaded. |
| Message | Text | A human-readable confirmation message. |

## Example
**Output**: Success=`true`, Message=`EB Arithmetic has no external dependencies; the extension is loaded and ready.`

## Error Handling
- This request has no failure path — there is no external system whose absence or misconfiguration
  could cause it to fail.
