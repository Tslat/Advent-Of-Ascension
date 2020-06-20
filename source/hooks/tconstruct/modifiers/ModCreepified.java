package net.tslat.aoa3.hooks.tconstruct.modifiers;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.tslat.aoa3.utils.WorldUtil;
import slimeknights.tconstruct.library.modifiers.ModifierNBT;
import slimeknights.tconstruct.library.modifiers.ModifierTrait;
import slimeknights.tconstruct.library.utils.TinkerUtil;

public class ModCreepified extends ModifierTrait {
	public ModCreepified() {
		super("creepified", 0xA4EA00, 2, 1);
	}

	@Override
	public void afterHit(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damageDealt, boolean wasCritical, boolean wasHit) {
		if (wasHit) {
			ModifierNBT.IntegerNBT modifierData = ModifierNBT.readInteger(TinkerUtil.getModifierTag(tool, identifier));
			float strength = modifierData.level;
			double offset = target.width / 1.99d;
			double offsetX = MathHelper.clamp(player.posX - target.posX, -offset, offset);
			double offsetY = MathHelper.clamp(player.posY + player.getEyeHeight() - target.posY, -0.1, target.height + 0.1);
			double offsetZ = MathHelper.clamp(player.posZ - target.posZ, -offset, offset);

			BlockPos explosionPos = new BlockPos(target.posX - offsetX, target.posY + offsetY, target.posZ - offsetZ);

			if (wasCritical)
				strength *= 1.1f;

			WorldUtil.createExplosion(player, player.world, explosionPos, strength);
		}
	}
}
