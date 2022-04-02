package net.tslat.aoa3.content.item.weapon.sword;

import com.google.common.collect.Multimap;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
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
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.content.capability.persistentstack.PersistentStackCapabilityHandles;
import net.tslat.aoa3.content.capability.persistentstack.PersistentStackCapabilityProvider;
import net.tslat.aoa3.library.constant.AttackSpeed;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;

import javax.annotation.Nullable;
import java.util.List;

public class GuardiansSword extends BaseSword {
	public GuardiansSword() {
		super(ItemUtil.customItemTier(2050, AttackSpeed.NORMAL, 15.0f, 4, 10, null, null));
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
		ItemStack heldStack = player.getItemInHand(hand);
		PersistentStackCapabilityHandles cap = PersistentStackCapabilityProvider.getOrDefault(heldStack, null);

		if (cap.getValue() <= 0 && ItemUtil.findInventoryItem(player, new ItemStack(AoAItems.CRYSTALLITE.get()), true, 1)) {
			cap.setValue(world.getGameTime());

			if (world instanceof ServerLevel) {
				double xOffset = -Mth.sin(player.getYRot() * (float)Math.PI / 140f);
				double zOffset = Mth.cos(player.getYRot() * (float)Math.PI / 140f);

				((ServerLevel)world).sendParticles(ParticleTypes.END_ROD, player.getX() + xOffset, player.getY() + player.getBbHeight() * 0.5d, player.getZ() + zOffset, 5, xOffset, 0, zOffset, 0);
			}

			return InteractionResultHolder.success(heldStack);
		}

		return super.use(world, player, hand);
	}

	@Override
	public boolean onLeftClickEntity(ItemStack stack, Player player, Entity entity) {
		PersistentStackCapabilityProvider.getOrDefault(stack, Direction.NORTH).setValue(player.getAttackStrengthScale(0.0f));

		return false;
	}

	@Nullable
	@Override
	public CompoundTag getShareTag(ItemStack stack) {
		CompoundTag tag = super.getShareTag(stack);

		if (tag == null)
			tag = new CompoundTag();

		tag.putFloat("AdventMiscStackCapability", PersistentStackCapabilityProvider.getOrDefault(stack, null).getValue());

		return tag;
	}

	@Override
	public void readShareTag(ItemStack stack, @Nullable CompoundTag nbt) {
		if (nbt != null && nbt.contains("AdventMiscStackCapability"))
			PersistentStackCapabilityProvider.getOrDefault(stack, null).setValue(nbt.getFloat("AdventMiscStackCapability"));

		super.readShareTag(stack, nbt);
	}

	@Override
	public void inventoryTick(ItemStack stack, Level world, Entity entity, int slot, boolean selected) {
		if (world.getGameTime() % 10 == 0) {
			PersistentStackCapabilityHandles cap = PersistentStackCapabilityProvider.getOrDefault(stack, null);

			if (cap.getValue() > 0 && cap.getValue() < world.getGameTime() - 2400)
				cap.setValue(0);
		}
	}

	@Override
	public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		ItemUtil.damageItem(stack, attacker, 1, EquipmentSlot.MAINHAND);

		return true;
	}

	@Nullable
	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
		return new PersistentStackCapabilityProvider(null);
	}

	@Override
	public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
		Multimap<Attribute, AttributeModifier> attributeMap = super.getAttributeModifiers(slot, stack);

		if (slot == EquipmentSlot.MAINHAND) {
			int buff = 0;
			PersistentStackCapabilityHandles cap = PersistentStackCapabilityProvider.getOrDefault(stack, null);

			if (cap.getValue() > 0)
				buff = 5;

			ItemUtil.setAttribute(attributeMap, Attributes.ATTACK_DAMAGE, BASE_ATTACK_DAMAGE_UUID, getDamage() + buff);
		}

		return attributeMap;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
	}
}
