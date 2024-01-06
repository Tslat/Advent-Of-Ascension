package net.tslat.aoa3.content.block.tileentity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.ForgeRegistries;
import net.tslat.aoa3.common.registration.block.AoABlockEntities;
import net.tslat.aoa3.util.RegistryUtil;
import org.jetbrains.annotations.Nullable;


public class BossAltarTileEntity extends BlockEntity {
	private EntityType<?> entityType = null;
	private Entity cachedEntity = null;

	public BossAltarTileEntity(BlockPos pos, BlockState blockState) {
		super(AoABlockEntities.BOSS_ALTAR.get(), pos, blockState);
	}

	public void updateEntity(@Nullable EntityType<?> entityType) {
		this.entityType = entityType;

		if (level != null)
			level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), Block.UPDATE_ALL);
	}

	@Override
	public CompoundTag getUpdateTag() {
		CompoundTag tag = super.getUpdateTag();

		tag.putString("entityType", entityType == null ? "" : RegistryUtil.getId(entityType).toString());

		return tag;
	}

	@Override
	public void load(CompoundTag tag) {
		super.load(tag);

		if (tag.contains("entityType")) {
			String entityTypeString = tag.getString("entityType");

			if (entityTypeString.isEmpty()) {
				entityType = null;
			}
			else {
				entityType = ForgeRegistries.ENTITY_TYPES.getValue(new ResourceLocation(entityTypeString));
			}

			if (cachedEntity != null)
				cachedEntity.discard();

			cachedEntity = entityType == null || level == null ? null : entityType.create(level);
		}
	}

	@Nullable
	public EntityType<?> getCurrentEntity() {
		return this.entityType;
	}

	@Nullable
	public Entity getCachedEntity() {
		return this.cachedEntity;
	}

	@Nullable
	@Override
	public Packet<ClientGamePacketListener> getUpdatePacket() {
		return ClientboundBlockEntityDataPacket.create(this);
	}
}
