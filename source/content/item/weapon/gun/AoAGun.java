package net.tslat.aoa3.content.item.weapon.gun;

import com.google.common.collect.ImmutableList;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.EnchantmentTarget;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.networking.AoANetworking;
import net.tslat.aoa3.common.networking.packets.GunRecoilPacket;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.common.registration.item.AoADataComponents;
import net.tslat.aoa3.common.registration.item.AoAEnchantments;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.content.entity.projectile.base.PhysicalWeaponProjectile;
import net.tslat.aoa3.content.entity.projectile.base.WeaponFiringContext;
import net.tslat.aoa3.content.entity.projectile.base.WeaponProjectile;
import net.tslat.aoa3.content.item.ProjectileFiringWeapon;
import net.tslat.aoa3.content.item.datacomponent.GunStats;
import net.tslat.aoa3.content.item.datacomponent.ItemUseSound;
import net.tslat.aoa3.content.item.tool.artifice.AmmoVoidPouch;
import net.tslat.aoa3.content.item.weapon.sniper.AoASniper;
import net.tslat.aoa3.content.item.weapon.staff.AoAStaff;
import net.tslat.aoa3.library.object.extension.MutableFloat;
import net.tslat.aoa3.library.object.interfaces.ToFloatFunction;
import net.tslat.aoa3.util.*;
import net.tslat.tme.api.object.RayTrace;
import net.tslat.tme.api.particle.ParticleBuilder;
import net.tslat.tme.api.sound.SoundBuilder;
import net.tslat.tme.api.util.RandomUtil;
import net.tslat.tme.internal.networking.packet.TMEParticlePacket;
import org.apache.commons.lang3.mutable.MutableBoolean;
import org.apache.commons.lang3.mutable.MutableObject;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class AoAGun extends ProjectileWeaponItem implements ProjectileFiringWeapon {
	public AoAGun(Item.Properties properties) {
		super(properties);
	}

	public GunStats getGunStats() {
		return getGunStats(getDefaultInstance());
	}

	public GunStats getGunStats(ItemStack stack) {
		final GunStats stats = stack.get(AoADataComponents.GUN_STATS);

		return stats != null ? stats : getGunStats();
	}

	public ItemUseSound getUseSound(ItemStack stack) {
		final ItemUseSound stats = stack.get(AoADataComponents.ITEM_USE_SOUND);

		return stats != null ? stats : ItemUseSound.none();
	}

	public float getGunDamage(ItemStack stack) {
		return getGunStats(stack).damage();
	}

	public float getRecoilModifier(ItemStack stack) {
		return getGunStats(stack).recoilModifier();
	}

	public int getTicksBetweenShots(ItemStack stack) {
		return getGunStats(stack).ticksBetweenShots();
	}

	public double getUnholsterTimeModifier(ItemStack stack) {
		return getGunStats(stack).unholsterTimeModifier();
	}

	public Optional<HolderSet<Item>> getDefaultAmmo(ItemStack stack) {
		return getGunStats(stack).ammo();
	}

	public boolean isFullAutomatic(ItemStack stack) {
		return getGunStats(stack).isFullAuto();
	}

	public Optional<Holder<SoundEvent>> getFiringSound(ItemStack stack) {
		return getUseSound(stack).sound();
	}

	public float getFiringSoundPitch(ItemStack stack) {
		return getUseSound(stack).pitch();
	}

	protected void onDamageEntity(Level level, WeaponProjectile projectile, RayTrace<?> rayTrace, Entity hitEntity, float damage) {}
	protected void onHitBlock(Level level, WeaponProjectile projectile, RayTrace<Void> rayTrace, BlockState hitBlock) {}
	protected void onGunFire(ServerLevel level, WeaponFiringContext context, WeaponProjectile projectile) {}

	public WeaponFiringContext.Builder createFiringContext(ItemStack stack, @Nullable Entity shooter, InteractionHand hand) {
		return WeaponFiringContext.Builder.of(stack, shooter, hand).degreesInaccuracy(2f).damage(getGunDamage(stack));
	}

	public WeaponProjectile createProjectileEntity(Level level, WeaponFiringContext context) {
		return new PhysicalWeaponProjectile(AoAProjectiles.LIMONITE_BULLET.get(), level, context);
	}

	@Override
	public ItemStack getDefaultCreativeAmmo(@Nullable Player player, ItemStack weapon) {
		Optional<HolderSet<Item>> defaultAmmo = getDefaultAmmo(weapon);

		if (defaultAmmo.isPresent() && defaultAmmo.get().size() > 0)
			return defaultAmmo.get().get(0).value().getDefaultInstance();

		return AoAItems.LIMONITE_BULLET.toStack();
	}

	public boolean isAmmoStack(ItemStack weaponStack, ItemStack ammoStack) {
		return getDefaultAmmo(weaponStack).map(ammoStack::is).orElse(false);
	}

	public float getRecoilForShot(ItemStack stack, LivingEntity shooter) {
		float baseDamage = getGunDamage(stack);

		return (baseDamage == 0 ? 1 : (float)Math.pow(baseDamage, 1.4f)) * getRecoilModifier(stack);
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
		return EnchantmentUtil.hasEnchantment(level, stack, AoAEnchantments.BRACE) ? InteractionHand.OFF_HAND : InteractionHand.MAIN_HAND;
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

		player.startUsingItem(hand);

		return InteractionResultHolder.pass(stack);
	}

	@Override
	public void onUseTick(Level level, LivingEntity shooter, ItemStack stack, int count) {
		if (!isFullAutomatic(stack) && count < getUseDuration(stack, shooter)) {
			shooter.stopUsingItem();

			return;
		}

		if (level instanceof ServerLevel serverLevel) {
			ServerPlayer player = shooter instanceof ServerPlayer pl ? pl : null;

			if (player == null || player.getCooldowns().getCooldownPercent(this, 0) == 0) {
				InteractionHand hand = getGunHand(level, stack);
				WeaponFiringContext context = createFiringContext(stack, shooter, hand).build();
				WeaponProjectile projectile = fireGun(serverLevel, context);

				if (projectile != null) {
					if (hand == InteractionHand.MAIN_HAND) {
						ItemStack offHandItem = shooter.getOffhandItem();

						if (EnchantmentUtil.hasEnchantment(level, offHandItem, AoAEnchantments.BRACE))
							offHandItem.onUseTick(serverLevel, shooter, count);
					}

					ItemUtil.damageItemForUser(serverLevel, stack, shooter, hand == InteractionHand.OFF_HAND ? EquipmentSlot.OFFHAND : EquipmentSlot.MAINHAND);

					if (player != null) {
						player.awardStat(Stats.ITEM_USED.get(this));

						int cooldown = getTicksBetweenShots(stack);

						if (cooldown > 1)
							player.getCooldowns().addCooldown(this, cooldown);

						doRecoil(player, context, projectile);
					}
				}
				else {
					shooter.releaseUsingItem();
				}
			}
		}
	}

	@Nullable
	protected WeaponProjectile fireGun(ServerLevel level, WeaponFiringContext context) {
		WeaponProjectile projectile = findAndConsumeAmmo(level, context);

		if (projectile == null || !(projectile.asEntity() instanceof Entity bullet))
			return null;

		projectile.fromArmPos().shootingAtTarget(context.velocity(), context.inaccuracy());

		if (level.addFreshEntity(bullet)) {
			onGunFire(level, context, projectile);
			doFiringEffects(level, context, projectile);
		}

		return projectile;
	}

	public void doRecoil(ServerPlayer player, WeaponFiringContext context, WeaponProjectile projectile) {
		float recoilAmount = AoAEnchantments.modifyRecoil(player.serverLevel(), context.weaponStack(), getRecoilForShot(context.weaponStack(), player) * 2);

		AoANetworking.sendToPlayer(player, new GunRecoilPacket(context.weaponHand() == InteractionHand.OFF_HAND ? recoilAmount * 1.25f : recoilAmount));
	}

	protected void doFiringEffects(ServerLevel level, WeaponFiringContext context, WeaponProjectile projectile) {
		doFiringSound(level, context, projectile);

		TMEParticlePacket packet = new TMEParticlePacket();
		Entity bullet = projectile.asEntity();
		float gunDamage = getGunDamage(context.weaponStack());

		for (int i = 0; i < 2; i++) {
			packet.particle(ParticleBuilder.forPositions(ParticleTypes.SMOKE, bullet.position())
									.power(new Vec3(RandomUtil.scaledGaussianValue(0.025f), RandomUtil.scaledGaussianValue(0.025f), RandomUtil.scaledGaussianValue(0.025f))));

			if (gunDamage > 15) {
				if (gunDamage > 20)
					packet.particle(ParticleBuilder.forPositions(ParticleTypes.FLAME, bullet.position())
											.power(new Vec3(RandomUtil.scaledGaussianValue(0.025f), RandomUtil.scaledGaussianValue(0.025f), RandomUtil.scaledGaussianValue(0.025f))));

				packet.particle(ParticleBuilder.forPositions(ParticleTypes.POOF, bullet.position())
										.power(new Vec3(RandomUtil.scaledGaussianValue(0.025f), RandomUtil.scaledGaussianValue(0.025f), RandomUtil.scaledGaussianValue(0.025f))));
			}
		}

		packet.sendToAllPlayersTrackingBlock(level, bullet.blockPosition());
	}

	protected void doFiringSound(ServerLevel level, WeaponFiringContext context, WeaponProjectile projectile) {
		Entity shooter = context.getShooter();

		getFiringSound(context.weaponStack()).ifPresent(sound ->
																(shooter == null ?
																 SoundBuilder.at(sound, level, projectile.asEntity().position()).category(SoundSource.PLAYERS) :
																 SoundBuilder.following(sound, shooter))
																		.pitch(getFiringSoundPitch(context.weaponStack()))
																		.varyPitch(0.075f)
																		.play());
	}

	@Override
	public boolean doEntityImpact(Level level, WeaponProjectile projectile, RayTrace<?> rayTrace, Entity hitEntity) {
		if (level instanceof ServerLevel serverLevel) {
			WeaponFiringContext context = projectile.getShotContext();
			MutableFloat damage = new MutableFloat(context.damage());
			Entity shooter = context.getShooter();
			MutableObject<DamageSource> damageSource = new MutableObject<>();
			ToFloatFunction<DamageSource> damageCalculator = source -> {
				modifyImpactDamage(serverLevel, projectile, rayTrace, hitEntity, source, damage);
				damageSource.setValue(source);

				return damage.floatValue();
			};

			if (RandomUtil.percentChance(getTicksBetweenShots(context.weaponStack()) / 10f) ?
				DamageUtil.doHeavyGunAttack(shooter, projectile.asEntity(), hitEntity, damageCalculator) :
				DamageUtil.doGunAttack(shooter, projectile.asEntity(), hitEntity, damageCalculator)) {
				onDamageEntity(level, projectile, rayTrace, hitEntity, damage.floatValue());

				if (shooter instanceof LivingEntity livingShooter) {
					EnchantmentHelper.runIterationOnItem(context.weaponStack(), LivingEntity.getSlotForHand(context.weaponHand()), livingShooter, (enchant, enchantLevel, enchantItem) ->
							enchant.value().doPostAttack(serverLevel, enchantLevel, enchantItem, EnchantmentTarget.DAMAGING_ENTITY, projectile.asEntity(), damageSource.getValue()));
				}

				return true;
			}

			return false;
		}

		return true;
	}

	@Override
	public boolean doBlockImpact(Level level, WeaponProjectile projectile, RayTrace<Void> rayTrace, BlockState hitBlock) {
		onHitBlock(level, projectile, rayTrace, hitBlock);

		return true;
	}

	protected void modifyImpactDamage(ServerLevel level, WeaponProjectile projectile, RayTrace<?> rayTrace, Entity hitEntity, DamageSource source, MutableFloat damage) {
		damage.setValue(EnchantmentHelper.modifyDamage(level, projectile.getShotContext().weaponStack(), hitEntity, source, damage.floatValue()));
	}

	@Nullable
	public WeaponProjectile findAndConsumeAmmo(Level level, WeaponFiringContext context) {
		if (context.getShooter() instanceof Player pl && !pl.hasInfiniteMaterials()) {
			int cost = AoAEnchantments.modifyAmmoCost(pl.level(), context.weaponStack(), 1);

			if (!InventoryUtil.findItemForConsumption(pl, item -> isAmmoStack(context.weaponStack(), item), cost, true)) {
				MutableBoolean foundAmmo = new MutableBoolean(false);

				AmmoVoidPouch.tryProduceProjectile(pl, this, context.weaponStack(), cost, stack -> foundAmmo.setTrue());

				if (foundAmmo.isFalse())
					return null;
			}
		}

		return createProjectileEntity(level, context);
	}

	public static ItemAttributeModifiers createGunAttributeModifiers(double holsterSpeed) {
		final ImmutableList.Builder<ItemAttributeModifiers.Entry> entries = ImmutableList.builder();

		entries.add(new ItemAttributeModifiers.Entry(
				Attributes.ATTACK_SPEED,
				new AttributeModifier(BASE_ATTACK_SPEED_ID, -holsterSpeed, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL),
				EquipmentSlotGroup.MAINHAND));
		entries.add(new ItemAttributeModifiers.Entry(
				Attributes.MOVEMENT_SPEED,
				new AttributeModifier(AdventOfAscension.id("brace_debuff"), -0.35, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
				EquipmentSlotGroup.OFFHAND));

		return new ItemAttributeModifiers(entries.build(), false);
	}

	@Override
	public int getDefaultProjectileRange() {
		return 32;
	}

	@Deprecated
	@Override
	public Predicate<ItemStack> getAllSupportedProjectiles() {
		GunStats gunStats = getGunStats();

		return gunStats.ammo().map(holderSet -> (Predicate<ItemStack>)stack -> stack.is(holderSet)).orElse(stack -> false);
	}

	@Override
	public Predicate<ItemStack> getAllSupportedProjectiles(ItemStack stack) {
		return getDefaultAmmo(stack).map(holderSet -> (Predicate<ItemStack>)ammoStack -> ammoStack.is(holderSet)).orElse(ammoStack -> false);
	}

	@Override
	protected void shoot(ServerLevel level, LivingEntity shooter, InteractionHand hand, ItemStack weapon, List<ItemStack> projectileItems, float velocity, float inaccuracy, boolean isCrit, @Nullable LivingEntity target) {
		WeaponFiringContext context = createFiringContext(weapon, shooter, hand).build();

		fireGun(level, context);
	}

	@Override
	public void shootProjectile(LivingEntity shooter, Projectile projectile, int projectileIndex, float velocity, float inaccuracy, float offset, @Nullable LivingEntity target) {
		if (shooter.level() instanceof ServerLevel level) {
			ItemStack defaultWeaponStack = getDefaultInstance();
			ItemStack ammo = shooter instanceof Player pl ? getDefaultCreativeAmmo(pl, defaultWeaponStack) : null;

			if (ammo == null) {
				Optional<HolderSet<Item>> defaultAmmo = getDefaultAmmo(defaultWeaponStack);

				if (defaultAmmo.isPresent() && defaultAmmo.get().size() > 0)
					ammo = defaultAmmo.get().get(0).value().getDefaultInstance();
			}


			if (ammo != null)
				shoot(level, shooter, InteractionHand.MAIN_HAND, defaultWeaponStack, List.of(ammo), velocity, 0, true, target);
		}
	}

	@Override
	public Projectile createProjectile(Level level, LivingEntity shooter, ItemStack weapon, ItemStack ammo, boolean isCrit) {
		return createProjectileEntity(level, createFiringContext(weapon, shooter, InteractionHand.MAIN_HAND).build()).asEntity();
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
		GunStats gunStats = getGunStats(stack);
		float damage = gunStats.damage();

		if (damage > 0)
			tooltip.add(1, LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Keys.GUN_DAMAGE, LocaleUtil.ItemDescriptionType.ITEM_DAMAGE, Component.literal(NumberUtil.roundToNthDecimalPlace(damage, 2))));

		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Keys.FIRING_SPEED, LocaleUtil.ItemDescriptionType.NEUTRAL, Component.literal(NumberUtil.roundToNthDecimalPlace(20 / (float) getTicksBetweenShots(stack), 2))));

		getDefaultAmmo(stack).ifPresent(ammo -> tooltip.add(gunStats.getAmmoNameForTooltip()));

		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(isFullAutomatic(stack) ? LocaleUtil.Keys.FULLY_AUTOMATIC_GUN : LocaleUtil.Keys.SEMI_AUTOMATIC_GUN, LocaleUtil.ItemDescriptionType.ITEM_TYPE_INFO));
	}
}
