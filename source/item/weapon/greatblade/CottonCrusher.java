package net.tslat.aoa3.item.weapon.greatblade;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.constant.AttackSpeed;

import javax.annotation.Nullable;
import java.util.List;

public class CottonCrusher extends BaseGreatblade {
	public CottonCrusher() {
		super(24.0f, AttackSpeed.GREATBLADE, 1600);
	}

	@Override
	protected void doMeleeEffect(ItemStack stack, LivingEntity attacker, Entity target, float dmgDealt) {
		if (attacker instanceof PlayerEntity && EntityUtil.getAttackCooldown(attacker) > 0.95f)
			((PlayerEntity)attacker).getFoodData().eat(1, 0);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
	}
}
