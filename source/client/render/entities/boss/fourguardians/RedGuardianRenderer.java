package net.tslat.aoa3.client.render.entities.boss.fourguardians;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.client.gui.render.BossBarRenderer;
import net.tslat.aoa3.client.model.entities.boss.fourguardians.ModelFourGuardians;
import net.tslat.aoa3.entity.boss.fourguardians.EntityRedGuardian;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class RedGuardianRenderer extends RenderLiving<EntityRedGuardian> {
	private final ResourceLocation texture;

	public RedGuardianRenderer(RenderManager renderManager, final ResourceLocation texture) {
		super(renderManager, new ModelFourGuardians(), EntityRedGuardian.entityWidth / 3);
		this.texture = texture;
	}

	@Override
	protected void preRenderCallback(EntityRedGuardian redGuardian, float partialTickTime) {
		GlStateManager.scale(2f, 2f, 2f);
	}

	@Override
	public void doRender(EntityRedGuardian entity, double x, double y, double z, float entityYaw, float partialTicks) {
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
		BossBarRenderer.boss = entity;
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntityRedGuardian redGuardian) {
		return texture;
	}
}