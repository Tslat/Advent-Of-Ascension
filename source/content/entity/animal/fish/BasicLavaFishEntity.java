package net.tslat.aoa3.content.entity.animal.fish;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import net.tslat.aoa3.client.render.AoAAnimations;
import net.tslat.aoa3.content.entity.base.AbstractLavaFishEntity;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class BasicLavaFishEntity extends AbstractLavaFishEntity implements IAnimatable {
	private final AnimationFactory animationFactory = new AnimationFactory(this);

	public BasicLavaFishEntity(EntityType<? extends BasicLavaFishEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected SoundEvent getFlopSound() {
		return SoundEvents.SALMON_FLOP;
	}

	@Override
	protected ActionResultType mobInteract(PlayerEntity pPlayer, Hand pHand) {
		return ActionResultType.PASS;
	}

	@Override
	public void registerControllers(AnimationData data) {
		data.addAnimationController(AoAAnimations.genericSwimController(this));
	}

	@Override
	public AnimationFactory getFactory() {
		return animationFactory;
	}
}
