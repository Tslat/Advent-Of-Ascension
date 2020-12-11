package net.tslat.aoa3.item.misc.summoning;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
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
import net.tslat.aoa3.util.player.PlayerUtil;

import javax.annotation.Nullable;
import java.util.List;

public class BoneHorn extends Item {
	public BoneHorn() {
		super(new Item.Properties().group(AoAItemGroups.MISC_ITEMS).maxDamage(3));
	}

	@Override
	public UseAction getUseAction(ItemStack stack) {
		return UseAction.BOW;
	}

	@Override
	public int getUseDuration(ItemStack stack) {
		return 60;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
		ItemStack heldItem = player.getHeldItem(hand);

		if (world.getDifficulty() != Difficulty.PEACEFUL) {
			if (!world.isRemote)
				player.playSound(AoASounds.ITEM_BONE_HORN_CALL.get(), 1.0f, 1.0f);

			player.setActiveHand(hand);
			player.getCooldownTracker().setCooldown(this, 150);

			return ActionResult.resultSuccess(heldItem);
		}
		else if (player instanceof ServerPlayerEntity) {
			PlayerUtil.notifyPlayer((ServerPlayerEntity)player, "message.feedback.spawnBoss.difficultyFail", TextFormatting.RED);
		}

		return ActionResult.resultFail(heldItem);
	}

	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World world, LivingEntity user) {
		if (!world.isRemote && world.getDimension().getType() == AoADimensions.PRECASIA.type()) {
			TyrosaurEntity tyrosaur = new TyrosaurEntity(AoAEntities.Mobs.TYROSAUR.get(), world);
			BlockPos spawnPos = RandomUtil.getRandomPositionWithinRange(user.getPosition(), 20, 10, 20, true, world);
			int tries = 10;

			while (spawnPos.getY() > user.getPosY() + 10 && tries > 0) {
				spawnPos = RandomUtil.getRandomPositionWithinRange(user.getPosition(), 20, 10, 20, true, world);
				tries--;
			}

			if (tries == 0)
				return stack;

			tyrosaur.setPosition(spawnPos.getX(), spawnPos.getY(), spawnPos.getZ());
			tyrosaur.setAttackTarget(user);
			world.addEntity(tyrosaur);
			ItemUtil.damageItem(stack, user, user.getActiveHand());
			PlayerUtil.messageAllPlayersInRange(LocaleUtil.getLocaleMessage("entity.aoa3.tyrosaur.spawn", user.getDisplayName().getFormattedText()), world, user.getPosition(), 50);
		}

		return stack;
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.NEUTRAL, 1));
	}
}
