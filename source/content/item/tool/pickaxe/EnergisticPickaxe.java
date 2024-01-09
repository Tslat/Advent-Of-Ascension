package net.tslat.aoa3.content.item.tool.pickaxe;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
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
import net.tslat.aoa3.common.registration.custom.AoAResources;
import net.tslat.aoa3.common.registration.item.AoATiers;
import net.tslat.aoa3.content.item.ChargeableItem;
import net.tslat.aoa3.library.constant.AttackSpeed;
import net.tslat.aoa3.player.resource.AoAResource;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.PlayerUtil;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class EnergisticPickaxe extends BasePickaxe implements ChargeableItem {
	public EnergisticPickaxe() {
		super(AoATiers.ENERGISTIC, -2, AttackSpeed.PICKAXE);
	}

	@Override
	public float minRequiredCharge() {
		return 5;
	}

	@Override
	public float maxCharge() {
		return 2000f;
	}

	@Override
	public float getDestroySpeed(ItemStack stack, BlockState state) {
		float speed = super.getDestroySpeed(stack, state);

		if (speed != 1.0f && hasEnoughCharge(stack))
			return speed * 3f;

		return speed;
	}

	@Override
	public boolean mineBlock(ItemStack stack, Level world, BlockState state, BlockPos pos, LivingEntity entity) {
		if (super.getDestroySpeed(stack, state) != 1.0f)
			subtractCharge(stack, 5, true);

		return super.mineBlock(stack, world, state, pos, entity);
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
		if (player instanceof ServerPlayer pl) {
			ItemStack stack = player.getItemInHand(hand);
			AoAResource.Instance spirit = PlayerUtil.getAdventPlayer(pl).getResource(AoAResources.SPIRIT.get());
			float storeAmount = Mth.clamp(2000f - getCharge(stack), 0, Math.min(20, spirit.getCurrentValue()));

			addCharge(stack, storeAmount);
			spirit.consume(storeAmount, true);

			return InteractionResultHolder.success(stack);
		}

		return super.use(world, player, hand);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Keys.ENERGISTIC_TOOL_CHARGE, LocaleUtil.ItemDescriptionType.NEUTRAL));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Keys.ENERGISTIC_TOOL_STORED, LocaleUtil.ItemDescriptionType.NEUTRAL,  Component.literal(Integer.toString((int)getCharge(stack)))));
	}
}
