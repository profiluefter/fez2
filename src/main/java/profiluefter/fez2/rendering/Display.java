package profiluefter.fez2.rendering;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.GL_TRUE;
import static org.lwjgl.opengl.GL11.glViewport;

public class Display {
	private static boolean initialized = false;
	@SuppressWarnings({"FieldCanBeLocal", "unused"})
	private static GLFWErrorCallback errorCallback;
	private static final int WINDOW_WIDTH = 720;
	private static final int WINDOW_HEIGHT = 720;
	private static long window;

	private static void init() {
		glfwInit();
		glfwSetErrorCallback(errorCallback = GLFWErrorCallback.createPrint(System.err));
		initialized = true;
	}

	public static void create() {
		if(!initialized) init();
		glfwWindowHint(GLFW_RESIZABLE, GL_TRUE);
		glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
		glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
		glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
		window = glfwCreateWindow(WINDOW_WIDTH, WINDOW_HEIGHT, "Fez 2", 0, 0);
		if(window == 0) {
			throw new RuntimeException("Failed to create window");
		}
		glfwMakeContextCurrent(window);
		GL.createCapabilities();
		glViewport(0,0,WINDOW_WIDTH,WINDOW_HEIGHT);
		glfwShowWindow(window);
	}

	public static void update() {
		glfwPollEvents();
		glfwSwapBuffers(window);
	}

	public static void destroy() {
		glfwDestroyWindow(window);
	}

	public static long getWindow() {
		return window;
	}
}
