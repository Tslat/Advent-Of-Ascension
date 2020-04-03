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
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.StatList;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.capabilities.handlers.AdventGunCapability;
import net.tslat.aoa3.capabilities.providers.AdventGunProvider;
import net.tslat.aoa3.common.packet.PacketRecoil;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;
import net.tslat.aoa3.common.registration.EnchantmentsRegister;
import net.tslat.aoa3.entity.projectiles.gun.BaseBullet;
import net.tslat.aoa3.entity.projectiles.gun.EntityLimoniteBullet;
import net.tslat.aoa3.event.GlobalEvents;
import net.tslat.aoa3.item.weapon.AdventWeapon;
import net.tslat.aoa3.item.weapon.sniper.BaseSniper;
import net.tslat.aoa3.item.weapon.staff.BaseStaff;
import net.tslat.aoa3.item.weapon.thrown.BaseThrownWeapon;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.library.misc.AoAAttributes;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.PacketUtil;
import net.tslat.aoa3.utils.StringUtil;

import javax.annotation.Nullable;
import java.util.List;

public abstract class BaseGun extends Item implements AdventWeapon {
	protected final double dmg;
	protected final int firingDelay;
	protected final float recoil;
	protected double holsterMod;

	public BaseGun(final double dmg, final int durability, final int fireDelayTicks, final float recoil) {
		this.dmg = dmg;
		this.firingDelay = fireDelayTicks;
		this.recoil = recoil;
		this.holsterMod = getDamage() == 0 ? 0.85 : this instanceof BaseThrownWeapon ? 0.5 : 0.8 + 0.17 * Math.min(((20 / (double)getFiringDelay()) * getDamage()) / 55, 0.85);
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

	@Nullable
	public SoundEvent getFiringSound() {
		return null;
	}

	public float getRecoilForShot(ItemStack stack, EntityLivingBase shooter) {
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
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		ItemStack stack = player.getHeldItem(hand);

		if (hand != getGunHand(stack))
			return ActionResult.newResult(EnumActionResult.FAIL, stack);

		if (player.isHandActive() && player.isActiveItemStackBlocking())
			return ActionResult.newResult(EnumActionResult.PASS, stack);

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
			BaseBullet ammo = null;

			if (!world.isRemote)
				ammo = findAndConsumeAmmo(player, this, hand);

			if (ammo != null) {
				world.spawnEntity(ammo);
				player.addStat(StatList.getObjectUseStats(this));
				stack.damageItem(1, player);
				cap.setNextShotDelay(getFiringDelay());

				if (getFiringSound() != null)
					player.world.playSound(null, player.posX, player.posY, player.posZ, getFiringSound(), SoundCategory.PLAYERS, 1.0f, 1.0f);

				if (player instanceof EntityPlayerMP) {
					int control = EnchantmentHelper.getEnchantmentLevel(EnchantmentsRegister.control, stack);
					float recoiling = getRecoilForShot(stack, player) * (1 - control * 0.15f);

					PacketUtil.network.sendTo(new PacketRecoil(hand == EnumHand.OFF_HAND ? recoiling * 2.5f : recoiling, getFiringDelay()), (EntityPlayerMP)player);
				}

				return ActionResult.newResult(EnumActionResult.SUCCESS, stack);
			}

			if (player instanceof EntityPlayerMP)
				((EntityPlayerMP)player).sendContainerToPlayer(player.inventoryContainer);
		}
		else if (cap.getNextFireTime() > GlobalEvents.tick + getFiringDelay() * 2) {
			cap.setNextShotDelay(0);
		}

		return ActionResult.newResult(EnumActionResult.PASS, stack);
	}

	public EnumHand getGunHand(ItemStack stack) {
		return EnchantmentHelper.getEnchantmentLevel(EnchantmentsRegister.brace, stack) > 0 ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND;
	}

	public void doImpactDamage(Entity target, EntityLivingBase shooter, BaseBullet bullet, float bulletDmgMultiplier) {
		if (target != null) {
			float shellMod = 1;

			if (bullet.getHand() != null)
				shellMod += 0.1 * EnchantmentHelper.getEnchantmentLevel(EnchantmentsRegister.shell, shooter.getHeldItem(bullet.getHand()));

			if (EntityUtil.dealGunDamage(target, shooter, bullet, (float)getDamage() * bulletDmgMultiplier * shellMod))
				doImpactEffect(target, shooter, bullet, bulletDmgMultiplier);
		}
	}

	protected void doImpactEffect(Entity target, EntityLivingBase shooter, BaseBullet bullet, float bulletDmgMultiplier) {}

	public BaseBullet findAndConsumeAmmo(EntityPlayer player, BaseGun gun, EnumHand hand) {
		return findAndConsumeAmmo(player, gun, hand, true);
	}

	public BaseBullet findAndConsumeAmmo(EntityPlayer player, BaseGun gun, EnumHand hand, boolean consume) {
		Item ammo = ItemUtil.findAndConsumeBullet(player, gun, consume, player.getHeldItem(hand));

		if (ammo != null) {
			switch (ammo.getTranslationKey()) {
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
		return 8;
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
		tooltip.add(1, ItemUtil.getFormattedDescriptionText("items.description.damage.gun", Enums.ItemDescriptionType.ITEM_DAMAGE, Double.toString(getDamage())));
		tooltip.add(StringUtil.getLocaleStringWithArguments("items.description.gun.speed", Double.toString((2000 / firingDelay) / (double)100)));
		tooltip.add(ItemUtil.getFormattedDescriptionText("items.description.ammo.bullets", Enums.ItemDescriptionType.ITEM_AMMO_COST));
	}

	private double getHolsterSpeed() {
		return holsterMod;
	}

	@Override
	public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot equipmentSlot, ItemStack stack) {
		Multimap<String, AttributeModifier> multimap = super.getAttributeModifiers(equipmentSlot, stack);
		
		if (equipmentSlot == EntityEquipmentSlot.MAINHAND) {
			multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), AoAAttributes.gunMainHandSpeedModifier(-getHolsterSpeed()));
		}
		else if (equipmentSlot == EntityEquipmentSlot.OFFHAND) {
			multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), AoAAttributes.gunOffHandSpeedModifier(-getHolsterSpeed()));
			multimap.put(SharedMonsterAttributes.MOVEMENT_SPEED.getName(), AoAAttributes.BRACE_DEBUFF);
		}

		return multimap;
	}
}
