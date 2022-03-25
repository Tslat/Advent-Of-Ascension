package net.tslat.aoa3.content.item.tool.shovel;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;

import javax.annotation.Nullable;
import java.util.List;

public class GoofyShovel extends BaseShovel {
	private final Multimap<Attribute, AttributeModifier> attributeModifiers = HashMultimap.create();

	public GoofyShovel() {
		super(ItemUtil.customItemTier(1500, 8.0f, -1f, 4, 10, null));

		attributeModifiers.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Tool modifier", -1, AttributeModifier.Operation.MULTIPLY_TOTAL));
	}

	// TODO see about sound effect on hit

	@Override
	public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
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

				if (entity instanceof PlayerEntity)
					((PlayerEntity)entity).inventoryMenu.broadcastChanges();
			}
		}
	}

	@Override
	public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot, ItemStack stack) {
		Multimap<Attribute, AttributeModifier> multimap = super.getAttributeModifiers(equipmentSlot, stack);

		if (equipmentSlot == EquipmentSlotType.MAINHAND)
			return attributeModifiers;

		return multimap;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("items.description.tool.goofyRegen", LocaleUtil.ItemDescriptionType.BENEFICIAL));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("items.description.tool.goofyNoDamage", LocaleUtil.ItemDescriptionType.HARMFUL));
	}
}
