package net.tslat.aoa3.client.render.entity.projectile.cannonshots;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.tslat.aoa3.client.AoAEntityRendering;
import net.tslat.aoa3.client.model.entity.projectile.CobblestoneProjectileModel;
import net.tslat.aoa3.client.render.entity.projectile.ModelledProjectileRenderer;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.util.ColourUtil;
import net.tslat.tme.api.particle.ParticleBuilder;
import net.tslat.tme.api.util.RandomUtil;

public class FungalRockFragmentRenderer extends ModelledProjectileRenderer<Entity> {
	public FungalRockFragmentRenderer(final EntityRendererProvider.Context context, final ResourceLocation textureResource) {
		super(context, AoAEntityRendering.ROCK_FRAGMENT.getMainLayerLocation(), CobblestoneProjectileModel::new, textureResource);
	}

	@Override
	protected void preRenderCallback(Entity entity, PoseStack matrix, float partialTicks) {
		model.setupAnim(entity, 0, entity.tickCount + partialTicks, entity.tickCount, 0, 0);

		super.preRenderCallback(entity, matrix, partialTicks);
	}

	@Override
	public void render(Entity entity, float entityYaw, float partialTicks, PoseStack matrix, MultiBufferSource buffer, int packedLight) {
		super.render(entity, entityYaw, partialTicks, matrix, buffer, packedLight);

		for (int i = 0; i < 8; i++) {
			ParticleBuilder.forPositions(AoAParticleTypes.GENERIC_DUST.get(), entity.position())
					.colourTint(RandomUtil.selection(ColourUtil.GREEN, ColourUtil.YELLOW, ColourUtil.BLUE, 0xC140D7))
					.spawnClientParticles(entity.level());
		}
	}
}