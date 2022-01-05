package net.tslat.aoa3.object.item.weapon.greatblade;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.WorldUtil;
import net.tslat.aoa3.util.misc.AttackSpeed;

import javax.annotation.Nullable;
import java.util.List;

public class CreepoidGreatblade extends BaseGreatblade {
	public CreepoidGreatblade() {
		super(19.0f, AttackSpeed.GREATBLADE, 1080);
	}

	@Override
	protected void doMeleeEffect(ItemStack stack, LivingEntity target, LivingEntity attacker, float attackCooldown) {
		if (attackCooldown > 0.85f) {
			double offset = target.getBbWidth() / 1.99d;
			double offsetX = MathHelper.clamp(attacker.getX() - target.getX(), -offset, offset);
			double offsetY = MathHelper.clamp(attacker.getY() + attacker.getEyeHeight() - target.getY(), -0.1, target.getBbHeight() + 0.1);
			double offsetZ = MathHelper.clamp(attacker.getZ() - target.getZ(), -offset, offset);

			WorldUtil.createExplosion(attacker, attacker.level, target.getX() + offsetX, target.getY() + offsetY, target.getZ() + offsetZ, 1.5f);
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Constants.EXPLODES_ON_HIT, LocaleUtil.ItemDescriptionType.BENEFICIAL));
	}
}
