package net.tslat.aoa3.client.render.entities.projectiles.mob;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.ParticleRegister;
import net.tslat.aoa3.entity.projectiles.mob.EntityOmnilightShot;
import net.tslat.aoa3.library.Enums;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class OmnilightShotRenderer extends Render<EntityOmnilightShot> {
	private final ResourceLocation texture;

	public OmnilightShotRenderer(final RenderManager manager, final ResourceLocation textureResource) {
		super(manager);
		texture = textureResource;
	}

	@Override
	public void doRender(EntityOmnilightShot entity, double x, double y, double z, float entityYaw, float partialTicks) {
		super.doRender(entity, x, y, z, entityYaw, partialTicks);

		entity.world.spawnParticle(ParticleRegister.FLUFFY, entity.posX, entity.posY, entity.posZ, 0, 0, 0, Enums.RGBIntegers.CYAN, 100, 3, 5);
		entity.world.spawnParticle(ParticleRegister.FLUFFY, entity.posX, entity.posY + 0.25, entity.posZ, 0, 0, 0, Enums.RGBIntegers.YELLOW, 100, 3, 5);
		entity.world.spawnParticle(ParticleRegister.FLUFFY, entity.posX, entity.posY - 0.25, entity.posZ, 0, 0, 0, Enums.RGBIntegers.WHITE, 100, 3, 5);
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntityOmnilightShot entity) {
		return texture;
	}
}
