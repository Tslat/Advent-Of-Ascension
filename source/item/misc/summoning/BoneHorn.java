package net.tslat.aoa3.item.misc.summoning;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.item.UseAction;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoADimensions;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.common.registration.AoAItemGroups;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.boss.TyrosaurEntity;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.RandomUtil;
import net.tslat.aoa3.util.WorldUtil;
import net.tslat.aoa3.util.PlayerUtil;

import javax.annotation.Nullable;
import java.util.List;

public class BoneHorn extends Item {
	public BoneHorn() {
		super(new Item.Properties().tab(AoAItemGroups.MISC_ITEMS).durability(3).rarity(Rarity.UNCOMMON));
	}

	@Override
	public UseAction getUseAnimation(ItemStack stack) {
		return UseAction.BOW;
	}

	@Override
	public int getUseDuration(ItemStack stack) {
		return 60;
	}

	@Override
	public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
		ItemStack heldItem = player.getItemInHand(hand);

		if (world.getDifficulty() != Difficulty.PEACEFUL) {
			player.playSound(AoASounds.ITEM_BONE_HORN_CALL.get(), 1.0f, 1.0f);
			player.startUsingItem(hand);
			player.getCooldowns().addCooldown(this, 150);

			return ActionResult.success(heldItem);
		}
		else if (player instanceof ServerPlayerEntity) {
			PlayerUtil.notifyPlayer((ServerPlayerEntity)player, new TranslationTextComponent("message.feedback.spawnBoss.difficultyFail").withStyle(TextFormatting.RED));
		}

		return ActionResult.fail(heldItem);
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, World world, LivingEntity user) {
		if (!world.isClientSide && WorldUtil.isWorld(world, AoADimensions.PRECASIA.key)) {
			TyrosaurEntity tyrosaur = new TyrosaurEntity(AoAEntities.Mobs.TYROSAUR.get(), world);
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
			PlayerUtil.messageAllPlayersInRange(LocaleUtil.getLocaleMessage(AoAEntities.Mobs.TYROSAUR.get().getDescriptionId() + ".spawn", user.getDisplayName()), world, user.blockPosition(), 50);
		}

		return stack;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.NEUTRAL, 1));
	}
}
