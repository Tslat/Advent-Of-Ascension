package net.tslat.aoa3.client.model.misc;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;

import java.util.List;
import java.util.Map;

public class FullbrightModelPart extends ModelPart {
	public FullbrightModelPart(List<Cube> cubes, Map<String, ModelPart> children) {
		super(cubes, children);
	}

	@Override
	public void render(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		super.render(poseStack, vertexConsumer, 15728880, packedOverlay, red, green, blue, alpha);
	}
}
