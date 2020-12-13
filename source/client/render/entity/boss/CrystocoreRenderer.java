package net.tslat.aoa3.client.render.entity.boss;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.client.model.entity.boss.CrystocoreModel;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.entity.boss.CrystocoreEntity;

import javax.annotation.Nullable;

public class CrystocoreRenderer extends MobRenderer<CrystocoreEntity, EntityModel<CrystocoreEntity>> {
	private static final ResourceLocation SLOWNESS_PHASE_TEXTURE = new ResourceLocation("aoa3", "textures/entities/boss/crystocore/crystocore_slowness.png");
	private static final ResourceLocation POISON_PHASE_TEXTURE = new ResourceLocation("aoa3", "textures/entities/boss/crystocore/crystocore_poison.png");
	private static final ResourceLocation BLINDNESS_PHASE_TEXTURE = new ResourceLocation("aoa3", "textures/entities/boss/crystocore/crystocore_blindness.png");
	private static final ResourceLocation WEAKNESS_PHASE_TEXTURE = new ResourceLocation("aoa3", "textures/entities/boss/crystocore/crystocore_weakness.png");
	private static final ResourceLocation NAUSEA_PHASE_TEXTURE = new ResourceLocation("aoa3", "textures/entities/boss/crystocore/crystocore_nausea.png");
	private static final ResourceLocation WITHER_PHASE_TEXTURE = new ResourceLocation("aoa3", "textures/entities/boss/crystocore/crystocore_wither.png");

	public CrystocoreRenderer(EntityRendererManager renderManager) {
		super(renderManager, new CrystocoreModel(), AoAEntities.Mobs.CRYSTOCORE.get().getWidth() / 3);
	}

	@Override
	protected void preRenderCallback(CrystocoreEntity crystocore, MatrixStack matrix, float partialTicks) {
		matrix.scale(2, 2, 2);
	}

	@Nullable
	@Override
	public ResourceLocation getEntityTexture(CrystocoreEntity crystocore) {
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
}