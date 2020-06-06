package net.tslat.aoa3.item.weapon.cannon;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.projectiles.cannon.EntityHeavyShadowball;
import net.tslat.aoa3.entity.projectiles.gun.BaseBullet;
import net.tslat.aoa3.item.weapon.gun.BaseGun;
import net.tslat.aoa3.utils.ItemUtil;

import javax.annotation.Nullable;

public class ShadowBlaster extends BaseCannon {
	public ShadowBlaster(double dmg, int durability, int firingDelayTicks, float recoil) {
		super(dmg, durability, firingDelayTicks, recoil);
		setTranslationKey("ShadowBlaster");
		setRegistryName("aoa3:shadow_blaster");
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return SoundsRegister.SHADOW_BLASTER_FIRE;
	}

	@Override
	public BaseBullet findAndConsumeAmmo(EntityPlayer player, BaseGun gun, EnumHand hand) {
		Item ammo = ItemUtil.findAndConsumeSpecialBullet(player, gun, true, ItemRegister.CANNONBALL, player.getHeldItem(hand));

		if (ammo != null)
			return new EntityHeavyShadowball(player, gun, hand, 120, 0);

		return null;
	}
}
