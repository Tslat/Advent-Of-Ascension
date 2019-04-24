package net.tslat.aoa3.item.weapon.cannon;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.WeaponRegister;
import net.tslat.aoa3.entity.projectiles.cannon.EntityErebonSticklerShot;
import net.tslat.aoa3.entity.projectiles.gun.BaseBullet;
import net.tslat.aoa3.entity.projectiles.misc.EntityErebonSticklerStuck;
import net.tslat.aoa3.item.weapon.AdventWeapon;
import net.tslat.aoa3.item.weapon.gun.BaseGun;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.StringUtil;

import java.util.List;

public class ErebonStickler extends BaseCannon implements AdventWeapon {
	private double dmg;
	private int firingDelay;

	public ErebonStickler(double dmg, SoundEvent sound, int durability, int firingDelayTicks, float recoil) {
		super(dmg, sound, durability, firingDelayTicks, recoil);
		setUnlocalizedName("ErebonStickler");
		setRegistryName("aoa3:erebon_stickler");
		this.dmg = dmg;
		this.firingDelay = firingDelayTicks;
	}

	@Override
	public void doImpactDamage(Entity target, EntityLivingBase shooter, BaseBullet bullet, float bulletDmgMultiplier) {
		super.doImpactDamage(target, shooter, bullet, bulletDmgMultiplier);

		if (target instanceof EntityLivingBase) {
			target.world.spawnEntity(new EntityErebonSticklerStuck(shooter, this, (EntityLivingBase)target, bulletDmgMultiplier));
			((EntityLivingBase)target).addPotionEffect(new PotionEffect(MobEffects.WITHER, 100, 1));
		}

		bullet.setDead();
	}

	@Override
	public BaseBullet findAndConsumeAmmo(EntityPlayer player, BaseGun gun, EnumHand hand) {
		Item ammo = ItemUtil.findAndConsumeSpecialBullet(player, gun, true, WeaponRegister.throwableGrenade, player.getHeldItem(hand));

		if (ammo != null)
			return new EntityErebonSticklerShot(player, gun, hand, 120, 0);

		return null;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(1, StringUtil.getColourLocaleStringWithArguments("items.description.damage.gun", TextFormatting.DARK_RED, Double.toString(dmg)));
		tooltip.add(StringUtil.getColourLocaleString("item.ErebonStickler.desc.1", TextFormatting.DARK_GREEN));
		tooltip.add(StringUtil.getColourLocaleString("item.ErebonStickler.desc.2", TextFormatting.DARK_GREEN));
		tooltip.add(StringUtil.getColourLocaleString("items.description.damage.wither", TextFormatting.DARK_GREEN));
		tooltip.add(StringUtil.getColourLocaleString("items.description.cannon.damage", TextFormatting.AQUA));
		tooltip.add(StringUtil.getLocaleStringWithArguments("items.description.gun.speed", Double.toString((2000 / firingDelay) / (double)100)));
		tooltip.add(StringUtil.getColourLocaleString("items.description.ammo.grenades", TextFormatting.LIGHT_PURPLE));
	}
}
