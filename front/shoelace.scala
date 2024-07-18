//> using scala 3.3.1
//> using platform js

//> using dep com.raquo::laminar-shoelace::0.1.0
//> using dep com.raquo::laminar::17.0.0

//> using dep io.github.quafadas::dedav4s::0.9.0
//> using dep io.github.quafadas::dedav_laminar::0.9.0
//> using jsModuleKind es
//> using jsEsModuleImportMap importmap.json
//> using jsModuleSplitStyleStr smallmodulesfor
//> using jsSmallModuleForPackage frontend

package frontend

import com.raquo.laminar.shoelace.sl
import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.annotation.JSName
import scala.scalajs.js.typedarray.Float64Array
import scala.scalajs.js.annotation.JSGlobal
import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.typedarray.Float64Array
import scala.scalajs.js.|
import com.raquo.laminar.api.L.{*, given}
import org.scalajs.dom
import com.raquo.airstream.core.Signal

import com.raquo.laminar.keys.{EventProp, HtmlProp, HtmlAttr}
import com.raquo.laminar.api.L
import com.raquo.laminar.nodes.Slot
import com.raquo.laminar.tags.CustomHtmlTag

import viz.extensions.RawIterables.*
import viz.Utils
import viz.LaminarViz
import viz.vega.plots.{BarChart, given}
import viz.vega.facades.EmbedOptions
import scala.scalajs.js.JSON

import org.scalajs.dom.document

// @js.native
// @JSImport("@easepick/bundle@1.2.1/+esm", "easepick")
// class easepick extends js.Object {

//   def create(opts: EasepickOptions): Unit = js.native
// }

// @js.native
// trait Easepick extends js.Object {
//   def create(props: EasepickOptions): Easepick = js.native
// }

// @js.native
// trait EasepickOptions extends js.Object {
//   var css: js.UndefOr[String | js.Array[String]] = js.native
//   var element: js.UndefOr[String] = js.native
//   var zIndex: js.UndefOr[Int] = js.native
//   // Add other options as needed
// }

// object EasepickOptions {
//   def apply(
//       css: js.UndefOr[String | js.Array[String]] = js.undefined,
//       element: js.UndefOr[String] = js.undefined,
//       zIndex: js.UndefOr[Int] = js.undefined
//   ): EasepickOptions = {
//     val options = (new js.Object).asInstanceOf[EasepickOptions]
//     element.foreach(options.element = _)
//     css.foreach(options.css = _)
//     zIndex.foreach(options.zIndex = _)
//     options
//   }
// }

// object EasepickWrapper {
//   def create(options: EasepickOptions): Unit = {
//     new easepick().create(options)
//   }
// }

def used(any: Any): Unit = ()

object DatePicker extends WebComponent {

  // noinspection ScalaUnusedSymbol
  @js.native
  trait RawElement extends js.Object {
    def dateValue: js.Date = js.native

    var value: String = js.native

    def closePicker(): Unit = js.native

    def formatValue(date: js.Date): String = js.native

    def isInValidRange(input: String): Boolean = js.native

    def isOpen(): Boolean = js.native

    def isValid(value: String): Boolean = js.native

    def openPicker(): Unit = js.native

    def create(opts: js.Object): Unit = js.native
  }

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("easepick")

  @js.native
  @JSImport(
    "@vaadin/date-picker@24.4.3/+esm",
    JSImport.Namespace
  )
  object RawImport extends js.Object
}

object Foo {

  def main(args: Array[String]): Unit = {
    val value1 = Var(5.0)
    val value2 = Var(10.0)
    val data = Var(List(2.4, 3.4, 5.1, -2.3))
    renderOnDomContentLoaded(
      dom.document.getElementById("app"),
      div(
        h1("Hello Laminar!"),
        div(
          input(idAttr := "input1"),
          sl.Input(),
          DatePicker()
          // onMountCallback { _ =>
          //   EasepickWrapper.create(EasepickOptions.apply {
          //     val element = "#input1"
          //     val zIndex = 1000
          //   })
          // }
        )
        // p(
        //   child <-- dataSig.map { data =>
        //     val barChart: BarChart = data.plotBarChart(
        //       List(
        //         viz.Utils.fillDiv,
        //         (spec : ujson.Value) => spec.obj("background") = "rgb(0, 0, 0, 0)"
        //       )
        //     )
        //     val theme = EmbedOptions(theme = "dark")
        //     LaminarViz.simpleEmbed(barChart, Some(chartDiv), Option(theme))
        //   },
        // ),
      )
    )
  }
}
