package net.tslat.aoa3.item.minionslab;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAItemGroups;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.config.AoAConfig;
import net.tslat.aoa3.entity.minion.AoAMinion;
import net.tslat.aoa3.item.armour.AdventArmour;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.PotionUtil;
import net.tslat.aoa3.util.RandomUtil;
import net.tslat.aoa3.util.constant.Resources;
import net.tslat.aoa3.util.constant.Skills;
import net.tslat.aoa3.util.player.PlayerDataManager;
import net.tslat.aoa3.util.player.PlayerUtil;

import javax.annotation.Nullable;
import java.util.List;

public abstract class BaseSlab extends Item {
	public final int lvl;
	public final float cost;
	public final int sacrificeLvl;
	public final float sacrificeXp;

	public BaseSlab(int lvl, float creationCost, int sacrificeLvl, float sacrificeXp) {
		super(new Item.Properties().group(AoAItemGroups.MINION_SLABS));

		this.lvl = lvl;
		this.cost = creationCost;
		this.sacrificeLvl = sacrificeLvl;
		this.sacrificeXp = sacrificeXp;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
		ItemStack stack = player.getHeldItem(hand);

		if (player instanceof ServerPlayerEntity) {
			ServerPlayerEntity pl = (ServerPlayerEntity)player;
			PlayerDataManager plData = PlayerUtil.getAdventPlayer(pl);

			if (!player.isCreative() && !PlayerUtil.doesPlayerHaveLevel(pl, Skills.CREATION, lvl)) {
				PlayerUtil.notifyPlayerOfInsufficientLevel(pl, Skills.CREATION, lvl);

				return ActionResult.resultFail(stack);
			}

			if (world.getEntitiesWithinAABB(AoAMinion.class, player.getBoundingBox().grow(30), minion -> minion != null && player.getUniqueID().equals(minion.getOwnerId())).size() >= AoAConfig.SERVER.maxMinions.get()) {
				player.sendMessage(LocaleUtil.getLocaleMessage("message.feedback.minionSlab.maxMinions", String.valueOf(AoAConfig.SERVER.maxMinions.get())));

				return ActionResult.resultFail(stack);
			}

			if (player.isCreative() || plData.stats().consumeResource(Resources.CREATION, cost, false)) {
				if (!player.isCreative())
					stack.shrink(1);

				AoAMinion minion = activateSlab(player, stack);

				if (minion != null && plData.equipment().getCurrentFullArmourSet() == AdventArmour.Type.CREATION)
					applyBuffs(minion);

				player.playSound(AoASounds.ITEM_CREATION_SLAB_USE.get(), 1.0f, 1.0f);
				return ActionResult.resultSuccess(stack);
			}
		}

		return ActionResult.resultPass(stack);
	}

	public abstract AoAMinion activateSlab(PlayerEntity pl, ItemStack stack);

	protected void applyBuffs(AoAMinion minion) {
		EntityUtil.applyPotions(minion, RandomUtil.getRandomSelection(
				new PotionUtil.EffectBuilder(Effects.SPEED, 1200).level(2).hideParticles().isAmbient(),
				new PotionUtil.EffectBuilder(Effects.STRENGTH, 1200).level(2).hideParticles().isAmbient(),
				new PotionUtil.EffectBuilder(Effects.REGENERATION, 1200).level(2).hideParticles().isAmbient(),
				new PotionUtil.EffectBuilder(Effects.RESISTANCE, 1200).level(2).hideParticles().isAmbient()));
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("items.description.slab.cost", LocaleUtil.ItemDescriptionType.ITEM_AMMO_COST, Integer.toString((int)cost)));
		tooltip.add(LocaleUtil.getFormattedLevelRestrictedDescriptionText(Skills.CREATION, lvl));
	}
}
