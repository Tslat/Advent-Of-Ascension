package net.tslat.aoa3.content.item.weapon.sword;

import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.content.item.ChargeableItem;
import net.tslat.aoa3.util.InventoryUtil;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;

import java.util.List;

public class GuardiansSword extends AoASword implements ChargeableItem {
	public GuardiansSword(Tier tier, Item.Properties properties) {
		super(tier, properties);
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		ItemStack heldStack = player.getItemInHand(hand);

		if (getCharge(heldStack) <= 0 && InventoryUtil.findItemForConsumption(player, AoAItems.CRYSTALLITE, player.getAbilities().instabuild ? 0 : 1, true)) {
			setCharge(heldStack, level.getGameTime() + 2400);
			heldStack.update(DataComponents.ATTRIBUTE_MODIFIERS, ItemAttributeModifiers.EMPTY, modifiers -> modifiers.withModifierAdded(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_ID, 3 + getTier().getAttackDamageBonus(), AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND));

			if (level instanceof ServerLevel serverLevel) {
				double xOffset = -Mth.sin(player.getYRot() * (float)Math.PI / 140f);
				double zOffset = Mth.cos(player.getYRot() * (float)Math.PI / 140f);

				serverLevel.sendParticles(ParticleTypes.END_ROD, player.getX() + xOffset, player.getY() + player.getBbHeight() * 0.5d, player.getZ() + zOffset, 5, xOffset, 0, zOffset, 0);
			}

			return InteractionResultHolder.success(heldStack);
		}

		return super.use(level, player, hand);
	}

	@Override
	public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean selected) {
		if (level.getGameTime() % 10 == 0) {
			float charge = getCharge(stack);

			if (charge > 0 && charge < level.getGameTime()) {
				setCharge(stack, 0);
				stack.update(DataComponents.ATTRIBUTE_MODIFIERS, ItemAttributeModifiers.EMPTY, modifiers -> modifiers.withModifierAdded(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_ID, getTier().getAttackDamageBonus(), AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND));
			}
		}
	}

	@Override
	public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		if (attacker.level() instanceof ServerLevel level)
			ItemUtil.damageItemForUser(level, stack, attacker, EquipmentSlot.MAINHAND);

		return true;
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
	}
}
