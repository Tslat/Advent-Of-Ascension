package net.tslat.aoa3.item.weapon.gun;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.projectiles.gun.BaseBullet;
import net.tslat.aoa3.entity.projectiles.gun.EntityShroomBullet;
import net.tslat.aoa3.utils.ItemUtil;

import javax.annotation.Nullable;

public class MKFung extends BaseGun {
	public MKFung(double dmg, int durability, int firingDelayTicks, float recoil) {
		super(dmg, durability, firingDelayTicks, recoil);
		setTranslationKey("MKFung");
		setRegistryName("aoa3:mk_fung");
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return SoundsRegister.REVOLVER_FIRE;
	}

	@Override
	public BaseBullet findAndConsumeAmmo(EntityPlayer player, BaseGun gun, EnumHand hand) {
		Item ammo = ItemUtil.findAndConsumeBullet(player, gun, true, player.getHeldItem(hand));

		if (ammo != null)
			return new EntityShroomBullet(player, gun, hand, 120, 0);

		return null;
	}
}
