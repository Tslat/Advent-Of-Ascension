package net.tslat.aoa3.client.render.entity.animal;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.client.model.AoAEntityRendering;
import net.tslat.aoa3.client.model.entity.animal.ShinySquidModel;
import net.tslat.aoa3.content.entity.animal.ShinySquidEntity;

public class ShinySquidRenderer extends MobRenderer<ShinySquidEntity, ShinySquidModel> {
	private static final ResourceLocation TEXTURE = AdventOfAscension.id("textures/entity/animal/shiny_squid.png");

	public ShinySquidRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new ShinySquidModel(renderManager.bakeLayer(AoAEntityRendering.SHINY_SQUID.getMainLayerLocation())), 0.7f);
	}

	@Override
	protected void setupRotations(ShinySquidEntity squid, PoseStack matrixStack, float pAgeInTicks, float pRotationYaw, float partialTicks) {
		matrixStack.translate(0, 0.5d, 0);
		matrixStack.mulPose(Vector3f.YP.rotationDegrees(180 - pRotationYaw));
		matrixStack.mulPose(Vector3f.XP.rotationDegrees(Mth.lerp(partialTicks, squid.xBodyRotO, squid.xBodyRot)));
		matrixStack.mulPose(Vector3f.YP.rotationDegrees(Mth.lerp(partialTicks, squid.zBodyRotO, squid.zBodyRot)));
		matrixStack.translate(0, -1.2f, 0);
	}

	@Override
	protected float getBob(ShinySquidEntity squid, float partialTicks) {
		return Mth.lerp(partialTicks, squid.oldTentacleAngle, squid.tentacleAngle);
	}

	@Override
	public ResourceLocation getTextureLocation(ShinySquidEntity pEntity) {
		return TEXTURE;
	}
}
