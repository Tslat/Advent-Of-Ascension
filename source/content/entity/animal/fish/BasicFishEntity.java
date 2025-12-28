package net.tslat.aoa3.content.entity.animal.fish;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.tslat.aoa3.common.registration.entity.AoAEntityStats;
import net.tslat.aoa3.library.builder.EntitySpawnConditions;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.constant.DefaultAnimations;
import software.bernie.geckolib.util.GeckoLibUtil;

public class BasicFishEntity extends AbstractFish implements GeoEntity {
	private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);

	public BasicFishEntity(EntityType<? extends BasicFishEntity> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	public ItemStack getBucketItemStack() {
		return ItemStack.EMPTY;
	}

	@Override
	protected SoundEvent getFlopSound() {
		return SoundEvents.SALMON_FLOP;
	}

	@Override
	protected InteractionResult mobInteract(Player pPlayer, InteractionHand pHand) {
		return InteractionResult.PASS;
	}

	public static SpawnPlacements.SpawnPredicate<BasicFishEntity> spawnRules(EntityType<BasicFishEntity> entityType) {
		return EntitySpawnConditions.create(entityType).onlySpawnIn(Blocks.WATER).onlySpawnUnder(Blocks.WATER);
	}

	public static AoAEntityStats.AttributeBuilder entityStats(EntityType<BasicFishEntity> entityType) {
		return AoAEntityStats.AttributeBuilder.create(entityType)
				.health(4)
				.followRange(16);
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
		controllers.add(DefaultAnimations.genericSwimController(this));
	}

	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return this.geoCache;
	}
}
