package net.tslat.aoa3.object.item.weapon.gun;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.stats.Stats;
import net.minecraft.util.*;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.tslat.aoa3.common.packet.AoAPackets;
import net.tslat.aoa3.common.packet.packets.GunRecoilPacket;
import net.tslat.aoa3.common.registration.AoAEnchantments;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.object.enchantment.BraceEnchantment;
import net.tslat.aoa3.object.entity.projectile.gun.BaseBullet;
import net.tslat.aoa3.object.entity.projectile.gun.LimoniteBulletEntity;
import net.tslat.aoa3.object.item.weapon.sniper.BaseSniper;
import net.tslat.aoa3.object.item.weapon.staff.BaseStaff;
import net.tslat.aoa3.object.item.weapon.thrown.BaseThrownWeapon;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.NumberUtil;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public abstract class BaseGun extends Item {
	private final Multimap<Attribute, AttributeModifier> attributeModifiers = HashMultimap.create();

	protected static final UUID ATTACK_SPEED_MAINHAND = UUID.fromString("99fdc256-279e-4c8e-b1c6-9209571f134e");
	protected static final UUID ATTACK_SPEED_OFFHAND = UUID.fromString("63f030a6-7269-444d-b26c-ae3514b36e1b");

	protected final double dmg;
	protected final int firingDelay;
	protected final float recoilMod;
	protected double holsterMod;

	public BaseGun(Item.Properties properties, final double dmg, final int fireDelayTicks, final float recoilMod) {
		super(properties);

		this.dmg = dmg;
		this.firingDelay = fireDelayTicks;
		this.recoilMod = recoilMod;
		this.holsterMod = getDamage() == 0 ? 0.85 : this instanceof BaseThrownWeapon ? 0.5 : 0.8 + 0.17 * Math.min(((20 / (double)getFiringDelay()) * getDamage()) / 55, 0.85);

		attributeModifiers.put(Attributes.ATTACK_SPEED, new AttributeModifier(ATTACK_SPEED_MAINHAND, "AoAGunMainHand", -getHolsterSpeed(), AttributeModifier.Operation.MULTIPLY_TOTAL));
	}

	public BaseGun(ItemGroup itemGroup, final double dmg, final int durability, final int fireDelayTicks, final float recoilMod) {
		this(new Item.Properties().tab(itemGroup).durability(durability), dmg, fireDelayTicks, recoilMod);
	}

	public double getDamage() {
		return dmg;
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

	public Hand getGunHand(ItemStack stack) {
		return EnchantmentHelper.getItemEnchantmentLevel(AoAEnchantments.BRACE.get(), stack) > 0 ? Hand.OFF_HAND : Hand.MAIN_HAND;
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
	public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
		ItemStack stack = player.getItemInHand(hand);

		if (hand != getGunHand(stack))
			return ActionResult.fail(stack);

		if (player.isUsingItem() && player.isBlocking())
			return ActionResult.pass(stack);

		if (player.getAttackStrengthScale(0.0f) < 1)
			return ActionResult.fail(stack);

		if (hand == Hand.OFF_HAND && player.isShiftKeyDown()) {
			Item mainItem = player.getItemInHand(Hand.MAIN_HAND).getItem();

			if (mainItem instanceof BaseSniper || mainItem instanceof BaseStaff)
				return ActionResult.fail(stack);
		}

		player.startUsingItem(hand);

		return ActionResult.pass(stack);
	}

	@Override
	public void onUsingTick(ItemStack stack, LivingEntity shooter, int count) {
		if (!isFullAutomatic() && count < getUseDuration(stack))
			return;

		ServerPlayerEntity player = shooter instanceof ServerPlayerEntity ? (ServerPlayerEntity)shooter : null;
		int nextFireDelay = getFiringDelay();

		if (player == null || player.getCooldowns().getCooldownPercent(this, 0) == 0) {
			Hand hand = getGunHand(stack);

			if (fireGun(shooter, stack, hand)) {
				ItemStack offhand;

				if (hand == Hand.MAIN_HAND && EnchantmentHelper.getItemEnchantmentLevel(AoAEnchantments.BRACE.get(), (offhand = shooter.getItemInHand(Hand.OFF_HAND))) > 0)
					offhand.onUsingTick(shooter, count);

				ItemUtil.damageItem(stack, shooter, 1, hand == Hand.OFF_HAND ? EquipmentSlotType.OFFHAND : EquipmentSlotType.MAINHAND);

				if (player != null) {
					player.awardStat(Stats.ITEM_USED.get(this));
					player.getCooldowns().addCooldown(this, nextFireDelay);

					doRecoil(player, stack, hand);
				}
			}
			else {
				shooter.releaseUsingItem();
			}
		}
	}

	protected boolean fireGun(LivingEntity shooter, ItemStack stack, Hand hand) {
		BaseBullet bullet = findAndConsumeAmmo(shooter, stack, hand);

		if (bullet == null)
			return false;

		shooter.level.addFreshEntity(bullet);

		if (!shooter.level.isClientSide())
			doFiringEffects(shooter, bullet, stack, hand);

		return true;
	}

	public void doRecoil(ServerPlayerEntity player, ItemStack stack, Hand hand) {
		int control = EnchantmentHelper.getItemEnchantmentLevel(AoAEnchantments.CONTROL.get(), stack);
		float recoilAmount = getRecoilForShot(stack, player) * 2 * (1 - control * 0.15f);

		AoAPackets.messagePlayer(player, new GunRecoilPacket(hand == Hand.OFF_HAND ? recoilAmount * 1.25f : recoilAmount, getFiringDelay()));
	}

	protected void doFiringEffects(LivingEntity shooter, BaseBullet bullet, ItemStack stack, Hand hand) {
		doFiringSound(shooter, bullet, stack, hand);

		((ServerWorld)shooter.level).sendParticles(ParticleTypes.SMOKE, bullet.getX(), bullet.getY(), bullet.getZ(), 2, 0, 0, 0, 0.025f);

		if (dmg > 15) {
			if (dmg > 20) {
				((ServerWorld)shooter.level).sendParticles(ParticleTypes.FLAME, bullet.getX(), bullet.getY(), bullet.getZ(), 2, 0, 0, 0, 0.025f);
			}

			((ServerWorld)shooter.level).sendParticles(ParticleTypes.POOF, bullet.getX(), bullet.getY(), bullet.getZ(), 2, 0, 0, 0, 0.025f);
		}
	}

	public void doImpactDamage(Entity target, LivingEntity shooter, BaseBullet bullet, float bulletDmgMultiplier) {
		if (target != null) {
			float shellMod = 1;

			if (bullet.getHand() != null)
				shellMod += 0.1 * EnchantmentHelper.getItemEnchantmentLevel(AoAEnchantments.SHELL.get(), shooter.getItemInHand(bullet.getHand()));

			if (DamageUtil.dealGunDamage(target, shooter, bullet, (float)getDamage() * bulletDmgMultiplier * shellMod)) {
				doImpactEffect(target, shooter, bullet, bulletDmgMultiplier);
			}
			else if (!(target instanceof LivingEntity)) {
				target.hurt(new IndirectEntityDamageSource("gun", bullet, shooter).setProjectile(), (float)getDamage() * bulletDmgMultiplier * shellMod);
			}
		}
	}

	protected void doImpactEffect(Entity target, LivingEntity shooter, BaseBullet bullet, float bulletDmgMultiplier) {}

	protected void doFiringSound(LivingEntity shooter, BaseBullet bullet, ItemStack stack, Hand hand) {
		if (getFiringSound() != null)
			shooter.level.playSound(null, shooter.getX(), shooter.getY(), shooter.getZ(), getFiringSound(), SoundCategory.PLAYERS, 1.0f, getFiringSoundPitchAdjust() + (float)random.nextGaussian() * 0.075f);
	}

	@Nullable
	public BaseBullet findAndConsumeAmmo(LivingEntity shooter, ItemStack gunStack, Hand hand) {
		if (shooter.getType() != EntityType.PLAYER || ItemUtil.findInventoryItem((PlayerEntity)shooter, new ItemStack(getAmmoItem()), !shooter.level.isClientSide(), 1 + EnchantmentHelper.getItemEnchantmentLevel(AoAEnchantments.GREED.get(), gunStack)))
			return createProjectileEntity(shooter, gunStack, hand);

		return null;
	}

	public BaseBullet createProjectileEntity(LivingEntity shooter, ItemStack gunStack, Hand hand) {
		return new LimoniteBulletEntity(shooter, this, hand, 120, 0);
	}

	@Nullable
	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundNBT nbt) {
		stack.getOrCreateTag().putInt("HideFlags", ItemStack.TooltipDisplayFlags.MODIFIERS.getMask());

		return null;
	}

	@Override
	public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlotType slot, ItemStack stack) {
		if (slot == EquipmentSlotType.MAINHAND) {
			return HashMultimap.create(attributeModifiers);
		}
		else if (slot == EquipmentSlotType.OFFHAND) {
			Multimap<Attribute, AttributeModifier> multimap = HashMultimap.create(attributeModifiers);

			multimap.put(Attributes.MOVEMENT_SPEED, BraceEnchantment.BRACE_DEBUFF);

			return multimap;
		}

		return super.getAttributeModifiers(slot, stack);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		tooltip.add(1, LocaleUtil.getFormattedItemDescriptionText("items.description.damage.gun", LocaleUtil.ItemDescriptionType.ITEM_DAMAGE, new StringTextComponent(NumberUtil.roundToNthDecimalPlace((float)getDamage() * (1 + (0.1f * EnchantmentHelper.getItemEnchantmentLevel(AoAEnchantments.SHELL.get(), stack))), 2))));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Constants.FIRING_SPEED, LocaleUtil.ItemDescriptionType.NEUTRAL, new StringTextComponent(NumberUtil.roundToNthDecimalPlace(20 / (float)getFiringDelay(), 2))));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Constants.AMMO_ITEM, LocaleUtil.ItemDescriptionType.ITEM_AMMO_COST, getAmmoItem().getDescription()));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(isFullAutomatic() ? "items.description.gun.fully_automatic" : "items.description.gun.semi_automatic", LocaleUtil.ItemDescriptionType.ITEM_TYPE_INFO));
	}
}
