package net.tslat.aoa3.item.weapon.sword;

import com.google.common.collect.Multimap;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.tslat.aoa3.capabilities.persistentstack.PersistentStackCapabilityHandles;
import net.tslat.aoa3.capabilities.persistentstack.PersistentStackCapabilityProvider;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.constant.AttackSpeed;

import javax.annotation.Nullable;
import java.util.List;

public class GuardiansSword extends BaseSword {
	public GuardiansSword() {
		super(ItemUtil.customItemTier(2050, AttackSpeed.NORMAL, 15.0f, 4, 10, null));
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
		ItemStack heldStack = player.getHeldItem(hand);
		PersistentStackCapabilityHandles cap = PersistentStackCapabilityProvider.getOrDefault(heldStack, null);

		if (cap.getValue() <= 0 && ItemUtil.findInventoryItem(player, new ItemStack(AoAItems.CRYSTALLITE.get()), true, 1)) {
			cap.setValue(world.getGameTime());

			if (world instanceof ServerWorld) {
				double xOffset = -MathHelper.sin(player.rotationYaw * (float)Math.PI / 140f);
				double zOffset = MathHelper.cos(player.rotationYaw * (float)Math.PI / 140f);

				((ServerWorld)world).spawnParticle(ParticleTypes.END_ROD, player.getPosX() + xOffset, player.getPosY() + player.getHeight() * 0.5d, player.getPosZ() + zOffset, 5, xOffset, 0, zOffset, 0);
			}

			return ActionResult.resultSuccess(heldStack);
		}

		return super.onItemRightClick(world, player, hand);
	}

	@Override
	public boolean onLeftClickEntity(ItemStack stack, PlayerEntity player, Entity entity) {
		PersistentStackCapabilityProvider.getOrDefault(stack, Direction.NORTH).setValue(player.getCooledAttackStrength(0.0f));

		return false;
	}


	@Override
	public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
		if (world.getGameTime() % 10 == 0) {
			PersistentStackCapabilityHandles cap = PersistentStackCapabilityProvider.getOrDefault(stack, null);

			if (cap.getValue() > 0 && cap.getValue() < world.getGameTime() - 2400)
				cap.setValue(0);
		}
	}

	@Override
	public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		ItemUtil.damageItem(stack, attacker, 1, EquipmentSlotType.MAINHAND);

		return true;
	}

	@Nullable
	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundNBT nbt) {
		return new PersistentStackCapabilityProvider(null);
	}

	@Override
	public Multimap<String, AttributeModifier> getAttributeModifiers(EquipmentSlotType slot, ItemStack stack) {
		Multimap<String, AttributeModifier> attributeMap = super.getAttributeModifiers(slot, stack);

		if (slot == EquipmentSlotType.MAINHAND) {
			int buff = 0;
			PersistentStackCapabilityHandles cap = PersistentStackCapabilityProvider.getOrDefault(stack, null);

			if (cap.getValue() > 0)
				buff = 5;

			ItemUtil.setAttribute(attributeMap, SharedMonsterAttributes.ATTACK_DAMAGE, ATTACK_DAMAGE_MODIFIER, getAttackDamage() + buff);
		}

		return attributeMap;
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
	}
}
