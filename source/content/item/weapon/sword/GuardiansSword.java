package net.tslat.aoa3.content.item.weapon.sword;

import com.google.common.collect.Multimap;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.common.registration.item.AoATiers;
import net.tslat.aoa3.content.item.ChargeableItem;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class GuardiansSword extends BaseSword implements ChargeableItem {
	public GuardiansSword() {
		super(AoATiers.GUARDIAN);
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		ItemStack heldStack = player.getItemInHand(hand);

		if (getCharge(heldStack) <= 0 && ItemUtil.findInventoryItem(player, new ItemStack(AoAItems.CRYSTALLITE.get()), true, 1)) {
			setCharge(heldStack, level.getGameTime());

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

			if (charge > 0 && charge < level.getGameTime() - 2400)
				setCharge(stack, 0);
		}
	}

	@Override
	public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		ItemUtil.damageItem(stack, attacker, 1, EquipmentSlot.MAINHAND);

		return true;
	}

	@Override
	public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
		Multimap<Attribute, AttributeModifier> attributeMap = super.getAttributeModifiers(slot, stack);

		if (slot == EquipmentSlot.MAINHAND)
			ItemUtil.setAttribute(attributeMap, Attributes.ATTACK_DAMAGE, BASE_ATTACK_DAMAGE_UUID, getDamage() + (getCharge(stack) > 0 ? 3 : 0));

		return attributeMap;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
	}
}
