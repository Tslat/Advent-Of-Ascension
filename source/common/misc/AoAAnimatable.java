package net.tslat.aoa3.common.misc;

import net.minecraft.world.entity.Entity;
import software.bernie.geckolib3.core.IAnimatable;

public interface AoAAnimatable<T extends Entity & IAnimatable> extends IAnimatable {
	@Override
	AoAAnimationFactory<T> getFactory();

	default void triggerAnim(T entity, String animId) {
		getFactory().triggerAnim(entity, animId);
	}
}
