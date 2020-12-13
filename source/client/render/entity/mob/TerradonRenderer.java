package net.tslat.aoa3.client.render.entity.mob;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.client.model.entity.mob.precasia.TerradonModel;
import net.tslat.aoa3.client.render.entity.AoAMobRenderer;
import net.tslat.aoa3.common.registration.AoAEntities;

public class TerradonRenderer extends AoAMobRenderer {
	private final ResourceLocation invulnTexture = new ResourceLocation("aoa3", "textures/entities/mobs/precasia/terradon_invincible.png");

	public TerradonRenderer(EntityRendererManager renderManager) {
		super(renderManager, new TerradonModel(), AoAEntities.Mobs.TERRADON.get().getWidth() / 3f, 1.0f, new ResourceLocation("aoa3", "textures/entities/mobs/precasia/terradon.png"));
	}

	@Override
	protected void preRenderCallback(MobEntity entity, MatrixStack matrix, float partialTicks) {
		matrix.scale(2, 2, 2);
	}

	@Override
	public ResourceLocation getEntityTexture(MobEntity entity) {
		return entity.isInvulnerable() ? invulnTexture : super.getEntityTexture(entity);
	}
}