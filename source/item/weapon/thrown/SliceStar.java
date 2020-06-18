package net.tslat.aoa3.item.weapon.thrown;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;
import net.tslat.aoa3.common.registration.EnchantmentsRegister;
import net.tslat.aoa3.entity.projectiles.gun.BaseBullet;
import net.tslat.aoa3.entity.projectiles.thrown.EntitySliceStar;
import net.tslat.aoa3.item.weapon.gun.BaseGun;
import net.tslat.aoa3.utils.ItemUtil;

import javax.annotation.Nullable;

public class SliceStar extends BaseThrownWeapon {
	public static final float dmg = 4.5f;
	public SliceStar() {
		super(dmg, 7);
		setTranslationKey("SliceStar");
		setRegistryName("aoa3:slice_star");
		setCreativeTab(CreativeTabsRegister.THROWN_WEAPONS);
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return SoundEvents.ENTITY_ARROW_SHOOT;
	}

	@Override
	public BaseBullet findAndConsumeAmmo(EntityPlayer player, ItemStack weaponStack, EnumHand hand) {
		if (ItemUtil.findInventoryItem(player, new ItemStack(this), true, 1 + EnchantmentHelper.getEnchantmentLevel(EnchantmentsRegister.GREED, weaponStack)))
			return new EntitySliceStar(player, (BaseGun)weaponStack.getItem());

		return null;
	}
}
