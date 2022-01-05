package net.tslat.aoa3.integration.tinkersconstruct.modifier;

import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.tslat.aoa3.util.WorldUtil;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;

public class CreepifiedModifier extends Modifier {
	public CreepifiedModifier() {
		super(0xA4EA00);
	}

	@Override
	public int afterEntityHit(IModifierToolStack tool, int level, ToolAttackContext context, float damageDealt) {
		if (damageDealt > 0) {
			LivingEntity target = context.getLivingTarget();
			LivingEntity attacker = context.getAttacker();
			float strength = level;
			double offset = target.getBbWidth() / 1.99d;
			double offsetX = MathHelper.clamp(attacker.getX() - target.getX(), -offset, offset);
			double offsetY = MathHelper.clamp(attacker.getY() + attacker.getEyeHeight() - target.getY(), -0.1, target.getBbHeight() + 0.1);
			double offsetZ = MathHelper.clamp(attacker.getZ() - target.getZ(), -offset, offset);

			BlockPos explosionPos = new BlockPos(target.getX() - offsetX, target.getY() + offsetY, target.getZ() - offsetZ);

			if (context.isCritical())
				strength *= 1.1f;

			WorldUtil.createExplosion(attacker, attacker.level, explosionPos, strength);
		}

		return super.afterEntityHit(tool, level, context, damageDealt);
	}
}
