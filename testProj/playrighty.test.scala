//> using platform jvm
//> using jvm temurin:21
//> using scala 3.4.0
//> using test.dep org.scalameta::munit::1.0.0-M10
//> using dep com.microsoft.playwright:playwright:1.41.1
//> using dep com.microsoft.playwright:driver-bundle:1.41.1
//> using javaProp "playwright.driver.impl=jsnev.DriverJar"
//> using dep com.lihaoyi::os-lib:0.9.3

import com.microsoft.playwright.*
import com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat
import com.sun.net.httpserver.*;
import java.net.InetSocketAddress;
import com.microsoft.playwright.Page.InputValueOptions
import com.sun.net.httpserver.SimpleFileServer
import java.nio.file.Paths

class PlaywrightTest extends munit.FunSuite:

  // val env = new java.util.HashMap[String, String]();
  // env.put("PLAYWRIGHT_SKIP_BROWSER_DOWNLOAD", "1");
  // val createOptions = new Playwright.CreateOptions();
  // createOptions.setEnv(env);

  // val browserType = new BrowserType.LaunchOptions()
  //   .setExecutablePath(Paths.get("Path/to/browser.exe"))
  //   .setHeadless(false)
  //   .setChromiumSandbox(true)


  // val browser = Playwright.create(createOptions).chromium().launch(

  // context = browser.newContext(
  // new Browser.NewContextOptions()
  // .setViewportSize(1800, 1080)

  val port = 8080
  var pw: Playwright = _
  var browser: Browser = _
  var page: Page = _
  var server: HttpServer = _

  override def beforeAll(): Unit =

    // System.setProperty("playwright.driver.impl", "jsenv.DriverJar")
    pw = Playwright.create()
    browser = pw.chromium().launch();
    page = browser.newPage();
    val buildOutDir = sys.env.get("SHOELACE_SANS_OUT_DIR").get
    val wd = os.Path(buildOutDir)
    println(wd)
    server = SimpleFileServer.createFileServer(new InetSocketAddress(port), wd.toNIO, SimpleFileServer.OutputLevel.VERBOSE)
    server.start()
    ()
  end beforeAll

  test("concept") {
    page.navigate(s"http://127.0.0.1:$port")
    page.click("#input1")
    page.`type`(s"input#input", "0")
    assertThat(page.locator("#mult")).containsText("500")
  }

  override def afterAll(): Unit =
    super.afterAll()
    pw.close()
    server.stop(0)

end PlaywrightTest
