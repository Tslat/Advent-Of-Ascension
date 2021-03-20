package net.tslat.aoa3.item.tool.misc;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.tslat.aoa3.capabilities.persistentstack.PersistentStackCapabilityHandles;
import net.tslat.aoa3.capabilities.persistentstack.PersistentStackCapabilityProvider;
import net.tslat.aoa3.common.registration.AoAItemGroups;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.NumberUtil;
import net.tslat.aoa3.util.player.PlayerUtil;

import javax.annotation.Nullable;
import java.util.List;

public class ExpFlask extends Item {
	public ExpFlask() {
		super(new Item.Properties().tab(AoAItemGroups.TOOLS).stacksTo(1));
	}

	@Override
	public boolean isEnchantable(ItemStack stack) {
		return false;
	}

	@Override
	public UseAction getUseAnimation(ItemStack stack) {
		return UseAction.DRINK;
	}

	@Override
	public int getUseDuration(ItemStack stack) {
		return 100;
	}

	@Override
	public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
		ItemStack stack = player.getItemInHand(hand);

		if (!world.isClientSide) {
			PersistentStackCapabilityHandles cap = PersistentStackCapabilityProvider.getOrDefault(stack, null);

			if (cap.getValue() <= 0)
				return ActionResult.fail(stack);

			player.startUsingItem(hand);
		}

		return ActionResult.pass(stack);
	}

	@Override
	public void onUsingTick(ItemStack stack, LivingEntity entity, int count) {
		if (entity instanceof ServerPlayerEntity) {
			ServerPlayerEntity player = (ServerPlayerEntity)entity;
			PersistentStackCapabilityHandles cap = PersistentStackCapabilityProvider.getOrDefault(stack, null);

			if (cap.getValue() > 0) {
				int xpChange = (int)Math.min(1 + ((int)(player.experienceLevel / 15f)), cap.getValue());

				player.giveExperiencePoints(xpChange);
				cap.setValue(cap.getValue() - xpChange);

				if (cap.getValue() == 0)
					player.stopUsingItem();
			}
		}
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

	@Nullable
	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundNBT nbt) {
		return new PersistentStackCapabilityProvider(null);
	}

	public static void addExp(ItemStack stack, int xp) {
		PersistentStackCapabilityHandles cap = PersistentStackCapabilityProvider.getOrDefault(stack, null);

		cap.setValue(cap.getValue() + xp);
	}

	public static void setExp(ItemStack stack, int xp) {
		PersistentStackCapabilityProvider.getOrDefault(stack, null).setValue(xp);
	}

	public static boolean consumeExp(ItemStack stack, int xp) {
		PersistentStackCapabilityHandles cap = PersistentStackCapabilityProvider.getOrDefault(stack, null);

		if (cap.getValue() >= xp) {
			cap.setValue(cap.getValue() - xp);

			return true;
		}

		return false;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		int storedValue = (int)PersistentStackCapabilityProvider.getOrDefault(stack, null).getValue();

		if (storedValue > 0)
			tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.SPECIAL, 1, new StringTextComponent(NumberUtil.floorAndAppendSuffix(storedValue, true) + (storedValue >= 7 ? " (" + PlayerUtil.getPlayerLevelFromExp(storedValue) + ")" : ""))));

		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.NEUTRAL, 2));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.NEUTRAL, 3));
	}
}
