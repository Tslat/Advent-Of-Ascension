package net.tslat.aoa3.item.weapon.gun;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.tslat.aoa3.entity.projectiles.gun.BaseBullet;
import net.tslat.aoa3.entity.projectiles.gun.EntityToxicBullet;
import net.tslat.aoa3.item.weapon.AdventWeapon;
import net.tslat.aoa3.utils.ItemUtil;

public class Iominator extends BaseGun implements AdventWeapon {
	public Iominator(double dmg, SoundEvent sound, int durability, int firingDelayTicks, float recoil) {
		super(dmg, sound, durability, firingDelayTicks, recoil);
		setUnlocalizedName("Iominator");
		setRegistryName("aoa3:iominator");
	}

	@Override
	public BaseBullet findAndConsumeAmmo(EntityPlayer player, BaseGun gun, EnumHand hand) {
		Item ammo = ItemUtil.findAndConsumeBullet(player, gun, true, player.getHeldItem(hand));

		if (ammo != null)
			return new EntityToxicBullet(player, gun, hand, 120, 0);

		return null;
	}
}
