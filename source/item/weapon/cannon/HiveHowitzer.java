package net.tslat.aoa3.item.weapon.cannon;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.projectiles.cannon.EntityHeavyCannonballRed;
import net.tslat.aoa3.entity.projectiles.gun.BaseBullet;
import net.tslat.aoa3.item.weapon.gun.BaseGun;
import net.tslat.aoa3.utils.ItemUtil;

import javax.annotation.Nullable;

public class HiveHowitzer extends BaseCannon {
	public HiveHowitzer(double dmg, int durability, int firingDelayTicks, float recoil) {
		super(dmg, durability, firingDelayTicks, recoil);
		setTranslationKey("HiveHowitzer");
		setRegistryName("aoa3:hive_howitzer");
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return SoundsRegister.BALL_CANNON_FIRE;
	}

	@Override
	public BaseBullet findAndConsumeAmmo(EntityPlayer player, BaseGun gun, EnumHand hand) {
		Item ammo = ItemUtil.findAndConsumeSpecialBullet(player, gun, true, ItemRegister.CANNONBALL, player.getHeldItem(hand));

		if (ammo != null)
			return new EntityHeavyCannonballRed(player, gun, hand, 120, 0);

		return null;
	}
}
