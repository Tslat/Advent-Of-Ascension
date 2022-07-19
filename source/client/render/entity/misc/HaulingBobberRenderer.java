package net.tslat.aoa3.client.render.entity.misc;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Matrix3f;
import com.mojang.math.Matrix4f;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.FishingHookRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.item.FishingRodItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.event.RenderNameTagEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.Event;

public class HaulingBobberRenderer extends FishingHookRenderer {
	private final ResourceLocation texture;
	private final RenderType renderType;

	public HaulingBobberRenderer(EntityRendererProvider.Context rendererManager) {
		this(rendererManager, new ResourceLocation("textures/entity/fishing_hook.png"));
	}

	public HaulingBobberRenderer(EntityRendererProvider.Context renderManager, ResourceLocation texture) {
		super(renderManager);

		this.texture = texture;
		this.renderType = RenderType.entityCutout(texture);
	}

	@Override
	public void render(FishingHook bobber, float entityYaw, float partialTicks, PoseStack matrix, MultiBufferSource buffer, int packedLight) {
		Player player = bobber.getPlayerOwner();

		if (player == null)
			return;

		matrix.pushPose();
		matrix.pushPose();
		matrix.scale(0.5f, 0.5f, 0.5f);
		matrix.mulPose(this.entityRenderDispatcher.cameraOrientation());
		matrix.mulPose(Vector3f.YP.rotationDegrees(180f));

		PoseStack.Pose lastPose = matrix.last();
		Matrix4f lastPose4f = lastPose.pose();
		Matrix3f lastPoseNormal = lastPose.normal();
		VertexConsumer vertexConsumer = buffer.getBuffer(renderType);

		vertex(vertexConsumer, lastPose4f, lastPoseNormal, packedLight, 0, 0, 0, 1);
		vertex(vertexConsumer, lastPose4f, lastPoseNormal, packedLight, 1, 0, 1, 1);
		vertex(vertexConsumer, lastPose4f, lastPoseNormal, packedLight, 1, 1, 1, 0);
		vertex(vertexConsumer, lastPose4f, lastPoseNormal, packedLight, 0, 1, 0, 0);
		matrix.popPose();

		int sideMod = player.getMainArm() == HumanoidArm.RIGHT ? 1 : -1;
		ItemStack stack = player.getMainHandItem();

		if (!(stack.getItem() instanceof FishingRodItem))
			sideMod = -sideMod;

		float swingTime = player.getAttackAnim(partialTicks);
		float swingAngle = Mth.sin(Mth.sqrt(swingTime) * (float)Math.PI);
		float bodyRot = Mth.lerp(partialTicks, player.yBodyRotO, player.yBodyRot) * ((float)Math.PI / 180F);
		double sinRot = Mth.sin(bodyRot);
		double cosRot = Mth.cos(bodyRot);
		double sideMod2 = (double)sideMod * 0.35d;
		double startX;
		double startY;
		double startZ;
		float eyeHeight;

		if ((this.entityRenderDispatcher.options == null || this.entityRenderDispatcher.options.getCameraType().isFirstPerson()) && player == Minecraft.getInstance().player) {
			double fovScale = 960d / (float)this.entityRenderDispatcher.options.fov().get();
			Vec3 firstPersonStartPos = this.entityRenderDispatcher.camera.getNearPlane().getPointOnPlane((float)sideMod * 0.525f, -0.1f);
			firstPersonStartPos = firstPersonStartPos.scale(fovScale);
			firstPersonStartPos = firstPersonStartPos.yRot(swingAngle * 0.5f);
			firstPersonStartPos = firstPersonStartPos.xRot(-swingAngle * 0.7f);
			startX = Mth.lerp(partialTicks, player.xo, player.getX()) + firstPersonStartPos.x;
			startY = Mth.lerp(partialTicks, player.yo, player.getY()) + firstPersonStartPos.y;
			startZ = Mth.lerp(partialTicks, player.zo, player.getZ()) + firstPersonStartPos.z;
			eyeHeight = player.getEyeHeight();
		}
		else {
			startX = Mth.lerp(partialTicks, player.xo, player.getX()) - cosRot * sideMod2 - sinRot * 0.8d;
			startY = player.yo + (double)player.getEyeHeight() + (player.getY() - player.yo) * (double)partialTicks - 0.45d;
			startZ = Mth.lerp(partialTicks, player.zo, player.getZ()) - sinRot * sideMod2 + cosRot * 0.8d;
			eyeHeight = player.isCrouching() ? -0.1875f : 0;
		}

		double bobberX = Mth.lerp(partialTicks, bobber.xo, bobber.getX());
		double bobberY = Mth.lerp(partialTicks, bobber.yo, bobber.getY()) + 0.25D;
		double bobberZ = Mth.lerp(partialTicks, bobber.zo, bobber.getZ());
		float distX = (float)(startX - bobberX);
		float distY = (float)(startY - bobberY) + eyeHeight;
		float distZ = (float)(startZ - bobberZ);
		vertexConsumer = buffer.getBuffer(RenderType.lineStrip());
		lastPose = matrix.last();

		for(int section = 0; section <= 16; ++section) {
			stringVertex(distX, distY, distZ, vertexConsumer, lastPose, section / 16f, (section + 1) / 16f);
		}

		matrix.popPose();

		RenderNameTagEvent renderNameplateEvent = new RenderNameTagEvent(bobber, bobber.getDisplayName(), this, matrix, buffer, packedLight, partialTicks);

		MinecraftForge.EVENT_BUS.post(renderNameplateEvent);

		if (renderNameplateEvent.getResult() != Event.Result.DENY && (renderNameplateEvent.getResult() == Event.Result.ALLOW || this.shouldShowName(bobber)))
			this.renderNameTag(bobber, renderNameplateEvent.getContent(), matrix, buffer, packedLight);
	}

	private static void vertex(VertexConsumer vertexConsumer, Matrix4f matrixPos, Matrix3f matrixNormal, int packedLight, float x, int y, int u, int v) {
		vertexConsumer.vertex(matrixPos, x - 0.5F, (float)y - 0.5f, 0).color(255, 255, 255, 255).uv((float)u, (float)v).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(matrixNormal, 0, 1, 0).endVertex();
	}

	private static void stringVertex(float lengthX, float lengthY, float lengthZ, VertexConsumer vertexConsumer, PoseStack.Pose pose, float sectionStart, float sectionEnd) {
		float x = lengthX * sectionStart;
		float y = lengthY * (sectionStart * sectionStart + sectionStart) * 0.5f + 0.25f;
		float z = lengthZ * sectionStart;
		float xNormalised = lengthX * sectionEnd - x;
		float yNormalised = lengthY * (sectionEnd * sectionEnd + sectionEnd) * 0.5f + 0.25f - y;
		float zNormalised = lengthZ * sectionEnd - z;
		float hypot = Mth.sqrt(xNormalised * xNormalised + yNormalised * yNormalised + zNormalised * zNormalised);
		xNormalised /= hypot;
		yNormalised /= hypot;
		zNormalised /= hypot;

		vertexConsumer.vertex(pose.pose(), x, y, z).color(0, 0, 0, 255).normal(pose.normal(), xNormalised, yNormalised, zNormalised).endVertex();
	}

	@Override
	public ResourceLocation getTextureLocation(FishingHook bobber) {
		return texture;
	}
}
