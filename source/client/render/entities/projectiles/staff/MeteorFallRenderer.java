package net.tslat.aoa3.client.render.entities.projectiles.staff;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.ParticleRegister;
import net.tslat.aoa3.entity.projectiles.staff.EntityMeteorFall;
import net.tslat.aoa3.library.Enums;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class MeteorFallRenderer extends Render<EntityMeteorFall> {
	private final ResourceLocation texture;

	public MeteorFallRenderer(final RenderManager manager, final ResourceLocation textureResource) {
		super(manager);
		texture = textureResource;
	}

	@Override
	public void doRender(EntityMeteorFall entity, double x, double y, double z, float entityYaw, float partialTicks) {
		for (int i = 0; i < 3; i++) {
			entity.world.spawnParticle(ParticleRegister.FLUFFY, true, entity.posX, entity.posY , entity.posZ, 0, 0, 0, Enums.RGBIntegers.RED, 100, 3, 3);
			entity.world.spawnParticle(ParticleRegister.FLUFFY, true, entity.posX, entity.posY - 0.3D , entity.posZ, 0, 0, 0, Enums.RGBIntegers.ORANGE, 100, 3, 3);
			entity.world.spawnParticle(ParticleRegister.FLUFFY, true, entity.posX, entity.posY - 0.6D , entity.posZ, 0, 0, 0, Enums.RGBIntegers.YELLOW, 100, 3, 3);
		}
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntityMeteorFall entity) {
		return texture;
	}
}
