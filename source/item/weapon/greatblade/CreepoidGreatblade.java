package net.tslat.aoa3.item.weapon.greatblade;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.WorldUtil;
import net.tslat.aoa3.util.constant.AttackSpeed;

import javax.annotation.Nullable;
import java.util.List;

public class CreepoidGreatblade extends BaseGreatblade {
	public CreepoidGreatblade() {
		super(19.0f, AttackSpeed.GREATBLADE, 1080);
	}

	@Override
	protected void doMeleeEffect(ItemStack stack, LivingEntity attacker, Entity target, float dmgDealt) {
		if (EntityUtil.getAttackCooldown(attacker) > 0.85f) {
			double offset = target.getWidth() / 1.99d;
			double offsetX = MathHelper.clamp(attacker.getPosX() - target.getPosX(), -offset, offset);
			double offsetY = MathHelper.clamp(attacker.getPosY() + attacker.getEyeHeight() - target.getPosY(), -0.1, target.getHeight() + 0.1);
			double offsetZ = MathHelper.clamp(attacker.getPosZ() - target.getPosZ(), -offset, offset);

			WorldUtil.createExplosion(attacker, attacker.world, target.getPosX() + offsetX, target.getPosY() + offsetY, target.getPosZ() + offsetZ, 1.5f);
		}
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Constants.EXPLODES_ON_HIT, LocaleUtil.ItemDescriptionType.BENEFICIAL));
	}
}
