package net.tslat.aoa3.hooks.ic2;

import ic2.core.item.armor.ItemArmorHazmat;
import net.minecraft.entity.player.EntityPlayer;
import net.tslat.aoa3.utils.ConfigurationUtil;

public class IC2CompatUtil {
	public boolean isPlayerEnvironmentallyProtected(EntityPlayer pl) {
		if (!ConfigurationUtil.IntegrationsConfig.ic2.hazmatArmour)
			return false;

		return ItemArmorHazmat.hasCompleteHazmat(pl);
	}
}