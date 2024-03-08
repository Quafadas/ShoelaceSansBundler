//> using scala 3.3.1
//> using platform js

//> using dep com.raquo::laminar-shoelace::0.1.0-M2
//> using dep com.raquo::laminar::17.0.0-M6

//> using dep io.github.quafadas::dedav4s::0.9-5498682-20240308T115005Z-SNAPSHOT
//> using dep io.github.quafadas::dedav_laminar::0.9-5498682-20240308T115005Z-SNAPSHOT

//> using jsModuleKind es
//> using jsEsModuleImportMap importmap.json
//> using jsModuleSplitStyleStr smallmodulesfor
//> using jsSmallModuleForPackage frontend

package frontend

import com.raquo.laminar.shoelace.sl.Input
import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.typedarray.Float64Array
import scala.scalajs.js.annotation.JSGlobal
import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.typedarray.Float64Array
import com.raquo.laminar.api.L.{renderOnDomContentLoaded,Var,div, h1, idAttr, tpe,width, value, onInput, placeholder, p, child, height, given}
import org.scalajs.dom
import com.raquo.airstream.core.Signal

import viz.extensions.RawIterables.*
import viz.Utils
import viz.LaminarViz
import viz.vega.plots.{BarChart, given}
import viz.vega.facades.EmbedOptions
import scala.scalajs.js.JSON

object Foo {
  def main(args: Array[String]): Unit =
    val value1 = Var(5.0)
    val value2 = Var(10.0)
    val data = Var(List(2.4, 3.4, 5.1, -2.3))
    val chartDiv = div(
      width := "40vmin",
      height := "40vmin",
    )
    renderOnDomContentLoaded(
      dom.document.getElementById("app"),
      div(
        h1("Hello Laminar!"),
          div(
          Input(
            idAttr := "input1",
            tpe := "number",
            width := "100px",
            _.label := "First number",
            value <-- value1.signal.map(_.toString),
            onInput.mapToValue.map(
              _.filter(Character.isDigit)
            ) --> value1.writer.contramap{(s: String) => s.toDouble}

          ),
          Input(
            idAttr := "input2",
            tpe := "number",
            width := "100px",
            _.label := "Second, glorious number",
            value <-- value2.signal.map(_.toString),
            onInput.mapToValue.map(
              _.filter(Character.isDigit)
            ) --> value2.writer.contramap{(s: String) => s.toDouble}

          ),
          p(
            "Multipled together : ",
            p(
              idAttr := "mult",
              child.text <-- value1.signal.combineWith(value2.signal).map{ (a,b) => (a * b).toString()}
            )
          ),
          p("test charting"),
          p(
            child <-- data.signal.map { data =>
              val barChart: BarChart = data.plotBarChart(
                List(
                  viz.Utils.fillDiv,
                  (spec : ujson.Value) => spec.obj("background") = "rgb(0, 0, 0, 0)"
                )
              )
              val theme = EmbedOptions(theme = "dark")
              println(JSON.stringify(theme))
              LaminarViz.simpleEmbed(barChart, Some(chartDiv), Option(theme))
            },
          ),
        )
      )
    )
}