/*
package net.tslat.aoa3.client.render.entity.boss;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

import net.tslat.aoa3.content.entity.boss.CrystocoreEntity;


public class CrystocoreRenderer extends MobRenderer<CrystocoreEntity, EntityModel<CrystocoreEntity>> {
	private static final ResourceLocation SLOWNESS_PHASE_TEXTURE = new ResourceLocation("aoa3", "textures/entity/boss/crystocore/crystocore_slowness.png");
	private static final ResourceLocation POISON_PHASE_TEXTURE = new ResourceLocation("aoa3", "textures/entity/boss/crystocore/crystocore_poison.png");
	private static final ResourceLocation BLINDNESS_PHASE_TEXTURE = new ResourceLocation("aoa3", "textures/entity/boss/crystocore/crystocore_blindness.png");
	private static final ResourceLocation WEAKNESS_PHASE_TEXTURE = new ResourceLocation("aoa3", "textures/entity/boss/crystocore/crystocore_weakness.png");
	private static final ResourceLocation NAUSEA_PHASE_TEXTURE = new ResourceLocation("aoa3", "textures/entity/boss/crystocore/crystocore_nausea.png");
	private static final ResourceLocation WITHER_PHASE_TEXTURE = new ResourceLocation("aoa3", "textures/entity/boss/crystocore/crystocore_wither.png");

	public CrystocoreRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new CrystocoreModel(), AoAMobs.CRYSTOCORE.get().getWidth() / 3);
	}

	@Override
	protected void scale(CrystocoreEntity crystocore, PoseStack matrix, float partialTicks) {
		matrix.scale(2, 2, 2);
	}

	@Nullable
	@Override
	public ResourceLocation getTextureLocation(CrystocoreEntity crystocore) {
		switch (crystocore.getPhase()) {
			case 0:
				return POISON_PHASE_TEXTURE;
			case 1:
				return BLINDNESS_PHASE_TEXTURE;
			case 2:
				return WEAKNESS_PHASE_TEXTURE;
			case 3:
				return NAUSEA_PHASE_TEXTURE;
			case 4:
				return WITHER_PHASE_TEXTURE;
			case 5:
			default:
				return SLOWNESS_PHASE_TEXTURE;
		}
	}
}*/
