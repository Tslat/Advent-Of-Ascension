package net.tslat.aoa3.content.block.functional.misc;

import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.tslat.aoa3.advent.Logging;
import net.tslat.aoa3.common.registration.AoARegistries;
import net.tslat.aoa3.common.registration.block.AoABlockEntities;
import net.tslat.aoa3.common.registration.block.AoABlocks;
import net.tslat.aoa3.content.block.WaterloggableBlock;
import net.tslat.aoa3.content.block.tileentity.TrophyTileEntity;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.RegistryUtil;
import net.tslat.aoa3.util.WorldUtil;
import org.jetbrains.annotations.Nullable;


public class TrophyBlock extends WaterloggableBlock implements EntityBlock {
	private static final VoxelShape FULL_AABB = Shapes.or(
			Block.box(4, 0, 4, 12, 2, 12),
			Block.box(5, 2, 5, 11, 9, 11),
			Block.box(4.5, 9, 4.5, 11.5, 11, 11.5));

	public TrophyBlock(BlockBehaviour.Properties properties) {
		super(properties);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return FULL_AABB;
	}

	@Nullable
	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new TrophyTileEntity(pos, state);
	}

	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
		if (blockEntityType != AoABlockEntities.TROPHY.get())
			return null;

		if (!level.isClientSide())
			return null;

		return (entityLevel, entityPos, entityState, blockEntity) -> TrophyTileEntity.doClientTick(entityLevel, entityPos, entityState, (TrophyTileEntity)blockEntity);
	}

	@Override
	public void setPlacedBy(Level world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
		if (stack.hasTag()) {
			CompoundTag tag = stack.getTag();

			if (tag.contains("BlockEntityTag")) {
				CompoundTag dataTag = tag.getCompound("BlockEntityTag");

				if (dataTag.contains("EntityID", Tag.TAG_STRING)) {
					BlockEntity tile = world.getBlockEntity(pos);

					if (tile instanceof TrophyTileEntity)
						((TrophyTileEntity)tile).setEntity(dataTag.getString("EntityID"), dataTag.contains("OriginalTrophy") && !dataTag.getBoolean("OriginalTrophy"));
				}
			}
		}
	}

	@Override
	public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
		ItemStack heldStack = player.getItemInHand(hand);

		if (!WorldUtil.canModifyBlock(world, pos, player, heldStack))
			return InteractionResult.FAIL;

		if (heldStack.getItem() instanceof SpawnEggItem) {
			BlockEntity tile = world.getBlockEntity(pos);
			SpawnEggItem egg = (SpawnEggItem)heldStack.getItem();

			if (tile instanceof TrophyTileEntity) {
				((TrophyTileEntity)tile).setEntity(RegistryUtil.getId(egg.getType(heldStack.getTag())).toString(), true);

				if (!world.isClientSide() && !player.isCreative())
					heldStack.shrink(1);
			}

			return InteractionResult.SUCCESS;
		}

		return InteractionResult.PASS;
	}

	@Override
	public ItemStack getCloneItemStack(BlockState state, HitResult target, LevelReader world, BlockPos pos, Player player) {
		ItemStack stack = new ItemStack(asItem());
		BlockEntity tile = world.getBlockEntity(pos);
		TrophyTileEntity trophyTile;

		if (tile instanceof TrophyTileEntity && ((trophyTile = (TrophyTileEntity)tile).getEntityId() != null)) {
			CompoundTag nbt = new CompoundTag();
			CompoundTag dataTag = new CompoundTag();

			dataTag.putString("EntityID", ((TrophyTileEntity)tile).getEntityId());
			dataTag.putBoolean("OriginalTrophy", ((TrophyTileEntity)tile).isOriginal());
			nbt.put("BlockEntityTag", dataTag);

			stack.setTag(nbt);

			if (trophyTile.getCachedEntity() != null) {
				Entity cachedEntity = ((TrophyTileEntity)tile).getCachedEntity();
				Component entityName = cachedEntity == null ? Component.literal("") : cachedEntity.getName();
				stack.setHoverName(LocaleUtil.getLocaleMessage("block.aoa3.trophy.desc", entityName));
			}
		}

		return stack;
	}

	public static CompoundTag getTagForEntity(EntityType<?> entity) {
		CompoundTag nbt = new CompoundTag();
		CompoundTag dataTag = new CompoundTag();

		dataTag.putString("EntityID", RegistryUtil.getId(entity).toString());
		dataTag.putBoolean("OriginalTrophy", true);
		nbt.put("BlockEntityTag", dataTag);

		return nbt;
	}

	public static ItemStack cloneTrophy(ItemStack sourceTrophy, ItemLike destTrophy) {
		CompoundTag sourceTag = sourceTrophy.getOrCreateTag();
		ItemStack newStack = new ItemStack(destTrophy.asItem());
		CompoundTag destTag = newStack.getOrCreateTag();

		try {
			if (sourceTag.isEmpty())
				return newStack;

			if (sourceTag.contains("BlockEntityTag"))
				destTag.put("BlockEntityTag", sourceTag.getCompound("BlockEntityTag"));

			if (sourceTag.contains("display")) {
				CompoundTag displayTag = new CompoundTag();
				String localePrefix = "block.aoa3.trophy.desc";

				if (destTrophy == AoABlocks.GOLD_TROPHY.get()) {
					localePrefix = "block.aoa3.gold_trophy.desc";
				}
				else if (destTrophy == AoABlocks.ORNATE_TROPHY.get()) {
					localePrefix = "block.aoa3.ornate_trophy.desc";
				}

				if (sourceTag.contains("BlockEntityTag")) {
					displayTag.putString("Name", MutableComponent.Serializer.toJson(Component.translatable(localePrefix, Component.translatable(Util.makeDescriptionId("entity", new ResourceLocation(sourceTag.getCompound("BlockEntityTag").getString("EntityID")))))));
				}
				else {
					displayTag = sourceTag.getCompound("display");
				}

				destTag.put("display", displayTag);
			}
		}
		catch (Exception ex) {
			Logging.logMessage(org.apache.logging.log4j.Level.ERROR, "Failed to clone trophy data.", ex);
		}

		return newStack;
	}

	public static CompoundTag getOriginalTrophyTag(EntityType<?> entity, Block trophyBlock) {
		CompoundTag tag = TrophyBlock.getTagForEntity(entity);
		CompoundTag displayTag = new CompoundTag();
		String localePrefix = "block.aoa3.trophy.desc";

		if (trophyBlock == AoABlocks.GOLD_TROPHY.get()) {
			localePrefix = "block.aoa3.gold_trophy.desc";
		}
		else if (trophyBlock == AoABlocks.ORNATE_TROPHY.get()) {
			localePrefix = "block.aoa3.ornate_trophy.desc";
		}

		displayTag.putString("Name", MutableComponent.Serializer.toJson(Component.translatable(localePrefix, Component.translatable(entity.getDescriptionId()))));
		tag.put("display", displayTag);

		return tag;
	}

	public static boolean isOriginal(ItemStack stack) {
		if (!(stack.getItem() instanceof BlockItem block) || !(block.getBlock() instanceof TrophyBlock))
			return false;

		if (!stack.hasTag())
			return false;

		CompoundTag tag = stack.getTag();

		if (!tag.contains("BlockEntityTag", Tag.TAG_COMPOUND))
			return false;

		CompoundTag subTag = tag.getCompound("BlockEntityTag");

		return subTag.contains("OriginalTrophy", Tag.TAG_BYTE) && subTag.getBoolean("OriginalTrophy");
	}

	@Nullable
	public static EntityType<?> getCachedEntityType(ItemStack stack) {
		if (!(stack.getItem() instanceof BlockItem block) || !(block.getBlock() instanceof TrophyBlock))
			return null;

		CompoundTag tag = stack.getTag();

		if (tag == null)
			return null;

		String entityId = tag.getCompound("BlockEntityTag").getString("EntityID");

		return entityId.isEmpty() ? null : AoARegistries.ENTITIES.getEntry(new ResourceLocation(entityId));
	}
}
