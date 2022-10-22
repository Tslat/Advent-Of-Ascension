package net.tslat.aoa3.client.model.entity.mob;

import net.tslat.aoa3.client.model.entity.EntityGeoModel;
import net.tslat.aoa3.content.entity.mob.nether.FlamewalkerEntity;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;

public class FlamewalkerModel extends EntityGeoModel<FlamewalkerEntity> {
	private IBone rock1;
	private IBone rock2;
	private IBone rock3;
	private IBone rock4;
	private IBone rock5;

	public FlamewalkerModel() {
		super("mob/nether/flamewalker");
	}

	@Override
	public void setLivingAnimations(FlamewalkerEntity entity, Integer uniqueID, @Nullable AnimationEvent customPredicate) {
		super.setLivingAnimations(entity, uniqueID, customPredicate);

		findBones();

		if (entity.isOnGround() && (customPredicate == null || customPredicate.isMoving())) {
			this.rock1.setHidden(false);
			this.rock2.setHidden(false);
			this.rock3.setHidden(false);
			this.rock4.setHidden(false);
			this.rock5.setHidden(false);
		}
		else {
			this.rock1.setHidden(true);
			this.rock2.setHidden(true);
			this.rock3.setHidden(true);
			this.rock4.setHidden(true);
			this.rock5.setHidden(true);
		}
	}

	protected void findBones() {
		if (this.rock1 != null)
			return;

		this.rock1 = getBone("rock_1");
		this.rock2 = getBone("rock_2");
		this.rock3 = getBone("rock_3");
		this.rock4 = getBone("rock_4");
		this.rock5 = getBone("rock_5");
	}
}
