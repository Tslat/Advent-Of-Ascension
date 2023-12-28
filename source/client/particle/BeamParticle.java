package net.tslat.aoa3.client.particle;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.common.particletype.CustomisableParticleType;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.opengl.GL11;

import java.util.OptionalDouble;

public class BeamParticle extends Particle {
	private Vec3 endPos;
	private float scale;
	private Vec3 angle;

	public BeamParticle(ClientLevel world, double posX, double posY, double posZ, double velocityX, double velocityY, double velocityZ, float scale, float ageModifier) {
		super(world, posX, posY, posZ, velocityX, velocityY, velocityZ);

		this.xo = this.x;
		this.yo = this.y;
		this.zo = this.z;
		this.xd = 0;
		this.yd = 0;
		this.zd = 0;
		this.endPos = new Vec3(this.x + velocityX, this.y + velocityY, this.z + velocityZ);
		this.angle = this.endPos.subtract(this.x, this.y, this.z).normalize();
		this.lifetime = (int)ageModifier;
		this.rCol = 1;
		this.gCol = 1;
		this.bCol = 1;
		this.alpha = 1;
		this.scale = scale;
		this.hasPhysics = false;
	}

	@Override
	public ParticleRenderType getRenderType() {
		return ParticleRenderType.CUSTOM;
	}

	@Override
	public int getLightColor(float partialTick) {
		return super.getLightColor(partialTick);
	}

	@Override
	public void tick() {
		if (this.age++ >= this.lifetime)
			remove();
	}

	@Override
	public boolean shouldCull() {
		return false;
	}

	@Override
	public void render(VertexConsumer buffer, Camera camera, float partialTick) {
		final PoseStack poseStack = RenderSystem.getModelViewStack();
		final MultiBufferSource.BufferSource bufferSource = Minecraft.getInstance().renderBuffers().bufferSource();
		final float radius = (0.1f * this.scale) / 2f;
		Vec3 cameraPosition = camera.getPosition();
		float xPos = (float)(Mth.lerp(partialTick, this.xo, this.x));
		float yPos = (float)(Mth.lerp(partialTick, this.yo, this.y));
		float zPos = (float)(Mth.lerp(partialTick, this.zo, this.z));
		buffer = bufferSource.getBuffer(RenderType.create(
				"occult_block_lines", DefaultVertexFormat.POSITION_COLOR_NORMAL, VertexFormat.Mode.LINES, 256, true, false, RenderType.CompositeState.builder()
						.setShaderState(RenderStateShard.RENDERTYPE_LINES_SHADER)
						.setLineState(new RenderStateShard.LineStateShard(OptionalDouble.of(5)))
						.setTransparencyState(RenderStateShard.TRANSLUCENT_TRANSPARENCY)
						.setOutputState(RenderStateShard.OUTLINE_TARGET)
						.setWriteMaskState(RenderStateShard.COLOR_WRITE)
						.setCullState(RenderStateShard.NO_CULL)
						.createCompositeState(false)));

		poseStack.pushPose();
		poseStack.translate(-cameraPosition.x, -cameraPosition.y, -cameraPosition.z);

		final PoseStack.Pose pose = poseStack.last();

		RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
		RenderSystem.enableBlend();

		buffer.vertex(pose.pose(), xPos, yPos, zPos).color(this.rCol, this.gCol, this.bCol, this.alpha).normal(pose.normal(), (float)this.angle.x, (float)this.angle.y, (float)this.angle.z).endVertex();
		buffer.vertex(pose.pose(), (float)this.endPos.x, (float)this.endPos.y, (float)this.endPos.z).color(this.rCol, this.gCol, this.bCol, this.alpha).normal(pose.normal(), (float)this.angle.x, (float)this.angle.y, (float)this.angle.z).endVertex();

		poseStack.popPose();

		RenderSystem.polygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_FILL);
		RenderSystem.disableBlend();

		bufferSource.endBatch();
	}

	public static class Factory implements ParticleProvider<CustomisableParticleType.Data> {
		@Nullable
		@Override
		public Particle createParticle(CustomisableParticleType.Data data, ClientLevel world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
			return new BeamParticle(world, x, y, z, velocityX, velocityY, velocityZ, data.scale, data.ageModifier);
		}
	}
}