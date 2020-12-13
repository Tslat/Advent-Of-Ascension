package net.tslat.aoa3.item.misc;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoADimensions;
import net.tslat.aoa3.common.registration.AoAItemGroups;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.player.PlayerDataManager;
import net.tslat.aoa3.util.player.PlayerUtil;

import javax.annotation.Nullable;
import java.util.List;

public class ReturnCrystal extends Item {
	public ReturnCrystal() {
		super(new Item.Properties().group(AoAItemGroups.MISC_ITEMS));
	}

	@Override
	public UseAction getUseAction(ItemStack stack) {
		return UseAction.EAT;
	}

	@Override
	public int getUseDuration(ItemStack stack) {
		return 40;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
		if (world.getDimension().getType() == AoADimensions.IMMORTALLIS.type()) {
			player.setActiveHand(hand);

			return ActionResult.resultConsume(player.getHeldItem(hand));
		}
		else {
			return ActionResult.resultPass(player.getHeldItem(hand));
		}
	}

	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World world, LivingEntity entity) {
		if (entity instanceof ServerPlayerEntity) {
			if (world.getDimension().getType() != AoADimensions.IMMORTALLIS.type()) {
				PlayerUtil.notifyPlayer((ServerPlayerEntity)entity, "message.feedback.item.returnCrystal.wrongDim");

				return stack;
			}

			PlayerDataManager plData = PlayerUtil.getAdventPlayer((ServerPlayerEntity)entity);

			if (!((ServerPlayerEntity)entity).isCreative())
				stack.shrink(1);

			plData.stats().resetAllTribute();
			ItemUtil.clearInventoryOfItems((ServerPlayerEntity)entity, new ItemStack(AoAItems.PROGRESS_TOKEN.get()), new ItemStack(AoAItems.RETURN_CRYSTAL.get()));
			entity.setPositionAndUpdate(-5, 20, 3);
		}

		return stack;
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.NEUTRAL, 1));
	}
}
