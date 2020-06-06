package net.tslat.aoa3.hooks.tconstruct.modifiers;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.item.ItemStack;
import net.tslat.aoa3.entity.misc.EntityBloodlust;
import net.tslat.aoa3.hooks.tconstruct.traits.Traits;
import net.tslat.aoa3.utils.skills.ButcheryUtil;
import slimeknights.tconstruct.library.modifiers.ModifierTrait;

public class ModButcherer extends ModifierTrait {
	public ModButcherer() {
		super("butcherer", 0x960000, 3, 3);
	}

	@Override
	public void afterHit(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damageDealt, boolean wasCritical, boolean wasHit) {
		if (wasHit) {
			int modStage = Traits.getModifierStage(tool, this);
			double attackSpeed = Math.max(0.1, player.getEntityAttribute(SharedMonsterAttributes.ATTACK_SPEED).getAttributeValue());

			if (random.nextFloat() < modStage * 0.005 * 1.6d / attackSpeed && ButcheryUtil.canMobSpawnBloodlust(target))
				player.world.spawnEntity(new EntityBloodlust(player.world, target.getPosition()));
		}
	}
}
