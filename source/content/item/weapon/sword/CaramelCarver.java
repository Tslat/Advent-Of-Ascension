package net.tslat.aoa3.content.item.weapon.sword;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.library.constant.AttackSpeed;

import javax.annotation.Nullable;
import java.util.List;

public class CaramelCarver extends BaseSword {
	public CaramelCarver() {
		super(ItemUtil.customItemTier(1900, AttackSpeed.NORMAL, 15.5f, 4, 10, null));
	}

	@Override
	protected void doMeleeEffect(ItemStack stack, LivingEntity target, LivingEntity attacker, float attackCooldown) {
		final int multiplier = attacker.level.getEntitiesOfClass(LivingEntity.class, attacker.getBoundingBox().inflate(5.0f), EntityUtil.Predicates.HOSTILE_MOB).size();

		target.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, (int)(multiplier * 15 * attackCooldown), 1));
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Constants.SLOWS_TARGETS, LocaleUtil.ItemDescriptionType.BENEFICIAL));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
	}
}
