package net.tslat.aoa3.content.entity.monster.overworld;

import com.google.common.collect.ImmutableList;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.damagesource.DamageContainer;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.entity.AoAEntityStats;
import net.tslat.aoa3.common.registration.entity.AoAMobEffects;
import net.tslat.aoa3.content.entity.ai.mob.TelegraphedMeleeAttackGoal;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;
import net.tslat.aoa3.library.builder.EntitySpawnConditions;
import net.tslat.aoa3.library.constant.ScreenImageEffect;
import net.tslat.aoa3.util.AdvancementUtil;
import net.tslat.aoa3.util.AttributeUtil;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.tme.api.object.builder.EffectBuilder;
import net.tslat.tme.api.particle.ParticleBuilder;
import net.tslat.tme.api.sound.SoundBuilder;
import net.tslat.tme.internal.networking.packet.TMEParticlePacket;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.constant.DefaultAnimations;

import java.util.List;

public class WoodGiantEntity extends AoAMeleeMob<WoodGiantEntity> {
	public static final EntityDataAccessor<Integer> STAGE = makeSynchedData(WoodGiantEntity.class, EntityDataSerializers.INT);

	private int lastMeleeHit = 0;

	public WoodGiantEntity(EntityType<? extends WoodGiantEntity> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	protected Brain.Provider<WoodGiantEntity> brainProvider() { // TODO
		return Brain.provider(List.of(MemoryModuleType.ATTACK_TARGET), ImmutableList.of());
	}

	@Override
	protected void registerGoals() {
		goalSelector.addGoal(1, new FloatGoal(this));
		goalSelector.addGoal(2, new TelegraphedMeleeAttackGoal<>(this).preAttackTime(getPreAttackTime()).attackInterval(getCurrentSwingDuration()));
		goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1));
		goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8f));
		goalSelector.addGoal(8, new RandomLookAroundGoal(this));
		targetSelector.addGoal(1, new HurtByTargetGoal(this));
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);

		builder.define(STAGE, 0);
	}

	@Nullable
	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, @Nullable SpawnGroupData spawnDataIn) {
		AttributeUtil.applyTransientModifier(this, Attributes.ARMOR, getArmourMod(getStage()));
		AttributeUtil.applyTransientModifier(this, Attributes.ARMOR_TOUGHNESS, getToughnessMod(getStage()));

		return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn);
	}

	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.TREE_FALL.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.WOODY_HIT.get();
	}

	@Override
	protected SoundEvent getStepSound(BlockPos pos, BlockState blockState) {
		return AoASounds.WOODY_THUMP.get();
	}

	@Override
	public boolean isPushable() {
		return false;
	}

	@Override
	protected int getAttackSwingDuration() {
		return 11;
	}

	@Override
	protected int getPreAttackTime() {
		return 6;
	}

	@Override
	public void onDamageTaken(DamageContainer damageContainer) {
		if (level() instanceof ServerLevel level && DamageUtil.isMeleeDamage(damageContainer.getSource())) {
			lastMeleeHit = tickCount;

			if (damageContainer.getSource().getEntity() instanceof LivingEntity attacker) {
				TMEParticlePacket particlePacket = new TMEParticlePacket();
				ItemStack weapon = attacker.getItemInHand(InteractionHand.MAIN_HAND);

				if (weapon.isCorrectToolForDrops(Blocks.OAK_LOG.defaultBlockState())) {
					lastMeleeHit += 100;

					particlePacket.particle(ParticleBuilder.forRandomPosInEntity(new BlockParticleOption(ParticleTypes.BLOCK, Blocks.OAK_LOG.defaultBlockState()), this).spawnNTimes(10));

					if (getHealth() <= 0 && attacker instanceof ServerPlayer pl)
						AdvancementUtil.grantCriterion(pl, AdventOfAscension.id("i_axed_you_a_question"), "tool_kill");
				}

				if (getStage() < 3) {
					setSynchedData(STAGE, getStage() + 1);
					particlePacket.particle(ParticleBuilder.forRandomPosInEntity(new BlockParticleOption(ParticleTypes.BLOCK, Blocks.OAK_LOG.defaultBlockState()), this).spawnNTimes(5));

					if (!(attacker instanceof ServerPlayer pl) || !pl.getAbilities().invulnerable) {
						if (attacker instanceof ServerPlayer pl)
							new ScreenImageEffect(ScreenImageEffect.Type.BLOOD).duration(80).randomScale().coloured(255, 0, 0, 127).sendToPlayer(pl);

						EntityUtil.applyPotions(attacker, this, new EffectBuilder(AoAMobEffects.BLEEDING, 600).hideParticles());
					}

					SoundBuilder.following(AoASounds.HEAVY_WOOD_SHATTER.get(), this).play();
				}

				particlePacket.sendToAllPlayersNearby(level, position(), 20);
			}
		}
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);

		if (compound.contains("WoodStage"))
			setStage(compound.getInt("WoodStage"));
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);

		compound.putInt("WoodStage", getStage());
	}

	@Override
	protected void customServerAiStep() {
		super.customServerAiStep();

		if (tickCount - lastMeleeHit > 600) {
			int stage = getStage();
			lastMeleeHit = tickCount;

			if (stage > 0 && stage <= 3) {
				setSynchedData(STAGE, stage - 1);
				heal(20);
			}
		}
	}

	private void setStage(int stage) {
		if (!level().isClientSide()) {
			setSynchedData(STAGE, Mth.clamp(stage, 0, 3));
			int oldStage = getStage();

			AttributeUtil.applyTransientModifier(this, Attributes.ARMOR, getArmourMod(getStage()));
			AttributeUtil.applyTransientModifier(this, Attributes.ARMOR_TOUGHNESS, getToughnessMod(getStage()));

			if (oldStage < stage) {
				ParticleBuilder.forRandomPosInEntity(new BlockParticleOption(ParticleTypes.BLOCK, Blocks.OAK_LOG.defaultBlockState()), this)
						.spawnNTimes(10)
						.sendToAllPlayersNearby((ServerLevel)level(), position(), 20);
			}
		}
	}

	public int getStage() {
		return getSynchedData(STAGE);
	}

	private static AttributeModifier getArmourMod(int stage) {
		return new AttributeModifier(AdventOfAscension.id("wood_giant_stage"), 35 - (Math.max(0, stage + 1) * 10), AttributeModifier.Operation.ADD_VALUE);
	}

	private static AttributeModifier getToughnessMod(int stage) {
		return new AttributeModifier(AdventOfAscension.id("wood_giant_stage"), 50 - (Math.max(0, stage + 1) * 15), AttributeModifier.Operation.ADD_VALUE);
	}

	public static SpawnPlacements.SpawnPredicate<WoodGiantEntity> spawnRules(EntityType<WoodGiantEntity> entityType) {
		return EntitySpawnConditions.createDayMonster(entityType).spawnChance(1 / 15f);
	}

	public static AoAEntityStats.AttributeBuilder entityStats(EntityType<WoodGiantEntity> entityType) {
		return AoAEntityStats.AttributeBuilder.createMonster(entityType)
				.health(140)
				.moveSpeed(0.32)
				.meleeStrength(11)
				.knockbackResist(1)
				.armour(10, 5)
				.followRange(40)
				.stepHeight(1.5f);
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
		controllers.add(DefaultAnimations.genericWalkController(this),
				DefaultAnimations.genericAttackAnimation(this, DefaultAnimations.ATTACK_SLAM).transitionLength(0));
	}
}
