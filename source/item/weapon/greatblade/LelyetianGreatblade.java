package net.tslat.aoa3.item.weapon.greatblade;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.constant.AttackSpeed;

import javax.annotation.Nullable;
import java.util.List;

public class LelyetianGreatblade extends BaseGreatblade {
	public LelyetianGreatblade() {
		super(18.5f, AttackSpeed.GREATBLADE, 1100);
	}

	@Override
	protected double getDamageForAttack(ItemStack stack, Entity target, LivingEntity attacker, double baseDmg) {
		return EntityUtil.isFlyingCreature(target) ? (float)this.getAttackDamage() * 1.25f : (float)this.getAttackDamage();
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
	}
}
