package net.tslat.aoa3.content.block.functional.misc;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.block.material.PushReaction;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.common.util.Constants;
import net.tslat.aoa3.advent.Logging;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.content.block.WaterloggableBlock;
import net.tslat.aoa3.content.block.tileentity.TrophyTileEntity;
import net.tslat.aoa3.util.BlockUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.WorldUtil;
import org.apache.logging.log4j.Level;

import javax.annotation.Nullable;

public class TrophyBlock extends WaterloggableBlock {
	private static final VoxelShape BASE_SHAPE = Block.box(4, 0, 4, 12, 2, 12);
	private static final VoxelShape MIDDLE_SHAPE = Block.box(5, 2, 5, 11, 9, 11);
	private static final VoxelShape TOP_SHAPE = Block.box(4.5, 9, 4.5, 11.5, 11, 11.5);
	private static final VoxelShape FULL_AABB = VoxelShapes.or(BASE_SHAPE, MIDDLE_SHAPE, TOP_SHAPE);

	public TrophyBlock() {
		super(new BlockUtil.CompactProperties(new Material(MaterialColor.GOLD, false, false, true, false, false, false, PushReaction.BLOCK), MaterialColor.STONE).stats(10f, 2000f).get());
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
		return FULL_AABB;
	}

	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}

	@Nullable
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new TrophyTileEntity();
	}

	@Override
	public boolean isToolEffective(BlockState state, ToolType tool) {
		return tool == ToolType.PICKAXE;
	}

	@Override
	public void setPlacedBy(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
		if (stack.hasTag()) {
			CompoundNBT tag = stack.getTag();

			if (tag.contains("BlockEntityTag")) {
				CompoundNBT dataTag = tag.getCompound("BlockEntityTag");

				if (dataTag.contains("EntityID", Constants.NBT.TAG_STRING)) {
					TileEntity tile = world.getBlockEntity(pos);

					if (tile instanceof TrophyTileEntity)
						((TrophyTileEntity)tile).setEntity(dataTag.getString("EntityID"), dataTag.contains("OriginalTrophy") && !dataTag.getBoolean("OriginalTrophy"));
				}
			}
		}
	}

	@Override
	public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
		ItemStack heldStack = player.getItemInHand(hand);

		if (!WorldUtil.canModifyBlock(world, pos, player, heldStack))
			return ActionResultType.FAIL;

		if (heldStack.getItem() instanceof SpawnEggItem) {
			TileEntity tile = world.getBlockEntity(pos);
			SpawnEggItem egg = (SpawnEggItem)heldStack.getItem();

			if (tile instanceof TrophyTileEntity) {
				((TrophyTileEntity)tile).setEntity(egg.getType(heldStack.getTag()).getRegistryName().toString(), true);

				if (!world.isClientSide() && !player.isCreative())
					heldStack.shrink(1);
			}

			return ActionResultType.SUCCESS;
		}

		return ActionResultType.PASS;
	}

	@Override
	public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player) {
		ItemStack stack = new ItemStack(asItem());
		TileEntity tile = world.getBlockEntity(pos);
		TrophyTileEntity trophyTile;

		if (tile instanceof TrophyTileEntity && ((trophyTile = (TrophyTileEntity)tile).getEntityId() != null)) {
			CompoundNBT nbt = new CompoundNBT();
			CompoundNBT dataTag = new CompoundNBT();

			dataTag.putString("EntityID", ((TrophyTileEntity)tile).getEntityId());
			dataTag.putBoolean("OriginalTrophy", ((TrophyTileEntity)tile).isOriginal());
			nbt.put("BlockEntityTag", dataTag);

			stack.setTag(nbt);

			if (trophyTile.getCachedEntity() != null || dataTag.getString("EntityID").equals("minecraft:player")) {
				Entity cachedEntity = trophyTile.getCachedEntity();
				ITextComponent entityName = cachedEntity == null ? new TranslationTextComponent("entity.minecraft.player") : cachedEntity.getName();
				stack.setHoverName(LocaleUtil.getLocaleMessage("block.aoa3.trophy.desc", entityName));
			}
		}

		return stack;
	}

	public static CompoundNBT getTagForEntity(EntityType<?> entity) {
		CompoundNBT nbt = new CompoundNBT();
		CompoundNBT dataTag = new CompoundNBT();

		dataTag.putString("EntityID", entity.getRegistryName().toString());
		dataTag.putBoolean("OriginalTrophy", true);
		nbt.put("BlockEntityTag", dataTag);

		return nbt;
	}

	public static ItemStack cloneTrophy(ItemStack sourceTrophy, IItemProvider destTrophy) {
		CompoundNBT sourceTag = sourceTrophy.getOrCreateTag();
		ItemStack newStack = new ItemStack(destTrophy.asItem());
		CompoundNBT destTag = newStack.getOrCreateTag();

		try {
			if (sourceTag.isEmpty())
				return newStack;

			if (sourceTag.contains("BlockEntityTag"))
				destTag.put("BlockEntityTag", sourceTag.getCompound("BlockEntityTag"));

			if (sourceTag.contains("display")) {
				CompoundNBT displayTag = new CompoundNBT();
				String localePrefix = "block.aoa3.trophy.desc";

				if (destTrophy == AoABlocks.GOLD_TROPHY.get()) {
					localePrefix = "block.aoa3.gold_trophy.desc";
				}
				else if (destTrophy == AoABlocks.ORNATE_TROPHY.get()) {
					localePrefix = "block.aoa3.ornate_trophy.desc";
				}

				if (sourceTag.contains("BlockEntityTag")) {
					displayTag.putString("Name", ITextComponent.Serializer.toJson(new TranslationTextComponent(localePrefix, new TranslationTextComponent(Util.makeDescriptionId("entity", new ResourceLocation(sourceTag.getCompound("BlockEntityTag").getString("EntityID")))))));
				}
				else {
					displayTag = sourceTag.getCompound("display");
				}

				destTag.put("display", displayTag);
			}
		}
		catch (Exception ex) {
			Logging.logMessage(Level.ERROR, "Failed to clone trophy data.", ex);
		}

		return newStack;
	}

	public static CompoundNBT getOriginalTrophyTag(EntityType<?> entity, Block trophyBlock) {
		CompoundNBT tag = TrophyBlock.getTagForEntity(entity);
		CompoundNBT displayTag = new CompoundNBT();
		String localePrefix = "block.aoa3.trophy.desc";

		if (trophyBlock == AoABlocks.GOLD_TROPHY.get()) {
			localePrefix = "block.aoa3.gold_trophy.desc";
		}
		else if (trophyBlock == AoABlocks.ORNATE_TROPHY.get()) {
			localePrefix = "block.aoa3.ornate_trophy.desc";
		}

		displayTag.putString("Name", ITextComponent.Serializer.toJson(new TranslationTextComponent(localePrefix, new TranslationTextComponent(entity.getDescriptionId()))));
		tag.put("display", displayTag);

		return tag;
	}
}
