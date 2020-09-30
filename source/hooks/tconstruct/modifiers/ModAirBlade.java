package net.tslat.aoa3.hooks.tconstruct.modifiers;

import com.google.common.collect.ImmutableList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.tslat.aoa3.hooks.tconstruct.traits.Traits;
import slimeknights.tconstruct.library.Util;
import slimeknights.tconstruct.library.modifiers.ModifierAspect;
import slimeknights.tconstruct.library.modifiers.ModifierTrait;
import slimeknights.tconstruct.library.utils.ToolHelper;
import slimeknights.tconstruct.tools.melee.item.BroadSword;

import java.util.List;

public class ModAirBlade extends ModifierTrait {
	public ModAirBlade() {
		super("air_blade", 0xFFFDD8, 3, 1);

		addAspects(ModifierAspect.weaponOnly);
	}

	@Override
	public boolean canApplyCustom(ItemStack stack) {
		return super.canApplyCustom(stack) && stack.getItem() instanceof BroadSword;
	}

	@Override
	public void afterHit(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damageDealt, boolean wasCritical, boolean wasHit) {
		if (wasHit && !ToolHelper.isBroken(tool) && tool.getItem() instanceof BroadSword) {
			if (player.fallDistance > 0 || !player.onGround || player.isSprinting() || player.isOnLadder() || player.isInWater() || player.isRiding())
				return;

			if (player instanceof EntityPlayer && ((EntityPlayer)player).getCooledAttackStrength(0.5f) <= 0.9)
				return;

			if (player.distanceWalkedModified - player.prevDistanceWalkedModified >= player.getAIMoveSpeed())
				return;

			int modStage = Traits.getModifierStage(tool, this);
			float damage = damageDealt * (modStage / (float)(modStage + 2));

			for (EntityLivingBase entity : player.getEntityWorld().getEntitiesWithinAABB(EntityLivingBase.class, target.getEntityBoundingBox().expand(1, 0.25, 1))) {
				if (entity != player && entity != target && !player.isOnSameTeam(entity) && player.getDistanceSq(target) < 9)
					entity.attackEntityFrom(player instanceof EntityPlayer ? DamageSource.causePlayerDamage((EntityPlayer)player) : DamageSource.causeMobDamage(player), damage);
			}
		}
	}

	@Override
	public List<String> getExtraInfo(ItemStack tool, NBTTagCompound modifierTag) {
		int modStage = Traits.getModifierStage(tool, this);
		float percentBoost = modStage / (float)(modStage + 2);

		return ImmutableList.of(Util.translateFormatted(String.format(LOC_Extra, getModifierIdentifier()), Util.dfPercent.format(percentBoost)));
	}
}
