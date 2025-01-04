package org.example

import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL.createCapabilities
import org.lwjgl.opengl.GL11.*
import org.lwjgl.opengl.awt.AWTGLCanvas
import java.awt.BorderLayout
import java.awt.Dimension
import javax.swing.JFrame
import javax.swing.SwingUtilities
import kotlin.math.abs
import kotlin.math.sin

fun main() {
    JFrame("AWT test").apply {
        defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        layout = BorderLayout()
        preferredSize = Dimension(600, 600)
        val demo = OpenGlDemo()
        add(demo, BorderLayout.CENTER)
        pack()
        isVisible = true
        transferFocus()
        SwingUtilities.invokeLater(OpenGlDemo.RenderLoop(demo))
    }
}

class OpenGlDemo : AWTGLCanvas() {
    override fun initGL() {
        createCapabilities()
        glClearColor(0.3f, 0.4f, 0.5f, 1f)
    }

    override fun paintGL() {
        glClear(GL_COLOR_BUFFER_BIT)
        glViewport(0, 0, framebufferWidth, framebufferHeight)

        val aspect = framebufferWidth.toFloat() / framebufferHeight
        val shapeWidth = abs(sin(System.currentTimeMillis() * 0.001 * 0.3)).toFloat()
        glBegin(GL_QUADS)
        glColor3f(0.4f, 0.6f, 0.8f)
        glVertex2f(-0.75f * shapeWidth / aspect, 0.0f)
        glVertex2f(0f, -0.75f)
        glVertex2f(+0.75f * shapeWidth / aspect, 0f)
        glVertex2f(0f, +0.75f)
        glEnd()

        swapBuffers()
    }

    class RenderLoop(private val demo: OpenGlDemo) : Runnable {
        override fun run() {
            if (demo.isValid) {
                demo.render()
                SwingUtilities.invokeLater(this)
            } else {
                GL.setCapabilities(null)
            }
        }
    }
}