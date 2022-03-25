package net.tslat.aoa3.content.entity.base;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.DamageSource;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public abstract class LavaMobEntity extends CreatureEntity {
	protected LavaMobEntity(EntityType<? extends LavaMobEntity> entityType, World world) {
		super(entityType, world);

		this.setPathfindingMalus(PathNodeType.LAVA, 0);
		this.setPathfindingMalus(PathNodeType.DAMAGE_FIRE, 0);
	}

	public boolean canBreatheUnderwater() {
		return true;
	}

	public boolean checkSpawnObstruction(IWorldReader pLevel) {
		return pLevel.isUnobstructed(this);
	}

	public int getAmbientSoundInterval() {
		return 120;
	}

	protected int getExperienceReward(PlayerEntity pPlayer) {
		return 1 + this.level.random.nextInt(3);
	}

	protected void handleAirSupply(int air) {
		if (this.isAlive() && !this.isInLava()) {
			this.setAirSupply(air - 1);

			if (this.getAirSupply() == -20) {
				this.setAirSupply(0);
				this.hurt(DamageSource.DROWN, 2.0F);
			}
		}
		else {
			this.setAirSupply(300);
		}
	}

	@Override
	public boolean isInvulnerableTo(DamageSource source) {
		return source == DamageSource.LAVA || source == DamageSource.ON_FIRE || super.isInvulnerableTo(source);
	}

	public void baseTick() {
		int air = this.getAirSupply();

		super.baseTick();
		this.handleAirSupply(air);
	}

	public boolean isPushedByFluid() {
		return false;
	}

	public boolean canBeLeashed(PlayerEntity player) {
		return false;
	}
}
