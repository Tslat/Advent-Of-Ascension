package net.tslat.aoa3.content.item.weapon.sword;

import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.common.Tags;
import net.tslat.aoa3.common.registration.AoARegistries;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.tme.api.util.RandomUtil;

import java.util.List;

public class SweetSword extends AoASword {
	public SweetSword(Tier tier, Item.Properties properties) {
		super(tier, properties);
	}

	@Override
	protected void doMeleeEffect(ItemStack stack, LivingEntity target, LivingEntity attacker, float attackCooldown) {
		if (RandomUtil.percentChance(0.2f * attackCooldown)) {
			ItemStack drop = AoARegistries.ITEMS.registry().get()
					.getTag(Tags.Items.FOODS_CANDY)
					.map(tag -> tag.getRandomElement(target.level().random).map(Holder::value))
					.get().map(Item::getDefaultInstance)
					.orElseGet(() -> new ItemStack(Items.SUGAR, 3));

			target.spawnAtLocation(drop, target.getBbHeight() / 2f);
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag tooltipFlag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.UNIQUE, 1));
	}
}
