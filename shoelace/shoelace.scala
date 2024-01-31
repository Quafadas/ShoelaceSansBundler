
import com.raquo.laminar.shoelace.sl.Input
import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.typedarray.Float64Array
import scala.scalajs.js.annotation.JSGlobal
import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.typedarray.Float64Array
import com.raquo.laminar.api.L.{*, given}
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

            value <-- value1.signal.map(_.toString),
            onInput.mapToValue.map(
              _.filter(Character.isDigit)
            ) --> value1.writer.contramap{(s: String) => s.toDouble}

          ),
          Input(
            placeholder := "Linspace from 0 to ",
            idAttr := "input2",
            tpe := "number",

            value <-- value2.signal.map(_.toString),
            onInput.mapToValue.map(
              _.filter(Character.isDigit)
            ) --> value2.writer.contramap{(s: String) => s.toDouble}

          ),
          p(
            "Multiplication: ",
            p(
              idAttr := "mult",
              child.text <-- value1.signal.combineWith(value2.signal).map{ (a,b) => (a * b).toString()}
            )
          )
        )
      )
    )
}