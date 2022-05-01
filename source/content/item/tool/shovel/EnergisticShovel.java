package net.tslat.aoa3.content.item.tool.shovel;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.tslat.aoa3.common.registration.AoATiers;
import net.tslat.aoa3.common.registration.custom.AoAResources;
import net.tslat.aoa3.content.capability.persistentstack.PersistentStackCapabilityHandles;
import net.tslat.aoa3.content.capability.persistentstack.PersistentStackCapabilityProvider;
import net.tslat.aoa3.library.constant.AttackSpeed;
import net.tslat.aoa3.player.resource.AoAResource;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.PlayerUtil;

import javax.annotation.Nullable;
import java.util.List;

public class EnergisticShovel extends BaseShovel {
	public EnergisticShovel() {
		super(AoATiers.ENERGISTIC, -3.5f, AttackSpeed.SHOVEL);
	}

	@Override
	public float getDestroySpeed(ItemStack stack, BlockState state) {
		float speed = super.getDestroySpeed(stack, state);

		if (speed != 1.0f && hasEnergy(stack))
			return speed * 3f;

		return speed;
	}

	@Override
	public boolean mineBlock(ItemStack stack, Level world, BlockState state, BlockPos pos, LivingEntity entity) {
		PersistentStackCapabilityHandles cap = getCapability(stack);

		if (super.getDestroySpeed(stack, state) != 1.0f)
			cap.setValue(Math.max(0, cap.getValue() - 5));

		return super.mineBlock(stack, world, state, pos, entity);
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
		if (!world.isClientSide) {
			PersistentStackCapabilityHandles cap = getCapability(player.getItemInHand(hand));
			AoAResource.Instance spirit = PlayerUtil.getAdventPlayer((ServerPlayer)player).getResource(AoAResources.SPIRIT.get());
			float storeAmount = Mth.clamp(2000f - cap.getValue(), 0, Math.min(20, spirit.getCurrentValue()));

			cap.setValue(cap.getValue() + storeAmount);
			spirit.consume(storeAmount, true);

			return InteractionResultHolder.success(player.getItemInHand(hand));
		}

		return super.use(world, player, hand);
	}

	private boolean hasEnergy(ItemStack stack) {
		return getCapability(stack).getValue() >= 5;
	}

	@Nullable
	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
		return new PersistentStackCapabilityProvider(null);
	}

	@Nullable
	@Override
	public CompoundTag getShareTag(ItemStack stack) {
		CompoundTag tag = super.getShareTag(stack);

		if (tag == null)
			tag = new CompoundTag();

		tag.putFloat("AoAEnergyStored", getCapability(stack).getValue());

		return tag;
	}

	@Override
	public void readShareTag(ItemStack stack, @Nullable CompoundTag nbt) {
		if (nbt != null && nbt.contains("AoAEnergyStored"))
			getCapability(stack).setValue(Math.min(2000, nbt.getFloat("AoAEnergyStored")));

		super.readShareTag(stack, nbt);
	}

	public static PersistentStackCapabilityHandles getCapability(ItemStack stack) {
		return PersistentStackCapabilityProvider.getOrDefault(stack, null);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("items.description.tool.energisticCharge", LocaleUtil.ItemDescriptionType.NEUTRAL));

		PersistentStackCapabilityHandles cap = PersistentStackCapabilityProvider.getOrDefault(stack, null);

		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("items.description.tool.energisticStorage", LocaleUtil.ItemDescriptionType.NEUTRAL, new TextComponent( Integer.toString((int)cap.getValue()))));
	}
}
