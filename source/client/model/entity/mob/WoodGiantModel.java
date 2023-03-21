package net.tslat.aoa3.client.model.entity.mob;

import net.minecraft.resources.ResourceLocation;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.content.entity.mob.overworld.WoodGiantEntity;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class WoodGiantModel extends DefaultedEntityGeoModel<WoodGiantEntity> {
	public WoodGiantModel() {
		super(AdventOfAscension.id("mob/overworld/wood_giant"));
	}

	@Override
	public ResourceLocation getTextureResource(WoodGiantEntity entity) {
		int stage = entity.getStage();

		if (stage == 0)
			return super.getTextureResource(entity);

		return AdventOfAscension.id("textures/entity/mob/overworld/wood_giant_broken_" + stage + ".png");
	}
}
