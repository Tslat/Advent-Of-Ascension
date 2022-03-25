package net.tslat.aoa3.content.item.weapon.greatblade;

import com.google.common.collect.Multimap;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.custom.AoAResources;
import net.tslat.aoa3.player.resource.AoAResource;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.PlayerUtil;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class PlutonScythe extends BaseGreatblade {
	private static final AttributeModifier LUCK_BUFF = new AttributeModifier(UUID.fromString("e446949b-1792-4a66-8f83-5037d6dcce9b"), "AoALuxonScytheLuckBuff", 2, AttributeModifier.Operation.ADDITION);

	public PlutonScythe() {
		super(19.0f, -3D, 175);
	}

	@Override
	protected void doMeleeEffect(ItemStack stack, LivingEntity target, LivingEntity attacker, float attackCooldown) {
		if (!attacker.level.isClientSide) {
			AoAResource.Instance spirit = target instanceof ServerPlayerEntity ? PlayerUtil.getResource((ServerPlayerEntity)target, AoAResources.SPIRIT.get()) : null;
			float consumeAmount = (spirit != null ? Math.min(50, spirit.getCurrentValue()) : 50) * attackCooldown;

			if (consumeAmount > 0) {
				if (spirit != null && !spirit.consume(consumeAmount, true))
					return;

				if (attacker instanceof ServerPlayerEntity)
					PlayerUtil.addResourceToPlayer((ServerPlayerEntity)attacker, AoAResources.SPIRIT.get(), consumeAmount);
			}
		}
	}

	@Override
	public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot, ItemStack stack) {
		Multimap<Attribute, AttributeModifier> multimap =  super.getAttributeModifiers(equipmentSlot, stack);

		if (equipmentSlot == EquipmentSlotType.MAINHAND)
			multimap.put(Attributes.LUCK, LUCK_BUFF);

		return multimap;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("items.description.scythe", LocaleUtil.ItemDescriptionType.ITEM_TYPE_INFO));
	}
}
