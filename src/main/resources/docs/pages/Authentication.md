# Authentication

EB Arithmetic requires **no authentication**. Both of its operations — addition and subtraction —
are computed entirely inside the extension; it never calls an external API or service, so there is
no token, API key, or login to configure.

## Obtaining credentials

Not applicable — there is no external system to authenticate against.

## Required scopes / permissions

| Scope / Permission | Why it's needed |
|---|---|
| _(none)_ | No external system is called. |

## Verifying

Run **Test Connection** in the Setup area. It always confirms the extension is loaded and ready,
since there is no external connectivity to check.
