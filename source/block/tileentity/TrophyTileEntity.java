package net.tslat.aoa3.block.tileentity;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.INameable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
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

		if (trophyBlock == null && world != null)
			trophyBlock = world.getBlockState(getPos()).getBlock();
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
	public void handleUpdateTag(CompoundNBT tag) {
		super.handleUpdateTag(tag);

		if (tag.contains("EntityID", 8)) {
			entityId = tag.getString("EntityID");
			isOriginal = tag.getBoolean("OriginalTrophy");
		}
	}

	@Override
	public CompoundNBT write(CompoundNBT compound) {
		super.write(compound);

		if (entityId != null) {
			compound.putString("EntityID", entityId);
			compound.putBoolean("OriginalTrophy", isOriginal);
		}

		return compound;
	}

	@Override
	public void read(CompoundNBT compound) {
		super.read(compound);

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

			cachedEntity = EntityType.loadEntityAndExecute(entityNBT, getWorld(), Function.identity());

			if (cachedEntity == null) {
				entityNBT = new CompoundNBT();
				entityId = "minecraft:end_crystal";

				entityNBT.putString("id", entityId);

				cachedEntity = EntityType.loadEntityAndExecute(entityNBT, getWorld(), Function.identity());
			}

			if (entityNBT.size() == 1 && entityNBT.contains("id", 8) && this.cachedEntity instanceof MobEntity)
				((MobEntity)cachedEntity).onInitialSpawn(getWorld(), this.getWorld().getDifficultyForLocation(new BlockPos(cachedEntity)), SpawnReason.SPAWNER, null, null);
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
			return new TranslationTextComponent("block.aoa3.trophy.desc", LocaleUtil.getLocaleMessage(getCachedEntity().getType().getTranslationKey()));

		if (trophyBlock == AoABlocks.GOLD_TROPHY.get())
			return new TranslationTextComponent("block.aoa3.gold_trophy.desc", LocaleUtil.getLocaleMessage(getCachedEntity().getType().getTranslationKey()));

		if (trophyBlock == AoABlocks.ORNATE_TROPHY.get())
			return new TranslationTextComponent("block.aoa3.ornate_trophy.desc", LocaleUtil.getLocaleMessage(getCachedEntity().getType().getTranslationKey()));

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
