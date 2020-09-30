package net.tslat.aoa3.client.render.entities.mobs.overworld;

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.client.model.entities.mobs.overworld.ModelBloodmist;
import net.tslat.aoa3.client.render.entities.AoAMeleeMobRenderer;
import net.tslat.aoa3.common.registration.ParticleRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.entity.mobs.overworld.bloodhunt.EntityBloodmist;
import net.tslat.aoa3.library.Enums;

@SideOnly(Side.CLIENT)
public class BloodmistRenderer extends AoAMeleeMobRenderer {
	public BloodmistRenderer(RenderManager renderManager, final ResourceLocation textureResource) {
		super(renderManager, new ModelBloodmist(), EntityBloodmist.entityWidth, 1.0f, textureResource);
	}

	@Override
	public void doRender(AoAMeleeMob entity, double x, double y, double z, float entityYaw, float partialTicks) {
		super.doRender(entity, x, y, z, entityYaw, partialTicks);

		entity.world.spawnParticle(ParticleRegister.FLICKERING_FLUFFY, entity.posX, entity.posY - 0.2, entity.posZ, 0, 0, 0, Enums.RGBIntegers.RED, 75, 3, 5);
		entity.world.spawnParticle(ParticleRegister.FLICKERING_FLUFFY, entity.posX, entity.posY + 0.2, entity.posZ, 0, 0, 0, Enums.RGBIntegers.RED, 75, 3, 5);
	}
}