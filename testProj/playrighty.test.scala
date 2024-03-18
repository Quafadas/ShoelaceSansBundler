//> using platform jvm
//> using jvm temurin:21
//> using scala 3.4.0
//> using test.dep org.scalameta::munit::1.0.0-M10
//> using dep com.microsoft.playwright:playwright:1.41.1
//> using dep com.microsoft.playwright:driver-bundle:1.41.1
//> using dep com.lihaoyi::os-lib:0.9.3

import scala.compiletime.uninitialized
import com.microsoft.playwright.*
import com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat
import com.sun.net.httpserver.*;
import java.net.InetSocketAddress;
import com.microsoft.playwright.Page.InputValueOptions
import com.sun.net.httpserver.SimpleFileServer
import java.nio.file.Paths
import com.microsoft.playwright.impl.driver.Driver

/*
Run
cs launch com.microsoft.playwright:playwright:1.41.1 -M "com.microsoft.playwright.CLI" -- install --with-deps
before this test, to make sure that the driver bundles are downloaded.
  */
class PlaywrightTest extends munit.FunSuite:

  val port = 8080
  var pw: Playwright = uninitialized
  var browser: Browser = uninitialized
  var page: Page = uninitialized
  var server: HttpServer = uninitialized

  override def beforeAll(): Unit =

    pw = Playwright.create()
    browser = pw.chromium().launch();
    page = browser.newPage();
    val buildOutDir = sys.env.get("SHOELACE_SANS_OUT_DIR").get
    val wd = os.Path(buildOutDir)
    server = SimpleFileServer.createFileServer(new InetSocketAddress(port), wd.toNIO, SimpleFileServer.OutputLevel.VERBOSE)
    server.start()
    ()
  end beforeAll

  test("concept") {
    page.navigate(s"http://127.0.0.1:$port/index.html")
    page.getByLabel("First number").fill("50");
    page.getByLabel("Second, glorious number").fill("50");
    assertThat(page.locator("#mult")).containsText("2500")
  }

  override def afterAll(): Unit =
    super.afterAll()
    pw.close()
    server.stop(0)

end PlaywrightTest
