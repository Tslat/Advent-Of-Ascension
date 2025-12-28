package net.tslat.aoa3.content.item.weapon.sniper;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.tslat.aoa3.content.item.weapon.gun.AoAGun;

public class BayonetteSR extends AoASniper {
	public BayonetteSR(Item.Properties properties) {
		super(properties);
	}

	@Override
	public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		stack.hurtAndBreak(1, attacker, EquipmentSlot.MAINHAND);

		return true;
	}

	public static ItemAttributeModifiers createAttributes(float unholsterTimeModifier) {
		ItemAttributeModifiers.Builder builder = ItemAttributeModifiers.builder();

		builder.add(
				Attributes.ATTACK_DAMAGE,
				new AttributeModifier(
						BASE_ATTACK_DAMAGE_ID,
						9,
						AttributeModifier.Operation.ADD_VALUE),
				EquipmentSlotGroup.MAINHAND);

		for (ItemAttributeModifiers.Entry entry : AoAGun.createGunAttributeModifiers(unholsterTimeModifier).modifiers()) {
			builder.add(entry.attribute(), entry.modifier(), entry.slot());
		}

		return builder.build();
	}
}
