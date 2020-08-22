package net.tslat.aoa3.client.render.entities.projectiles.misc;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.ParticleRegister;
import net.tslat.aoa3.entity.projectiles.misc.EntityLuxonSticklerStuck;
import net.tslat.aoa3.library.Enums;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class LuxonSticklerStuckRenderer extends Render<EntityLuxonSticklerStuck> {
	private final ResourceLocation texture;

	public LuxonSticklerStuckRenderer(final RenderManager manager, final ResourceLocation textureResource) {
		super(manager);
		texture = textureResource;
	}

	@Override
	public void doRender(EntityLuxonSticklerStuck entity, double x, double y, double z, float entityYaw, float partialTicks) {
		for (int i = 0; i < 7; i++) {
			entity.world.spawnParticle(ParticleRegister.SWIRLY, true, entity.posX, entity.posY, entity.posZ, 0, 0, 0, Enums.RGBIntegers.CYAN, 100, 3, 5);
		}
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntityLuxonSticklerStuck entity) {
		return texture;
	}
}
