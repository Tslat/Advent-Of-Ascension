package net.tslat.aoa3.content.entity.misc;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.event.ForgeEventFactory;
import net.tslat.aoa3.common.registration.entity.AoAMiscEntities;
import net.tslat.aoa3.library.builder.EntityPredicate;
import net.tslat.smartbrainlib.util.EntityRetrievalUtil;

import java.util.List;
import java.util.function.Consumer;

public class CustomisableLightningBolt extends LightningBolt {
	protected boolean doFire = true;
	protected Consumer<Entity> onEntityStrike = null;

	public CustomisableLightningBolt(EntityType<? extends CustomisableLightningBolt> entityType, Level level) {
		super(entityType, level);
	}

	public CustomisableLightningBolt(Level level, BlockPos pos) {
		this(level, pos.getX(), pos.getY(), pos.getZ());
	}

	public CustomisableLightningBolt(Level level, double x, double y, double z) {
		this(AoAMiscEntities.CUSTOMISABLE_LIGHTNING_BOLT.get(), level);

		moveTo(x, y, z);
	}

	public void noFire() {
		this.doFire = false;
	}

	public void whenStrikingEntity(Consumer<Entity> consumer) {
		this.onEntityStrike = consumer;
	}

	@Override
	public void tick() {
		this.baseTick();

		if (this.life == 2) {
			if (this.level().isClientSide()) {
				if (!isSilent()) {
					this.level().playLocalSound(getX(), getY(), getZ(), SoundEvents.LIGHTNING_BOLT_THUNDER, SoundSource.WEATHER, 10, 0.8F + this.random.nextFloat() * 0.2F, false);
					this.level().playLocalSound(getX(), getY(), getZ(), SoundEvents.LIGHTNING_BOLT_IMPACT, SoundSource.WEATHER, 1, 0.5F + this.random.nextFloat() * 0.2F, false);
				}
			}
			else {
				Difficulty difficulty = this.level().getDifficulty();

				if ((difficulty == Difficulty.NORMAL || difficulty == Difficulty.HARD) && this.doFire)
					spawnFire(4);

				powerLightningRod();
				clearCopperOnLightningStrike(this.level(), this.getStrikePosition());
				gameEvent(GameEvent.LIGHTNING_STRIKE);
			}
		}

		--this.life;

		if (this.life < 0) {
			if (this.flashes == 0) {
				if (this.level() instanceof ServerLevel) {
					List<Entity> entities = EntityRetrievalUtil.getEntities(this.level(), new AABB(getX() - 15, getY() - 15, getZ() - 15, getX() + 15, getY() + 21, getZ() + 15), new EntityPredicate<>(this).isAlive().notInCollection(this.hitEntities));

					for(ServerPlayer player : ((ServerLevel)this.level()).getPlayers(player -> player.distanceTo(this) < 256)) {
						CriteriaTriggers.LIGHTNING_STRIKE.trigger(player, this, entities);
					}
				}

				discard();
			}
			else if (this.life < -this.random.nextInt(10)) {
				--this.flashes;
				this.life = 1;
				this.seed = this.random.nextLong();

				if (this.doFire)
					spawnFire(0);
			}
		}

		if (this.life >= 0) {
			if (!(this.level() instanceof ServerLevel)) {
				this.level().setSkyFlashTime(2);
			}
			else if (!this.visualOnly) {
				List<Entity> entities = EntityRetrievalUtil.getEntities(this.level(), new AABB(getX() - 3, getY() - 3, getZ() - 3, getX() + 3, getY() + 9, getZ() + 3), new EntityPredicate<>(this).isAlive());

				for(Entity entity : entities) {
					if (!ForgeEventFactory.onEntityStruckByLightning(entity, this)) {
						entity.thunderHit((ServerLevel)this.level(), this);

						if (this.onEntityStrike != null)
							this.onEntityStrike.accept(entity);
					}
				}

				this.hitEntities.addAll(entities);

				if (this.cause != null)
					CriteriaTriggers.CHANNELED_LIGHTNING.trigger(this.cause, entities);
			}
		}
	}
}
