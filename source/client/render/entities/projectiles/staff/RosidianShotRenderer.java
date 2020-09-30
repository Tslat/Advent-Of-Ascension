package net.tslat.aoa3.client.render.entities.projectiles.staff;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.ParticleRegister;
import net.tslat.aoa3.entity.projectiles.staff.EntityRosidianShot;
import net.tslat.aoa3.library.Enums;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class RosidianShotRenderer extends Render<EntityRosidianShot> {
	private final ResourceLocation texture;

	public RosidianShotRenderer(final RenderManager manager, final ResourceLocation textureResource) {
		super(manager);
		texture = textureResource;
	}

	@Override
	public void doRender(EntityRosidianShot entity, double x, double y, double z, float entityYaw, float partialTicks) {
		if (entity.motionY > 0.98) {
			for (int i = 0; i < 8; i++) {
				entity.world.spawnParticle(ParticleRegister.SWIRLY, true, entity.posX, entity.posY + 0.25d, entity.posZ, 0, 0, 0, Enums.RGBIntegers.BROWN, 100, 3, 3);
			}
		}
		else {
			entity.world.spawnParticle(ParticleRegister.FLICKERING_FLUFFY, true, entity.posX, entity.posY + 0.25d, entity.posZ, 0, 0, 0, Enums.RGBIntegers.GREEN, 100, 3, 3);
		}
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntityRosidianShot entity) {
		return texture;
	}
}
