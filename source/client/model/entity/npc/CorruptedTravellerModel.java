package net.tslat.aoa3.client.model.entity.npc;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.MobEntity;

public class CorruptedTravellerModel extends BipedModel<MobEntity> {
	public CorruptedTravellerModel() {
		super(RenderType::getEntityTranslucent, 0, 0, 64, 32);
	}
}
