package net.tslat.aoa3.client.render.entity.mob;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.client.render.entity.AnimatedMobRenderer;
import net.tslat.aoa3.common.registration.entity.AoAMobs;
import net.tslat.aoa3.content.entity.mob.precasia.AttercopusEntity;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer;

public class AttercopusRenderer extends AnimatedMobRenderer<AttercopusEntity> {
    public AttercopusRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new DefaultedEntityGeoModel<>(AdventOfAscension.id("mob/precasia/attercopus")), AoAMobs.ATTERCOPUS.get().getWidth() / 3f);

        addRenderLayer(new AutoGlowingGeoLayer<>(this));
    }

    @Override
    protected float getDeathMaxRotation(AttercopusEntity animatable) {
        return 180;
    }
}
