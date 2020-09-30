package net.tslat.aoa3.hooks.tconstruct.traits;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.player.PlayerUtil;
import slimeknights.tconstruct.library.traits.AbstractTrait;

public class TraitSoulSap extends AbstractTrait {
	public TraitSoulSap() {
		super("soul_sap", 0x99A8FC);
	}

	@Override
	public void afterHit(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damageDealt, boolean wasCritical, boolean wasHit) {
		if (wasHit && !player.world.isRemote && damageDealt > 0 && player instanceof EntityPlayer)
			PlayerUtil.addResourceToPlayer((EntityPlayer)player, Enums.Resources.SOUL, damageDealt / 3.5f);
	}
}
