package net.tslat.aoa3.content.item.misc.summoning;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.AoAItemGroups;
import net.tslat.aoa3.util.LocaleUtil;

import javax.annotation.Nullable;
import java.util.List;

public class TreatBag extends Item {
	public TreatBag() {
		super(new Item.Properties().tab(AoAItemGroups.MISC_ITEMS).rarity(Rarity.UNCOMMON).food(new FoodProperties.Builder().alwaysEat().build()));
	}

	@Override
	public int getUseDuration(ItemStack stack) {
		return 120;
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity eater) {
		if (eater instanceof ServerPlayer player) {

			/*if (world.getDifficulty() == Difficulty.PEACEFUL) {
				PlayerUtil.notifyPlayer(player, Component.translatable("message.feedback.spawnBoss.difficultyFail").withStyle(ChatFormatting.RED));
			}
			else {
				if (!WorldUtil.isWorld(world, AoADimensions.CANDYLAND.key)) {
					PlayerUtil.notifyPlayer(player, Component.translatable(AoAMobs.COTTON_CANDOR.get().getDescriptionId() + ".wrongDimension").withStyle(ChatFormatting.RED));
				}
				else {
					CottonCandorEntity cottonCandor = new CottonCandorEntity(AoAMobs.COTTON_CANDOR.get(), world);

					cottonCandor.setPos(eater.getX(), eater.getY() + 15, eater.getZ());
					world.addFreshEntity(cottonCandor);

					if (!((ServerPlayer)eater).isCreative())
						stack.shrink(1);

					PlayerUtil.messageAllPlayersInRange(LocaleUtil.getLocaleMessage(AoAMobs.COTTON_CANDOR.get().getDescriptionId() + ".spawn", player.getDisplayName()), eater.level, eater.blockPosition(), 50);
				}
			}*/
		}

		return stack;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.NEUTRAL, 1));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.NEUTRAL, 2));
	}
}
