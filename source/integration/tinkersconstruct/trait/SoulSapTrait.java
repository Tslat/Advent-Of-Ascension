package net.tslat.aoa3.integration.tinkersconstruct.trait;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.tslat.aoa3.common.registration.custom.AoAResources;
import net.tslat.aoa3.util.PlayerUtil;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;

public class SoulSapTrait extends Modifier {
	public SoulSapTrait() {
		super(0x99A8FC);
	}

	@Override
	public int afterEntityHit(IModifierToolStack tool, int level, ToolAttackContext context, float damageDealt) {
		if (damageDealt > 0 && context.getPlayerAttacker() instanceof ServerPlayerEntity)
			PlayerUtil.addResourceToPlayer((ServerPlayerEntity)context.getPlayerAttacker(), AoAResources.ENERGY.get(), damageDealt / 2f);

		return super.afterEntityHit(tool, level, context, damageDealt);
	}
}
