package net.tslat.aoa3.client.render.entity.animal;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.client.model.entity.animal.ShinySquidModel;
import net.tslat.aoa3.object.entity.animal.ShinySquidEntity;

public class ShinySquidRenderer extends MobRenderer<ShinySquidEntity, ShinySquidModel> {
	private static final ResourceLocation TEXTURE = AdventOfAscension.id("textures/entity/animal/shiny_squid.png");

	public ShinySquidRenderer(EntityRendererManager renderManager) {
		super(renderManager, new ShinySquidModel(), 0.7f);
	}

	@Override
	protected void setupRotations(ShinySquidEntity squid, MatrixStack matrixStack, float pAgeInTicks, float pRotationYaw, float partialTicks) {
		matrixStack.translate(0, 0.5d, 0);
		matrixStack.mulPose(Vector3f.YP.rotationDegrees(180 - pRotationYaw));
		matrixStack.mulPose(Vector3f.XP.rotationDegrees(MathHelper.lerp(partialTicks, squid.xBodyRotO, squid.xBodyRot)));
		matrixStack.mulPose(Vector3f.YP.rotationDegrees(MathHelper.lerp(partialTicks, squid.zBodyRotO, squid.zBodyRot)));
		matrixStack.translate(0, -1.2f, 0);
	}

	@Override
	protected float getBob(ShinySquidEntity squid, float partialTicks) {
		return MathHelper.lerp(partialTicks, squid.oldTentacleAngle, squid.tentacleAngle);
	}

	@Override
	public ResourceLocation getTextureLocation(ShinySquidEntity pEntity) {
		return TEXTURE;
	}
}
