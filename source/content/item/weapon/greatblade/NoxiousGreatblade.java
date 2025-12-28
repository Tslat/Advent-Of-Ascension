package net.tslat.aoa3.content.item.weapon.greatblade;

import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.tslat.aoa3.util.ColourUtil;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.tme.api.object.builder.EffectBuilder;

import java.util.List;
import java.util.Optional;

public class NoxiousGreatblade extends AoAGreatblade {
	public NoxiousGreatblade(Tier tier, Item.Properties properties) {
		super(tier, properties);
	}

	@Override
	protected void doMeleeEffect(ItemStack stack, LivingEntity target, LivingEntity attacker, float attackCooldown) {
		if (target.hasEffect(MobEffects.POISON)) {
			AreaEffectCloud cloud = new AreaEffectCloud(target.level(), target.getX(), target.getY(), target.getZ());

			cloud.setRadius(2);
			cloud.setPotionContents(new PotionContents(Optional.of(Potions.STRONG_POISON), Optional.of(ColourUtil.RGB(51, 102, 0)), List.of(new MobEffectInstance(MobEffects.POISON, (int)(60 * attackCooldown), 2, true, true))));
			cloud.setDuration(6);
			cloud.setOwner(attacker);

			target.level().addFreshEntity(cloud);
		}
		else {
			EntityUtil.applyPotions(target, attacker, new EffectBuilder(MobEffects.POISON, (int)(40 * attackCooldown)).level(2).isAmbient().hideParticles());
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag tooltipFlag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Keys.POISONS_TARGETS, LocaleUtil.ItemDescriptionType.BENEFICIAL));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
	}
}