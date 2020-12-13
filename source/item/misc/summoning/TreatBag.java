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
import net.tslat.aoa3.util.player.PlayerUtil;

import javax.annotation.Nullable;
import java.util.List;

public class TreatBag extends Item {
	public TreatBag() {
		super(new Item.Properties().group(AoAItemGroups.MISC_ITEMS).food(new Food.Builder().setAlwaysEdible().build()));
	}

	@Override
	public int getUseDuration(ItemStack stack) {
		return 120;
	}

	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World world, LivingEntity eater) {
		if (eater instanceof ServerPlayerEntity) {
			ServerPlayerEntity pl = (ServerPlayerEntity)eater;

			if (world.getDifficulty() == Difficulty.PEACEFUL) {
				PlayerUtil.notifyPlayer(pl, "message.feedback.spawnBoss.difficultyFail", TextFormatting.RED);
			}
			else {
				if (world.getDimension().getType() != AoADimensions.CANDYLAND.type()) {
					PlayerUtil.notifyPlayer(pl, "entity.aoa3.cotton_candor.wrongDimension", TextFormatting.RED);
				}
				else {
					CottonCandorEntity cottonCandor = new CottonCandorEntity(AoAEntities.Mobs.COTTON_CANDOR.get(), world);

					cottonCandor.setPosition(eater.getPosX(), eater.getPosY() + 15, eater.getPosZ());
					world.addEntity(cottonCandor);

					if (!((ServerPlayerEntity)eater).isCreative())
						stack.shrink(1);

					PlayerUtil.messageAllPlayersInRange(LocaleUtil.getLocaleMessage("message.mob.cottonCandor.spawn", pl.getDisplayName().getFormattedText()), eater.world, eater.getPosition(), 50);
				}
			}
		}

		return stack;
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.NEUTRAL, 1));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.NEUTRAL, 2));
	}
}
