outDir := justfile_directory() + "/.out"
frontDir := justfile_directory() + "/front"
testDir := justfile_directory() + "/test"


# This list of available targets
default:
	@just --list


# JVM tests - compile, link and serve
manuallyInspect: buildJs writeHtmlFile serve

# For us in GHA - should create everything needed to publish this example
gha: buildJs writeHtmlFile

# JVM tests - start server, use playwright to test
test $SHOELACE_SANS_OUT_DIR="{{outDir}}":
  @echo "Testing"
  scala-cli test testProj

# Serve the ESModule linked directory - viewable in browser
serve:
  @echo "Serving, be default this directory, port 8000"
  $JAVA_HOME/bin/jwebserver -d {{outDir}}

buildJsW:
  scala-cli --power package shoelace.scala -o {{outDir}} -f -w

buildJs:
  scala-cli --power package front/shoelace.scala -o {{outDir}} -f

setupIde:
  scala-cli setup-ide front --suppress-experimental-feature-warning --suppress-directives-in-multiple-files-warning
  scala-cli setup-ide testProj --suppress-experimental-feature-warning --suppress-directives-in-multiple-files-warning
  code --add front
  code --add testProj

# Install the Playwright browser / driver bundle
installBundle:
  cs launch com.microsoft.playwright:playwright:1.41.1 -M "com.microsoft.playwright.CLI" -- install --with-deps

# Creates an html file with a link to the main.js in the .out directory
writeHtmlFile:
  mkdir -p outDir
  echo "<html class="sl-theme-dark"><head><link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/@shoelace-style/shoelace@2.14.0/cdn/themes/dark.css'/><script type='module' src='./main.js'></script></head><body><div id="app"></div></body></html>" > out/index.html
