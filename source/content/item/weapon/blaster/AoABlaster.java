package net.tslat.aoa3.content.item.weapon.blaster;

import com.google.common.collect.ImmutableList;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.tslat.aoa3.client.ClientOperations;
import net.tslat.aoa3.common.registration.custom.AoAResources;
import net.tslat.aoa3.common.registration.item.AoAArmourMaterials;
import net.tslat.aoa3.common.registration.item.AoADataComponents;
import net.tslat.aoa3.common.registration.item.AoAEnchantments;
import net.tslat.aoa3.content.entity.projectile.base.NonPhysicalWeaponProjectile;
import net.tslat.aoa3.content.entity.projectile.base.WeaponFiringContext;
import net.tslat.aoa3.content.entity.projectile.base.WeaponProjectile;
import net.tslat.aoa3.content.entity.projectile.base.WeaponRayTrace;
import net.tslat.aoa3.content.item.ProjectileFiringWeapon;
import net.tslat.aoa3.content.item.datacomponent.BlasterStats;
import net.tslat.aoa3.content.item.datacomponent.ItemUseSound;
import net.tslat.aoa3.content.item.weapon.sniper.AoASniper;
import net.tslat.aoa3.content.item.weapon.staff.AoAStaff;
import net.tslat.aoa3.library.constant.AttackSpeed;
import net.tslat.aoa3.library.object.extension.MutableFloat;
import net.tslat.aoa3.library.object.interfaces.ToFloatFunction;
import net.tslat.aoa3.util.*;
import net.tslat.tme.api.object.RayTrace;
import net.tslat.tme.api.sound.SoundBuilder;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.UnaryOperator;

public abstract class AoABlaster<T> extends Item implements ProjectileFiringWeapon {
	public AoABlaster(Item.Properties properties) {
		super(properties);
	}

	public BlasterStats getBlasterStats() {
		return getBlasterStats(getDefaultInstance());
	}

	public BlasterStats getBlasterStats(ItemStack stack) {
		final BlasterStats stats = stack.get(AoADataComponents.BLASTER_STATS);

		return stats != null ? stats : getBlasterStats();
	}

	public ItemUseSound getUseSound(ItemStack stack) {
		final ItemUseSound stats = stack.get(AoADataComponents.ITEM_USE_SOUND);

		return stats != null ? stats : ItemUseSound.none();
	}

	public float getBlasterDamage(ItemStack stack) {
		return getBlasterStats(stack).damage();
	}

	public int getTicksBetweenShots(ItemStack stack) {
		return getBlasterStats(stack).ticksBetweenShots();
	}

	public int getChargeTime(ItemStack stack) {
		return getBlasterStats(stack).chargeUpTicks();
	}

	public float getBaseSpiritCost(ItemStack stack) {
		return getBlasterStats(stack).spiritCost();
	}

	public float getBeamDistance(ItemStack stack) {
		return getBlasterStats(stack).beamDistance();
	}

	public Optional<Holder<SoundEvent>> getFiringSound(ItemStack stack) {
		return getUseSound(stack).sound();
	}

	public float getFiringSoundPitch(ItemStack stack) {
		return getUseSound(stack).pitch();
	}

	protected void onDamageEntity(Level level, T effect, WeaponFiringContext context, RayTrace<?> rayTrace, Entity hitEntity, float damage) {}
	protected void onHitBlock(Level level, T effect, WeaponFiringContext context, RayTrace<Void> rayTrace, BlockState hitBlock) {}
	protected void onGunFire(ServerLevel level, T effect, WeaponFiringContext context) {}

	public WeaponFiringContext.Builder createFiringContext(ItemStack stack, @Nullable Entity shooter, InteractionHand hand) {
		return WeaponFiringContext.Builder.of(stack, shooter, hand).degreesInaccuracy(1f).damage(getBlasterDamage(stack)).lifespan(60);
	}

	@Override
	public UseAnim getUseAnimation(ItemStack stack) {
		return UseAnim.NONE;
	}

	@Override
	public int getUseDuration(ItemStack stack, LivingEntity user) {
		return 72000;
	}

