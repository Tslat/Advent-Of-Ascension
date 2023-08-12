package net.tslat.aoa3.client.model.entity.mob;

import it.unimi.dsi.fastutil.floats.FloatFloatPair;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.content.entity.mob.precasia.SpinoledonEntity;

public class SpinoledonModel extends MultiStageHeadModel<SpinoledonEntity> {
	public SpinoledonModel() {
		super(AdventOfAscension.id("mob/precasia/spinoledon"));

		withStages(
				new Stage("head", FloatFloatPair.of(-12.5f, 17.5f), FloatFloatPair.of(-10, 10)),
				new Stage("neck2", FloatFloatPair.of(-12.5f, 7.5f), FloatFloatPair.of(-10, 10)),
				new Stage("neck", FloatFloatPair.of(-5, 40), FloatFloatPair.of(-20, 20))
		);
	}
}
