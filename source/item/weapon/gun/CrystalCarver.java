package net.tslat.aoa3.item.weapon.gun;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;

import javax.annotation.Nullable;

public class CrystalCarver extends BaseGun {
	public CrystalCarver(double dmg, int durability, int firingDelayTicks, float recoil) {
		super(dmg, durability, firingDelayTicks, recoil);
		setTranslationKey("CrystalCarver");
		setRegistryName("aoa3:crystal_carver");
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return SoundsRegister.SNIPER_FIRE;
	}

	@Override
	public boolean getIsRepairable(ItemStack stack, ItemStack repairMaterial) {
		Item item = repairMaterial.getItem();

		if (item == Items.DIAMOND || item == Items.EMERALD || item == ItemRegister.GEMENYTE || item == ItemRegister.JEWELYTE || item == ItemRegister.AMETHYST ||
		item == ItemRegister.BLOODSTONE || item == ItemRegister.BLUE_GEMSTONES || item == ItemRegister.GREEN_GEMSTONES || item == ItemRegister.PURPLE_GEMSTONES ||
		 item == ItemRegister.RED_GEMSTONES || item == ItemRegister.WHITE_GEMSTONES || item == ItemRegister.YELLOW_GEMSTONES || item == ItemRegister.CRYSTALLITE ||
		item == ItemRegister.ORNAMYTE || item == ItemRegister.JADE || item == ItemRegister.SAPPHIRE || item == ItemRegister.SHYREGEM)
			return true;

		return super.getIsRepairable(stack, repairMaterial);
	}
}
