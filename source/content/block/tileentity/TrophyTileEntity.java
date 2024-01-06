package net.tslat.aoa3.content.block.tileentity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Nameable;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.tslat.aoa3.common.registration.block.AoABlockEntities;
import net.tslat.aoa3.common.registration.block.AoABlocks;
import net.tslat.aoa3.util.LocaleUtil;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;

public class TrophyTileEntity extends BlockEntity implements Nameable {
	private final Block trophyBlock;

	@Nullable
	private Entity cachedEntity = null;
	@Nullable
	private String entityId = null;
	private boolean isOriginal = true;

	private float mobRotation;
	private float prevMobRotation;
	public double hoverStep;

	public TrophyTileEntity(BlockPos pos, BlockState state) {
		super(AoABlockEntities.TROPHY.get(), pos, state);

		this.trophyBlock = state.getBlock();
	}

	public void setEntity(String entityId, boolean isEgg) {
		this.entityId = entityId;
		this.cachedEntity = null;
		this.isOriginal = !isEgg;
	}

	public static void doClientTick(Level level, BlockPos pos, BlockState state, TrophyTileEntity blockEntity) {
		blockEntity.prevMobRotation = blockEntity.mobRotation;
		blockEntity.mobRotation = (blockEntity.mobRotation + 0.05f) % 360;
	}

	public float getMobRotation() {
		return mobRotation;
	}

	public float getPrevMobRotation() {
		return prevMobRotation;
	}

	public String getEntityId() {
		return entityId;
	}

	public boolean isOriginal() {
		return isOriginal;
	}

	@Override
	public CompoundTag getUpdateTag() {
		CompoundTag tag = super.getUpdateTag();

		if (entityId != null) {
			tag.putString("EntityID", entityId);
			tag.putBoolean("OriginalTrophy", isOriginal);
		}

		return tag;
	}

	@Override
	public void handleUpdateTag(CompoundTag tag) {
		super.handleUpdateTag(tag);

		if (tag.contains("EntityID", Tag.TAG_STRING)) {
			entityId = tag.getString("EntityID");
			isOriginal = tag.getBoolean("OriginalTrophy");
		}
	}

	@Override
	protected void saveAdditional(CompoundTag compound) {
		if (entityId != null) {
			compound.putString("EntityID", entityId);
			compound.putBoolean("OriginalTrophy", isOriginal);
		}
	}

	@Override
	public void load(CompoundTag compound) {
		super.load(compound);

		if (compound.contains("EntityID")) {
			entityId = compound.getString("EntityID");
			isOriginal = compound.getBoolean("OriginalTrophy");
		}
	}

	@Nullable
	public Entity getCachedEntity() {
		if (this.cachedEntity == null && entityId != null) {
			CompoundTag entityNBT = new CompoundTag();

			entityNBT.putString("id", entityId);

			cachedEntity = EntityType.loadEntityRecursive(entityNBT, getLevel(), Function.identity());

			if (cachedEntity == null) {
				entityNBT = new CompoundTag();
				entityId = "minecraft:end_crystal";

				entityNBT.putString("id", entityId);

				cachedEntity = EntityType.loadEntityRecursive(entityNBT, getLevel(), Function.identity());
			}

			if (cachedEntity != null)
				cachedEntity.tickCount = 1;
		}

		return this.cachedEntity;
	}

	@Override
	public Component getName() {
		if (trophyBlock == null || entityId == null)
			return LocaleUtil.getLocaleMessage("block.aoa3.trophy");

		if (getCachedEntity() == null)
			return LocaleUtil.getLocaleMessage("block.aoa3.trophy");

		if (trophyBlock == AoABlocks.TROPHY.get())
			return LocaleUtil.getLocaleMessage("block.aoa3.trophy.desc", cachedEntity.getName());

		if (trophyBlock == AoABlocks.GOLD_TROPHY.get())
			return LocaleUtil.getLocaleMessage("block.aoa3.gold_trophy.desc", cachedEntity.getName());

		if (trophyBlock == AoABlocks.ORNATE_TROPHY.get())
			return LocaleUtil.getLocaleMessage("block.aoa3.ornate_trophy.desc", cachedEntity.getName());

		return LocaleUtil.getLocaleMessage("block.aoa3.trophy");
	}

	@Override
	public boolean hasCustomName() {
		return entityId != null;
	}

	@Nullable
	@Override
	public Component getCustomName() {
		return getName();
	}
}
