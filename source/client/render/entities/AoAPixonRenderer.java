package net.tslat.aoa3.client.render.entities;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.client.model.entities.misc.ModelNothing;
import net.tslat.aoa3.common.registration.ParticleRegister;
import net.tslat.aoa3.entity.misc.pixon.EntityPixon;

import javax.annotation.Nullable;

public class AoAPixonRenderer extends RenderLiving<EntityPixon> {
	private final int colour;

	public AoAPixonRenderer(RenderManager renderManager, int colourInt) {
		super(renderManager, new ModelNothing(), 0);
		this.colour = colourInt;
	}

	@Override
	public void doRender(EntityPixon entity, double x, double y, double z, float entityYaw, float partialTicks) {
		entity.world.spawnParticle(ParticleRegister.FLICKERING_FLUFFY, entity.posX, entity.posY + 0.65d, entity.posZ, 0, 0, 0, colour, 150, 3, 5);
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntityPixon entity) {
		return null;
	}
}
