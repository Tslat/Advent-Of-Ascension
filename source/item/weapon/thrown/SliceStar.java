package net.tslat.aoa3.item.weapon.thrown;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.EnumHand;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;
import net.tslat.aoa3.entity.projectiles.gun.BaseBullet;
import net.tslat.aoa3.entity.projectiles.thrown.EntitySliceStar;
import net.tslat.aoa3.item.weapon.gun.BaseGun;
import net.tslat.aoa3.utils.ItemUtil;

public class SliceStar extends BaseThrownWeapon {
	public static final float dmg = 4.5f;
	public SliceStar() {
		super(dmg, SoundEvents.ENTITY_ARROW_SHOOT, 7);
		setUnlocalizedName("SliceStar");
		setRegistryName("aoa3:slice_star");
		setCreativeTab(CreativeTabsRegister.thrownWeaponsTab);
	}

	@Override
	public BaseBullet findAndConsumeAmmo(EntityPlayer player, BaseGun gun, EnumHand hand) {
		if (ItemUtil.findAndConsumeSpecialBullet(player, gun, true, this, player.getHeldItem(hand)) != null)
			return new EntitySliceStar(player, gun);

		return null;
	}
}
