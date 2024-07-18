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


object Foo {

  def main(args: Array[String]): Unit = {
    val value1 = Var(5.0)
    val value2 = Var(10.0)
    val data = Var(List(2.4, 3.4, 5.1, -2.3))
    renderOnDomContentLoaded(
      dom.document.getElementById("app"),
      div(
        h1("Hello Laminar and ui5 webcomponents!"),
        p("plus fast reload"),
        div(
          input(idAttr := "input1"),
          sl.Input()
        )
      )
    )
  }
}
