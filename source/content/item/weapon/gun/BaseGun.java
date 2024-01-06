package net.tslat.aoa3.content.item.weapon.gun;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.common.packet.AoANetworking;
import net.tslat.aoa3.common.packet.packets.GunRecoilPacket;
import net.tslat.aoa3.common.registration.item.AoAEnchantments;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.content.enchantment.BraceEnchantment;
import net.tslat.aoa3.content.entity.projectile.gun.BaseBullet;
import net.tslat.aoa3.content.entity.projectile.gun.LimoniteBulletEntity;
import net.tslat.aoa3.content.item.weapon.sniper.BaseSniper;
import net.tslat.aoa3.content.item.weapon.staff.BaseStaff;
import net.tslat.aoa3.content.item.weapon.thrown.BaseThrownWeapon;
import net.tslat.aoa3.library.builder.SoundBuilder;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.NumberUtil;
import net.tslat.smartbrainlib.util.RandomUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;

public abstract class BaseGun extends Item {
	private final Multimap<Attribute, AttributeModifier> attributeModifiers = HashMultimap.create();

	protected static final UUID ATTACK_SPEED_MAINHAND = UUID.fromString("99fdc256-279e-4c8e-b1c6-9209571f134e");
	protected static final UUID ATTACK_SPEED_OFFHAND = UUID.fromString("63f030a6-7269-444d-b26c-ae3514b36e1b");

	protected final float dmg;
	protected final int firingDelay;
	protected final float recoilMod;
	protected double holsterMod;

	public BaseGun(Properties properties, final float dmg, final int fireDelayTicks, final float recoilMod) {
		super(properties);

		this.dmg = dmg;
		this.firingDelay = fireDelayTicks;
		this.recoilMod = recoilMod;
		this.holsterMod = getDamage() == 0 ? 0.85 : this instanceof BaseThrownWeapon ? 0.5 : 0.8 + 0.17 * Math.min(((20 / (double)getFiringDelay()) * getDamage()) / 55, 0.85);

		attributeModifiers.put(Attributes.ATTACK_SPEED, new AttributeModifier(ATTACK_SPEED_MAINHAND, "AoAGunMainHand", -getHolsterSpeed(), AttributeModifier.Operation.MULTIPLY_TOTAL));
	}

	public BaseGun(final float dmg, final int durability, final int fireDelayTicks, final float recoilMod) {
		this(new Item.Properties().durability(durability), dmg, fireDelayTicks, recoilMod);
	}

	public float getDamage() {
		return this.dmg;
	}

	public float getRecoilModifier() {
		return 0.35f;
	}

	public int getFiringDelay() {
		return firingDelay;
	}

	private double getHolsterSpeed() {
		return holsterMod;
	}

	@Nullable
	public SoundEvent getFiringSound() {
		return null;
	}

	protected float getFiringSoundPitchAdjust() {
		return 1f;
	}

	public float getRecoilForShot(ItemStack stack, LivingEntity shooter) {
		return (getDamage() == 0 ? 1 : (float)Math.pow(dmg, 1.4f)) * getRecoilModifier();
	}

	@Override
	public int getUseDuration(ItemStack stack) {
		return 72000;
	}

	@Override
	public int getEnchantmentValue() {
		return 8;
	}

	public Item getAmmoItem() {
		return AoAItems.LIMONITE_BULLET.get();
	}

	public InteractionHand getGunHand(ItemStack stack) {
		return stack.getEnchantmentLevel(AoAEnchantments.BRACE.get()) > 0 ? InteractionHand.OFF_HAND : InteractionHand.MAIN_HAND;
	}

