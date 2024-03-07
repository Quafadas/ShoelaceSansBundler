//> using scala 3.4.0
//> using platform js
//> using dep com.raquo::laminar-shoelace::0.1.0-M2
//> using dep com.raquo::laminar::17.0.0-M6
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
import com.raquo.laminar.api.L.{renderOnDomContentLoaded,Var,div, h1, idAttr, tpe,width, value, onInput, placeholder, p, child, given}
import org.scalajs.dom
import com.raquo.airstream.core.Signal



object Foo {
  def main(args: Array[String]): Unit =
    val value1 = Var(5.0)
    val value2 = Var(10.0)
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
          )
        )
      )
    )
}