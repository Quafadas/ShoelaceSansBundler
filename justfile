# compile, link and test - scala cli only...
default: scalaJsCli test

# JVM tests - compile, link and serve
manuallyInspect: scalaJsCli serve

# JVM tests - start server, use playwright to test
test:
  @echo "Testing"
  scala-cli test testDir

# Serve the ESModule linked directory - viewable in browser
serve:
  @echo "Serving, be default this directory, port 8000"
  $JAVA_HOME/bin/jwebserver -d {{invocation_directory()}}/shoelace/linked

# Run the scalaJsCli - currently manages the classpath itself, need to land https://github.com/VirtusLab/scala-cli/issues/2698 so that this is just scala-cli
scalaJsCli:
  @echo "Compiling ScalaJS"
  @echo "-- CLASSPATH --"
  @echo "{{stdLibclasspath}}"
  mkdir -p {{invocation_directory()}}/shoelace/bin
  mkdir -p {{invocation_directory()}}/shoelace/linked
  @echo "----------------"
  cs launch scalac:3.3.1 -- -classpath {{stdLibclasspath}} -scalajs -d {{invocation_directory()}}/shoelace/bin {{invocation_directory()}}/shoelace/shoelace.scala
  @echo "----------------"
  ./scala-js-cli --stdlib {{stdLibclasspath}} --fastOpt -s --outputDir {{invocation_directory()}}/shoelace/linked -mm Foo.main {{invocation_directory()}}/shoelace/bin --moduleKind ESModule --importmap {{invocation_directory()}}/shoelace/importmap.json
  @echo "write html file"
  echo '<html><head><script type="module" src="main.js"></script></head><body><div id="app"></div></body></html>' > {{invocation_directory()}}/shoelace/linked/index.html
  @echo "----------------"
  @echo "We have linked... see linked directory"
  @echo "AIM: PR this to PR this to scala-cli"


stdLibclasspath := `cs fetch --classpath -E org.scala-lang:scala-library_3.3.1 org.scala-js::scalajs-library:1.15.0 com.raquo:laminar_sjs1_3:17.0.0-M2 org.scala-js::scalajs-dom_sjs1:2.8.0 com.raquo:laminar-shoelace_sjs1_3:0.1.0-M2`

# Wish this worked.
# shoelaceDir := `{{invocation_directory()}}/shoelace`
