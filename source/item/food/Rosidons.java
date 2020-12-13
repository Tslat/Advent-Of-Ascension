package net.tslat.aoa3.item.food;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoADimensions;
import net.tslat.aoa3.common.registration.AoAItemGroups;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.WorldUtil;
import net.tslat.aoa3.util.player.PlayerDataManager;
import net.tslat.aoa3.util.player.PlayerUtil;

import javax.annotation.Nullable;
import java.util.List;

public class Rosidons extends Item {
	public Rosidons() {
		super(new Item.Properties().group(AoAItemGroups.FOOD).food(new Food.Builder().hunger(0).saturation(0).setAlwaysEdible().build()));
	}

	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World world, LivingEntity entity) {
		if (!world.isRemote) {
			PlayerDataManager plData = entity instanceof ServerPlayerEntity ? PlayerUtil.getAdventPlayer((ServerPlayerEntity)entity) : null;

			if (entity.world.dimension.getType() == AoADimensions.ANCIENT_CAVERN.type() || entity.world.dimension.getType() == AoADimensions.IMMORTALLIS.type()) {
				if (plData != null)
					plData.sendThrottledChatMessage("message.feedback.item.rosidons.dimFail");

				return super.onItemUseFinish(stack, world, entity);
			}

			int calculatedY = WorldUtil.getTrueWorldHeight(world, (int)entity.getPosX(), (int)entity.getPosZ());

			if (calculatedY == 0) {
				if (plData != null)
					plData.sendThrottledChatMessage("message.feedback.item.rosidons.noHeightFail");

				return super.onItemUseFinish(stack, world, entity);
			}

			entity.setPositionAndUpdate(entity.getPosX(), calculatedY + 2, entity.getPosZ());
		}

		return super.onItemUseFinish(stack, world, entity);
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.NEUTRAL, 1));
	}
}
