package net.tslat.aoa3.content.entity.base;

import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.pathfinder.BlockPathTypes;

public abstract class LavaMobEntity extends PathfinderMob {
	protected LavaMobEntity(EntityType<? extends LavaMobEntity> entityType, Level world) {
		super(entityType, world);

		this.setPathfindingMalus(BlockPathTypes.LAVA, 0);
		this.setPathfindingMalus(BlockPathTypes.DAMAGE_FIRE, 0);
	}

	public boolean canBreatheUnderwater() {
		return true;
	}

	public boolean checkSpawnObstruction(LevelReader pLevel) {
		return pLevel.isUnobstructed(this);
	}

	public int getAmbientSoundInterval() {
		return 120;
	}

	protected int getExperienceReward(Player pPlayer) {
		return 1 + this.level().random.nextInt(3);
	}

	protected void handleAirSupply(int air) {
		if (this.isAlive() && !this.isInLava()) {
			this.setAirSupply(air - 1);

			if (this.getAirSupply() == -20) {
				this.setAirSupply(0);
				this.hurt(level().damageSources().drown(), 2.0F);
			}
		}
		else {
			this.setAirSupply(300);
		}
	}

	@Override
	public boolean isInvulnerableTo(DamageSource source) {
		return source.is(DamageTypeTags.IS_FIRE) || super.isInvulnerableTo(source);
	}

	public void baseTick() {
		int air = this.getAirSupply();

		super.baseTick();
		this.handleAirSupply(air);
	}

	public boolean isPushedByFluid() {
		return false;
	}

	public boolean canBeLeashed(Player player) {
		return false;
	}
}
