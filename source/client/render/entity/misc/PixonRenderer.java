package net.tslat.aoa3.client.render.entity.misc;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.tslat.aoa3.content.entity.misc.PixonEntity;

public class PixonRenderer extends EntityRenderer<PixonEntity> {
    public PixonRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public void render(PixonEntity pixon, float yaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
    }

    @Override
    protected void renderNameTag(PixonEntity pixon, Component name, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {}

    @Override
    protected boolean shouldShowName(PixonEntity pixon) {
        return false;
    }

    @Override
    public ResourceLocation getTextureLocation(PixonEntity pixon) {
        return null;
    }
}