	@Override
	public int getEnchantmentValue() {
		return 8;
	}

	public InteractionHand getGunHand(Level level, ItemStack stack) {
		return InteractionHand.MAIN_HAND;
	}

	@Override
	public boolean isValidRepairItem(ItemStack stack, ItemStack repairMaterial) {
		return false;
	}

	@Override
	public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
		return slotChanged || oldStack.getItem() != newStack.getItem();
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		ItemStack stack = player.getItemInHand(hand);

		if (hand != getGunHand(level, stack))
			return InteractionResultHolder.fail(stack);

		if (player.isBlocking())
			return InteractionResultHolder.pass(stack);

		if (player.getAttackStrengthScale(0) < 1)
			return InteractionResultHolder.fail(stack);

		if (hand == InteractionHand.OFF_HAND) {
			if (AoASniper.isScoped(player) || player.getMainHandItem().getItem() instanceof AoAStaff)
				return InteractionResultHolder.fail(stack);
		}

		final float spiritCost = getSpiritCost(stack, player, false);

		if (spiritCost == 0 || player.hasInfiniteMaterials() || PlayerUtil.hasResourceAmount(player, AoAResources.SPIRIT.get(), spiritCost)) {
			player.startUsingItem(hand);

			return InteractionResults.ItemUse.noActionTaken(stack);
		}

