package net.tslat.aoa3.common.misc;

import net.minecraft.world.entity.Entity;
import net.tslat.aoa3.common.packet.AoAPackets;
import net.tslat.aoa3.common.packet.packets.GeckolibAnimationTriggerPacket;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class AoAAnimationFactory<T extends Entity & IAnimatable> extends AnimationFactory {
	private final AnimationData animationData;

	public AoAAnimationFactory(final T entity) {
		super(null);

		if (entity.level.isClientSide()) {
			this.animationData = new AnimationData();

			entity.registerControllers(animationData);
		}
		else {
			this.animationData = null;
		}
	}

	@Override
	public AnimationData getOrCreateAnimationData(Integer uniqueID) {
		return this.animationData;
	}

	public void triggerAnim(T entity, String triggerName) {
		if (entity.level.isClientSide()) {
			for (AnimationController<T> controller : this.animationData.getAnimationControllers().values()) {
				if (controller instanceof AoAAnimationController aoaController)
					aoaController.tryTrigger(triggerName);
			}
		}
		else {
			AoAPackets.messageAllPlayersTrackingEntity(new GeckolibAnimationTriggerPacket(entity.getId(), triggerName), entity);
		}
	}
}
