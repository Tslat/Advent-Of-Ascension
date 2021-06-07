package net.tslat.aoa3.loottable.modifier;

import com.google.gson.JsonObject;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.loot.*;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.item.armour.AdventArmour;
import net.tslat.aoa3.util.RandomUtil;
import net.tslat.aoa3.util.WorldUtil;
import net.tslat.aoa3.util.constant.Deities;
import net.tslat.aoa3.util.constant.Skills;
import net.tslat.aoa3.util.player.PlayerDataManager;
import net.tslat.aoa3.util.player.PlayerUtil;
import net.tslat.aoa3.util.skill.ForagingUtil;

import javax.annotation.Nonnull;
import java.util.List;

public class ForagingLootModifier extends LootModifier {
	private static final ResourceLocation FORAGING_TABLE = new ResourceLocation(AdventOfAscension.MOD_ID, "skills/foraging");

	public ForagingLootModifier(ILootCondition[] conditions) {
		super(conditions);
	}

	@Nonnull
	@Override
	protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
		for (LootParameter<?> param : LootParameterSets.BLOCK.getRequired()) {
			if (!context.hasParam(param))
				return generatedLoot;
		}

		if (!context.hasParam(LootParameters.THIS_ENTITY))
			return generatedLoot;

		Entity entity = context.getParamOrNull(LootParameters.THIS_ENTITY);

		if (!(entity instanceof ServerPlayerEntity))
			return generatedLoot;

		ItemStack tool = context.getParamOrNull(LootParameters.TOOL);
		BlockState state = context.getParamOrNull(LootParameters.BLOCK_STATE);

		if (tool.getItem().getDestroySpeed(tool, state) <= 1 && !(tool.getItem() instanceof PickaxeItem))
			return generatedLoot;

		ServerWorld world = context.getLevel();
		BlockPos pos = new BlockPos(context.getParamOrNull(LootParameters.ORIGIN));

		if (state.getMaterial() == Material.STONE && state.isSolidRender(world, pos)) {
			PlayerDataManager plData = PlayerUtil.getAdventPlayer((ServerPlayerEntity)entity);

			if (state.getDestroySpeed(world, pos) > 1 || RandomUtil.fiftyFifty()) {
				int lvl = plData.stats().getLevel(Skills.FORAGING);

				if (ForagingUtil.shouldGetLoot(lvl)) {
					LootTable table = context.getLootTable(FORAGING_TABLE);

					table.getRandomItems(context, generatedLoot::add);

					if (plData.equipment().getCurrentFullArmourSet() == AdventArmour.Type.FORAGING)
						table.getRandomItems(context, generatedLoot::add);

					world.playSound(null, pos, AoASounds.SKILL_LOOT.get(), SoundCategory.MASTER, 1.0f, 1.0f);

					if (WorldUtil.isWorld((World)world, World.OVERWORLD) && world.isDay())
						plData.stats().addTribute(Deities.PLUTON, 11 - lvl / 10);
				}
			}
		}

		return generatedLoot;
	}

	public static class Serializer extends GlobalLootModifierSerializer<ForagingLootModifier> {
		@Override
		public ForagingLootModifier read(ResourceLocation location, JsonObject object, ILootCondition[] lootConditions) {
			return new ForagingLootModifier(lootConditions);
		}

		@Override
		public JsonObject write(ForagingLootModifier instance) {
			return makeConditions(instance.conditions);
		}
	}
}
