package net.tslat.aoa3.content.item.weapon.greatblade;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.custom.AoAResources;
import net.tslat.aoa3.player.resource.AoAResource;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.PlayerUtil;

import java.util.List;
import java.util.UUID;

public class PlutonScythe extends AoAGreatblade {
	public static final UUID LUCK_BUFF = UUID.fromString("e446949b-1792-4a66-8f83-5037d6dcce9b");

	public PlutonScythe(Tier tier, Item.Properties properties) {
		super(tier, properties);
	}

	public static Item.Properties baseProperties(Tier tier, float attackSpeed) {
		return new Item.Properties().attributes(AoAGreatblade.createAttributes(tier, 0f, attackSpeed).withModifierAdded(Attributes.LUCK, new AttributeModifier(AdventOfAscension.id("pluton_scythe"), 2, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND));
	}

	@Override
	protected void doMeleeEffect(ItemStack stack, LivingEntity target, LivingEntity attacker, float attackCooldown) {
		if (!attacker.level().isClientSide) {
			AoAResource.Instance spirit = target instanceof ServerPlayer ? PlayerUtil.getResource((ServerPlayer)target, AoAResources.SPIRIT.get()) : null;
			float consumeAmount = (spirit != null ? Math.min(50, spirit.getCurrentValue()) : 50) * attackCooldown;

			if (consumeAmount > 0) {
				if (spirit != null && !spirit.consume(consumeAmount, true))
					return;

				if (attacker instanceof ServerPlayer)
					PlayerUtil.addResourceToPlayer((ServerPlayer)attacker, AoAResources.SPIRIT.get(), consumeAmount);
			}
		}
	}

	public static ItemAttributeModifiers createAttributes() {
		return ItemAttributeModifiers.builder()
				.build();
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag tooltipFlag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Keys.LEECHES_SPIRIT, LocaleUtil.ItemDescriptionType.ITEM_TYPE_INFO));
	}
}
