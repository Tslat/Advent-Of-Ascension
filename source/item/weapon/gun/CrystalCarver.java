package net.tslat.aoa3.item.weapon.gun;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.item.weapon.AdventWeapon;

import javax.annotation.Nullable;

public class CrystalCarver extends BaseGun implements AdventWeapon {
	public CrystalCarver(double dmg, int durability, int firingDelayTicks, float recoil) {
		super(dmg, durability, firingDelayTicks, recoil);
		setTranslationKey("CrystalCarver");
		setRegistryName("aoa3:crystal_carver");
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return SoundsRegister.gunSniper;
	}

	@Override
	public boolean getIsRepairable(ItemStack stack, ItemStack repairMaterial) {
		Item item = repairMaterial.getItem();

		if (item == Items.DIAMOND || item == Items.EMERALD || item == ItemRegister.gemGemenyte || item == ItemRegister.gemJewelyte || item == ItemRegister.gemAmethyst ||
		item == ItemRegister.gemBloodstone || item == ItemRegister.gemstonesBlue || item == ItemRegister.gemstonesGreen || item == ItemRegister.gemstonesPurple ||
		 item == ItemRegister.gemstonesRed || item == ItemRegister.gemstonesWhite || item == ItemRegister.gemstonesYellow || item == ItemRegister.gemCrystallite ||
		item == ItemRegister.gemOrnamyte || item == ItemRegister.gemJade || item == ItemRegister.gemSapphire || item == ItemRegister.gemShyregem)
			return true;

		return super.getIsRepairable(stack, repairMaterial);
	}
}
