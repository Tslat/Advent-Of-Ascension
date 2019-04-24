package net.tslat.aoa3.client.render.entities.boss.mechbot;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.client.gui.render.BossBarRenderer;
import net.tslat.aoa3.client.model.entities.boss.mechbot.ModelMechbot;
import net.tslat.aoa3.entity.boss.mechbot.EntityMechbot;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class MechbotRenderer extends RenderLiving<EntityMechbot> {
	private final ResourceLocation texture;

	public MechbotRenderer(RenderManager renderManager, final ResourceLocation texture) {
		super(renderManager, new ModelMechbot(), EntityMechbot.entityWidth / 3);
		this.texture = texture;
	}

	@Override
	public void doRender(EntityMechbot entity, double x, double y, double z, float entityYaw, float partialTicks) {
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
		BossBarRenderer.boss = entity;

		if (partialTicks < 0.3)
			entity.checkMusicStatus();
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntityMechbot corallus) {
		return texture;
	}
}