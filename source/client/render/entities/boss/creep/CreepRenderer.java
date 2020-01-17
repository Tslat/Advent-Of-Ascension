package net.tslat.aoa3.client.render.entities.boss.creep;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.client.gui.render.BossBarRenderer;
import net.tslat.aoa3.client.model.entities.boss.creep.ModelCreep;
import net.tslat.aoa3.entity.boss.creep.EntityCreep;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class CreepRenderer extends RenderLiving<EntityCreep> {
	private final ResourceLocation texture;

	public CreepRenderer(RenderManager renderManager, final ResourceLocation resource) {
		super(renderManager, new ModelCreep(), EntityCreep.entityWidth / 3);
		texture = resource;
	}

	@Override
	public void doRender(EntityCreep entity, double x, double y, double z, float entityYaw, float partialTicks) {
		super.doRender(entity, x, y, z, entityYaw, partialTicks);

		if (x + y + z != 0)
			BossBarRenderer.boss = entity;
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntityCreep entity) {
		return texture;
	}
}