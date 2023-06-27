package net.tslat.aoa3.player.ability;

import com.google.gson.JsonObject;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.custom.AoAAbilities;
import net.tslat.aoa3.common.registration.custom.AoASkills;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.player.skill.ExtractionSkill;
import net.tslat.aoa3.util.LootUtil;
import net.tslat.aoa3.util.PlayerUtil;

import java.util.List;

public class RareTableHarvestingChance extends ScalableModAbility {
	private static final ListenerType[] LISTENERS = new ListenerType[] {ListenerType.LOOT_MODIFICATION};

	public RareTableHarvestingChance(AoASkill.Instance skill, JsonObject data) {
		super(AoAAbilities.RARE_TABLE_HARVESTING_CHANCE.get(), skill, data);
	}

	public RareTableHarvestingChance(AoASkill.Instance skill, CompoundTag data) {
		super(AoAAbilities.RARE_TABLE_HARVESTING_CHANCE.get(), skill, data);
	}

	@Override
	public ListenerType[] getListenerTypes() {
		return LISTENERS;
	}

	@Override
	public void handleLootModification(List<ItemStack> loot, LootContext context) {
		if (!testAsChance())
			return;

		BlockState state = context.getParamOrNull(LootContextParams.BLOCK_STATE);

		if (state == null)
			return;

		Vec3 origin = context.getParamOrNull(LootContextParams.ORIGIN);

		if (origin == null)
			return;

		if (!Block.isShapeFullBlock(state.getCollisionShape(context.getLevel(), BlockPos.containing(origin))))
			return;

		if (!ExtractionSkill.isApplicableBlock(state))
			return;

		ServerPlayer player = getPlayer();
		ServerLevel world = context.getLevel();

		loot.addAll(LootUtil.generateLoot(new ResourceLocation(AdventOfAscension.MOD_ID, "misc/lotto_totem"), LootUtil.getGiftParameters(world, origin, player.getLuck(), player)));
		PlayerUtil.giveXpToPlayer(player, getSkill().type(), PlayerUtil.getTimeBasedXpForLevel(PlayerUtil.getLevel(player, AoASkills.FARMING.get()), 10), false);
	}
}
