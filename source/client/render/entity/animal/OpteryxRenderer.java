package net.tslat.aoa3.client.render.entity.animal;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.tslat.aoa3.client.render.entity.AnimatedMobRenderer;
import net.tslat.aoa3.common.registration.entity.AoAAnimals;
import net.tslat.aoa3.content.entity.animal.precasia.OpteryxEntity;
import software.bernie.geckolib.cache.object.BakedGeoModel;

public class OpteryxRenderer extends AnimatedMobRenderer<OpteryxEntity> {
    public OpteryxRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new OpteryxModel(), AoAAnimals.OPTERYX.get().getWidth() / 3f);
    }

    @Override
    public void scaleModelForRender(float widthScale, float heightScale, PoseStack poseStack, OpteryxEntity animatable, BakedGeoModel model, boolean isReRender, float partialTick, int packedLight, int packedOverlay) {
        if (animatable.isEgg()) {
            widthScale *= 0.5f;
            heightScale *= 0.5f;
        }

        super.scaleModelForRender(widthScale, heightScale, poseStack, animatable, model, isReRender, partialTick, packedLight, packedOverlay);
    }
}