		return player.hasInfiniteMaterials() ? InteractionResultHolder.pass(stack) : InteractionResultHolder.fail(stack);
	}

	@Override
	public void onUseTick(Level level, LivingEntity shooter, ItemStack stack, int count) {
		if (getUseDuration(stack, shooter) - count < getChargeTime(stack) - 2)
			return;

		if (level instanceof ServerLevel serverLevel) {
			ServerPlayer player = shooter instanceof ServerPlayer pl ? pl : null;

			if (player == null || player.getCooldowns().getCooldownPercent(this, 0) == 0) {
				if (tryFireBlaster(serverLevel, createFiringContext(stack, shooter, shooter.getUsedItemHand()).build())) {
					ItemUtil.damageItemForUser(serverLevel, stack, 1, shooter, shooter.getUsedItemHand());

					if (player != null) {
						player.awardStat(Stats.ITEM_USED.get(this));

						int cooldown = getTicksBetweenShots(stack);

						if (cooldown > 1)
							player.getCooldowns().addCooldown(this, cooldown);
					}
				}
				else {
					shooter.releaseUsingItem();
				}
			}
		}
	}

	protected boolean tryFireBlaster(ServerLevel level, WeaponFiringContext context) {
		final Entity shooter = context.getShooter();
		final float spiritCost = getSpiritCost(context.weaponStack(), shooter, true);

		if (spiritCost == 0 || !(shooter instanceof ServerPlayer pl) || PlayerUtil.consumeResource(pl, AoAResources.SPIRIT.get(), spiritCost, false)) {
			fireBlaster(level, context);

			return true;
		}

		PlayerUtil.notifyPlayerOfInsufficientResources(pl, AoAResources.SPIRIT.get(), spiritCost);

		return false;
	}

	abstract void fireBlaster(ServerLevel level, WeaponFiringContext context);

	protected <P extends NonPhysicalWeaponProjectile> void fireBasicBlasterProjectile(ServerLevel level, WeaponFiringContext context, DeferredHolder<EntityType<?>, EntityType<P>> projectileType) {
		fireBlasterProjectile(level, context, new NonPhysicalWeaponProjectile(projectileType.get(), level, context));
	}

	protected void fireBlasterProjectile(ServerLevel level, WeaponFiringContext context, BiFunction<ServerLevel, WeaponFiringContext, WeaponProjectile> factory) {
		fireBlasterProjectile(level, context, factory.apply(level, context), shot -> shot.fromArmPos().shootingAtTarget(context.velocity(), context.inaccuracy()));
	}

	protected void fireBlasterProjectile(ServerLevel level, WeaponFiringContext context, WeaponProjectile projectile) {
		fireBlasterProjectile(level, context, projectile, shot -> shot.fromArmPos().shootingAtTarget(context.velocity(), context.inaccuracy()));
	}

	protected void fireBlasterProjectile(ServerLevel level, WeaponFiringContext context, BiFunction<ServerLevel, WeaponFiringContext, WeaponProjectile> factory, UnaryOperator<WeaponProjectile> shootFunction) {
		fireBlasterProjectile(level, context, factory.apply(level, context), shootFunction);
	}

	protected void fireBlasterProjectile(ServerLevel level, WeaponFiringContext context, WeaponProjectile projectile, UnaryOperator<WeaponProjectile> shootFunction) {
		if (projectile == null || !(projectile.asEntity() instanceof Entity energyShot))
			return;

		shootFunction.apply(projectile);

		if (level.addFreshEntity(energyShot)) {
			onGunFire(level, (T)projectile, context);
			doFiringEffects(level, (T)projectile, projectile.asEntity().position(), context);
		}
	}

	protected void fireRayTrace(ServerLevel level, WeaponFiringContext context) {
		fireRayTrace(level, context, getBeamDistance(context.weaponStack()));
	}

	protected void fireRayTrace(ServerLevel level, WeaponFiringContext context, float maxDist) {
		if (context.getShooter() == null)
			return;

		final WeaponRayTrace rayTrace = WeaponRayTrace.create(this, context, context.getShooter(), maxDist);
		final RayTrace result = rayTrace.result();
		final T effect = (T)rayTrace;

		if (result.hitBlockPos() != null) {
			doBlockImpact(level, effect, context, result, level.getBlockState(result.hitBlockPos()));
		}
		else if (result.hitEntity() != null) {
			doEntityImpact(level, effect, context, result, (Entity)result.hitEntity());
		}

		onGunFire(level, effect, context);
		doFiringEffects(level, effect, result.fromPos(), context);
	}

	@Override
	public int getBarWidth(ItemStack stack) {
		final float chargeProgress = getChargeProgress(stack);

		if (chargeProgress > 0)
			return Mth.floor(chargeProgress * 13);

		return super.getBarWidth(stack);
	}

	@Override
	public boolean isBarVisible(ItemStack stack) {
		return super.isBarVisible(stack) || getChargeProgress(stack) > 0;
	}

	@Override
	public int getBarColor(ItemStack stack) {
		final float chargeProgress = getChargeProgress(stack);

		if (chargeProgress > 0)
			return 0x00C1DB;

		return super.getBarColor(stack);
	}

	private float getChargeProgress(ItemStack stack) {
		if (FMLEnvironment.dist == Dist.CLIENT) {
			Player player = ClientOperations.getPlayer();

			if (player.getUseItem() == stack) {
				final int useTime = stack.getUseDuration(player) - player.getUseItemRemainingTicks();
				final int chargeTime = getChargeTime(stack);

				if (useTime < chargeTime - 1)
					return useTime / (float)(chargeTime - 1);
			}
		}

		return 0;
	}

	public float getSpiritCost(ItemStack stack, @Nullable Entity shooter, boolean forShotConsumption) {
		if (!(shooter instanceof Player pl) || (pl.hasInfiniteMaterials() && forShotConsumption))
			return 0;

		float spiritCost = getBaseSpiritCost(stack);

		spiritCost = AoAEnchantments.modifySpiritConsumption(pl.level(), stack, spiritCost);
		spiritCost *= (1 + AoAEnchantments.modifyAmmoCost(pl.level(), stack, 0) * 0.3f);

		if (PlayerUtil.isWearingFullSet(pl, AoAArmourMaterials.GHOULISH))
			spiritCost *= 0.7f;

		return spiritCost;
	}

	protected void doFiringEffects(ServerLevel level, T effect, Vec3 pos, WeaponFiringContext context) {
		doFiringSound(level, effect, pos, context);
	}

	protected void doFiringSound(ServerLevel level, T effect, Vec3 pos, WeaponFiringContext context) {
		Entity shooter = context.getShooter();

		getFiringSound(context.weaponStack()).ifPresent(sound ->
																(shooter == null ? SoundBuilder.at(sound, level, pos).category(SoundSource.PLAYERS) : SoundBuilder.following(sound, shooter))
																		.pitch(getFiringSoundPitch(context.weaponStack()))
																		.varyPitch(0.075f)
																		.play());
	}

	@Override
	public final boolean doEntityImpact(Level level, WeaponProjectile projectile, RayTrace<?> rayTrace, Entity hitEntity) {
		return doEntityImpact(level, (T)projectile, projectile.getShotContext(), rayTrace, hitEntity);
	}

	@Override
	public final boolean doBlockImpact(Level level, WeaponProjectile projectile, RayTrace<Void> rayTrace, BlockState hitBlock) {
		return doBlockImpact(level, (T)projectile, projectile.getShotContext(), rayTrace, hitBlock);
	}

	protected boolean doEntityImpact(Level level, T effect, WeaponFiringContext context, RayTrace<?> rayTrace, Entity hitEntity) {
		if (level instanceof ServerLevel serverLevel) {
			MutableFloat damage = new MutableFloat(context.damage());
			ToFloatFunction<DamageSource> damageCalculator = source -> {
				modifyImpactDamage(serverLevel, effect, context, rayTrace, hitEntity, source, damage);

				return damage.floatValue();
			};

			if (DamageUtil.doEnergyProjectileAttack(context.getShooter(), effect instanceof WeaponProjectile projectile ? projectile.asEntity() : null, hitEntity, damageCalculator)) {
				onDamageEntity(level, effect, context, rayTrace, hitEntity, damage.floatValue());

				return true;
			}

			return false;
		}

		return true;
	}

	protected boolean doBlockImpact(Level level, T effect, WeaponFiringContext context, RayTrace<Void> rayTrace, BlockState hitBlock) {
		onHitBlock(level, effect, context, rayTrace, hitBlock);

		return true;
	}

	protected void modifyImpactDamage(ServerLevel level, T effect, WeaponFiringContext context, RayTrace<?> rayTrace, Entity hitEntity, DamageSource source, MutableFloat damage) {
		damage.setValue(EnchantmentHelper.modifyDamage(level, context.weaponStack(), hitEntity, source, damage.floatValue()));
	}

	public static ItemAttributeModifiers createBlasterAttributeModifiers() {
		final ImmutableList.Builder<ItemAttributeModifiers.Entry> entries = ImmutableList.builder();

		entries.add(new ItemAttributeModifiers.Entry(
				Attributes.ATTACK_SPEED,
				new AttributeModifier(BASE_ATTACK_SPEED_ID, AttackSpeed.BLASTER, AttributeModifier.Operation.ADD_VALUE),
				EquipmentSlotGroup.MAINHAND));

		return new ItemAttributeModifiers(entries.build(), false);
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
		if (getBlasterDamage(stack) > 0)
			tooltip.add(1, LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Keys.ENERGY_DAMAGE, LocaleUtil.ItemDescriptionType.ITEM_DAMAGE, Component.literal(NumberUtil.roundToNthDecimalPlace(getBlasterDamage(stack), 1))));

		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Keys.BLASTER_CHARGE, LocaleUtil.ItemDescriptionType.ITEM_TYPE_INFO, Component.literal(NumberUtil.roundToNthDecimalPlace((float)getChargeTime(stack) / 20f, 2))));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Keys.BLASTER_PENETRATION, LocaleUtil.ItemDescriptionType.ITEM_TYPE_INFO));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Keys.FIRING_SPEED, LocaleUtil.ItemDescriptionType.NEUTRAL, Component.literal(NumberUtil.roundToNthDecimalPlace(20 / (float) getTicksBetweenShots(stack), 2))));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Keys.AMMO_RESOURCE, LocaleUtil.ItemDescriptionType.ITEM_AMMO_COST, Component.literal(NumberUtil.roundToNthDecimalPlace(getSpiritCost(stack, FMLEnvironment.dist == Dist.CLIENT ? ClientOperations.getPlayer() : null, false), 2)), AoAResources.SPIRIT.get().getName()));
	}
}
