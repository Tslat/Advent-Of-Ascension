package net.tslat.aoa3.client.render.entity.boss;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.core.particles.ParticleTypes;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.client.render.entity.AnimatedMobRenderer;
import net.tslat.aoa3.common.registration.entity.AoAMobs;
import net.tslat.aoa3.content.entity.boss.king_bambambam.KingBamBamBamEntity;
import net.tslat.aoa3.library.builder.ParticleBuilder;
import org.joml.Vector3d;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer;

public class KingBamBamBamRenderer extends AnimatedMobRenderer<KingBamBamBamEntity> {
	public KingBamBamBamRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new DefaultedEntityGeoModel<>(AdventOfAscension.id("boss/king_bambambam/king_bambambam"), true), AoAMobs.KING_BAMBAMBAM.get().getWidth() / 3f);

		addRenderLayer(new AutoGlowingGeoLayer<>(this));
	}

	@Override
	public void postRender(PoseStack poseStack, KingBamBamBamEntity animatable, BakedGeoModel model, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		if (partialTick == 1) {
			model.getBone("sphere").ifPresent(bone -> {
				if (bone.getScaleX() == 1) {
					Vector3d worldPos = bone.getWorldPosition();

					ParticleBuilder.forPos(ParticleTypes.FLAME, worldPos.x + animatable.getRandom().nextGaussian() * 0.1f, worldPos.y + animatable.getRandom().nextGaussian() * 0.1f, worldPos.z + animatable.getRandom().nextGaussian() * 0.1f)
							.ignoreDistanceAndLimits()
							.velocity(0, 0.05, 0)
							.spawnParticles();
				}
			});
		}
	}
}