	@Override
	public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
		return slotChanged || oldStack.getItem() != newStack.getItem();
	}

	@Override
	public boolean isValidRepairItem(ItemStack stack, ItemStack repairMaterial) {
		return false;
	}

	public boolean isFullAutomatic() {
		return true;
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
		ItemStack stack = player.getItemInHand(hand);

		if (hand != getGunHand(stack))
			return InteractionResultHolder.fail(stack);

		if (player.isUsingItem() && player.isBlocking())
			return InteractionResultHolder.pass(stack);

		if (player.getAttackStrengthScale(0.0f) < 1)
			return InteractionResultHolder.fail(stack);

		if (hand == InteractionHand.OFF_HAND && player.isShiftKeyDown()) {
			Item mainItem = player.getItemInHand(InteractionHand.MAIN_HAND).getItem();

			if (mainItem instanceof BaseSniper || mainItem instanceof BaseStaff)
				return InteractionResultHolder.fail(stack);
		}

		player.startUsingItem(hand);

		return InteractionResultHolder.pass(stack);
	}

	@Override
	public void onUseTick(Level level, LivingEntity shooter, ItemStack stack, int count) {
		if (!isFullAutomatic() && count < getUseDuration(stack))
			return;

		if (level instanceof ServerLevel serverLevel) {
			ServerPlayer player = shooter instanceof ServerPlayer ? (ServerPlayer)shooter : null;

			if (player == null || player.getCooldowns().getCooldownPercent(this, 0) == 0) {
				InteractionHand hand = getGunHand(stack);

				if (fireGun(serverLevel, shooter, stack, hand)) {
					ItemStack offhand;

					if (hand == InteractionHand.MAIN_HAND && (offhand = shooter.getItemInHand(InteractionHand.OFF_HAND)).getEnchantmentLevel(AoAEnchantments.BRACE.get()) > 0)
						offhand.onUseTick(serverLevel, shooter, count);

					ItemUtil.damageItem(stack, shooter, 1, hand == InteractionHand.OFF_HAND ? EquipmentSlot.OFFHAND : EquipmentSlot.MAINHAND);

					if (player != null) {
						player.awardStat(Stats.ITEM_USED.get(this));

						if (getFiringDelay() > 1)
							player.getCooldowns().addCooldown(this, getFiringDelay());

						doRecoil(player, stack, hand);
					}
				}
				else {
					shooter.releaseUsingItem();
				}
			}
		}
	}

	protected boolean fireGun(ServerLevel level, LivingEntity shooter, ItemStack stack, InteractionHand hand) {
		BaseBullet bullet = findAndConsumeAmmo(shooter, stack, hand);

		if (bullet == null)
			return false;

		shooter.level().addFreshEntity(bullet);
		doFiringEffects(level, shooter, bullet, stack, hand);

		return true;
	}

	public void doRecoil(ServerPlayer player, ItemStack stack, InteractionHand hand) {
		int control = stack.getEnchantmentLevel(AoAEnchantments.CONTROL.get());
		float recoilAmount = getRecoilForShot(stack, player) * 2 * (1 - control * 0.15f);

		AoANetworking.sendToPlayer(player, new GunRecoilPacket(hand == InteractionHand.OFF_HAND ? recoilAmount * 1.25f : recoilAmount, getFiringDelay()));
	}

	protected void doFiringEffects(ServerLevel level, LivingEntity shooter, BaseBullet bullet, ItemStack stack, InteractionHand hand) {
		doFiringSound(shooter, bullet, stack, hand);

		level.sendParticles(ParticleTypes.SMOKE, bullet.getX(), bullet.getY(), bullet.getZ(), 2, 0, 0, 0, 0.025f);

		if (this.dmg > 15) {
			if (this.dmg > 20) {
				level.sendParticles(ParticleTypes.FLAME, bullet.getX(), bullet.getY(), bullet.getZ(), 2, 0, 0, 0, 0.025f);
			}

			level.sendParticles(ParticleTypes.POOF, bullet.getX(), bullet.getY(), bullet.getZ(), 2, 0, 0, 0, 0.025f);
		}
	}

	public void doImpactDamage(Entity target, LivingEntity shooter, BaseBullet bullet, Vec3 impactPosition, float bulletDmgMultiplier) {
		if (target != null) {
			float shellMod = 1;

			if (bullet.getHand() != null)
				shellMod += 0.1f * shooter.getItemInHand(bullet.getHand()).getEnchantmentLevel(AoAEnchantments.SHELL.get());

			if (RandomUtil.percentChance(this.firingDelay / 10f)) {
				if (DamageUtil.doHeavyGunAttack(shooter, bullet, target, getDamage() * bulletDmgMultiplier * shellMod))
					doImpactEffect(target, shooter, bullet, impactPosition, bulletDmgMultiplier * shellMod);
			}
			else {
				if (DamageUtil.doGunAttack(shooter, bullet, target, getDamage() * bulletDmgMultiplier * shellMod))
					doImpactEffect(target, shooter, bullet, impactPosition, bulletDmgMultiplier * shellMod);
			}
		}
	}

	protected void doImpactEffect(Entity target, LivingEntity shooter, BaseBullet bullet, Vec3 impactPos, float bulletDmgMultiplier) {}

	protected void doFiringSound(LivingEntity shooter, BaseBullet bullet, ItemStack stack, InteractionHand hand) {
		if (getFiringSound() != null)
			new SoundBuilder(getFiringSound()).isPlayer().pitch(getFiringSoundPitchAdjust()).varyPitch(0.075f).followEntity(shooter).execute();
	}

	@Nullable
	public BaseBullet findAndConsumeAmmo(LivingEntity shooter, ItemStack gunStack, InteractionHand hand) {
		if (shooter.getType() != EntityType.PLAYER || ItemUtil.findInventoryItem((Player)shooter, new ItemStack(getAmmoItem()), !shooter.level().isClientSide(), 1 + gunStack.getEnchantmentLevel(AoAEnchantments.GREED.get())))
			return createProjectileEntity(shooter, gunStack, hand);

		return null;
	}

	public BaseBullet createProjectileEntity(LivingEntity shooter, ItemStack gunStack, InteractionHand hand) {
		return new LimoniteBulletEntity(shooter, this, hand, 120, 0);
	}

	@Override
	public int getDefaultTooltipHideFlags(@NotNull ItemStack stack) {
		return ItemStack.TooltipPart.MODIFIERS.getMask();
	}

	@Override
	public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
		if (slot == EquipmentSlot.MAINHAND) {
			return HashMultimap.create(attributeModifiers);
		}
		else if (slot == EquipmentSlot.OFFHAND) {
			Multimap<Attribute, AttributeModifier> multimap = HashMultimap.create(attributeModifiers);

			multimap.put(Attributes.MOVEMENT_SPEED, BraceEnchantment.BRACE_DEBUFF);

			return multimap;
		}

		return super.getAttributeModifiers(slot, stack);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
		float damage = getDamage() * (1 + (0.1f * stack.getEnchantmentLevel(AoAEnchantments.SHELL.get())));

		if (damage > 0)
			tooltip.add(1, LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Keys.GUN_DAMAGE, LocaleUtil.ItemDescriptionType.ITEM_DAMAGE, Component.literal(NumberUtil.roundToNthDecimalPlace(damage, 2))));

		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Keys.FIRING_SPEED, LocaleUtil.ItemDescriptionType.NEUTRAL, Component.literal(NumberUtil.roundToNthDecimalPlace(20 / (float)getFiringDelay(), 2))));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Keys.AMMO_ITEM, LocaleUtil.ItemDescriptionType.ITEM_AMMO_COST, getAmmoItem().getDescription()));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(isFullAutomatic() ? LocaleUtil.Keys.FULLY_AUTOMATIC_GUN : LocaleUtil.Keys.SEMI_AUTOMATIC_GUN, LocaleUtil.ItemDescriptionType.ITEM_TYPE_INFO));
	}
}
