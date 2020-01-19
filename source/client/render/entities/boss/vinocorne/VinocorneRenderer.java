package net.tslat.aoa3.client.render.entities.boss.vinocorne;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.client.gui.render.BossBarRenderer;
import net.tslat.aoa3.client.model.entities.boss.vinocorne.ModelVinocorne;
import net.tslat.aoa3.entity.boss.vinocorne.EntityVinocorne;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class VinocorneRenderer extends RenderLiving<EntityVinocorne> {
	private final ResourceLocation texture;

	public VinocorneRenderer(RenderManager renderManager, final ResourceLocation resource) {
		super(renderManager, new ModelVinocorne(), EntityVinocorne.entityWidth / 3);
		texture = resource;
	}

	@Override
	public void doRender(EntityVinocorne entity, double x, double y, double z, float entityYaw, float partialTicks) {
		super.doRender(entity, x, y, z, entityYaw, partialTicks);

		if (x + y + z != 0)
			BossBarRenderer.boss = entity;
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntityVinocorne entity) {
		return texture;
	}
}