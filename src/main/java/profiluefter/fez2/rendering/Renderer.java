package profiluefter.fez2.rendering;

import org.joml.Matrix4f;
import profiluefter.fez2.entities.Entity;
import profiluefter.fez2.rendering.models.TexturedModel;
import profiluefter.fez2.rendering.shader.StaticShader;
import profiluefter.fez2.tools.Maths;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

public class Renderer {

	public void prepare() {
		glClearColor(0.4352941176470588f,0.7647058823529412f,0.8745098039215686f,1f);
		glClear(GL_COLOR_BUFFER_BIT);
	}

	public void render(Entity entity, StaticShader shader) {
		TexturedModel model = entity.getModel();
		glBindVertexArray(model.getRawModel().getVaoID());
		glEnableVertexAttribArray(0);
		glEnableVertexAttribArray(1);
		Matrix4f transformationMatrix = Maths.createTransformationMatrix(entity.getPosition(),entity.getRotX(),entity.getRotY(),entity.getRotZ(),entity.getScale());
		shader.loadTransformationMatrix(transformationMatrix);
		glActiveTexture(GL_TEXTURE0);
		glBindTexture(GL_TEXTURE_2D, model.getTexture().getTextureID());
		glDrawElements(GL_TRIANGLES, model.getRawModel().getVertexCount(), GL_UNSIGNED_INT, 0);
		glDisableVertexAttribArray(0);
		glDisableVertexAttribArray(1);
		glBindVertexArray(0);
	}

}
