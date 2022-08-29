package net.tslat.aoa3.client.model.entity.boss;

import net.minecraft.resources.ResourceLocation;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.client.model.entity.EntityGeoModel;
import net.tslat.aoa3.content.entity.boss.smash.EliteSmashEntity;

public class EliteSmashModel extends EntityGeoModel<EliteSmashEntity> {
	public EliteSmashModel() {
		super("boss/smash/elite_smash");
	}

	@Override
	public ResourceLocation getTextureResource(EliteSmashEntity entity) {
		return AdventOfAscension.id("textures/entity/boss/smash/elite_smash" + (entity.isEnraged() ? "_angry" : "") + ".png");
	}
}
