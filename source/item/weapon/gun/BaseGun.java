package net.tslat.aoa3.item.weapon.gun;

import com.google.common.collect.Multimap;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.stats.Stats;
import net.minecraft.util.*;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.tslat.aoa3.common.packet.AoAPackets;
import net.tslat.aoa3.common.packet.packets.GunRecoilPacket;
import net.tslat.aoa3.common.registration.AoAEnchantments;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.config.AoAConfig;
import net.tslat.aoa3.enchantment.BraceEnchantment;
import net.tslat.aoa3.entity.projectile.gun.BaseBullet;
import net.tslat.aoa3.entity.projectile.gun.LimoniteBulletEntity;
import net.tslat.aoa3.item.weapon.sniper.BaseSniper;
import net.tslat.aoa3.item.weapon.staff.BaseStaff;
import net.tslat.aoa3.item.weapon.thrown.BaseThrownWeapon;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.NumberUtil;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public abstract class BaseGun extends Item {
	protected static final UUID ATTACK_SPEED_MAINHAND = UUID.fromString("99fdc256-279e-4c8e-b1c6-9209571f134e");
	protected static final UUID ATTACK_SPEED_OFFHAND = UUID.fromString("63f030a6-7269-444d-b26c-ae3514b36e1b");

	protected final double dmg;
	protected final int firingDelay;
	protected final float recoil;
	protected double holsterMod;

	public BaseGun(Item.Properties properties, final double dmg, final int fireDelayTicks, final float recoil) {
		super(properties);

		this.dmg = dmg;
		this.firingDelay = fireDelayTicks;
		this.recoil = recoil;
		this.holsterMod = getDamage() == 0 ? 0.85 : this instanceof BaseThrownWeapon ? 0.5 : 0.8 + 0.17 * Math.min(((20 / (double)getFiringDelay()) * getDamage()) / 55, 0.85);
	}

	public BaseGun(ItemGroup itemGroup, final double dmg, final int durability, final int fireDelayTicks, final float recoil) {
		this(new Item.Properties().group(itemGroup).maxDamage(durability), dmg, fireDelayTicks, recoil);
	}

	public double getDamage() {
		return dmg * (AoAConfig.COMMON.hardcoreMode.get() ? 1.25f : 1f);
	}

	public float getRecoil() {
		return recoil;
	}

	public int getFiringDelay() {
		return firingDelay;
	}

	public double getDrawTime() {
		return holsterMod;
	}

	@Nullable
	public SoundEvent getFiringSound() {
		return null;
	}

	public float getRecoilForShot(ItemStack stack, LivingEntity shooter) {
		return getRecoil();
	}

	@Override
	public boolean getIsRepairable(ItemStack stack, ItemStack repairMaterial) {
		return false;
	}

	@Override
	public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
		return slotChanged || oldStack.getItem() != newStack.getItem();
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
		ItemStack stack = player.getHeldItem(hand);

		if (hand != getGunHand(stack))
			return ActionResult.resultFail(stack);

		if (player.isHandActive() && player.isActiveItemStackBlocking())
			return ActionResult.resultPass(stack);

		if (hand == Hand.OFF_HAND && player.isSneaking()) {
			Item mainItem = player.getHeldItem(Hand.MAIN_HAND).getItem();

			if (mainItem instanceof BaseSniper || mainItem instanceof BaseStaff)
				return ActionResult.resultFail(stack);
		}

		if (player.getCooledAttackStrength(0.0f) < 1)
			return ActionResult.resultFail(stack);

		BaseBullet ammo = null;

		if (!world.isRemote)
			ammo = findAndConsumeAmmo(player, stack, hand);

		if (ammo != null) {
			world.addEntity(ammo);
			player.addStat(Stats.ITEM_USED.get(this));
			player.getCooldownTracker().setCooldown(this, getFiringDelay());
			ItemUtil.damageItem(stack, player, hand);

			if (getFiringSound() != null)
				player.world.playSound(null, player.getPosX(), player.getPosY(), player.getPosZ(), getFiringSound(), SoundCategory.PLAYERS, 1.0f, 1.0f);

			if (player instanceof ServerPlayerEntity) {
				int control = EnchantmentHelper.getEnchantmentLevel(AoAEnchantments.CONTROL.get(), stack);
				float recoiling = getRecoilForShot(stack, player) * (1 - control * 0.15f);

				AoAPackets.messagePlayer((ServerPlayerEntity)player, new GunRecoilPacket(hand == Hand.OFF_HAND ? recoiling * 2.5f : recoiling, getFiringDelay()));
			}

			return ActionResult.resultSuccess(stack);
		}

		if (player instanceof ServerPlayerEntity)
			((ServerPlayerEntity)player).sendContainerToPlayer(player.container);

		return ActionResult.resultPass(stack);
	}

	public Hand getGunHand(ItemStack stack) {
		return EnchantmentHelper.getEnchantmentLevel(AoAEnchantments.BRACE.get(), stack) > 0 ? Hand.OFF_HAND : Hand.MAIN_HAND;
	}

	public void doImpactDamage(Entity target, LivingEntity shooter, BaseBullet bullet, float bulletDmgMultiplier) {
		if (target != null) {
			float shellMod = 1;

			if (bullet.getHand() != null)
				shellMod += 0.1 * EnchantmentHelper.getEnchantmentLevel(AoAEnchantments.SHELL.get(), shooter.getHeldItem(bullet.getHand()));

			if (DamageUtil.dealGunDamage(target, shooter, bullet, (float)getDamage() * bulletDmgMultiplier * shellMod)) {
				doImpactEffect(target, shooter, bullet, bulletDmgMultiplier);
			}
			else if (!(target instanceof LivingEntity)) {
				target.attackEntityFrom(new IndirectEntityDamageSource("gun", bullet, shooter).setProjectile(), (float)getDamage() * bulletDmgMultiplier * shellMod);
			}
		}
	}

	protected void doImpactEffect(Entity target, LivingEntity shooter, BaseBullet bullet, float bulletDmgMultiplier) {}

	public BaseBullet findAndConsumeAmmo(PlayerEntity player, ItemStack gunStack, Hand hand) {
		if (ItemUtil.findInventoryItem(player, new ItemStack(AoAItems.LIMONITE_BULLET.get()), true, 1 + EnchantmentHelper.getEnchantmentLevel(AoAEnchantments.GREED.get(), gunStack)))
			return new LimoniteBulletEntity(player, (BaseGun)gunStack.getItem(), hand, 120, 0);

		return null;
	}

	@Override
	public int getItemEnchantability() {
		return 8;
	}

	@Nullable
	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundNBT nbt) {
		CompoundNBT tag = stack.getOrCreateTag();

		tag.putByte("HideFlags", (byte)2);

		return null;
	}

	private double getHolsterSpeed() {
		return holsterMod;
	}

	@Override
	public Multimap<String, AttributeModifier> getAttributeModifiers(EquipmentSlotType slot, ItemStack stack) {
		Multimap<String, AttributeModifier> multimap = super.getAttributeModifiers(slot, stack);

		if (slot == EquipmentSlotType.MAINHAND) {
			multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MAINHAND, "AoAGunMainHand", -getHolsterSpeed(), AttributeModifier.Operation.MULTIPLY_TOTAL));
		}
		else if (slot == EquipmentSlotType.OFFHAND) {
			multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_OFFHAND, "AoAGunOffhand", -getHolsterSpeed(), AttributeModifier.Operation.MULTIPLY_TOTAL));
			multimap.put(SharedMonsterAttributes.MOVEMENT_SPEED.getName(), BraceEnchantment.BRACE_DEBUFF);
		}

		return multimap;
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		tooltip.add(1, LocaleUtil.getFormattedItemDescriptionText("items.description.damage.gun", LocaleUtil.ItemDescriptionType.ITEM_DAMAGE, NumberUtil.roundToNthDecimalPlace((float)getDamage() * (1 + (0.1f * EnchantmentHelper.getEnchantmentLevel(AoAEnchantments.SHELL.get(), stack))), 2)));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Constants.FIRING_SPEED, LocaleUtil.ItemDescriptionType.NEUTRAL, Double.toString((2000 / firingDelay) / (double)100)));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Constants.AMMO_ITEM, LocaleUtil.ItemDescriptionType.ITEM_AMMO_COST, LocaleUtil.getItemName(AoAItems.LIMONITE_BULLET.get())));
	}
}
