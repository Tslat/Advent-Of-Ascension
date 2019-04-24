package net.tslat.aoa3.item.weapon.sniper;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.capabilities.handlers.AdventGunCapability;
import net.tslat.aoa3.capabilities.providers.AdventGunProvider;
import net.tslat.aoa3.common.packet.PacketRecoil;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;
import net.tslat.aoa3.common.registration.EnchantmentsRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.entity.projectiles.gun.BaseBullet;
import net.tslat.aoa3.entity.projectiles.gun.EntityMetalSlug;
import net.tslat.aoa3.entity.projectiles.gun.EntitySniperSlug;
import net.tslat.aoa3.event.GlobalEvents;
import net.tslat.aoa3.item.weapon.AdventWeapon;
import net.tslat.aoa3.item.weapon.gun.BaseGun;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.PacketUtil;
import net.tslat.aoa3.utils.StringUtil;

import java.util.List;

public abstract class BaseSniper extends BaseGun implements AdventWeapon {
	public BaseSniper(double dmg, SoundEvent sound, int durability, int fireDelayTicks, float recoil) {
		super(dmg, sound, durability, fireDelayTicks, recoil);
		setCreativeTab(CreativeTabsRegister.snipersTab);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		ItemStack stack = player.getHeldItem(hand);

		if (hand != EnumHand.MAIN_HAND)
			return ActionResult.newResult(EnumActionResult.FAIL, stack);

		if (player.getCooledAttackStrength(0.0f) < 1)
			return ActionResult.newResult(EnumActionResult.FAIL, stack);

		AdventGunCapability cap = (AdventGunCapability)stack.getCapability(AdventGunProvider.ADVENT_GUN, null);

		if (cap.getNextFireTime() < GlobalEvents.tick) {
			BaseBullet ammo = findAndConsumeAmmo(player, this, hand);

			if (ammo != null) {
				if (!world.isRemote)
					fireSniper(player, hand);

				player.world.playSound(null, player.posX, player.posY, player.posZ, sound, SoundCategory.PLAYERS, 1.0f, 1.0f);

				stack.damageItem(1, player);
				cap.setNextFireTime(firingDelay);
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

	public void fireSniper(EntityLivingBase shooter, EnumHand hand) {
		BaseBullet bullet;

		if (shooter.isSneaking() && shooter.onGround) {
			bullet = new EntitySniperSlug(shooter, this, 0);

			if (shooter instanceof EntityPlayerMP) {
				int control = EnchantmentHelper.getEnchantmentLevel(EnchantmentsRegister.control, shooter.getHeldItem(hand));
				float recoiling = recoil * (1 - control * 0.15f);

				PacketUtil.network.sendTo(new PacketRecoil(recoiling, firingDelay), (EntityPlayerMP)shooter);
			}
		}
		else {
			bullet = new EntityMetalSlug(shooter, this, 0);
			bullet.shoot(shooter, shooter.rotationPitch, shooter.rotationYaw, 0.0f, 20.0f, 50.0f);

			if (shooter instanceof EntityPlayerMP)
				PacketUtil.network.sendTo(new PacketRecoil(recoil * 2f, firingDelay), (EntityPlayerMP)shooter);
		}

		shooter.world.spawnEntity(bullet);
	}

	@Override
	public void doImpactDamage(Entity target, EntityLivingBase shooter, BaseBullet bullet, float bulletDmgMultiplier) {
		super.doImpactDamage(target, shooter, bullet, bullet.getAge() <= 1 ? bulletDmgMultiplier * 0.5f : bulletDmgMultiplier);
	}

	@Override
	public BaseBullet findAndConsumeAmmo(EntityPlayer player, BaseGun gun, EnumHand hand) {
		Item ammo = ItemUtil.findAndConsumeSpecialBullet(player, gun, true, ItemRegister.metalSlug, player.getHeldItem(hand));

		if (ammo != null)
			return new EntitySniperSlug(player, gun, 0);

		return null;
	}

	public Enums.ScopeScreens getScreen() {
		return Enums.ScopeScreens.BASIC;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(1, StringUtil.getColourLocaleStringWithArguments("items.description.damage.gun", TextFormatting.DARK_RED, Double.toString(dmg)));
		tooltip.add(StringUtil.getColourLocaleString("items.description.sniper.use", TextFormatting.AQUA));
		tooltip.add(StringUtil.getLocaleStringWithArguments("items.description.gun.speed", Double.toString((2000 / firingDelay) / (double)100)));
		tooltip.add(StringUtil.getColourLocaleString("items.description.ammo.metalSlugs", TextFormatting.LIGHT_PURPLE));
	}
}
