package net.tslat.aoa3.content.item.tool.axe;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.AoATiers;
import net.tslat.aoa3.util.LocaleUtil;

import javax.annotation.Nullable;
import java.util.List;

public class GoofyAxe extends BaseAxe {
	private final Multimap<Attribute, AttributeModifier> attributeModifiers = HashMultimap.create();

	public GoofyAxe() {
		super(AoATiers.GOOFY);

		attributeModifiers.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Tool modifier", -1, AttributeModifier.Operation.MULTIPLY_TOTAL));
	}

	// TODO see about sound effect on hit

	@Override
	public void inventoryTick(ItemStack stack, Level world, Entity entity, int slot, boolean selected) {
		if (!world.isClientSide && stack.isDamaged()) {
			int modulo;

			if (selected) {
				modulo = 120;
			}
			else if (slot < 9) {
				modulo = 200;
			}
			else {
				modulo = 280;
			}

			if (world.getGameTime() % modulo == 0) {
				stack.setDamageValue(stack.getDamageValue() - 1);

				if (entity instanceof Player)
					((Player)entity).inventoryMenu.broadcastChanges();
			}
		}
	}

	@Override
	public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot equipmentSlot, ItemStack stack) {
		Multimap<Attribute, AttributeModifier> multimap = super.getAttributeModifiers(equipmentSlot, stack);

		if (equipmentSlot == EquipmentSlot.MAINHAND)
			return attributeModifiers;

		return multimap;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("items.description.tool.goofyRegen", LocaleUtil.ItemDescriptionType.BENEFICIAL));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("items.description.tool.goofyNoDamage", LocaleUtil.ItemDescriptionType.HARMFUL));
	}
}
