package net.tslat.aoa3.common.registration;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.library.constant.AllDirections;
import net.tslat.aoa3.library.object.interfaces.ToFloatFunction;
import net.tslat.aoa3.scheduling.AoAScheduler;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.PlayerUtil;
import net.tslat.tme.api.explosion.ExplosionInfo;
import net.tslat.tme.api.object.builder.EffectBuilder;
import net.tslat.tme.api.particle.ParticleBuilder;
import net.tslat.tme.api.sound.SoundBuilder;
import net.tslat.tme.api.util.RandomUtil;
import net.tslat.tme.internal.networking.packet.TMEParticlePacket;

public final class AoAExplosions {
	//region <Entities>
	public static final ExplosionInfo KING_BAMBAMBAM_DISCHARGE = ExplosionInfo.builder()
			.radius(5)
			.entityDamage(40)
			.dynamicDamage((explosion, target, damage) -> PlayerUtil.getPlayerOrOwnerIfApplicable(target) != null ? damage : 0f)
			.onEntityHit((explosion, target) -> target.igniteForSeconds(target.getRemainingFireTicks() / 20f + 10))
			.build();
	public static final ExplosionInfo LITTLE_BAM_OVERLOAD = ExplosionInfo.builder()
			.radius(6)
			.entityDamage(20)
			.blockDamage(30)
			.entityKnockbackMultiplier(1.25f)
			.onEntityHit((explosion, entity) -> entity.igniteForSeconds(3))
			.onBlockHit((explosion, pos, state) -> {
				if (explosion.level.random.nextInt(3) == 0 && explosion.level.getBlockState(pos.below()).isFaceSturdy(explosion.level, pos.below(), Direction.UP))
					explosion.level.setBlock(pos, Blocks.FIRE.defaultBlockState(), Block.UPDATE_ALL);
			})
			.customFx((explosion, tick) -> {
				Vec3 pos = explosion.position();
				TMEParticlePacket packet = new TMEParticlePacket(ParticleBuilder.forPosition(ParticleTypes.EXPLOSION_EMITTER, pos.x, pos.y, pos.z));

				for (AllDirections direction : AllDirections.values()) {
					Vec3i angle = direction.angle();

					packet.particle(ParticleBuilder.forPositions(AoAParticleTypes.GENERIC_DUST.get(), pos)
											.lifespan(explosion.random().numberBetween(1, 5))
											.colourTint(RandomUtil.valueBetween(0.14588235f, 0.4862745f), 0, 0, 1f)
											.velocity(angle.getX() * 10, angle.getY() * 10, angle.getZ() * 10));
				}

				packet.sendToAllPlayersNearby((ServerLevel)explosion.level, pos, 64);
			})
			.build();
	public static final ExplosionInfo CREEP_COW = ExplosionInfo.builder()
			.radius(5f)
			.entityDamage(4)
			.blockDamage(8f)
			.build();
	//endregion
	//region <Mob Projectiles>
	public static final ExplosionInfo BOMB_CARRIER_DYNAMITE = net.tslat.tme.api.explosion.ExplosionInfo.builder()
			.radius(3f)
			.entityDamage(8)
			.blockDamage(8.5f)
			.build();
	public static final ExplosionInfo CREEP_TUBE = net.tslat.tme.api.explosion.ExplosionInfo.builder()
			.radius(5f)
			.entityDamage(6)
			.blockDamage(10f)
			.build();
	public static final ExplosionInfo NETHENGEIC_WITHER_FIREBALL = ExplosionInfo.builder()
			.radius(2)
			.blockDamage(50)
			.entityDamage(5)
			.onBlockHit((explosion, explodePos, state) -> {
				if (explosion.random().oneInNChance(3) && explosion.level.getBlockState(explodePos.below()).isSolidRender(explosion.level, explodePos.below()))
					explosion.level.setBlock(explodePos, Blocks.FIRE.defaultBlockState(), Block.UPDATE_ALL);
			})
			.build();
	public static final ExplosionInfo STICKY_FIREBALL = ExplosionInfo.builder()
			.radius(2)
			.entityDamage(1)
			.explosionSound(explosion -> SoundBuilder.at(SoundEvents.GENERIC_EXPLODE, explosion.level, explosion.position()).volume(0.5f).pitch(explosion.random().valueBetween(0.56f, 0.84f)))
			.dynamicDamage((explosion, target, damage) -> damage * ((target.level().getDifficulty().getId() + 1) * 5f))
			.build();
	//endregion
	//region <Weapon Projectiles>
	public static final ExplosionInfo GRENADE = ExplosionInfo.builder()
			.radius(3f)
			.blockDamage(7)
			.entityDamage(5)
			.blockDropChance(0.1f)
			.build();
	public static final ExplosionInfo RUNIC_BOMB = ExplosionInfo.builder()
			.radius(3f)
			.blockDamage(7)
			.entityDamage(3f)
			.blockDropChance(0.05f)
			.onEntityHit(((explosion, entity) -> {
				if (EntityUtil.areProbablyEnemies(entity, explosion.getOwnerOrSource()))
					EntityUtil.applyPotions(entity, explosion.getOwnerOrSource(), new EffectBuilder(MobEffects.MOVEMENT_SLOWDOWN, 30).level(100));
			}))
			.build();
	public static final ExplosionInfo EXPLOSIVE_BOW = ExplosionInfo.builder()
			.radius(2.5f)
			.entityDamage(4)
			.blockDamage(5)
			.entityKnockbackMultiplier(1.2f)
			.blockDropChance(0.1f)
			.build();
	public static final ExplosionInfo DISCHARGE_SHOT = ExplosionInfo.builder()
			.radius(1.5f)
			.entityDamage(3)
			.blockDamage(4)
			.blockDropChance(0.1f)
			.build();
	public static final ExplosionInfo DISCHARGE_SHOTGUN = ExplosionInfo.builder()
			.radius(7)
			.entityDamage(8)
			.blockDamage(15)
			.blockDropChance(0.1f)
			.build();
	public static final ExplosionInfo AMPLIFIER = ExplosionInfo.builder()
			.radius(1)
			.entityDamage(2)
			.blockDamage(3)
			.blockDropChance(0.01f)
			.build();
	public static final ExplosionInfo BLAST_BARREL = ExplosionInfo.builder()
			.radius(3)
			.entityDamage(5)
			.blockDamage(5)
			.blockDropChance(0.05f)
			.build();
	public static final ExplosionInfo TRI_DISCHARGE_SHOT = ExplosionInfo.builder()
			.radius(4)
			.entityDamage(5)
			.blockDamage(8)
			.blockDropChance(0.1f)
			.build();
	public static final ExplosionInfo VOLATILE_CANNONBALL = ExplosionInfo.builder()
			.radius(4)
			.entityDamage(5)
			.blockDamage(8)
			.blockDropChance(0.1f)
			.build();
	public static final ExplosionInfo BLISSFUL_BLAST = ExplosionInfo.builder()
			.radius(6)
			.entityDamage(5)
			.blockDamage(10)
			.blockDropChance(0.1f)
			.build();
	public static final ExplosionInfo DESTRUCTION_RIFLE = ExplosionInfo.builder()
			.radius(3f)
			.entityDamage(3)
			.blockDamage(3)
			.blockDropChance(0.05f)
			.build();
	public static final ExplosionInfo DISCHARGE_SLUG = ExplosionInfo.builder()
			.radius(5)
			.entityDamage(6)
			.blockDamage(13)
			.blockDropChance(0.1f)
			.build();
	public static final ExplosionInfo STICKLER = ExplosionInfo.builder()
			.radius(1.7f)
			.entityDamage(4)
			.blockDamage(6)
			.blockDropChance(0.1f)
			.build();
	public static final ExplosionInfo APOCO_SHOWER = ExplosionInfo.builder()
			.radius(4f)
			.entityDamage(7f)
			.blockDamage(25)
			.blockDropChance(0.1f)
			.build();
	public static final ExplosionInfo ATOMIZER = ExplosionInfo.builder()
			.radius(2.5f)
			.entityDamage(5f)
			.blockDamage(8)
			.blockDropChance(0.1f)
			.build();
	public static final ExplosionInfo DARK_DESTROYER = ExplosionInfo.builder()
			.radius(3.5f)
			.entityDamage(5f)
			.blockDamage(10)
			.blockDropChance(0.1f)
			.build();
	public static final ExplosionInfo DOOM_BRINGER = ExplosionInfo.builder()
			.radius(1.5f)
			.entityDamage(3f)
			.blockDamage(5)
			.blockDropChance(0.05f)
			.build();
	public static final ExplosionInfo GOLD_BRINGER = ExplosionInfo.builder()
			.radius(2f)
			.entityDamage(3.5f)
			.blockDamage(5)
			.blockDropChance(0.05f)
			.build();
	public static final ExplosionInfo MOON_DESTROYER = ExplosionInfo.builder()
			.radius(5f)
			.entityDamage(8f)
			.blockDamage(10)
			.blockDropChance(0.1f)
			.build();
	public static final ExplosionInfo REVOLUTION = ExplosionInfo.builder()
			.radius(7f)
			.entityDamage(8f)
			.blockDamage(10)
			.blockDropChance(0.1f)
			.build();
	public static final ExplosionInfo SPIRIT_SHOWER = ExplosionInfo.builder()
			.radius(5f)
			.entityDamage(8f)
			.blockDamage(10)
			.blockDropChance(0.1f)
			.build();
	public static final ExplosionInfo CELESTIAL_STAFF = ExplosionInfo.builder()
			.radius(5f)
			.entityDamage(8f)
			.blockDamage(10)
			.blockDropChance(0.1f)
			.build();
	public static final ExplosionInfo METEOR_STAFF = ExplosionInfo.builder()
			.radius(4f)
			.entityDamage(5f)
			.blockDamage(8)
			.blockDropChance(0.1f)
			.build();
	public static final ExplosionInfo DESTRUCTION_STAFF = ExplosionInfo.builder()
			.radius(6.5f)
			.entityDamage(9f)
			.blockDamage(10)
			.blockDropChance(0.1f)
			.build();
	public static final ExplosionInfo DESTRUCTION_STAFF_IMPACT = ExplosionInfo.builder()
			.radius(8f)
			.entityDamage(11f)
			.blockDamage(15)
			.blockDropChance(0.1f)
			.build();
	public static final ExplosionInfo SUN_STAFF = ExplosionInfo.builder()
			.radius(13)
			.entityDamage(15)
			.blockDamage(30)
			.blockDropChance(0.1f)
			.onBlockHit((explosion, pos, state) -> {
				if (explosion.random().oneInNChance(3) && explosion.level.getBlockState(pos.below()).isFaceSturdy(explosion.level, pos.below(), Direction.UP))
					explosion.level.setBlock(pos, Blocks.FIRE.defaultBlockState(), Block.UPDATE_ALL);
			})
			.build();
	public static final ExplosionInfo HAUNTER_SHOT = ExplosionInfo.builder()
			.radius(4.5f)
			.entityDamage(6f)
			.blockDamage(8)
			.blockDropChance(0.1f)
			.build();
	public static final ExplosionInfo POP_SHOT = ExplosionInfo.builder()
				.radius(2)
				.entityDamage(3)
				.blockDamage(5)
				.blockDropChance(0.1f)
				.build();
	public static final ExplosionInfo HAUNTED_BOW = ExplosionInfo.builder()
				.radius(2)
				.entityDamage(3)
				.blockDamage(5)
				.blockDropChance(0.1f)
				.build();

