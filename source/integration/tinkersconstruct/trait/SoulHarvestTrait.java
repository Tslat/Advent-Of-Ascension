package net.tslat.aoa3.integration.tinkersconstruct.trait;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.tslat.aoa3.common.registration.custom.AoAResources;
import net.tslat.aoa3.util.PlayerUtil;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolHarvestContext;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;

public class SoulHarvestTrait extends Modifier {
	public SoulHarvestTrait() {
		super(0x99A8FC);
	}

	@Override
	public void afterBlockBreak(IModifierToolStack tool, int level, ToolHarvestContext context) {
		if (context.isEffective() && context.getPlayer() != null) {
			ServerPlayerEntity player = context.getPlayer();

			PlayerUtil.addResourceToPlayer(player, AoAResources.ENERGY.get(), context.getState().getDestroySpeed(player.level, context.getPos()));
		}
	}
}
