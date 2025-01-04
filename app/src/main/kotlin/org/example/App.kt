package org.example

import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL.createCapabilities
import org.lwjgl.opengl.GL11.*
import org.lwjgl.opengl.awt.AWTGLCanvas
import org.lwjgl.opengl.awt.GLData
import java.awt.BorderLayout
import java.awt.Dimension
import javax.swing.JFrame
import javax.swing.SwingUtilities
import kotlin.math.abs
import kotlin.math.sin


fun main() {
    val frame = JFrame("AWT test")
    frame.defaultCloseOperation = JFrame.DISPOSE_ON_CLOSE
    frame.layout = BorderLayout()
    frame.preferredSize = Dimension(600, 600)
    val data = GLData()
    val canvas: AWTGLCanvas
    frame.add(object : AWTGLCanvas(data) {
        private val serialVersionUID = 1L
        override fun initGL() {
            println("OpenGL version: " + effective.majorVersion + "." + effective.minorVersion + " (Profile: " + effective.profile + ")")
            createCapabilities()
            glClearColor(0.3f, 0.4f, 0.5f, 1f)
        }

        override fun paintGL() {
            val w = framebufferWidth
            val h = framebufferHeight
            val aspect = w.toFloat() / h
            val now = System.currentTimeMillis() * 0.001
            val width = abs(sin(now * 0.3)).toFloat()
            glClear(GL_COLOR_BUFFER_BIT)
            glViewport(0, 0, w, h)
            glBegin(GL_QUADS)
            glColor3f(0.4f, 0.6f, 0.8f)
            glVertex2f(-0.75f * width / aspect, 0.0f)
            glVertex2f(0f, -0.75f)
            glVertex2f(+0.75f * width / aspect, 0f)
            glVertex2f(0f, +0.75f)
            glEnd()
            swapBuffers()
        }
    }.also { canvas = it }, BorderLayout.CENTER)
    frame.pack()
    frame.isVisible = true
    frame.transferFocus()
    val renderLoop: Runnable = object : Runnable {
        override fun run() {
            if (!canvas.isValid) {
                GL.setCapabilities(null)
                return
            }
            canvas.render()
            SwingUtilities.invokeLater(this)
        }
    }
    SwingUtilities.invokeLater(renderLoop)
}