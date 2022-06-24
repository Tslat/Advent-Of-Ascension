package net.tslat.aoa3.content.item.tool.misc;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.tslat.aoa3.common.registration.AoACreativeModeTabs;
import net.tslat.aoa3.content.capability.persistentstack.PersistentStackCapabilityHandles;
import net.tslat.aoa3.content.capability.persistentstack.PersistentStackCapabilityProvider;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.NumberUtil;
import net.tslat.aoa3.util.PlayerUtil;

import javax.annotation.Nullable;
import java.util.List;

public class ExpFlask extends Item {
	public ExpFlask() {
		super(new Item.Properties().tab(AoACreativeModeTabs.TOOLS).stacksTo(1));
	}

	@Override
	public boolean isEnchantable(ItemStack stack) {
		return false;
	}

	@Override
	public UseAnim getUseAnimation(ItemStack stack) {
		return UseAnim.DRINK;
	}

	@Override
	public int getUseDuration(ItemStack stack) {
		return 100;
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
		ItemStack stack = player.getItemInHand(hand);

		if (!world.isClientSide) {
			PersistentStackCapabilityHandles cap = PersistentStackCapabilityProvider.getOrDefault(stack, null);

			if (cap.getValue() <= 0)
				return InteractionResultHolder.fail(stack);

			player.startUsingItem(hand);
		}

		return InteractionResultHolder.pass(stack);
	}

	@Override
	public void onUsingTick(ItemStack stack, LivingEntity entity, int count) {
		if (entity instanceof ServerPlayer player) {
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

	@Nullable
	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
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
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		int storedValue = (int)PersistentStackCapabilityProvider.getOrDefault(stack, null).getValue();

		if (storedValue > 0)
			tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.SPECIAL, 1, Component.literal(NumberUtil.floorAndAppendSuffix(storedValue, true) + (storedValue >= 7 ? " (" + PlayerUtil.getPlayerLevelFromExp(storedValue) + ")" : ""))));

		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.NEUTRAL, 2));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.NEUTRAL, 3));
	}
}
