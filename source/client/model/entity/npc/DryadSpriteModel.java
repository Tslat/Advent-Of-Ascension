package net.tslat.aoa3.client.model.entity.npc;

import net.minecraft.resources.ResourceLocation;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.content.entity.npc.ambient.DryadSpriteEntity;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class DryadSpriteModel extends DefaultedEntityGeoModel<DryadSpriteEntity> {
	public DryadSpriteModel() {
		super(AdventOfAscension.id("npc/ambient/dryad_sprite"));
	}

	@Override
	public ResourceLocation getModelResource(DryadSpriteEntity sprite) {
		return AdventOfAscension.id("geo/entity/npc/ambient/dryad_sprite/" + sprite.getVariant().name() + "_dryad_sprite.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(DryadSpriteEntity sprite) {
		return AdventOfAscension.id("textures/entity/npc/ambient/dryad_sprite/" + sprite.getVariant().name() + "_dryad_sprite.png");
	}
}
