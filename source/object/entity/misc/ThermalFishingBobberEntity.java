package net.tslat.aoa3.object.entity.misc;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ITag;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.data.server.AoAHaulingFishReloadListener;
import net.tslat.aoa3.object.entity.base.LavaMobEntity;
import net.tslat.aoa3.util.RandomUtil;

import javax.annotation.Nullable;
import java.util.function.Function;

public class ThermalFishingBobberEntity extends HaulingFishingBobberEntity {
	public ThermalFishingBobberEntity(World world, PlayerEntity player, double posX, double posY, double posZ) {
		super(world, player, posX, posY, posZ);
	}

	public ThermalFishingBobberEntity(PlayerEntity player, World world, ItemStack rod) {
		super(player, world, rod);
	}

	public ThermalFishingBobberEntity(PlayerEntity player, World world, ItemStack rod, float luck, float lure) {
		super(player, world, rod, luck, lure);
	}

	@Override
	protected ITag<Fluid> getApplicableFluid() {
		return FluidTags.LAVA;
	}

	@Override
	public EntityType<?> getType() {
		return AoAEntities.Misc.THERMAL_BOBBER.get();
	}

	@Override
	protected void spawnFish(ServerPlayerEntity player) {
		Function<World, Entity> fishFunction = AoAHaulingFishReloadListener.getFishListForBiome(level.getBiome(blockPosition()), true).getRandomElement(player, getLuck());

		if (fishFunction != null) {
			Entity entity = fishFunction.apply(player.level);

			if (entity == null)
				return;

			if (entity instanceof MobEntity) {
				MobEntity mob = (MobEntity)entity;
				BlockPos pos;

				if (entity instanceof LavaMobEntity) {
					pos = RandomUtil.getRandomPositionWithinRange(this.blockPosition().below(5), 10, 10, 10, false, level, state -> state.getFluidState().getType() == Fluids.LAVA, 5);
				}
				else {
					pos = RandomUtil.getRandomPositionWithinRange(this.blockPosition(), 10, 10, 10, false, level, state -> state.getFluidState().getType() == Fluids.LAVA, 5);
				}

				mob.setPos(pos.getX(), pos.getY(), pos.getZ());
				mob.getNavigation().createPath(blockPosition(), 0);
				level.addFreshEntity(mob);
			}
			else {
				entity.setPos(getX(), getY() - entity.getBbHeight(), getZ());
				level.addFreshEntity(entity);
			}

			spawnedFish = entity;
		}
	}

	@Nullable
	public static ThermalFishingBobberEntity handleClientSpawn(FMLPlayMessages.SpawnEntity packet, World world) {
		Entity owner = world.getEntity((int)packet.getPosY());

		if (owner instanceof PlayerEntity)
			return new ThermalFishingBobberEntity(world, (PlayerEntity)owner, packet.getPosX(), owner.getEyeY(), packet.getPosZ());

		return null;
	}
}
