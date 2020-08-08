package net.tslat.aoa3.client.render.entities.projectiles.mob;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.ParticleRegister;
import net.tslat.aoa3.entity.projectiles.mob.EntityValkyrieShot;
import net.tslat.aoa3.library.Enums;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class ValkyrieShotRenderer extends Render<EntityValkyrieShot> {
	private final ResourceLocation texture;

	public ValkyrieShotRenderer(final RenderManager manager, final ResourceLocation textureResource) {
		super(manager);
		texture = textureResource;
	}

	@Override
	public void doRender(EntityValkyrieShot entity, double x, double y, double z, float entityYaw, float partialTicks) {
		entity.world.spawnParticle(ParticleRegister.FLICKERING_FLUFFY, true, entity.posX, entity.posY, entity.posZ, 0, 0, 0, Enums.RGBIntegers.BLACK, 100, 3, 7);
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntityValkyrieShot entity) {
		return texture;
	}
}
