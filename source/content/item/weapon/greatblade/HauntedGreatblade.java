package net.tslat.aoa3.content.item.weapon.greatblade;

import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.AoARegistries;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.tme.api.util.RandomUtil;
import net.tslat.tme.api.object.builder.EffectBuilder;

import java.util.List;
import java.util.Optional;

public class HauntedGreatblade extends AoAGreatblade {
	public HauntedGreatblade(Tier tier, Item.Properties properties) {
		super(tier, properties);
	}

	@Override
	public void inventoryTick(ItemStack stack, Level world, Entity entity, int slot, boolean isSelected) {
		if (!world.isClientSide && isSelected && entity instanceof Player pl && RandomUtil.oneInNChance(12000)) {
			int i = 0;
			Optional<Holder.Reference<MobEffect>> effect = AoARegistries.MOB_EFFECTS.getRandomElement(pl.getRandom());

			while (effect.isEmpty() & i++ < 10) {
				effect = AoARegistries.MOB_EFFECTS.getRandomElement(pl.getRandom());
			}

			effect.ifPresent(effect2 -> EntityUtil.applyPotions(pl, pl, new EffectBuilder(effect2, 600)));

			MutableComponent component = LocaleUtil.getLocaleMessage("item.aoa3.haunted_greatblade.message." + RandomUtil.numberBetween(1, 16), ChatFormatting.DARK_PURPLE);

			component.getStyle().withItalic(true);
			entity.sendSystemMessage(component);
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag tooltipFlag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.UNIQUE, 1));
	}
}
