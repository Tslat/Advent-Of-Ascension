package net.tslat.aoa3.object.item.misc.summoning;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.common.registration.AoAItemGroups;
import net.tslat.aoa3.object.entity.misc.GyrocopterEntity;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.PlayerUtil;

import javax.annotation.Nullable;
import java.util.List;

public class ToyGyrocopter extends Item {
	public ToyGyrocopter() {
		super(new Item.Properties().tab(AoAItemGroups.MISC_ITEMS).stacksTo(1).rarity(Rarity.UNCOMMON));
	}

	@Override
	public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
		ItemStack heldItem = player.getItemInHand(hand);

		if (world.getDifficulty() != Difficulty.PEACEFUL) {
			if (!world.isClientSide) {
				world.addFreshEntity(new GyrocopterEntity(player));
				PlayerUtil.messageAllPlayersInRange(LocaleUtil.getLocaleMessage(AoAEntities.Mobs.GYRO.get().getDescriptionId() + ".spawn", player.getDisplayName()), world, player.blockPosition(), 50);

				if (!player.isCreative())
					player.getItemInHand(hand).shrink(1);
			}

			return ActionResult.success(heldItem);
		}
		else if (player instanceof ServerPlayerEntity) {
			PlayerUtil.notifyPlayer((ServerPlayerEntity)player, new TranslationTextComponent("message.feedback.spawnBoss.difficultyFail").withStyle(TextFormatting.RED));
		}

		return ActionResult.fail(heldItem);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.NEUTRAL, 1));
	}
}
