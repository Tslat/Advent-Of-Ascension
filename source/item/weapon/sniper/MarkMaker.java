package net.tslat.aoa3.item.weapon.sniper;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.capabilities.providers.AdventPlayerProvider;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.entity.projectiles.gun.BaseBullet;
import net.tslat.aoa3.entity.projectiles.gun.EntityMetalSlug;
import net.tslat.aoa3.item.armour.SharpshotArmour;
import net.tslat.aoa3.item.weapon.AdventWeapon;
import net.tslat.aoa3.item.weapon.gun.BaseGun;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.StringUtil;

import java.util.Iterator;
import java.util.List;

public class MarkMaker extends BaseSniper implements AdventWeapon {
	public MarkMaker(double dmg, SoundEvent sound, int durability, int firingDelayTicks, float recoil) {
		super(dmg, sound, durability, firingDelayTicks, recoil);
		setUnlocalizedName("MarkMaker");
		setRegistryName("aoa3:mark_maker");
	}

	@Override
	public void doImpactDamage(Entity target, EntityLivingBase shooter, BaseBullet bullet, float bulletDmgMultiplier) {
		if (shooter instanceof EntityPlayer) {
			if (((shooter.getCapability(AdventPlayerProvider.ADVENT_PLAYER, null)).getArmourSetType() == Enums.ArmourSets.SHARPSHOT))
				bulletDmgMultiplier *= 1.115;

			super.doImpactDamage(target, shooter, bullet, bulletDmgMultiplier);
		}
		else {
			Iterable<ItemStack> armour = target.getArmorInventoryList();
			Iterator<ItemStack> it = armour.iterator();
			boolean isSharpshot = true;

			while (it.hasNext()) {
				if (it.next().getItem() instanceof SharpshotArmour) {
					continue;
				}

				isSharpshot = false;
			}

			if (isSharpshot)
				bulletDmgMultiplier *= 1.45;

			super.doImpactDamage(target, shooter, bullet, bulletDmgMultiplier);
		}
	}

	@Override
	public BaseBullet findAndConsumeAmmo(EntityPlayer player, BaseGun gun, EnumHand hand) {
		Item ammo = ItemUtil.findAndConsumeSpecialBullet(player, gun, itemRand.nextInt(5) > 0, ItemRegister.metalSlug, player.getHeldItem(hand));

		if (ammo != null)
			return new EntityMetalSlug(player, gun, 0);

		return null;
	}

	@Override
	public Enums.ScopeScreens getScreen() {
		return Enums.ScopeScreens.MARKER;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(StringUtil.getColourLocaleString("item.MarkMaker.desc.1", TextFormatting.DARK_GREEN));
		tooltip.add(StringUtil.getColourLocaleString("items.description.ammo.noConsume.20", TextFormatting.DARK_GREEN));
		super.addInformation(stack, world, tooltip, flag);
	}
}
