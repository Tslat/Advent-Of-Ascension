package net.tslat.aoa3.item.weapon.cannon;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.entity.projectiles.cannon.EntityCannonball;
import net.tslat.aoa3.entity.projectiles.gun.BaseBullet;
import net.tslat.aoa3.item.weapon.gun.BaseGun;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.StringUtil;

import java.util.List;

public abstract class BaseCannon extends BaseGun {
	public BaseCannon(double dmg, SoundEvent sound, int durability, int fireDelayTicks, float recoil) {
		super(dmg, sound, durability, fireDelayTicks, recoil);
		setCreativeTab(CreativeTabsRegister.cannonsTab);
	}

	@Override
	public void doImpactDamage(Entity target, EntityLivingBase shooter, BaseBullet bullet, float bulletDmgMultiplier) {
		if (target != null) {
			if (target instanceof EntityLivingBase)
				bulletDmgMultiplier *= 1 + (((EntityLivingBase)target).getEntityAttribute(SharedMonsterAttributes.ARMOR).getAttributeValue() * 6.66) / 100;

			EntityUtil.dealGunDamage(target, shooter, bullet, (float)dmg * bulletDmgMultiplier);
		}
	}

	@Override
	public BaseBullet findAndConsumeAmmo(EntityPlayer player, BaseGun gun, EnumHand hand) {
		Item ammo = ItemUtil.findAndConsumeSpecialBullet(player, gun, true, ItemRegister.cannonball, player.getHeldItem(hand));

		if (ammo != null)
			return new EntityCannonball(player, gun, hand, 120, 0);

		return null;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(1, StringUtil.getColourLocaleStringWithArguments("items.description.damage.gun", TextFormatting.DARK_RED, Double.toString(dmg)));
		tooltip.add(StringUtil.getColourLocaleString("items.description.cannon.damage", TextFormatting.AQUA));
		tooltip.add(StringUtil.getLocaleStringWithArguments("items.description.gun.speed", Double.toString((2000 / firingDelay) / (double)100)));
		tooltip.add(StringUtil.getColourLocaleString("items.description.ammo.cannonballs", TextFormatting.LIGHT_PURPLE));
	}
}
