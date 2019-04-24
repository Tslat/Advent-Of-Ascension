package net.tslat.aoa3.event.dimension;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.tslat.aoa3.capabilities.handlers.AdventPlayerCapability;
import net.tslat.aoa3.common.registration.ArmourRegister;
import net.tslat.aoa3.library.Enums;

public class VoxPondsEvents {
	public static void doPlayerTick(AdventPlayerCapability cap) {
		EntityPlayer pl = cap.getPlayer();

		if (!pl.capabilities.isCreativeMode) {
			if (cap.getArmourSetType() != Enums.ArmourSets.HAZMAT && cap.getHelmet() != ArmourRegister.FaceMask) {
				pl.attackEntityFrom(new DamageSource("vox_ponds").setDamageBypassesArmor().setDamageIsAbsolute(), 1f);
			}
		}
	}
}
