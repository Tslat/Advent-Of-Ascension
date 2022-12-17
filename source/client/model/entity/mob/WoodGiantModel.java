package net.tslat.aoa3.client.model.entity.mob;

import net.minecraft.resources.ResourceLocation;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.client.model.entity.AoAEntityGeoModel;
import net.tslat.aoa3.content.entity.mob.overworld.WoodGiantEntity;

public class WoodGiantModel extends AoAEntityGeoModel<WoodGiantEntity> {
	public WoodGiantModel() {
		super("mob/overworld/wood_giant");
	}

	@Override
	public ResourceLocation getTextureResource(WoodGiantEntity entity) {
		int stage = entity.getEntityData().get(WoodGiantEntity.STAGE);

		if (stage == 0)
			return super.getTextureResource(entity);

		return AdventOfAscension.id("textures/entity/mob/overworld/wood_giant_broken_" + stage + ".png");
	}
}
