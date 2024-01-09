package net.tslat.aoa3.content.item.weapon.gun;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.common.Tags;
import net.tslat.aoa3.common.registration.AoASounds;
import org.jetbrains.annotations.Nullable;


public class CrystalCarver extends BaseGun {
	public CrystalCarver(float dmg, int durability, int firingDelayTicks, float recoil) {
		super(dmg, durability, firingDelayTicks, recoil);
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return AoASounds.ITEM_GUN_GENERIC_FIRE_1.get();
	}

	@Override
	public boolean isValidRepairItem(ItemStack stack, ItemStack repairMaterial) {
		return repairMaterial.is(Tags.Items.GEMS) || super.isValidRepairItem(stack, repairMaterial);
	}
}
