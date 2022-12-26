package net.tslat.aoa3.client.model.entity.boss;

import net.minecraft.resources.ResourceLocation;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.content.entity.boss.smash.SmashEntity;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class SmashModel extends DefaultedEntityGeoModel<SmashEntity> {
	public SmashModel() {
		super(AdventOfAscension.id("boss/smash/smash"));
	}

	@Override
	public ResourceLocation getTextureResource(SmashEntity entity) {
		return AdventOfAscension.id("textures/entity/boss/smash/smash" + (entity.isEnraged() ? "_angry" : "") + ".png");
	}
}
