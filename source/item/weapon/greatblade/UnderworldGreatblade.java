package net.tslat.aoa3.item.weapon.greatblade;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.constant.AttackSpeed;

import javax.annotation.Nullable;
import java.util.List;

public class UnderworldGreatblade extends BaseGreatblade {
	public UnderworldGreatblade() {
		super(18.5f, AttackSpeed.GREATBLADE, 1050);
	}

	@Override
	protected double getDamageForAttack(ItemStack stack, Entity target, LivingEntity attacker, double baseDmg) {
		return target instanceof LivingEntity && ((LivingEntity)target).getCreatureAttribute() == CreatureAttribute.UNDEAD ? baseDmg + 5 : baseDmg;
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
	}
}
