package net.tslat.aoa3.client.render.entities.boss.hiveking;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.client.gui.render.BossBarRenderer;
import net.tslat.aoa3.client.model.entities.boss.hiveking.ModelHiveKing;
import net.tslat.aoa3.entity.boss.hiveking.EntityHiveKing;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class HiveKingRenderer extends RenderLiving<EntityHiveKing> {
	private final ResourceLocation texture;

	public HiveKingRenderer(RenderManager renderManager, final ResourceLocation texture) {
		super(renderManager, new ModelHiveKing(), EntityHiveKing.entityWidth / 3);
		this.texture = texture;
	}

	@Override
	public void doRender(EntityHiveKing entity, double x, double y, double z, float entityYaw, float partialTicks) {
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
		if (x + y + z != 0)
			BossBarRenderer.boss = entity;

		if (partialTicks < 0.3)
			entity.checkMusicStatus();
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntityHiveKing hiveKing) {
		return texture;
	}
}