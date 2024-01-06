package net.tslat.aoa3.content.item.tool.misc;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.misc.pixon.PixonEntity;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.smartbrainlib.util.RandomUtil;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class InfusionBowl extends Item {
	private final int harvestAmount;
	private final int harvestLevelModifier;

	public InfusionBowl(int durability, int harvestAmount, int harvestLevelModifier) {
		super(new Item.Properties().durability(durability));

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
	public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity target, InteractionHand hand) {
		if (!(target instanceof PixonEntity))
			return InteractionResult.FAIL;

		if (player instanceof ServerPlayer serverPlayer) {
			PixonEntity pixon = (PixonEntity)target;

			if (!pixon.canHarvest(serverPlayer, stack))
				return InteractionResult.PASS;

			LootTable harvestTable = serverPlayer.getServer().getLootData().getLootTable(pixon.getLootTable());

			int harvestCount = 0;
			List<ItemStack> harvestStacks = new ArrayList<>();
			LootParams lootParams = new LootParams.Builder(serverPlayer.serverLevel()).withParameter(LootContextParams.KILLER_ENTITY, serverPlayer).withParameter(LootContextParams.THIS_ENTITY, pixon).withParameter(LootContextParams.ORIGIN, pixon.position()).withParameter(LootContextParams.DAMAGE_SOURCE, pixon.level().damageSources().generic()).create(LootContextParamSets.ENTITY);

			while (harvestCount < getHarvestAmount() && pixon.getHealth() > 0) {
				if (!serverPlayer.isCreative())
					ItemUtil.damageItem(stack, serverPlayer, 1, hand == InteractionHand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND);

				harvestStacks.addAll(harvestTable.getRandomItems(lootParams));

				pixon.setHealth(pixon.getHealth() - 7 + RandomUtil.randomNumberUpTo(6));
				pixon.setLastHurtByPlayer(serverPlayer);

				harvestCount++;
			}

			if (!harvestStacks.isEmpty())
				ItemUtil.givePlayerMultipleItems(serverPlayer, harvestStacks);

			if (pixon.isAlive()) {
				pixon.nextHarvestTick = pixon.level().getGameTime() + 8 + pixon.getRandom().nextInt(32);
			}
			else {
				serverPlayer.awardStat(Stats.ENTITY_KILLED.get(pixon.getType()));
			}

			serverPlayer.awardStat(Stats.ITEM_USED.get(stack.getItem()));
			serverPlayer.level().playSound(null, pixon.blockPosition().getX(), pixon.blockPosition().getY(), pixon.blockPosition().getZ(), AoASounds.ENTITY_PIXON_HARVEST.get(), SoundSource.MASTER, 1.0f, 1.0f);
		}

		return InteractionResult.SUCCESS;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Keys.INFUSION_BOWL_DESCRIPTION, LocaleUtil.ItemDescriptionType.NEUTRAL));
	}
}
