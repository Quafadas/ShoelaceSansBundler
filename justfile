outDir := justfile_directory() + "/.out"
frontDir := justfile_directory() + "/front"
testDir := justfile_directory() + "/test"


# This list of available targets
default:
	@just --list

# JVM tests - compile, link and serve
manuallyInspect: buildJs writeHtmlFile serve

# For us in GHA - should create everything needed to publish this example
gha: buildJs writeHtmlFile test

# JVM tests - start server, use playwright to test
test $SHOELACE_SANS_OUT_DIR=outDir:
  @echo "Testing"
  scala-cli test testProj

# Serve the ESModule linked directory - viewable in browser for manual inspection
serve:
  @echo "Serving, be default this directory, port 8000"
  $JAVA_HOME/bin/jwebserver -d {{outDir}}

## Useful for live reload along with "live server" - I found live server to work in Edge (windows) and safari (mac)
buildJsW:
  scala-cli --power package shoelace.scala -o {{outDir}} -f -w

## Builds the front end project
buildJs:
  mkdir -p outDir
  scala-cli --power package front/shoelace.scala -o {{outDir}} -f

# Will add both projects to a metals "multi root" workspace for IDE support.
setupIde:
  scala-cli setup-ide front --suppress-experimental-feature-warning --suppress-directives-in-multiple-files-warning
  scala-cli setup-ide testProj --suppress-experimental-feature-warning --suppress-directives-in-multiple-files-warning
  code --add front
  code --add testProj

# Install the Playwright browser / driver bundle
installPlaywrightBundle:
  cs launch com.microsoft.playwright:playwright:1.41.1 -M "com.microsoft.playwright.CLI" -- install --with-deps

# Creates an html file with a link to the main.js in the .out directory
writeHtmlFile:
  mkdir -p outDir
  echo "<html class="sl-theme-dark"><head><link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/@shoelace-style/shoelace@2.14.0/cdn/themes/dark.css'/><script type='module' src='./main.js'></script></head><body><div id="app"></div></body></html>" > {{outDir}}/index.html

# make sure you are serving the directory with the html file, then this will use playwrights recording mechanism to record a test
recordATest:
  cs launch com.microsoft.playwright:playwright:1.41.1 -M "com.microsoft.playwright.CLI" -- codegen http://localhost:5500/.out/index.html