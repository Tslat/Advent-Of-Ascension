package net.tslat.aoa3.item.weapon.cannon;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.entity.projectiles.cannon.EntityEnergyShot;
import net.tslat.aoa3.entity.projectiles.gun.BaseBullet;
import net.tslat.aoa3.item.weapon.AdventWeapon;
import net.tslat.aoa3.item.weapon.gun.BaseGun;
import net.tslat.aoa3.utils.ItemUtil;

public class EnergyCannon extends BaseCannon implements AdventWeapon {
	double dmg;
	int firingDelay;

	public EnergyCannon(double dmg, SoundEvent sound, int durability, int firingDelayTicks, float recoil) {
		super(dmg, sound, durability, firingDelayTicks, recoil);
		this.dmg = dmg;
		this.firingDelay = firingDelayTicks;
		setUnlocalizedName("EnergyCannon");
		setRegistryName("aoa3:energy_cannon");
	}

	@Override
	public BaseBullet findAndConsumeAmmo(EntityPlayer player, BaseGun gun, EnumHand hand) {
		Item ammo = ItemUtil.findAndConsumeSpecialBullet(player, gun, true, ItemRegister.cannonball, player.getHeldItem(hand));

		if (ammo != null)
			return new EntityEnergyShot(player, gun, hand, 120, 0);

		return null;
	}
}
