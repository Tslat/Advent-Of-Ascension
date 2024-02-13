package net.tslat.aoa3.client.model.entity.mob;

import net.minecraft.resources.ResourceLocation;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.content.entity.mob.overworld.ChargerEntity;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class ChargerModel extends DefaultedEntityGeoModel<ChargerEntity> {
	public ChargerModel() {
		super(AdventOfAscension.id("mob/overworld/charger"));
	}

	@Override
	public ResourceLocation getModelResource(ChargerEntity charger) {
		return AdventOfAscension.id("geo/entity/mob/overworld/charger/" + charger.getVariant().name() + "_charger.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(ChargerEntity charger) {
		return AdventOfAscension.id("textures/entity/mob/overworld/charger/" + charger.getVariant().name() + "_charger.png");
	}
}
