package net.tslat.aoa3.content.entity.animal.fish;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.tslat.aoa3.common.registration.entity.AoAEntityStats;
import net.tslat.aoa3.content.entity.base.AbstractLavaFishEntity;
import net.tslat.aoa3.library.builder.EntitySpawnConditions;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.constant.DefaultAnimations;
import software.bernie.geckolib.util.GeckoLibUtil;

public class BasicLavaFishEntity extends AbstractLavaFishEntity implements GeoEntity {
	private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);

	public BasicLavaFishEntity(EntityType<? extends BasicLavaFishEntity> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	protected SoundEvent getFlopSound() {
		return SoundEvents.SALMON_FLOP;
	}

	@Override
	protected InteractionResult mobInteract(Player pPlayer, InteractionHand pHand) {
		return InteractionResult.PASS;
	}

	public static SpawnPlacements.SpawnPredicate<BasicLavaFishEntity> spawnRules(EntityType<BasicLavaFishEntity> entityType) {
		return EntitySpawnConditions.create(entityType).onlySpawnIn(Blocks.LAVA).onlySpawnUnder(Blocks.LAVA);
	}

	public static AoAEntityStats.AttributeBuilder entityStats(EntityType<BasicLavaFishEntity> entityType) {
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
