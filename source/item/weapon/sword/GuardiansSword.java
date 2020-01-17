package net.tslat.aoa3.item.weapon.sword;

import com.google.common.collect.Multimap;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.tslat.aoa3.capabilities.interfaces.CapabilityBaseMiscStackSerializable;
import net.tslat.aoa3.capabilities.providers.AdventMiscStackSerializeableProvider;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.item.weapon.AdventWeapon;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ItemUtil;

import javax.annotation.Nullable;
import java.util.List;

public class GuardiansSword extends BaseSword implements AdventWeapon {
	public GuardiansSword(final ToolMaterial material, final double speed) {
		super(material, speed);
		setTranslationKey("GuardiansSword");
		setRegistryName("aoa3:guardians_sword");
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		ItemStack heldStack = player.getHeldItem(hand);
		CapabilityBaseMiscStackSerializable cap = heldStack.getCapability(AdventMiscStackSerializeableProvider.MISC_STACK, null);

		if (cap != null && cap.getValue() <= 0 && ItemUtil.consumeItem(player, new ItemStack(ItemRegister.gemCrystallite))) {
			cap.setValue(world.getTotalWorldTime());

			if (world instanceof WorldServer) {
				double xOffset = -MathHelper.sin(player.rotationYaw * (float)Math.PI / 140f);
				double zOffset = MathHelper.cos(player.rotationYaw * (float)Math.PI / 140f);

				((WorldServer)world).spawnParticle(EnumParticleTypes.END_ROD, player.posX + xOffset, player.posY + player.height * 0.5d, player.posZ + zOffset, 5, xOffset, 0, zOffset, 0);
			}

			return ActionResult.newResult(EnumActionResult.SUCCESS, heldStack);
		}

		return super.onItemRightClick(world, player, hand);
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
		if (world.getTotalWorldTime() % 10 == 0) {
			CapabilityBaseMiscStackSerializable cap = stack.getCapability(AdventMiscStackSerializeableProvider.MISC_STACK, null);

			if (cap != null && cap.getValue() > 0 && cap.getValue() < world.getTotalWorldTime() - 2400)
				cap.setValue(0);
		}
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		stack.damageItem(1, attacker);

		return true;
	}

	@Nullable
	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable NBTTagCompound nbt) {
		return new AdventMiscStackSerializeableProvider();
	}

	@Override
	public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot slot, ItemStack stack) {
		Multimap<String, AttributeModifier> attributeMap = super.getAttributeModifiers(slot, stack);

		if (slot == EntityEquipmentSlot.MAINHAND) {
			int buff = 0;
			CapabilityBaseMiscStackSerializable cap = stack.getCapability(AdventMiscStackSerializeableProvider.MISC_STACK, null);

			if (cap != null && cap.getValue() > 0)
				buff = 5;

			ItemUtil.setAttribute(attributeMap, SharedMonsterAttributes.ATTACK_DAMAGE, ATTACK_DAMAGE_MODIFIER, dmg + buff);
		}

		return attributeMap;
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.GuardiansSword.desc.1", Enums.ItemDescriptionType.POSITIVE));
	}
}
