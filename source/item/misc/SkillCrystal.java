package net.tslat.aoa3.item.misc;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAItemGroups;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.constant.Skills;
import net.tslat.aoa3.util.player.PlayerDataManager;
import net.tslat.aoa3.util.player.PlayerUtil;

import javax.annotation.Nullable;
import java.util.List;

public class SkillCrystal extends Item {
	private final float denominator;
	private final int lowerLimit = 15;

	public SkillCrystal(float denominator) {
		this(denominator, Rarity.COMMON);
	}

	public SkillCrystal(float denominator, Rarity rarity) {
		super(new Item.Properties().tab(AoAItemGroups.MISC_ITEMS).rarity(rarity));

		this.denominator = denominator;
	}

	@Override
	public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
		ItemStack heldStack = player.getItemInHand(hand);

		if (player instanceof ServerPlayerEntity) {
			PlayerDataManager plData = PlayerUtil.getAdventPlayer((ServerPlayerEntity)player);
			Skills skill = PlayerUtil.getLowestSkillWithLimit(plData, lowerLimit);

			if (skill != null) {
				plData.stats().addXp(skill, PlayerUtil.getXpRequiredForNextLevel(plData.stats().getLevel(skill)) / denominator, false, true);

				if (!player.isCreative())
					heldStack.shrink(1);
			}
			else {
				plData.sendThrottledChatMessage("message.feedback.item.skillCrystal.levelFail",  Integer.toString(lowerLimit));
			}
		}

		return ActionResult.pass(heldStack);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("items.description.skillCrystal.desc.1", LocaleUtil.ItemDescriptionType.NEUTRAL));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("items.description.skillCrystal.desc.2", LocaleUtil.ItemDescriptionType.NEUTRAL, new StringTextComponent(Integer.toString(lowerLimit))));
	}
}
