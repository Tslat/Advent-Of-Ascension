package net.tslat.aoa3.client.render.entities.projectiles.blasters;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.ParticleRegister;
import net.tslat.aoa3.entity.projectiles.blaster.EntityParalyzerShot;
import net.tslat.aoa3.library.Enums;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class ParalyzerShotRenderer extends Render<EntityParalyzerShot> {
	private final ResourceLocation texture;

	public ParalyzerShotRenderer(final RenderManager manager, final ResourceLocation textureResource) {
		super(manager);
		texture = textureResource;
	}

	@Override
	public void doRender(EntityParalyzerShot entity, double x, double y, double z, float entityYaw, float partialTicks) {
		for (int i = 0; i < 3; i++) {
			entity.world.spawnParticle(ParticleRegister.SWIRLY, entity.posX, entity.posY, entity.posZ, 0, 0, 0, Enums.RGBIntegers.YELLOW, 100, 3, 7);
			entity.world.spawnParticle(ParticleRegister.FLUFFY, entity.posX, entity.posY, entity.posZ, 0, 0, 0, Enums.RGBIntegers.CYAN, 25, 3, 7);
		}
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntityParalyzerShot entity) {
		return texture;
	}
}
