package net.tslat.aoa3.client.render.entities.mobs.runandor;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.client.gui.render.BossBarRenderer;
import net.tslat.aoa3.client.model.entities.mobs.runandor.ModelRuneTemplar;
import net.tslat.aoa3.entity.mobs.runandor.templars.EntityRuneTemplar;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class RuneTemplarRenderer extends RenderLiving<EntityRuneTemplar> {
	private final ResourceLocation texture;
	private final ResourceLocation disabledTexture;

	public RuneTemplarRenderer(RenderManager renderManager, ResourceLocation textureResource, ResourceLocation disabledTexture) {
		super(renderManager, new ModelRuneTemplar(), EntityRuneTemplar.entityWidth / 3);

		this.texture = textureResource;
		this.disabledTexture = disabledTexture;
	}

	@Override
	public void doRender(EntityRuneTemplar entity, double x, double y, double z, float entityYaw, float partialTicks) {
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
		if (!entity.isDisabled()) {
			BossBarRenderer.boss = entity;
		}
		else {
			BossBarRenderer.boss = null;
		}
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntityRuneTemplar entity) {
		return entity.isDisabled() ? disabledTexture : texture;
	}
}