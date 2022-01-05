package net.tslat.aoa3.object.item.weapon.sword;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.WorldUtil;
import net.tslat.aoa3.util.misc.AttackSpeed;

import javax.annotation.Nullable;
import java.util.List;

public class RockbasherSword extends BaseSword {
	public RockbasherSword() {
		super(ItemUtil.customItemTier(2020, AttackSpeed.NORMAL, 16.0f, 4, 10, null));
	}

	@Override
	protected void doMeleeEffect(ItemStack stack, LivingEntity target, LivingEntity attacker, float attackCooldown) {
		double armour = target.getAttribute(Attributes.ARMOR).getValue();

		if (armour > 0)
			WorldUtil.createExplosion(attacker, attacker.level, target.getX(), target.getY() + target.getBbHeight() / 1.5, target.getZ(), 0.5f + (float)(3 * armour / 30f));
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
	}
}
