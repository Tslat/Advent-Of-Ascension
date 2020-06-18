package net.tslat.aoa3.item.weapon.gun;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.tslat.aoa3.common.registration.EnchantmentsRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.projectiles.gun.BaseBullet;
import net.tslat.aoa3.entity.projectiles.gun.EntityToxicBullet;
import net.tslat.aoa3.utils.ItemUtil;

import javax.annotation.Nullable;

public class Iominator extends BaseGun {
	public Iominator(double dmg, int durability, int firingDelayTicks, float recoil) {
		super(dmg, durability, firingDelayTicks, recoil);
		setTranslationKey("Iominator");
		setRegistryName("aoa3:iominator");
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return SoundsRegister.ABOMINATOR_FIRE;
	}

	public BaseBullet findAndConsumeAmmo(EntityPlayer player, ItemStack gunStack, EnumHand hand) {
		if (ItemUtil.findInventoryItem(player, new ItemStack(ItemRegister.LIMONITE_BULLET), true, 1 + EnchantmentHelper.getEnchantmentLevel(EnchantmentsRegister.GREED, gunStack)))
			return new EntityToxicBullet(player, (BaseGun)gunStack.getItem(), hand, 120, 0);

		return null;
	}
}
