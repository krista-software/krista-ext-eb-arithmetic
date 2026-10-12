# Overview

EB Arithmetic is a self-contained sample extension — it does not connect to any external system.
Use it to perform basic arithmetic (addition, subtraction, multiplication, division) on two
numbers directly from Krista workflows and agents.

## Use cases

- **Add two numbers** — return the sum of two numbers.
- **Subtract two numbers** — return the difference of two numbers.
- **Multiply two numbers** — return the product of two numbers.
- **Divide two numbers** — return the quotient of two numbers, with a clear error if the divisor is zero.

## What you can do

The extension exposes 4 calculation requests plus the standard Setup requests, across 2 areas — see
[Supported Requests](pages/supported-requests.md) for the full list, or the sidebar for each
request's detailed page.

## Before you start

There is nothing to configure — see [Configuration](pages/ExtensionConfiguration.md) and
[Authentication](pages/Authentication.md) for why. Run **Test Connection** (Setup area) to verify
the extension's arithmetic logic is loaded correctly, then call any Calculator request directly.
