package net.tslat.aoa3.client.render.entity.mob;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.tslat.aoa3.client.model.entity.AoAEntityGeoModel;
import net.tslat.aoa3.client.render.entity.layer.FireAuraLayer;
import net.tslat.aoa3.content.entity.mob.nether.NethengeicBeastEntity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class NethengeicBeastRenderer extends GeoEntityRenderer<NethengeicBeastEntity> {
	public NethengeicBeastRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new AoAEntityGeoModel<>("mob/nether/nethengeic_beast", true));

		addRenderLayer(new FireAuraLayer(this));
	}
}
