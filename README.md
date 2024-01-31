# ShoelaceSansBundlerTest

scala facade to shoelace in browser, sans vite, sans sbt, mit scala-cli.

# Dependancies

You'll need

- https://github.com/casey/just
- [scala js cli 1.15.0.1](https://github.com/VirtusLab/scala-js-cli/releases/tag/v1.15.0.1)
    - Download to this working directory
    - `chmod +x scala-js-cli`

# Justfile

I needed a way to manage heterogenous scala-cli scripts and settled on just.

`just --list` to see the available commands.

`just inspectManually` to see the "application". It'll take a second on browser load to resolve the ESM imports.

`just` (the default) is the `demo` as such... it;
- Builds the facade in scala-js-cli (with our import map linking trick)
- Runs the tests
  - Fires up playwright
  - Fires up a JDK 18+ `SimpleHttpServer`
  - Tests that the browser responds as expected to input events

## Goals

Demonstrate that it is possible to dramatically simplify parts of the scala js toolchain. In the first instance targting facade construction and testing.

Scala-cli only. No vite, no sbt.

In browser tests driven purely by the JVM API of [playwright](https://playwright.dev/java/), mean the facade is really tested in the browser.

## Structure

- /facade is the scalajs project that one would seek to publish. In this case, it contains a very simple facade for @stdlib/linspace. This project, would be publishable. It should be useable upstream, as long as the dependancies in the importmap.json are resolved through either ESModule or a bundler.

- /testDir contains a traditional scala-cli (JVM) project which is nothing more than a test harness for the facade.

Nits

- Two seperate scala-cli projects, is _slightly_ more complex than I wanted. I don't think they can be mixed though, as it would require seperate dependancies per platform.

- Need to land https://github.com/VirtusLab/scala-cli/issues/2698 as the scalaJsCli just task is currently rather complex!
