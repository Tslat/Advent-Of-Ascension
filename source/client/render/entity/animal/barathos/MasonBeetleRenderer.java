package net.tslat.aoa3.client.render.entity.animal.barathos;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.client.render.entity.AnimatedMobRenderer;
import net.tslat.aoa3.common.registration.entity.AoAAnimals;
import net.tslat.aoa3.content.entity.animal.barathos.MasonBeetleEntity;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class MasonBeetleRenderer extends AnimatedMobRenderer<MasonBeetleEntity> {
    public MasonBeetleRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new DefaultedEntityGeoModel<>(AdventOfAscension.id("animal/barathos/mason_beetle")), AoAAnimals.MASON_BEETLE.get().getWidth() / 3f);
    }

    @Override
    public void postRender(PoseStack poseStack, MasonBeetleEntity animatable, BakedGeoModel model, MultiBufferSource bufferSource, @Nullable VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, int colour) {
        animatable.getCarriedBlock().ifPresent(blockState -> {
            poseStack.pushPose();
            poseStack.translate(0, 0, 0);
            Minecraft.getInstance().getBlockRenderer().renderSingleBlock(blockState, poseStack, bufferSource, packedLight, OverlayTexture.NO_OVERLAY);
            poseStack.popPose();
        });
    }
}
