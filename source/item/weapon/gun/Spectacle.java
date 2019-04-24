package net.tslat.aoa3.item.weapon.gun;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.entity.projectiles.gun.BaseBullet;
import net.tslat.aoa3.item.weapon.AdventWeapon;
import net.tslat.aoa3.utils.StringUtil;

import java.util.List;

public class Spectacle extends BaseGun implements AdventWeapon {
	public Spectacle(double dmg, SoundEvent sound, int durability, int firingDelayTicks, float recoil) {
		super(dmg, sound, durability, firingDelayTicks, recoil);
		setUnlocalizedName("Spectacle");
		setRegistryName("aoa3:spectacle");
	}

	@Override
	public BaseBullet findAndConsumeAmmo(EntityPlayer player, BaseGun gun, EnumHand hand) {
		return super.findAndConsumeAmmo(player, gun, hand, itemRand.nextInt(5) > 0);
	}

	@Override
	public void doImpactDamage(Entity target, EntityLivingBase shooter, BaseBullet bullet, float bulletDmgMultiplier) {
		if (itemRand.nextInt(10) == 0) {
			super.doImpactDamage(target, shooter, bullet, bulletDmgMultiplier * 2);
		}
		else {
			super.doImpactDamage(target, shooter, bullet, bulletDmgMultiplier);
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(StringUtil.getColourLocaleString("item.Spectacle.desc.1", TextFormatting.DARK_GREEN));
		tooltip.add(StringUtil.getColourLocaleString("items.description.ammo.noConsume.20", TextFormatting.DARK_GREEN));
		super.addInformation(stack, world, tooltip, flag);
	}
}
