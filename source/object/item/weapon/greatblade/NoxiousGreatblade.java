package net.tslat.aoa3.object.item.weapon.greatblade;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.potion.Potions;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.util.ColourUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.misc.AttackSpeed;

import javax.annotation.Nullable;
import java.util.List;

public class NoxiousGreatblade extends BaseGreatblade {
	public NoxiousGreatblade() {
		super(23.0f, AttackSpeed.GREATBLADE, 1580);
	}

	@Override
	protected void doMeleeEffect(ItemStack stack, LivingEntity target, LivingEntity attacker, float attackCooldown) {
		if (target.hasEffect(Effects.POISON)) {
			AreaEffectCloudEntity cloud = new AreaEffectCloudEntity(target.level, target.getX(), target.getY(), target.getZ());

			cloud.setRadius(2);
			cloud.setPotion(Potions.STRONG_POISON);
			cloud.addEffect(new EffectInstance(Effects.POISON, (int)(60 * attackCooldown), 2, true, true));
			cloud.setDuration(6);
			cloud.setFixedColor(ColourUtil.RGB(51, 102, 0));
			cloud.setOwner(attacker);

			target.level.addFreshEntity(cloud);
		}
		else {
			target.addEffect(new EffectInstance(Effects.POISON, (int)(40 * attackCooldown), 1, true, true));
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Constants.POISONS_TARGETS, LocaleUtil.ItemDescriptionType.BENEFICIAL));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
	}
}