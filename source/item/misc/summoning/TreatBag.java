package net.tslat.aoa3.item.misc.summoning;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoADimensions;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.common.registration.AoAItemGroups;
import net.tslat.aoa3.entity.boss.CottonCandorEntity;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.WorldUtil;
import net.tslat.aoa3.util.player.PlayerUtil;

import javax.annotation.Nullable;
import java.util.List;

public class TreatBag extends Item {
	public TreatBag() {
		super(new Item.Properties().tab(AoAItemGroups.MISC_ITEMS).food(new Food.Builder().alwaysEat().build()));
	}

	@Override
	public int getUseDuration(ItemStack stack) {
		return 120;
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, World world, LivingEntity eater) {
		if (eater instanceof ServerPlayerEntity) {
			ServerPlayerEntity pl = (ServerPlayerEntity)eater;

			if (world.getDifficulty() == Difficulty.PEACEFUL) {
				PlayerUtil.notifyPlayer(pl, "message.feedback.spawnBoss.difficultyFail", TextFormatting.RED);
			}
			else {
				if (!WorldUtil.isWorld(world, AoADimensions.CANDYLAND.key)) {
					PlayerUtil.notifyPlayer(pl, AoAEntities.Mobs.COTTON_CANDOR.get().getDescriptionId() + ".wrongDimension", TextFormatting.RED);
				}
				else {
					CottonCandorEntity cottonCandor = new CottonCandorEntity(AoAEntities.Mobs.COTTON_CANDOR.get(), world);

					cottonCandor.setPos(eater.getX(), eater.getY() + 15, eater.getZ());
					world.addFreshEntity(cottonCandor);

					if (!((ServerPlayerEntity)eater).isCreative())
						stack.shrink(1);

					PlayerUtil.messageAllPlayersInRange(LocaleUtil.getLocaleMessage(AoAEntities.Mobs.COTTON_CANDOR.get().getDescriptionId() + ".spawn", pl.getDisplayName()), eater.level, eater.blockPosition(), 50);
				}
			}
		}

		return stack;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.NEUTRAL, 1));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.NEUTRAL, 2));
	}
}
