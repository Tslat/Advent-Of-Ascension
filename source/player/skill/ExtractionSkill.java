package net.tslat.aoa3.player.skill;

import com.google.gson.JsonObject;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameters;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.tslat.aoa3.common.registration.AoATags;
import net.tslat.aoa3.common.registration.custom.AoASkills;
import net.tslat.aoa3.player.ServerPlayerDataManager;
import net.tslat.aoa3.util.PlayerUtil;

import java.util.List;

public class ExtractionSkill extends AoASkill.Instance {
	private static final ListenerType[] LISTENERS = new ListenerType[] {ListenerType.LOOT_MODIFICATION, ListenerType.ITEM_SMELTED};

	public ExtractionSkill(ServerPlayerDataManager plData, JsonObject jsonData) {
		super(AoASkills.EXTRACTION.get(), plData, jsonData);
	}

	public ExtractionSkill(CompoundNBT nbtData) {
		super(AoASkills.EXTRACTION.get(), nbtData);
	}

	@Override
	public ListenerType[] getListenerTypes() {
		return LISTENERS;
	}

	@Override
	public void handleLootModification(List<ItemStack> loot, LootContext context) {
		if (!canGainXp(true))
			return;

		BlockState state = context.getParamOrNull(LootParameters.BLOCK_STATE);

		if (state == null)
			return;

		Vector3d origin = context.getParamOrNull(LootParameters.ORIGIN);

		if (origin == null)
			return;

		ServerWorld world = context.getLevel();
		BlockPos pos = new BlockPos(origin);
		Block block = state.getBlock();

		if (!Block.isShapeFullBlock(state.getCollisionShape(context.getLevel(), pos)))
			return;

		if (!isApplicableBlock(block))
			return;

		float hardness = state.getDestroySpeed(world, pos);
		float xp = PlayerUtil.getTimeBasedXpForLevel(getLevel(true), 2 * hardness);

		for (ItemStack item : loot) {
			if (item.getItem() != block.asItem()) {
				xp *= 2f;

				break;
			}
		}

		if (loot.size() > 2)
			xp *= 1.5f;

		adjustXp(xp, false, false);
	}

	@Override
	public void handleItemSmelted(PlayerEvent.ItemSmeltedEvent ev) {
		if (ev.getPlayer().level.isClientSide())
			return;

		Item item = ev.getSmelting().getItem();

		if (item.getFoodProperties() == null && canGainXp(true)) {
			float xp = PlayerUtil.getTimeBasedXpForLevel(getLevel(true), 40);

			if (item.is(Tags.Items.NUGGETS)) {
				xp *= 1.5f;
			}
			else if (item.is(Tags.Items.INGOTS)) {
				xp *= 2f;
			}
			else if (item.is(Tags.Items.GEMS)) {
				xp *= 2.5f;
			}

			adjustXp(xp, false, false);
		}
	}

	public static boolean isApplicableBlock(Block block) {
		return block.is(Tags.Blocks.STONE) ||
				block.is(Tags.Blocks.COBBLESTONE) ||
				block.is(Tags.Blocks.ORES) ||
				block.is(BlockTags.LOGS) ||
				block.is(Tags.Blocks.SAND) ||
				block.is(AoATags.Blocks.GRASS) ||
				block.is(Tags.Blocks.DIRT) ||
				block.is(Tags.Blocks.GRAVEL) ||
				block.is(Tags.Blocks.NETHERRACK) ||
				block.is(Tags.Blocks.OBSIDIAN);
	}
}
