package net.tslat.aoa3.item.weapon.shotgun;

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
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.entity.projectiles.gun.BaseBullet;
import net.tslat.aoa3.entity.projectiles.gun.EntityDischargeShot;
import net.tslat.aoa3.item.weapon.AdventWeapon;
import net.tslat.aoa3.item.weapon.gun.BaseGun;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.StringUtil;

import java.util.List;

public class DischargeShotgun extends BaseShotgun implements AdventWeapon {
	private int firingDelay;

	public DischargeShotgun(final double dmg, final int pellets, final SoundEvent sound, final int durability, final int fireDelayTicks, final float recoil) {
		super(dmg, pellets, sound, durability, fireDelayTicks, recoil);
		firingDelay = fireDelayTicks;
		setUnlocalizedName("DischargeShotgun");
		setRegistryName("aoa3:discharge_shotgun");
	}

	@Override
	public void doImpactDamage(Entity target, EntityLivingBase shooter, BaseBullet bullet, float bulletDmgMultiplier) {
		if (target != null)
			bullet.doImpactEffect();

		bullet.world.createExplosion(shooter, bullet.posX, bullet.posY, bullet.posZ, 2.5f, false);
	}

	@Override
	public void fireShotgun(EntityLivingBase shooter, EnumHand hand, float spreadFactor, int pellets) {
		for (int i = 0; i < pellets; i++) {
			BaseBullet pellet = new EntityDischargeShot(shooter, this, hand, 4, 1.0f, 0, (itemRand.nextFloat() - 0.5f) * spreadFactor, (itemRand.nextFloat() - 0.5f) * spreadFactor, (itemRand.nextFloat() - 0.5f) * spreadFactor);
			shooter.world.spawnEntity(pellet);
		}
	}

	@Override
	public BaseBullet findAndConsumeAmmo(EntityPlayer player, BaseGun gun, EnumHand hand) {
		Item ammo = ItemUtil.findAndConsumeSpecialBullet(player, gun, true, ItemRegister.dischargeCapsule, player.getHeldItem(hand));

		if (ammo != null)
			return new EntityDischargeShot(player, gun, hand,4, 0);

		return null;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(StringUtil.getColourLocaleString("item.DischargeShotgun.desc.1", TextFormatting.DARK_GREEN));
		tooltip.add(StringUtil.getLocaleStringWithArguments("items.description.gun.speed", Double.toString((2000 / firingDelay) / (double)100)));
		tooltip.add(StringUtil.getColourLocaleStringWithArguments("items.description.ammo.other", TextFormatting.LIGHT_PURPLE, StringUtil.getLocaleString("item.DischargeCapsule.name")));
	}
}
