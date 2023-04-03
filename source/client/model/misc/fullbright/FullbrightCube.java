package net.tslat.aoa3.client.model.misc.fullbright;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.core.Direction;

import java.util.Set;

public class FullbrightCube extends ModelPart.Cube {
	public FullbrightCube(int u, int v, float minX, float minY, float minZ, float width, float height, float depth, float scaleX, float scaleY, float scaleZ, boolean mirror, float uWidth, float vHeight, Set<Direction> visibleFaces) {
		super(u, v, minX, minY, minZ, width, height, depth, scaleX, scaleY, scaleZ, mirror, uWidth, vHeight, visibleFaces);
	}

	@Override
	public void compile(PoseStack.Pose pPose, VertexConsumer pVertexConsumer, int pPackedLight, int pPackedOverlay, float pRed, float pGreen, float pBlue, float pAlpha) {
		super.compile(pPose, pVertexConsumer, LightTexture.FULL_BRIGHT, pPackedOverlay, pRed, pGreen, pBlue, pAlpha);
	}
}
