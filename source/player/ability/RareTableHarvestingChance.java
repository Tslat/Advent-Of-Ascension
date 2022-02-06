package net.tslat.aoa3.player.ability;

import com.google.gson.JsonObject;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameters;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.server.ServerWorld;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.custom.AoAAbilities;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.player.skill.ExtractionSkill;
import net.tslat.aoa3.util.LootUtil;

import java.util.List;

public class RareTableHarvestingChance extends ScalableModAbility {
	private static final ListenerType[] LISTENERS = new ListenerType[] {ListenerType.LOOT_MODIFICATION};

	public RareTableHarvestingChance(AoASkill.Instance skill, JsonObject data) {
		super(AoAAbilities.RARE_TABLE_HARVESTING_CHANCE.get(), skill, data);
	}

	public RareTableHarvestingChance(AoASkill.Instance skill, CompoundNBT data) {
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

		BlockState state = context.getParamOrNull(LootParameters.BLOCK_STATE);

		if (state == null)
			return;

		Vector3d origin = context.getParamOrNull(LootParameters.ORIGIN);

		if (origin == null)
			return;

		ServerWorld world = context.getLevel();
		Block block = state.getBlock();

		if (!Block.isShapeFullBlock(state.getCollisionShape(context.getLevel(), new BlockPos(origin))))
			return;

		if (!ExtractionSkill.isApplicableBlock(block))
			return;

		PlayerEntity player = getPlayer();

		loot.addAll(LootUtil.generateLoot(world,  new ResourceLocation(AdventOfAscension.MOD_ID, "misc/lotto_totem"), LootUtil.getGiftContext(world, origin, player.getLuck(), player)));
	}
}
