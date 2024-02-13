package net.tslat.aoa3.content.block.functional.misc;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.common.registration.AoARegistries;
import net.tslat.aoa3.content.block.blockentity.TrophyBlockEntity;
import net.tslat.aoa3.content.item.misc.summoning.BossTokenItem;
import net.tslat.aoa3.util.EntitySpawningUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.RegistryUtil;
import org.jetbrains.annotations.Nullable;

public class GoldTrophyBlock extends TrophyBlock implements BossTokenItem {
	public GoldTrophyBlock(BlockBehaviour.Properties properties) {
		super(properties);
	}

	@Override
	public ItemStack getCloneItemStack(BlockState state, HitResult target, LevelReader world, BlockPos pos, Player player) {
		ItemStack stack = new ItemStack(asItem());
		BlockEntity tile = world.getBlockEntity(pos);
		TrophyBlockEntity trophyTile;

		if (tile instanceof TrophyBlockEntity && ((trophyTile = (TrophyBlockEntity)tile).getEntityId() != null)) {
			CompoundTag nbt = new CompoundTag();
			CompoundTag dataTag = new CompoundTag();

			dataTag.putString("EntityID", ((TrophyBlockEntity)tile).getEntityId());
			dataTag.putBoolean("OriginalTrophy", ((TrophyBlockEntity)tile).isOriginal());
			nbt.put("BlockEntityTag", dataTag);

			stack.setTag(nbt);

			if (trophyTile.getCachedEntity() != null) {
				Entity cachedEntity = ((TrophyBlockEntity)tile).getCachedEntity();
				Component entityName = cachedEntity == null ? Component.literal("") : cachedEntity.getType().getDescription();
				stack.setHoverName(LocaleUtil.getLocaleMessage("block.aoa3.gold_trophy.desc", ChatFormatting.GOLD, entityName));
			}
		}

		return stack;
	}

	@Override
	public Entity spawnBoss(ServerLevel level, Vec3 position, ItemStack itemStack) {
		return EntitySpawningUtil.spawnEntity(level, getEntityType(itemStack), position, MobSpawnType.TRIGGERED);
	}

	@Nullable
	@Override
	public EntityType<?> getEntityType(ItemStack stack) {
		if (!TrophyBlock.isOriginal(stack))
			return null;

		EntityType<?> cachedEntity = TrophyBlock.getCachedEntityType(stack);

		if (cachedEntity == null)
			return null;

		ResourceLocation id = RegistryUtil.getId(cachedEntity);

		return AoARegistries.ENTITIES.getEntry(new ResourceLocation(id.getNamespace(), "elite_" + id.getPath()));
	}
}
