import com.microsoft.playwright.*
import com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat
import com.sun.net.httpserver.*;
import java.net.InetSocketAddress;
import com.microsoft.playwright.Page.InputValueOptions

class PlaywrightTest extends munit.FunSuite:

  val port = 8080
  var pw: Playwright = _
  var browser: Browser = _
  var page: Page = _
  var server: HttpServer = _

  override def beforeAll(): Unit =

    System.setProperty("playwright.driver.impl", "jsenv.DriverJar")
    pw = Playwright.create()
    browser = pw.chromium().launch();
    page = browser.newPage();
    val wd = os.pwd / "shoelace" / "linked"
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
