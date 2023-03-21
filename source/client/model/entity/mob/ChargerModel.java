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
		ChargerEntity.Type variant = charger.getVariant();

		return AdventOfAscension.id("geo/entity/mob/overworld/" + (variant == ChargerEntity.Type.DEFAULT ? "charger" : variant.name + "_charger") + ".geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(ChargerEntity charger) {
		ChargerEntity.Type variant = charger.getVariant();

		return AdventOfAscension.id("textures/entity/mob/overworld/" + (variant == ChargerEntity.Type.DEFAULT ? "charger" : variant.name + "_charger") + ".png");
	}
}
