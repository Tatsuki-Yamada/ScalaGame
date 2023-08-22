package screen

import scala.swing._

object ScreenDrawer extends SimpleSwingApplication {
  var drawTargetObjects: Seq[DrawableObject] = Seq.empty

  def top: MainFrame = new MainFrame {
    title = "Window Title"
    minimumSize = new Dimension(300, 200)
  }

  def addDrawObject(target: DrawableObject): Unit = {
    drawTargetObjects = drawTargetObjects :+ target
  }

  def addDrawObjects(target: Seq[DrawableObject]): Unit = {
    drawTargetObjects = drawTargetObjects ++ target
  }

  def drawAll(): Unit = {
    for (drawTarget <- drawTargetObjects) {
      drawTarget.draw()
    }
  }

  def debug_addDummyData(): Unit = {
    val newSeq: Seq[DrawableObject] = Seq(
      new TestA(),
      new TestA(),
      new TestA()
    )
    addDrawObjects(newSeq)
  }

  debug_addDummyData()

  drawAll()
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
