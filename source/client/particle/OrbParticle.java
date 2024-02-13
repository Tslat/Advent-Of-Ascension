package net.tslat.aoa3.client.particle;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.util.Mth;
import net.tslat.aoa3.common.particletype.CustomisableParticleType;
import org.jetbrains.annotations.Nullable;


public class OrbParticle extends TextureSheetParticle {
	private final SpriteSet sprite;
	private float scale;
	private int inGroundTime = 0;

	public OrbParticle(ClientLevel world, double posX, double posY, double posZ, double velocityX, double velocityY, double velocityZ, SpriteSet sprite, float scale, float ageModifier, float red, float green, float blue, float alpha) {
		super(world, posX, posY, posZ, velocityX, velocityY, velocityZ);

		this.sprite = sprite;
		this.xd = velocityX;
		this.yd = velocityY;
		this.zd = velocityZ;
		this.scale = scale;
		this.quadSize = 0.25f * scale;
		this.lifetime = Mth.ceil(ageModifier * (1 + Mth.clamp(this.random.nextGaussian(), -0.5f, 0.5f)));
		this.gravity = 0.1f;
		this.rCol = red;
		this.gCol = green;
		this.bCol = blue;
		this.alpha = alpha;

		if (alpha == 0)
			this.alpha = 1f;

		setSpriteFromAge(sprite);
	}

	@Override
	public void tick() {
		this.xo = x;
		this.yo = y;
		this.zo = z;

		setSpriteFromAge(this.sprite);
		move(xd, yd, zd);

		this.xd *= 0.8999999761581421;
		this.yd *= 0.8999999761581421;
		this.zd *= 0.8999999761581421;
		this.yd -= gravity;

		if (onGround) {
			this.xd *= 0.699999988079071;
			this.zd *= 0.699999988079071;
			this.inGroundTime++;

			if (this.yd <= gravity && this.yd < -0.23f) {
				this.yd *= Math.max(-0.95f, -0.40f + this.scale / 10f);
			}
		}
		else {
			this.inGroundTime = 0;
		}

		if (this.inGroundTime > this.scale * 10)
			scale(0.7f);

		if (age++ >= lifetime || this.inGroundTime > this.scale * 100)
			remove();
	}

	@Override
	public void render(VertexConsumer pBuffer, Camera pRenderInfo, float pPartialTicks) {
		RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE_MINUS_SRC_COLOR);
		this.roll = 0.25f;
		super.render(pBuffer, pRenderInfo, pPartialTicks);
	}

	@Override
	public ParticleRenderType getRenderType() {
		return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
	}

	@Override
	public Particle scale(float scale) {
		this.scale = scale;

		return super.scale(scale);
	}

	@Override
	protected int getLightColor(float partialTick) {
		return LightTexture.FULL_BRIGHT - (int)(partialTick * 34);
	}

	public static class Factory implements ParticleProvider<CustomisableParticleType.Data> {
		private final SpriteSet sprite;

		public Factory(SpriteSet sprite) {
			this.sprite = sprite;
		}

		@Nullable
		@Override
		public Particle createParticle(CustomisableParticleType.Data data, ClientLevel world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
			return new OrbParticle(world, x, y, z, velocityX, velocityY, velocityZ, sprite, data.scale, data.ageModifier, data.red, data.green, data.blue, data.alpha);
		}
	}
}