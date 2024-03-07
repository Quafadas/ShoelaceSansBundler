# compile, link and test - scala cli only...
default: setupIde manuallyInspect

# JVM tests - compile, link and serve
manuallyInspect: buildJs writeHtmlFile serve

# JVM tests - start server, use playwright to test
test:
  @echo "Testing"
  scala-cli test playrighty.test.scala DriverJar.scala

# Serve the ESModule linked directory - viewable in browser
serve:
  @echo "Serving, be default this directory, port 8000"
  $JAVA_HOME/bin/jwebserver -d {{invocation_directory()}}/out

buildJsW:
  scala-cli --power package shoelace.scala -o out -f -w

buildJs:
  scala-cli --power package shoelace.scala -o out -f

setupIde:
  scala-cli setup-ide .

writeHtmlFile:
  @echo "Writing HTML file"
  echo "<html class="sl-theme-dark"><head><link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/@shoelace-style/shoelace@2.14.0/cdn/themes/dark.css'/><script type='module' src='./main.js'></script></head><body><div id="app"></div></body></html>" > out/index.html
