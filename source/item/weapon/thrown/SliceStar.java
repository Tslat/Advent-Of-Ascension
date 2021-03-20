package net.tslat.aoa3.item.weapon.thrown;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.tslat.aoa3.common.registration.AoAEnchantments;
import net.tslat.aoa3.entity.projectile.gun.BaseBullet;
import net.tslat.aoa3.entity.projectile.thrown.SliceStarEntity;
import net.tslat.aoa3.item.weapon.gun.BaseGun;
import net.tslat.aoa3.util.ItemUtil;

import javax.annotation.Nullable;

public class SliceStar extends BaseThrownWeapon {
	public static final float dmg = 4.5f;
	public SliceStar() {
		super(dmg, 7);
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return SoundEvents.ARROW_SHOOT;
	}

	@Override
	public BaseBullet findAndConsumeAmmo(PlayerEntity player, ItemStack weaponStack, Hand hand) {
		BaseGun item = (BaseGun)weaponStack.getItem();

		if (ItemUtil.findInventoryItem(player, new ItemStack(this), true, 1 + EnchantmentHelper.getItemEnchantmentLevel(AoAEnchantments.GREED.get(), weaponStack)))
			return new SliceStarEntity(player, item);

		return null;
	}
}
