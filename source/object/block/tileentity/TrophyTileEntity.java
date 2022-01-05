package net.tslat.aoa3.object.block.tileentity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.INameable;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.common.util.Constants;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.common.registration.AoATileEntities;
import net.tslat.aoa3.util.LocaleUtil;

import javax.annotation.Nullable;
import java.util.function.Function;

public class TrophyTileEntity extends TileEntity implements ITickableTileEntity, INameable {
	@Nullable
	private Entity cachedEntity = null;
	@Nullable
	private String entityId = null;
	private boolean isOriginal = true;

	private Block trophyBlock = null;

	private float mobRotation;
	private float prevMobRotation;
	public double hoverStep;

	public TrophyTileEntity() {
		super(AoATileEntities.TROPHY.get());
	}

	public void setEntity(String entityId, boolean isEgg) {
		this.entityId = entityId;
		this.cachedEntity = null;
		this.isOriginal = !isEgg;
	}

	@Override
	public void tick() {
		prevMobRotation = mobRotation;
		mobRotation = (mobRotation + 0.05f) % 360;

		if (trophyBlock == null && level != null)
			trophyBlock = level.getBlockState(getBlockPos()).getBlock();
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
	public CompoundNBT getUpdateTag() {
		CompoundNBT tag = super.getUpdateTag();

		if (entityId != null) {
			tag.putString("EntityID", entityId);
			tag.putBoolean("OriginalTrophy", isOriginal);
		}

		return tag;
	}

	@Override
	public void handleUpdateTag(BlockState state, CompoundNBT tag) {
		super.handleUpdateTag(state, tag);

		if (tag.contains("EntityID", Constants.NBT.TAG_STRING)) {
			entityId = tag.getString("EntityID");
			isOriginal = tag.getBoolean("OriginalTrophy");
		}
	}

	@Override
	public CompoundNBT save(CompoundNBT compound) {
		super.save(compound);

		if (entityId != null) {
			compound.putString("EntityID", entityId);
			compound.putBoolean("OriginalTrophy", isOriginal);
		}

		return compound;
	}

	@Override
	public void load(BlockState state, CompoundNBT compound) {
		super.load(state, compound);

		if (compound.contains("EntityID")) {
			entityId = compound.getString("EntityID");
			isOriginal = compound.getBoolean("OriginalTrophy");
		}
	}

	@Nullable
	public Entity getCachedEntity() {
		if (this.cachedEntity == null && entityId != null) {
			CompoundNBT entityNBT = new CompoundNBT();

			entityNBT.putString("id", entityId);

			cachedEntity = EntityType.loadEntityRecursive(entityNBT, getLevel(), Function.identity());

			if (cachedEntity == null) {
				entityNBT = new CompoundNBT();
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
	public ITextComponent getName() {
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
		return trophyBlock != null && entityId != null;
	}

	@Nullable
	@Override
	public ITextComponent getCustomName() {
		return getName();
	}
}
