package net.tslat.aoa3.item.weapon.greatblade;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.entity.projectile.misc.TidalWaveEntity;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.constant.AttackSpeed;

import javax.annotation.Nullable;
import java.util.List;

public class TidalGreatblade extends BaseGreatblade {
	public TidalGreatblade() {
		super(24.0f, AttackSpeed.GREATBLADE, 1750);
	}

	@Override
	protected void doMeleeEffect(ItemStack stack, LivingEntity attacker, Entity target, float dmgDealt) {
		if (EntityUtil.getAttackCooldown(attacker) < 0.95f)
			return;

		double xOffset = MathHelper.cos(attacker.yRot / 180.0F * (float)Math.PI) * 0.7F;
		double zOffset = MathHelper.sin(attacker.yRot / 180.0F * (float)Math.PI) * 0.7F;

		attacker.level.addFreshEntity(new TidalWaveEntity(attacker.level, attacker, xOffset, zOffset));
		attacker.level.addFreshEntity(new TidalWaveEntity(attacker.level, attacker, 0, 0));
		attacker.level.addFreshEntity(new TidalWaveEntity(attacker.level, attacker, -xOffset, -zOffset));
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
	}
}
