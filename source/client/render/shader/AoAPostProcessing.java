package net.tslat.aoa3.client.render.shader;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.PostChain;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.event.RenderLevelStageEvent;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.worldgen.AoADimensions;

public final class AoAPostProcessing {
    private static final ResourceLocation DUSTOPIA_DESATURATE = AdventOfAscension.id("shaders/post/dustopia_desaturate.json");

    public static void handlePostProcessing(final RenderLevelStageEvent ev) {
        if (ev.getStage() != RenderLevelStageEvent.Stage.AFTER_SKY)
            return;

        final Minecraft mc = Minecraft.getInstance();

        if (mc.level == null)
            return;

        final PostChain postProcessEffect = mc.gameRenderer.currentEffect();

        if (mc.level.dimension() != AoADimensions.DUSTOPIA) {
            if (postProcessEffect != null && postProcessEffect.getName().contains("dustopia"))
                mc.gameRenderer.shutdownEffect();
        }
        else {
            if (postProcessEffect == null || !postProcessEffect.getName().contains("dustopia"))
                mc.gameRenderer.loadEffect(DUSTOPIA_DESATURATE);
        }
    }
}
