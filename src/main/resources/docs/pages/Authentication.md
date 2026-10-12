# Authentication

EB Arithmetic uses **no authentication**. Every request (Add, Subtract, Multiply, Divide, and the
Setup requests) is computed locally inside the extension — there is no external API, so there are
no credentials to obtain, no token or API key to paste into the Setup tab, and no Authentication
tab.

## Obtaining credentials

Not applicable — this extension never calls an external system.

## Required scopes / permissions

Not applicable — there are no scopes or permissions to grant.

## Verifying

Run **Test Connection** in the Setup area ([Test Connection](pages/TestConnection.md)). A
successful result confirms the extension's own arithmetic logic is loaded and working correctly.