	public static ExplosionInfo rpg(ToFloatFunction<LivingEntity> damageFunction) {
		return ExplosionInfo.builder()
				.radius(4)
				.blockDamage(20)
				.blockDropChance(0.55f)
				.entityDamage(12)
				.dynamicDamage((explosion, entity, damage) -> damage * (entity instanceof LivingEntity target ? damageFunction.apply(target) : 1))
				.entityKnockbackMultiplier(2.5f)
				.build();
	}

	public static ExplosionInfo bigBlast(ToFloatFunction<LivingEntity> damageFunction) {
		return ExplosionInfo.builder()
				.radius(10)
				.blockDamage(30)
				.blockDropChance(0.55f)
				.entityDamage(12)
				.dynamicDamage((explosion, entity, damage) -> damage * (entity instanceof LivingEntity target ? damageFunction.apply(target) : 1))
				.build();
	}

	public static ExplosionInfo missileMaker(ToFloatFunction<LivingEntity> damageFunction) {
		return ExplosionInfo.builder()
				.radius(3f)
				.blockDamage(7)
				.entityDamage(5)
				.blockDropChance(0.1f)
				.dynamicDamage((explosion, entity, damage) -> damage * (entity instanceof LivingEntity target ? damageFunction.apply(target) : 1))
				.build();
	}

