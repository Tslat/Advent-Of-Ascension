package net.tslat.aoa3.integration.tinkersconstruct.trait;

import net.minecraft.util.SoundCategory;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.util.RandomUtil;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.context.ToolHarvestContext;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;

public class TweetingTrait extends Modifier {
	public TweetingTrait() {
		super(0xFFE500);
	}

	@Override
	public int afterEntityHit(IModifierToolStack tool, int level, ToolAttackContext context, float damageDealt) {
		if (context.getPlayerAttacker() != null)
			context.getPlayerAttacker().level.playSound(null, context.getPlayerAttacker().blockPosition(), AoASounds.ENTITY_CHARGER_AMBIENT.get(), SoundCategory.PLAYERS, 1f, 1f);

		return super.afterEntityHit(tool, level, context, damageDealt);
	}

	@Override
	public void finishBreakingBlocks(IModifierToolStack tool, int level, ToolHarvestContext context) {
		if (context.getPlayer() != null && RandomUtil.oneInNChance(5))
			context.getPlayer().level.playSound(null, context.getPlayer().blockPosition(), AoASounds.ENTITY_CHARGER_AMBIENT.get(), SoundCategory.PLAYERS, 1f, 1f);
	}
}
