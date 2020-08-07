package net.tslat.aoa3.client.render.entities.projectiles.mob;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.ParticleRegister;
import net.tslat.aoa3.entity.projectiles.mob.EntityBloodball;
import net.tslat.aoa3.library.Enums;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class BloodballRenderer extends Render<EntityBloodball> {
	private final ResourceLocation texture;

	public BloodballRenderer(final RenderManager manager, final ResourceLocation textureResource) {
		super(manager);
		texture = textureResource;
	}

	@Override
	public void doRender(EntityBloodball entity, double x, double y, double z, float entityYaw, float partialTicks) {
		entity.world.spawnParticle(ParticleRegister.FLICKERING_FLUFFY, entity.posX, entity.posY, entity.posZ, 0, 0, 0, Enums.RGBIntegers.RED, 100, 3, 5);
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntityBloodball entity) {
		return texture;
	}
}