	public static ExplosionInfo ancientBomber(ToFloatFunction<LivingEntity> damageFunction) {
		return ExplosionInfo.builder()
				.radius(3f)
				.blockDamage(7)
				.entityDamage(5)
				.blockDropChance(0.1f)
				.dynamicDamage((explosion, entity, damage) -> damage * (entity instanceof LivingEntity target ? damageFunction.apply(target) : 1))
				.build();
	}

	public static ExplosionInfo blastCannon(ToFloatFunction<LivingEntity> damageFunction) {
		return ExplosionInfo.builder()
				.radius(4)
				.blockDamage(15)
				.blockDropChance(0.55f)
				.entityDamage(8)
				.dynamicDamage((explosion, entity, damage) -> damage * (entity instanceof LivingEntity target ? damageFunction.apply(target) : 1))
				.build();
	}

	public static ExplosionInfo bombLauncher(ToFloatFunction<LivingEntity> damageFunction) {
		return ExplosionInfo.builder()
				.radius(5)
				.blockDamage(20)
				.blockDropChance(0.55f)
				.entityDamage(12)
				.dynamicDamage((explosion, entity, damage) -> damage * (entity instanceof LivingEntity target ? damageFunction.apply(target) : 1))
				.build();
	}

	public static ExplosionInfo floroRpg(ToFloatFunction<LivingEntity> damageFunction) {
		return ExplosionInfo.builder()
				.radius(6)
				.blockDamage(35)
				.blockDropChance(0.55f)
				.entityDamage(16)
				.entityKnockbackMultiplier(2.5f)
				.dynamicDamage((explosion, entity, damage) -> damage * (entity instanceof LivingEntity target ? damageFunction.apply(target) : 1))
				.build();
	}

