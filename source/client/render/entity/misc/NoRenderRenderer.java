package net.tslat.aoa3.client.render.entity.misc;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class NoRenderRenderer<T extends Entity> extends EntityRenderer<T> {
    public NoRenderRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public void render(T pixon, float yaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {}

    @Override
    protected void renderNameTag(T pixon, Component name, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, float partialTick) {}

    @Override
    protected boolean shouldShowName(T pixon) {
        return false;
    }

    @Override
    public ResourceLocation getTextureLocation(T pixon) {
        return null;
    }
}
