package net.tslat.aoa3.client.model.misc;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;

public class FullbrightModelRenderer extends ModelRenderer {
	public FullbrightModelRenderer(Model model) {
		super(model);
	}

	public FullbrightModelRenderer(Model model, int uOffset, int vOffset) {
		super(model, uOffset, vOffset);
	}

	public FullbrightModelRenderer(int textureWidth, int textureHeight, int uOffset, int vOffset) {
		super(textureWidth, textureHeight, uOffset, vOffset);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder vertexBuilder, int pPackedLight, int pPackedOverlay, float pRed, float pGreen, float pBlue, float pAlpha) {
		super.render(matrixStack, vertexBuilder, 15728880, pPackedOverlay, pRed, pGreen, pBlue, pAlpha);
	}
}
