package net.tslat.aoa3.client.render.dimension;

import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.advent.AdventOfAscension;

public class ShyrelandsRenderingEffects extends DimensionSpecialEffects {
    public static final ResourceLocation ID = new ResourceLocation(AdventOfAscension.MOD_ID, "shyrelands");

    public ShyrelandsRenderingEffects() {
        super(40, true, SkyType.NORMAL, false, true);
    }

    @Override
    public Vec3 getBrightnessDependentFogColor(Vec3 biomeFogColour, float celestialAngle) {
        return biomeFogColour.multiply(celestialAngle * 0.94F + 0.06F, celestialAngle * 0.94F + 0.06F, celestialAngle * 0.91F + 0.09F);
    }

    @Override // Show fog at X/Z
    public boolean isFoggyAt(int posX, int posZ) {
        return false;
    }
}