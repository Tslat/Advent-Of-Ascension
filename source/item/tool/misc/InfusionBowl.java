package net.tslat.aoa3.item.tool.misc;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameterSets;
import net.minecraft.loot.LootParameters;
import net.minecraft.loot.LootTable;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.tslat.aoa3.common.registration.AoADimensions;
import net.tslat.aoa3.common.registration.AoAItemGroups;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.misc.pixon.PixonEntity;
import net.tslat.aoa3.item.armour.AdventArmour;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.RandomUtil;
import net.tslat.aoa3.util.WorldUtil;
import net.tslat.aoa3.util.constant.Deities;
import net.tslat.aoa3.util.player.PlayerDataManager;
import net.tslat.aoa3.util.player.PlayerUtil;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class InfusionBowl extends Item {
	private final int harvestAmount;
	private final int harvestLevelModifier;

	public InfusionBowl(int durability, int harvestAmount, int harvestLevelModifier) {
		super(new Item.Properties().tab(AoAItemGroups.TOOLS).durability(durability));

		this.harvestAmount = harvestAmount;
		this.harvestLevelModifier = harvestLevelModifier;
	}

	@Override
	public boolean isEnchantable(ItemStack stack) {
		return false;
	}

	public int getHarvestAmount() {
		return harvestAmount;
	}

	public int getHarvestReqModifier() {
		return harvestLevelModifier;
	}

	@Override
	public ActionResultType interactLivingEntity(ItemStack stack, PlayerEntity player, LivingEntity target, Hand hand) {
		if (!(target instanceof PixonEntity))
			return ActionResultType.FAIL;

		if (player instanceof ServerPlayerEntity) {
			PixonEntity pixon = (PixonEntity)target;

			if (!pixon.canHarvest((ServerPlayerEntity)player, stack))
				return ActionResultType.PASS;

			LootTable harvestTable = player.getServer().getLootTables().get(pixon.getLootTable());

			int harvestCount = 0;
			List<ItemStack> harvestStacks = new ArrayList<ItemStack>();
			LootContext lootContext = (new LootContext.Builder((ServerWorld)player.level).withParameter(LootParameters.KILLER_ENTITY, player).withParameter(LootParameters.THIS_ENTITY, pixon).withParameter(LootParameters.ORIGIN, pixon.position())).withParameter(LootParameters.DAMAGE_SOURCE, DamageSource.GENERIC).create(LootParameterSets.ENTITY);
			PlayerDataManager plData = PlayerUtil.getAdventPlayer((ServerPlayerEntity)player);

			while (harvestCount < getHarvestAmount() && pixon.getHealth() > 0) {
				if (!player.isCreative())
					ItemUtil.damageItem(stack, player, 1, hand == Hand.MAIN_HAND ? EquipmentSlotType.MAINHAND : EquipmentSlotType.OFFHAND);

				harvestStacks.addAll(harvestTable.getRandomItems(lootContext));

				if (plData.equipment().getCurrentFullArmourSet() == AdventArmour.Type.INFUSION)
					harvestStacks.addAll(harvestTable.getRandomItems(lootContext));

				pixon.setHealth(pixon.getHealth() - 7 + RandomUtil.randomNumberUpTo(6));

				harvestCount++;
			}

			if (!harvestStacks.isEmpty())
				ItemUtil.givePlayerMultipleItems(player, harvestStacks);

			if (WorldUtil.isWorld(pixon.level, AoADimensions.OVERWORLD.key) && pixon.level.isDay())
				plData.stats().addTribute(Deities.LUXON, 4 * harvestCount);

			if (pixon.isAlive()) {
				pixon.setLastHurtByMob(player);
				pixon.nextHarvestTick = pixon.level.getGameTime() + 8 + pixon.getRandom().nextInt(32);
			}

			player.awardStat(Stats.ITEM_USED.get(stack.getItem()));
			player.level.playSound(null, pixon.blockPosition().getX(), pixon.blockPosition().getY(), pixon.blockPosition().getZ(), AoASounds.ENTITY_PIXON_HARVEST.get(), SoundCategory.MASTER, 1.0f, 1.0f);
		}

		return ActionResultType.SUCCESS;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("items.description.infusionBowl.desc.1", LocaleUtil.ItemDescriptionType.NEUTRAL));
	}
}
