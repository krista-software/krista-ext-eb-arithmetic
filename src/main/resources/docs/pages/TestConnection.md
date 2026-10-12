# Test Connection

## Overview
Verifies that the extension's arithmetic logic is loaded and working correctly. Because this
extension has no external system to reach, "connectivity" here means a fixed internal self-check
(2 + 2 = 4) rather than a network or credential check.

## Request Details
- **Area**: Setup
- **Type**: QUERY_SYSTEM
- **External API**: None — internal self-check only.
- **Ask Agent (MCP)**: Enabled (callable by the Krista AI agent)

## Input Parameters
None.

## Output Parameters
| Parameter | Type | Description |
|---|---|---|
| Success | Boolean | `true` if the self-check calculation produced the expected result. |
| Message | Text | A human-readable confirmation, or the error if the self-check failed. |

## Example
**Input**: (none)
**Output**: Success=`true`, Message=`"EB Arithmetic is loaded and its calculations are working correctly."`

## Error Handling
- **Self-check failure** — if the internal 2 + 2 calculation does not equal 4 (which would indicate
  a serious defect in the deployed build), Success=`false` and Message explains what was expected
  vs. returned.