	public static ExplosionInfo waterBalloonBomb(ToFloatFunction<LivingEntity> damageFunction) {
		return ExplosionInfo.builder()
				.radius(2)
				.entityDamage(4)
				.blockDamage(3)
				.blockDropChance(0.1f)
				.dynamicDamage((explosion, entity, damage) -> damage * (entity instanceof LivingEntity target ? damageFunction.apply(target) : 1))
				.postExplode(explosion -> {
					Level level = explosion.getLevel();

					if (level.isClientSide || level.dimensionType().ultraWarm() || !AoAGameRules.checkDestructiveWeaponPhysics(level))
						return;

					BlockPos pos = BlockPos.containing(explosion.position());

					if (level.getBlockState(pos).isAir()) {
						level.setBlockAndUpdate(pos, Blocks.WATER.defaultBlockState());
						AoAScheduler.scheduleCritical(20, tick -> level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState()));
					}
				})
				.build();
	}

	public static ExplosionInfo balloonBomb(ToFloatFunction<LivingEntity> damageFunction) {
		return ExplosionInfo.builder()
				.radius(2)
				.entityDamage(3)
				.blockDamage(3)
				.blockDropChance(0.1f)
				.dynamicDamage((explosion, entity, damage) -> damage * (entity instanceof LivingEntity target ? damageFunction.apply(target) : 1))
				.build();
	}

	public static ExplosionInfo boreicBow(boolean isCritArrow) {
		return ExplosionInfo.builder()
				.radius(isCritArrow ? 3 : 2)
				.entityDamage(isCritArrow ? 5 : 3)
				.blockDamage(isCritArrow ? 20 : 5)
				.blockDropChance(0.1f)
				.build();
	}
	//endregion
	//region <Other Weapons>
	public static final ExplosionInfo CREEPOID_GREATBLADE = ExplosionInfo.builder()
			.radius(4f)
			.entityDamage(4f)
			.blockDamage(6f)
			.build();
	public static final ExplosionInfo VULCAMMER_MAUL = ExplosionInfo.builder()
			.radius(6f)
			.entityDamage(6f)
			.blockDamage(9f)
			.build();
	public static final ExplosionInfo EXPLOCHRON_SWORD = ExplosionInfo.builder()
			.radius(3f)
			.entityDamage(4f)
			.blockDamage(6f)
			.build();
	public static final ExplosionInfo CONCUSSION_STAFF = ExplosionInfo.builder()
			.radius(4f)
			.entityDamage(8.5f)
			.blockDamage(3f)
			.build();
	public static ExplosionInfo rockbasherSword(float scale) {
		return ExplosionInfo.builder()
				.radius(3f + scale * 3f)
				.entityDamage(3f + scale * 7)
				.blockDamage(5 + scale * 8)
				.build();
	}
	//endregion
	//region <Armour>
	public static final ExplosionInfo OMNI_ARMOUR = ExplosionInfo.builder()
			.radius(5f)
			.entityDamage(5)
			.blockDamage(8f)
			.build();
	public static ExplosionInfo boreicArmour(int pieces) {
		return ExplosionInfo.builder()
				.radius(3f + pieces * 0.5f)
				.entityDamage(3f + pieces)
				.blockDamage(5 + pieces * 2)
				.build();
	}
	//endregion
	//region <Other>
	public static final ExplosionInfo CREEP_SPAWN = ExplosionInfo.builder()
			.radius(5f)
			.entityDamage(4)
			.blockDamage(8f)
			.build();
	//endregion
}
