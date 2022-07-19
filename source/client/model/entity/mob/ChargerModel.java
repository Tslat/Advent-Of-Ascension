package net.tslat.aoa3.client.model.entity.mob;

import net.minecraft.resources.ResourceLocation;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.client.model.entity.EntityGeoModel;
import net.tslat.aoa3.content.entity.mob.overworld.ChargerEntity;

public class ChargerModel extends EntityGeoModel<ChargerEntity> {
	public ChargerModel() {
		super("mob/overworld/charger");
	}

	@Override
	public ResourceLocation getModelResource(ChargerEntity charger) {
		ChargerEntity.Type variant = charger.chargerType();

		return AdventOfAscension.id("geo/entity/mob/overworld/" + (variant == ChargerEntity.Type.DEFAULT ? "charger" : variant.name + "_charger") + ".png");
	}

	@Override
	public ResourceLocation getTextureResource(ChargerEntity charger) {
		ChargerEntity.Type variant = charger.chargerType();

		return AdventOfAscension.id("textures/entity/mob/overworld/" + (variant == ChargerEntity.Type.DEFAULT ? "charger" : variant.name + "_charger") + ".png");
	}
}
