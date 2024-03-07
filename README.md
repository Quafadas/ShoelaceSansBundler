
One file static site with laminar and shoelace. No bundler.

# Dependancies

- https://github.com/casey/just
- [scala-cli](https://scala-cli.virtuslab.org)

# Quickstart

```sh
just
```
If you don't have / want *just*, then you can still easily read the justfile and puzzle it out :-).

This will setup the ide, write out a simple `html.index`, compile and link scalaJs into the `out` directory, and start a server.

## Development locally

The `just` command  puts up a server, which will not reload stuff. Kill it.

```sh
just buildJsW
```

Will continually rebuild the javascript. I then had success with the [live server](https://marketplace.visualstudio.com/items?itemName=ritwickdey.LiveServer) extension in vscode.

Point that extensions at `out/index.html`, and you have live reload.

## Goals

Demonstrate that it is possible to simplify parts of the scala js toolchain. In the first instance targting facade construction and testing.

Scala-cli only. No vite, no sbt.

In browser tests driven purely by the JVM API of [playwright](https://playwright.dev/java/), mean the facade is really tested in the browser.
