package net.tslat.aoa3.event.dimension;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.DamageSource;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.ModUtil;
import net.tslat.aoa3.utils.player.PlayerDataManager;
import net.tslat.aoa3.utils.player.PlayerUtil;

public class VoxPondsEvents {
	public static void doPlayerTick(EntityPlayer pl) {
		PlayerDataManager plData = PlayerUtil.getAdventPlayer(pl);

		if (PlayerUtil.shouldPlayerBeAffected(pl) && !ItemUtil.isPlayerEnvironmentallyProtected(pl)) {
			pl.attackEntityFrom(new DamageSource("vox_ponds").setDamageBypassesArmor().setDamageIsAbsolute(), 1f);
			ModUtil.completeAdvancement((EntityPlayerMP)plData.player(), "voxponds/oops", "atmosphere_damage");
		}
	}
}
