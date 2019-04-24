package net.tslat.aoa3.item.weapon.gun;

import com.google.common.collect.Multimap;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.StatList;
import net.minecraft.util.*;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import net.tslat.aoa3.capabilities.handlers.AdventGunCapability;
import net.tslat.aoa3.capabilities.providers.AdventGunProvider;
import net.tslat.aoa3.common.packet.PacketRecoil;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;
import net.tslat.aoa3.common.registration.EnchantmentsRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.entity.projectiles.gun.BaseBullet;
import net.tslat.aoa3.entity.projectiles.gun.EntityLimoniteBullet;
import net.tslat.aoa3.event.GlobalEvents;
import net.tslat.aoa3.item.enchantment.EnchantmentBrace;
import net.tslat.aoa3.item.weapon.AdventWeapon;
import net.tslat.aoa3.item.weapon.sniper.BaseSniper;
import net.tslat.aoa3.item.weapon.staff.BaseStaff;
import net.tslat.aoa3.item.weapon.thrown.BaseThrownWeapon;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.*;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public abstract class BaseGun extends Item implements AdventWeapon {
	protected final double dmg;
	protected final SoundEvent sound;
	protected final int firingDelay;
	protected final float recoil;
	protected double holsterMod;

	public BaseGun(final double dmg, final SoundEvent sound, final int durability, final int fireDelayTicks, final float recoil) {
		this.dmg = dmg;
		this.sound = sound;
		this.firingDelay = fireDelayTicks;
		this.recoil = recoil;
		this.holsterMod = dmg == 0 ? 0.85 : this instanceof BaseThrownWeapon ? 0.5 : 0.8 + 0.17 * Math.min(((20 / (double)firingDelay) * dmg) / 55, 0.95);
		setMaxDamage(durability);
		setCreativeTab(CreativeTabsRegister.gunsTab);
		setMaxStackSize(1);
		setFull3D();
		setNoRepair();
	}

	public double getDamage() {
		return dmg;
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

	@Override
	public boolean getIsRepairable(ItemStack stack, ItemStack repairMaterial) {
		return repairMaterial.getItem() != Items.ENCHANTED_BOOK && OreDictionary.itemMatches(repairMaterial, new ItemStack(ItemRegister.ingotRosite), false) || super.getIsRepairable(stack, repairMaterial);
	}

	@Override
	public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
		return slotChanged || oldStack.getItem() != newStack.getItem();
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		ItemStack stack = player.getHeldItem(hand);

		if (hand != getGunHand(stack))
			return ActionResult.newResult(EnumActionResult.FAIL, stack);

		if (hand == EnumHand.OFF_HAND && player.isSneaking()) {
			Item mainItem = player.getHeldItem(EnumHand.MAIN_HAND).getItem();

			if (mainItem instanceof BaseSniper || mainItem instanceof BaseStaff)
				return ActionResult.newResult(EnumActionResult.FAIL, stack);
		}

		if (player.getCooledAttackStrength(0.0f) < 1)
			return ActionResult.newResult(EnumActionResult.FAIL, stack);

		AdventGunCapability cap = (AdventGunCapability)stack.getCapability(AdventGunProvider.ADVENT_GUN, null);

		if (cap == null)
			return ActionResult.newResult(EnumActionResult.FAIL, stack);

		if (cap.getNextFireTime() <= GlobalEvents.tick) {
			player.setActiveHand(hand);
			BaseBullet ammo = null;

			if (!world.isRemote)
				ammo = findAndConsumeAmmo(player, this, hand);

			if (ammo != null) {
				world.spawnEntity(ammo);
				player.world.playSound(null, player.posX, player.posY, player.posZ, sound, SoundCategory.PLAYERS, 1.0f, 1.0f);
				player.addStat(StatList.getObjectUseStats(this));
				stack.damageItem(1, player);
				cap.setNextFireTime(firingDelay);

				if (player instanceof EntityPlayerMP) {
					int control = EnchantmentHelper.getEnchantmentLevel(EnchantmentsRegister.control, stack);
					float recoiling = recoil * (1 - control * 0.15f);

					PacketUtil.network.sendTo(new PacketRecoil(hand == EnumHand.OFF_HAND ? recoiling * 2.5f : recoiling, firingDelay), (EntityPlayerMP)player);
				}

				return ActionResult.newResult(EnumActionResult.PASS, stack);
			}

			if (player instanceof EntityPlayerMP)
				((EntityPlayerMP)player).sendContainerToPlayer(player.inventoryContainer);
		}
		else if (cap.getNextFireTime() > GlobalEvents.tick + firingDelay * 2) {
			cap.setNextFireTime(-GlobalEvents.tick);
		}

		return ActionResult.newResult(EnumActionResult.FAIL, stack);
	}

	public EnumHand getGunHand(ItemStack stack) {
		return EnchantmentHelper.getEnchantmentLevel(EnchantmentsRegister.brace, stack) > 0 ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND;
	}

	public void doImpactDamage(Entity target, EntityLivingBase shooter, BaseBullet bullet, float bulletDmgMultiplier) {
		if (target != null) {
			float shellMod = 1;

			if (bullet.getHand() != null)
				shellMod += 0.1 * EnchantmentHelper.getEnchantmentLevel(EnchantmentsRegister.shell, shooter.getHeldItem(bullet.getHand()));

			EntityUtil.dealGunDamage(target, shooter, bullet, (float)dmg * bulletDmgMultiplier * shellMod);
		}
	}

	public BaseBullet findAndConsumeAmmo(EntityPlayer player, BaseGun gun, EnumHand hand) {
		return findAndConsumeAmmo(player, gun, hand, true);
	}

	public BaseBullet findAndConsumeAmmo(EntityPlayer player, BaseGun gun, EnumHand hand, boolean consume) {
		Item ammo = ItemUtil.findAndConsumeBullet(player, gun, consume, player.getHeldItem(hand));

		if (ammo != null) {
			switch (ammo.getUnlocalizedName()) {
				case "item.LimoniteBullet":
					return new EntityLimoniteBullet(player, gun, hand, 120, 0);
				default:
					return null;
			}
		}

		return null;
	}

	@Override
	public boolean isFull3D() {
		return true;
	}

	@Override
	public int getItemEnchantability() {
		return 0;
	}

	@Nullable
	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable NBTTagCompound nbt) {
		if (!stack.hasTagCompound())
			stack.setTagCompound(new NBTTagCompound());

		stack.getTagCompound().setByte("HideFlags", (byte)2);

		return new AdventGunProvider();
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(1, StringUtil.getColourLocaleStringWithArguments("items.description.damage.gun", TextFormatting.DARK_RED, Double.toString(dmg)));
		tooltip.add(StringUtil.getLocaleStringWithArguments("items.description.gun.speed", Double.toString((2000 / firingDelay) / (double)100)));
		tooltip.add(StringUtil.getColourLocaleString("items.description.ammo.bullets", TextFormatting.LIGHT_PURPLE));
	}

	private double getHolsterSpeed() {
		return holsterMod;
	}

	@Override
	public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot equipmentSlot, ItemStack stack) {
		Multimap<String, AttributeModifier> multimap = super.getAttributeModifiers(equipmentSlot, stack);

		if (equipmentSlot == EntityEquipmentSlot.MAINHAND)
			multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(UUID.fromString(Enums.AttributeUUIDS.ATTACK_SPEED_MAINHAND), "AoAGunMainHand", -getHolsterSpeed(), 2));

		if (equipmentSlot == EntityEquipmentSlot.OFFHAND) {
			multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(UUID.fromString(Enums.AttributeUUIDS.ATTACK_SPEED_OFFHAND), "AoAGunOffHand", -getHolsterSpeed(), 2));
			multimap.put(SharedMonsterAttributes.MOVEMENT_SPEED.getName(), EnchantmentBrace.modifier());
		}

		return multimap;
	}
}
