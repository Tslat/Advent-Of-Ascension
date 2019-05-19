package net.tslat.aoa3.client.render.entities.boss.cottoncandor;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.client.gui.render.BossBarRenderer;
import net.tslat.aoa3.client.model.entities.boss.cottoncandor.ModelCottonCandor;
import net.tslat.aoa3.entity.boss.cottoncandor.EntityCottonCandor;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class CottonCandorRenderer extends RenderLiving<EntityCottonCandor> {
	private final ResourceLocation windStageTexture;
	private final ResourceLocation waterStageTexture;
	private final ResourceLocation fireStageTexture;
	private final ResourceLocation witherStageTexture;
	private final ResourceLocation poisonStageTexture;

	public CottonCandorRenderer(RenderManager renderManager, final ResourceLocation windStageTexture, final ResourceLocation waterStageTexture, final ResourceLocation fireStageTexture, final ResourceLocation witherStageTexture, final ResourceLocation poisonStageTexture) {
		super(renderManager, new ModelCottonCandor(), EntityCottonCandor.entityWidth / 3);
		this.windStageTexture = windStageTexture;
		this.waterStageTexture = waterStageTexture;
		this.fireStageTexture = fireStageTexture;
		this.witherStageTexture = witherStageTexture;
		this.poisonStageTexture = poisonStageTexture;
	}

	@Override
	public void doRender(EntityCottonCandor entity, double x, double y, double z, float entityYaw, float partialTicks) {
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
		if (x + y + z != 0)
			BossBarRenderer.boss = entity;

		if (partialTicks < 0.3)
			entity.checkMusicStatus();
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntityCottonCandor cottonCandor) {
		switch (cottonCandor.getStage()) {
			case 0:
				return windStageTexture;
			case 1:
				return waterStageTexture;
			case 2:
				return fireStageTexture;
			case 3:
				return poisonStageTexture;
			case 4:
			default:
				return witherStageTexture;
		}
	}
}