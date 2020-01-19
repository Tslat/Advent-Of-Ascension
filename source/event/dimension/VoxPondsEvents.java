package net.tslat.aoa3.event.dimension;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.DamageSource;
import net.tslat.aoa3.common.registration.ArmourRegister;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ModUtil;
import net.tslat.aoa3.utils.player.PlayerDataManager;
import net.tslat.aoa3.utils.player.PlayerUtil;

public class VoxPondsEvents {
	public static void doPlayerTick(PlayerDataManager plData) {
		EntityPlayer pl = plData.player();

		if (PlayerUtil.shouldPlayerBeAffected(pl) && plData.equipment().getCurrentFullArmourSet() != Enums.ArmourSets.HAZMAT && pl.inventory.armorInventory.get(EntityEquipmentSlot.HEAD.getIndex()).getItem() != ArmourRegister.faceMask) {
			pl.attackEntityFrom(new DamageSource("vox_ponds").setDamageBypassesArmor().setDamageIsAbsolute(), 1f);
			ModUtil.completeAdvancement((EntityPlayerMP)plData.player(), "voxponds/oops", "atmosphere_damage");
		}
	}
}
