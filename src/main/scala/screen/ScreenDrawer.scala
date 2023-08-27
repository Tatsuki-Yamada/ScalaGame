package screen

import scalafx.Includes._
import scalafx.application.JFXApp3
import scalafx.application.JFXApp3.PrimaryStage
import scalafx.scene.{Node, Scene}
import scalafx.scene.control.Button
import scalafx.scene.layout.{StackPane, VBox}
import scalafx.scene.paint.Color._
import scalafx.scene.shape._

import scala.collection.immutable.ListMap
import scala.collection.mutable.ListBuffer

object ScreenDrawer extends JFXApp3 {
  private var drawTargetObjectNodes: ListMap[Int, ListBuffer[Node]] =
    ListMap.empty

  override def start(): Unit = {
    debug_addButton()

    stage = new PrimaryStage {
      title.value = "Hello Stage"
      width = 600
      height = 480
      scene = draw()
    }
  }

  def addDrawTargetObject(objectNode: Node, priority: Int = 0): Unit = {
    drawTargetObjectNodes.get(priority) match {
      case Some(nodeList) =>
        nodeList += objectNode
      case None => {
        drawTargetObjectNodes =
          drawTargetObjectNodes.updated(priority, ListBuffer.empty += objectNode)
      }
    }

    drawTargetObjectNodes = ListMap(
      drawTargetObjectNodes.toSeq.sortBy(_._1): _*
    )
  }

  def draw(): Scene = {
    new Scene {
      val vBox = new VBox

      drawTargetObjectNodes.foreach {
        _._2.foreach {
          vBox.children.add(_)
        }
      }

      content = vBox
    }
  }

  def debug_addButton(): Unit = {
    val button1 = new Button("Button 1")
    val button2 = new Button("Button 2")

    addDrawTargetObject(button1)
    addDrawTargetObject(button2)
  }

}

class TestA extends DrawableObject {
  override val drawPositions: (Int, Int) = (2, 3)
}

trait DrawableObject {
  val drawPositions: (Int, Int)
  val body: String = "あ"

  def draw(): Unit = {
    println(body)
  }
}
