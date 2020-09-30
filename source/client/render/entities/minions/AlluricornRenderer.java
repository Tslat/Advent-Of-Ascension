package net.tslat.aoa3.client.render.entities.minions;

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.client.model.entities.mobs.haven.ModelRainicorn;
import net.tslat.aoa3.client.render.entities.AoAMinionRenderer;
import net.tslat.aoa3.common.registration.ParticleRegister;
import net.tslat.aoa3.entity.minions.AoAMinion;
import net.tslat.aoa3.entity.minions.EntityAlluricorn;
import net.tslat.aoa3.library.Enums;

@SideOnly(Side.CLIENT)
public class AlluricornRenderer extends AoAMinionRenderer {
	public AlluricornRenderer(RenderManager renderManager, ResourceLocation textureResource) {
		super(renderManager, new ModelRainicorn(), EntityAlluricorn.entityWidth, 1f, textureResource);
	}

	@Override
	public void doRender(AoAMinion entity, double x, double y, double z, float entityYaw, float partialTicks) {
		super.doRender(entity, x, y, z, entityYaw, partialTicks);

		if (entity.motionX > 0 || entity.motionZ > 0 || entity.motionY > 0) {
			entity.world.spawnParticle(ParticleRegister.FLUFFY, entity.posX, entity.posY + 1.5, entity.posZ, 0, 0, 0, Enums.RGBIntegers.ORANGE, 75, 3, 5);
			entity.world.spawnParticle(ParticleRegister.FLUFFY, entity.posX, entity.posY + 1.25, entity.posZ, 0, 0, 0, Enums.RGBIntegers.WHITE, 75, 3, 5);
			entity.world.spawnParticle(ParticleRegister.FLUFFY, entity.posX, entity.posY + 1.05, entity.posZ, 0, 0, 0, Enums.RGBIntegers.ORANGE, 75, 3, 5);
			entity.world.spawnParticle(ParticleRegister.FLUFFY, entity.posX, entity.posY + 0.95, entity.posZ, 0, 0, 0, Enums.RGBIntegers.WHITE, 75, 3, 5);
			entity.world.spawnParticle(ParticleRegister.FLUFFY, entity.posX, entity.posY + 0.75, entity.posZ, 0, 0, 0, Enums.RGBIntegers.ORANGE, 75, 3, 5);
			entity.world.spawnParticle(ParticleRegister.FLUFFY, entity.posX, entity.posY + 0.5, entity.posZ, 0, 0, 0, Enums.RGBIntegers.WHITE, 75, 3, 5);
		}
	}
}