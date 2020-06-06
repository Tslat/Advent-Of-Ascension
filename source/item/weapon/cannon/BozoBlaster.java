package net.tslat.aoa3.item.weapon.cannon;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.projectiles.cannon.EntityBozoBall;
import net.tslat.aoa3.entity.projectiles.gun.BaseBullet;
import net.tslat.aoa3.item.weapon.gun.BaseGun;
import net.tslat.aoa3.utils.ItemUtil;

import javax.annotation.Nullable;

public class BozoBlaster extends BaseCannon {
	public BozoBlaster(double dmg, int durability, int firingDelayTicks, float recoil) {
		super(dmg, durability, firingDelayTicks, recoil);
		setTranslationKey("BozoBlaster");
		setRegistryName("aoa3:bozo_blaster");
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return SoundsRegister.CLOWNER_FIRE;
	}

	@Override
	public BaseBullet findAndConsumeAmmo(EntityPlayer player, BaseGun gun, EnumHand hand) {
		Item ammo = ItemUtil.findAndConsumeSpecialBullet(player, gun, itemRand.nextInt(3) > 0, ItemRegister.CANNONBALL, player.getHeldItem(hand));

		if (ammo != null)
			return new EntityBozoBall(player, gun, hand, 120, 0);

		return null;
	}
}
