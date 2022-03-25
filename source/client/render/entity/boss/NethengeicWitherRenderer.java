package net.tslat.aoa3.client.render.entity.boss;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.client.model.entity.boss.NethengeicWitherModel;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.content.entity.boss.NethengeicWitherEntity;

import javax.annotation.Nullable;

public class NethengeicWitherRenderer extends MobRenderer<NethengeicWitherEntity, EntityModel<NethengeicWitherEntity>> {
	private static final ResourceLocation TEXTURE = new ResourceLocation("aoa3", "textures/entity/boss/nethengeicwither/nethengeic_wither.png");
	private static final ResourceLocation ENRAGED_TEXTURE = new ResourceLocation("aoa3", "textures/entity/boss/nethengeicwither/nethengeic_wither_enraged.png");
	private static final ResourceLocation CATACLYSMIC_TEXTURE = new ResourceLocation("aoa3", "textures/entity/boss/nethengeicwither/nethengeic_wither_cataclysmic.png");

	public NethengeicWitherRenderer(EntityRendererManager renderManager) {
		super(renderManager, new NethengeicWitherModel(), AoAEntities.Mobs.NETHENGEIC_WITHER.get().getWidth() / 3);
	}

	@Override
	protected void scale(NethengeicWitherEntity nethengeicWither, MatrixStack matrix, float partialTicks) {
		matrix.scale(1.5f, 1.5f, 1.5f);
	}

	@Nullable
	@Override
	public ResourceLocation getTextureLocation(NethengeicWitherEntity nethengeicWither) {
		switch (nethengeicWither.getStage()) {
			case 1:
			default:
				return TEXTURE;
			case 2:
				return ENRAGED_TEXTURE;
			case 3:
				return CATACLYSMIC_TEXTURE;
		}
	}
}