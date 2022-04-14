package net.tslat.aoa3.content.item.weapon.vulcane;

import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.library.builder.EffectBuilder;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.LocaleUtil;

import javax.annotation.Nullable;
import java.util.List;

public class BattleVulcane extends BaseVulcane {
	public BattleVulcane(double dmg, int durability) {
		super(dmg, durability);
	}

	@Override
	public void doAdditionalEffect(LivingEntity target, Player attacker, float damageDealt) {
		EntityUtil.applyPotions(attacker, new EffectBuilder(MobEffects.DAMAGE_BOOST, 200).level(2));
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, world, tooltip, flag);
	}
}
