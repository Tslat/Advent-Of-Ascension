package net.tslat.aoa3.client.render.entity.misc;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.FishRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.item.FishingRodItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.HandSide;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Matrix3f;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.client.event.RenderNameplateEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.Event;

public class HaulingBobberRenderer extends FishRenderer {
	private final ResourceLocation TEXTURE;
	private RenderType renderType = null;

	public HaulingBobberRenderer(EntityRendererManager rendererManager) {
		this(rendererManager, new ResourceLocation("textures/entity/fishing_hook.png"));
	}

	public HaulingBobberRenderer(EntityRendererManager renderManager, ResourceLocation texture) {
		super(renderManager);

		this.TEXTURE = texture;
	}

	public void render(FishingBobberEntity bobber, float yaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer renderTypeBuffer, int packedLight) {
		PlayerEntity player = bobber.getPlayerOwner();

		if (player != null) {
			ResourceLocation texture = getTextureLocation(bobber);

			if (renderType == null || texture != TEXTURE)
				renderType = RenderType.entityCutout(getTextureLocation(bobber));

			matrixStack.pushPose();
			matrixStack.pushPose();
			matrixStack.scale(0.5F, 0.5F, 0.5F);
			matrixStack.mulPose(this.entityRenderDispatcher.cameraOrientation());
			matrixStack.mulPose(Vector3f.YP.rotationDegrees(180.0F));

			MatrixStack.Entry lastPose = matrixStack.last();
			Matrix4f pose = lastPose.pose();
			Matrix3f normalisedPose = lastPose.normal();
			IVertexBuilder vertexBuilder = renderTypeBuffer.getBuffer(renderType);

			vertex(vertexBuilder, pose, normalisedPose, packedLight, 0, 0, 0, 1);
			vertex(vertexBuilder, pose, normalisedPose, packedLight, 1, 0, 1, 1);
			vertex(vertexBuilder, pose, normalisedPose, packedLight, 1, 1, 1, 0);
			vertex(vertexBuilder, pose, normalisedPose, packedLight, 0, 1, 0, 0);

			matrixStack.popPose();

			int armSideMod = player.getMainArm() == HandSide.RIGHT ? 1 : -1;
			ItemStack heldStack = player.getMainHandItem();

			if (!(heldStack.getItem() instanceof FishingRodItem))
				armSideMod = -armSideMod;

			double rodTipPosX;
			double rodTipPosY;
			double rodTipPosZ;
			float playerPoseAdjust;

			if ((this.entityRenderDispatcher.options == null || this.entityRenderDispatcher.options.getCameraType().isFirstPerson()) && player == Minecraft.getInstance().player) {
				double fov = this.entityRenderDispatcher.options.fov / 100d;
				float attackAnimRot = MathHelper.sin(MathHelper.sqrt(player.getAttackAnim(partialTicks)) * (float)Math.PI);
				Vector3d lineSideVec = new Vector3d((double)armSideMod * -0.36D * fov, -0.045D * fov, 0.4D);
				lineSideVec = lineSideVec.xRot(-MathHelper.lerp(partialTicks, player.xRotO, player.xRot) * ((float)Math.PI / 180F));
				lineSideVec = lineSideVec.yRot(-MathHelper.lerp(partialTicks, player.yRotO, player.yRot) * ((float)Math.PI / 180F));
				lineSideVec = lineSideVec.yRot(attackAnimRot * 0.5F);
				lineSideVec = lineSideVec.xRot(-attackAnimRot * 0.7F);
				rodTipPosX = MathHelper.lerp(partialTicks, player.xo, player.getX()) + lineSideVec.x;
				rodTipPosY = MathHelper.lerp(partialTicks, player.yo, player.getY()) + lineSideVec.y;
				rodTipPosZ = MathHelper.lerp(partialTicks, player.zo, player.getZ()) + lineSideVec.z;
				playerPoseAdjust = player.getEyeHeight();
			}
			else {
				float bodyRotPos = MathHelper.lerp(partialTicks, player.yBodyRotO, player.yBodyRot) * ((float)Math.PI / 180F);
				double rodTipBodyRotXAdjust = MathHelper.sin(bodyRotPos);
				double rodTipBodyRotZAdjust = MathHelper.cos(bodyRotPos);
				double armSideAdjust = (double)armSideMod * 0.35d;
				rodTipPosX = MathHelper.lerp(partialTicks, player.xo, player.getX()) - rodTipBodyRotZAdjust * armSideAdjust - rodTipBodyRotXAdjust * 0.8D;
				rodTipPosY = player.yo + (double)player.getEyeHeight() + (player.getY() - player.yo) * (double)partialTicks - 0.45D;
				rodTipPosZ = MathHelper.lerp(partialTicks, player.zo, player.getZ()) - rodTipBodyRotXAdjust * armSideAdjust + rodTipBodyRotZAdjust * 0.8D;
				playerPoseAdjust = player.isCrouching() ? -0.1875F : 0.0F;
			}

			float lineStartX = (float)(rodTipPosX - MathHelper.lerp(partialTicks, bobber.xo, bobber.getX()));
			float lineStartY = (float)(rodTipPosY - (MathHelper.lerp(partialTicks, bobber.yo, bobber.getY()) + 0.25D)) + playerPoseAdjust;
			float lineStartZ = (float)(rodTipPosZ - MathHelper.lerp(partialTicks, bobber.zo, bobber.getZ()));
			IVertexBuilder lineVertexBuilder = renderTypeBuffer.getBuffer(RenderType.lines());
			Matrix4f prevPose = matrixStack.last().pose();

			for(int i = 0; i < 16; ++i) {
				stringVertex(lineStartX, lineStartY, lineStartZ, lineVertexBuilder, prevPose, i / 16f);
				stringVertex(lineStartX, lineStartY, lineStartZ, lineVertexBuilder, prevPose, (i + 1) / 16f);
			}

			matrixStack.popPose();

			RenderNameplateEvent renderNameplateEvent = new RenderNameplateEvent(bobber, bobber.getDisplayName(), this, matrixStack, renderTypeBuffer, packedLight, partialTicks);

			MinecraftForge.EVENT_BUS.post(renderNameplateEvent);

			if (renderNameplateEvent.getResult() != Event.Result.DENY && (renderNameplateEvent.getResult() == Event.Result.ALLOW || this.shouldShowName(bobber)))
				this.renderNameTag(bobber, renderNameplateEvent.getContent(), matrixStack, renderTypeBuffer, packedLight);
		}
	}

	private static void vertex(IVertexBuilder vertexBuilder, Matrix4f pose, Matrix3f normalisedPose, int packedLight, float x, int y, int u, int v) {
		vertexBuilder.vertex(pose, x - 0.5F, (float)y - 0.5F, 0.0F).color(255, 255, 255, 255).uv((float)u, (float)v).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(normalisedPose, 0.0F, 1.0F, 0.0F).endVertex();
	}

	private static void stringVertex(float x, float y, float z, IVertexBuilder vertexBuilder, Matrix4f pose, float lerpSection) {
		vertexBuilder.vertex(pose, x * lerpSection, y * (lerpSection * lerpSection + lerpSection) * 0.5F + 0.25F, z * lerpSection).color(0, 0, 0, 255).endVertex();
	}

	@Override
	public ResourceLocation getTextureLocation(FishingBobberEntity bobber) {
		return TEXTURE;
	}
}
