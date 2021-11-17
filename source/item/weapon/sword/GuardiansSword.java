package net.tslat.aoa3.item.weapon.sword;

import com.google.common.collect.Multimap;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
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
import net.tslat.aoa3.util.misc.AttackSpeed;

import javax.annotation.Nullable;
import java.util.List;

public class GuardiansSword extends BaseSword {
	public GuardiansSword() {
		super(ItemUtil.customItemTier(2050, AttackSpeed.NORMAL, 15.0f, 4, 10, null));
	}

	@Override
	public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
		ItemStack heldStack = player.getItemInHand(hand);
		PersistentStackCapabilityHandles cap = PersistentStackCapabilityProvider.getOrDefault(heldStack, null);

		if (cap.getValue() <= 0 && ItemUtil.findInventoryItem(player, new ItemStack(AoAItems.CRYSTALLITE.get()), true, 1)) {
			cap.setValue(world.getGameTime());

			if (world instanceof ServerWorld) {
				double xOffset = -MathHelper.sin(player.yRot * (float)Math.PI / 140f);
				double zOffset = MathHelper.cos(player.yRot * (float)Math.PI / 140f);

				((ServerWorld)world).sendParticles(ParticleTypes.END_ROD, player.getX() + xOffset, player.getY() + player.getBbHeight() * 0.5d, player.getZ() + zOffset, 5, xOffset, 0, zOffset, 0);
			}

			return ActionResult.success(heldStack);
		}

		return super.use(world, player, hand);
	}

	@Override
	public boolean onLeftClickEntity(ItemStack stack, PlayerEntity player, Entity entity) {
		PersistentStackCapabilityProvider.getOrDefault(stack, Direction.NORTH).setValue(player.getAttackStrengthScale(0.0f));

		return false;
	}

	@Nullable
	@Override
	public CompoundNBT getShareTag(ItemStack stack) {
		CompoundNBT tag = super.getShareTag(stack);

		if (tag == null)
			tag = new CompoundNBT();

		tag.putFloat("AdventMiscStackCapability", PersistentStackCapabilityProvider.getOrDefault(stack, null).getValue());

		return tag;
	}

	@Override
	public void readShareTag(ItemStack stack, @Nullable CompoundNBT nbt) {
		if (nbt != null && nbt.contains("AdventMiscStackCapability"))
			PersistentStackCapabilityProvider.getOrDefault(stack, null).setValue(nbt.getFloat("AdventMiscStackCapability"));

		super.readShareTag(stack, nbt);
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
	public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		ItemUtil.damageItem(stack, attacker, 1, EquipmentSlotType.MAINHAND);

		return true;
	}

	@Nullable
	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundNBT nbt) {
		return new PersistentStackCapabilityProvider(null);
	}

	@Override
	public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlotType slot, ItemStack stack) {
		Multimap<Attribute, AttributeModifier> attributeMap = super.getAttributeModifiers(slot, stack);

		if (slot == EquipmentSlotType.MAINHAND) {
			int buff = 0;
			PersistentStackCapabilityHandles cap = PersistentStackCapabilityProvider.getOrDefault(stack, null);

			if (cap.getValue() > 0)
				buff = 5;

			ItemUtil.setAttribute(attributeMap, Attributes.ATTACK_DAMAGE, BASE_ATTACK_DAMAGE_UUID, getDamage() + buff);
		}

		return attributeMap;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
	}
}
