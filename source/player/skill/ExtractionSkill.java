package net.tslat.aoa3.player.skill;

import com.google.gson.JsonObject;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;
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

	public ExtractionSkill(CompoundTag nbtData) {
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

		BlockState state = context.getParamOrNull(LootContextParams.BLOCK_STATE);

		if (state == null)
			return;

		Vec3 origin = context.getParamOrNull(LootContextParams.ORIGIN);

		if (origin == null)
			return;

		ServerLevel world = context.getLevel();
		BlockPos pos = BlockPos.containing(origin);
		Block block = state.getBlock();

		if (!Block.isShapeFullBlock(state.getCollisionShape(context.getLevel(), pos)))
			return;

		if (!isApplicableBlock(state))
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
		if (ev.getEntity().level.isClientSide())
			return;

		ItemStack smelting = ev.getSmelting();
		Item item = smelting.getItem();

		if (item.getFoodProperties() == null && canGainXp(true)) {
			float xp = PlayerUtil.getTimeBasedXpForLevel(getLevel(true), 40);

			if (smelting.is(Tags.Items.NUGGETS)) {
				xp *= 1.5f;
			}
			else if (smelting.is(Tags.Items.INGOTS)) {
				xp *= 2f;
			}
			else if (smelting.is(Tags.Items.GEMS)) {
				xp *= 2.5f;
			}

			adjustXp(xp, false, false);
		}
	}

	public static boolean isApplicableBlock(BlockState block) {
		return block.is(Tags.Blocks.STONE) ||
				block.is(Tags.Blocks.COBBLESTONE) ||
				block.is(Tags.Blocks.ORES) ||
				block.is(BlockTags.LOGS) ||
				block.is(Tags.Blocks.SAND) ||
				block.is(AoATags.Blocks.GRASS) ||
				block.is(BlockTags.DIRT) ||
				block.is(Tags.Blocks.GRAVEL) ||
				block.is(Tags.Blocks.NETHERRACK) ||
				block.is(Tags.Blocks.OBSIDIAN);
	}
}
