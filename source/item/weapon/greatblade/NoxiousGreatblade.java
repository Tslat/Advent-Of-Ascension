package net.tslat.aoa3.item.weapon.greatblade;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.potion.Potions;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.NumberUtil;
import net.tslat.aoa3.util.constant.AttackSpeed;

import javax.annotation.Nullable;
import java.util.List;

public class NoxiousGreatblade extends BaseGreatblade {
	public NoxiousGreatblade() {
		super(23.0f, AttackSpeed.GREATBLADE, 1580);
	}

	@Override
	protected void doMeleeEffect(ItemStack stack, LivingEntity attacker, Entity target, float dmgDealt) {
		if (target instanceof LivingEntity) {
			if (((LivingEntity)target).hasEffect(Effects.POISON)) {
				AreaEffectCloudEntity cloud = new AreaEffectCloudEntity(target.level, target.getX(), target.getY(), target.getZ());

				cloud.setRadius(2);
				cloud.setPotion(Potions.STRONG_POISON);
				cloud.addEffect(new EffectInstance(Effects.POISON, 60, 2, true, true));
				cloud.setDuration(3);
				cloud.setFixedColor(NumberUtil.RGB(51, 102, 0));
				cloud.setOwner(attacker);

				target.level.addFreshEntity(cloud);
			}
			else {
				((LivingEntity)target).addEffect(new EffectInstance(Effects.POISON, 40, 1, true, true));
			}
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Constants.POISONS_TARGETS, LocaleUtil.ItemDescriptionType.BENEFICIAL));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
	}
}
