package net.tslat.aoa3.object.item.misc;

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
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoADimensions;
import net.tslat.aoa3.common.registration.AoAItemGroups;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.WorldUtil;
import net.tslat.aoa3.player.ServerPlayerDataManager;
import net.tslat.aoa3.util.PlayerUtil;

import javax.annotation.Nullable;
import java.util.List;

public class ReturnCrystal extends Item {
	public ReturnCrystal() {
		super(new Item.Properties().tab(AoAItemGroups.MISC_ITEMS));
	}

	@Override
	public UseAction getUseAnimation(ItemStack stack) {
		return UseAction.EAT;
	}

	@Override
	public int getUseDuration(ItemStack stack) {
		return 40;
	}

	@Override
	public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
		if (WorldUtil.isWorld(world, AoADimensions.NOWHERE.key)) {
			player.startUsingItem(hand);

			return ActionResult.consume(player.getItemInHand(hand));
		}
		else {
			return ActionResult.pass(player.getItemInHand(hand));
		}
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, World world, LivingEntity entity) {
		if (entity instanceof ServerPlayerEntity) {
			if (!WorldUtil.isWorld(world, AoADimensions.NOWHERE.key)) {
				PlayerUtil.notifyPlayer((ServerPlayerEntity)entity, new TranslationTextComponent("message.feedback.item.returnCrystal.wrongDim"));

				return stack;
			}

			ServerPlayerDataManager plData = PlayerUtil.getAdventPlayer((ServerPlayerEntity)entity);

			if (!((ServerPlayerEntity)entity).isCreative())
				stack.shrink(1);

			ItemUtil.clearInventoryOfItems((ServerPlayerEntity)entity, new ItemStack(AoAItems.PROGRESS_TOKEN.get()), new ItemStack(AoAItems.RETURN_CRYSTAL.get()));
			entity.teleportTo(0, 212, 0);
		}

		return stack;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.NEUTRAL, 1));
	}
}
