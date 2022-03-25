package net.tslat.aoa3.content.entity.misc;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.tslat.aoa3.common.registration.AoAEntities;

import javax.annotation.Nullable;

public class GoldFishingBobberEntity extends HaulingFishingBobberEntity {
	public GoldFishingBobberEntity(World world, PlayerEntity player, double posX, double posY, double posZ) {
		super(world, player, posX, posY, posZ);
	}

	public GoldFishingBobberEntity(PlayerEntity player, World world, ItemStack rod) {
		super(player, world, rod);
	}

	public GoldFishingBobberEntity(PlayerEntity player, World world, ItemStack rod, float luck, float lure) {
		super(player, world, rod, luck, lure);
	}

	@Override
	public EntityType<?> getType() {
		return AoAEntities.Misc.GOLD_BOBBER.get();
	}

	@Nullable
	public static GoldFishingBobberEntity handleClientSpawn(FMLPlayMessages.SpawnEntity packet, World world) {
		Entity owner = world.getEntity((int)packet.getPosY());

		if (owner instanceof PlayerEntity)
			return new GoldFishingBobberEntity(world, (PlayerEntity)owner, packet.getPosX(), owner.getEyeY(), packet.getPosZ());

		return null;
	}
}
