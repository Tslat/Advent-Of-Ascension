package net.tslat.aoa3.content.item.misc;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.AoAItemGroups;
import net.tslat.aoa3.player.ServerPlayerDataManager;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.PlayerUtil;

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
	public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
		ItemStack heldStack = player.getItemInHand(hand);

		if (player instanceof ServerPlayer) {
			ServerPlayerDataManager plData = PlayerUtil.getAdventPlayer((ServerPlayer)player);
			AoASkill skill = PlayerUtil.getLowestSkillWithLimit(plData, lowerLimit);

			if (skill != null) {
				plData.getSkill(skill).adjustXp(PlayerUtil.getXpForFractionOfLevel(plData.getSkill(skill).getLevel(true), 1 / denominator), false, true);

				if (!player.isCreative())
					heldStack.shrink(1);
			}
			else {
				PlayerUtil.notifyPlayer(plData.player(), new TranslatableComponent("message.feedback.item.skillCrystal.levelFail", Integer.toString(lowerLimit)));
			}
		}

		return InteractionResultHolder.pass(heldStack);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("items.description.skillCrystal.desc.1", LocaleUtil.ItemDescriptionType.NEUTRAL));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("items.description.skillCrystal.desc.2", LocaleUtil.ItemDescriptionType.NEUTRAL, new TextComponent(Integer.toString(lowerLimit))));
	}
}
