package net.tslat.aoa3.content.item.misc.summoning;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Difficulty;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.AoADimensions;
import net.tslat.aoa3.common.registration.AoAItemGroups;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.PlayerUtil;
import net.tslat.aoa3.util.WorldUtil;

import javax.annotation.Nullable;
import java.util.List;

public class BoneHorn extends Item {
	public BoneHorn() {
		super(new Item.Properties().tab(AoAItemGroups.MISC_ITEMS).durability(3).rarity(Rarity.UNCOMMON));
	}

	@Override
	public UseAnim getUseAnimation(ItemStack stack) {
		return UseAnim.BOW;
	}

	@Override
	public int getUseDuration(ItemStack stack) {
		return 60;
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
		ItemStack heldItem = player.getItemInHand(hand);

		if (world.getDifficulty() != Difficulty.PEACEFUL) {
			player.playSound(AoASounds.ITEM_BONE_HORN_CALL.get(), 1.0f, 1.0f);
			player.startUsingItem(hand);
			player.getCooldowns().addCooldown(this, 150);

			return InteractionResultHolder.success(heldItem);
		}
		else if (player instanceof ServerPlayer) {
			PlayerUtil.notifyPlayer(player, new TranslatableComponent("message.feedback.spawnBoss.difficultyFail").withStyle(ChatFormatting.RED));
		}

		return InteractionResultHolder.fail(heldItem);
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity user) {
		if (!world.isClientSide && WorldUtil.isWorld(world, AoADimensions.PRECASIA.key)) {
			/*TyrosaurEntity tyrosaur = new TyrosaurEntity(AoAMobs.TYROSAUR.get(), world);
			BlockPos spawnPos = RandomUtil.getRandomPositionWithinRange(user.blockPosition(), 20, 10, 20, true, world);
			int tries = 10;

			while (spawnPos.getY() > user.getY() + 10 && tries > 0) {
				spawnPos = RandomUtil.getRandomPositionWithinRange(user.blockPosition(), 20, 10, 20, true, world);
				tries--;
			}

			if (tries == 0)
				return stack;

			tyrosaur.setPos(spawnPos.getX(), spawnPos.getY(), spawnPos.getZ());
			tyrosaur.setTarget(user);
			world.addFreshEntity(tyrosaur);
			ItemUtil.damageItem(stack, user, user.getUsedItemHand());
			PlayerUtil.messageAllPlayersInRange(LocaleUtil.getLocaleMessage(AoAMobs.TYROSAUR.get().getDescriptionId() + ".spawn", user.getDisplayName()), world, user.blockPosition(), 50);*/
		}

		return stack;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.NEUTRAL, 1));
	}
}
