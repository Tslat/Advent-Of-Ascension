package net.tslat.aoa3.content.entity.misc;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.PlayMessages;
import net.tslat.aoa3.common.registration.entity.AoAMiscEntities;

import javax.annotation.Nullable;

public class GoldFishingBobberEntity extends HaulingFishingBobberEntity {
	public GoldFishingBobberEntity(Level world, Player player, double posX, double posY, double posZ) {
		super(world, player, posX, posY, posZ);
	}

	public GoldFishingBobberEntity(Player player, Level world, ItemStack rod) {
		super(player, world, rod);
	}

	public GoldFishingBobberEntity(Player player, Level world, ItemStack rod, float luck, float lure) {
		super(player, world, rod, luck, lure);
	}

	@Override
	public EntityType<?> getType() {
		return AoAMiscEntities.GOLD_BOBBER.get();
	}

	@Nullable
	public static GoldFishingBobberEntity handleClientSpawn(PlayMessages.SpawnEntity packet, Level world) {
		Entity owner = world.getEntity((int)packet.getPosY());

		if (owner instanceof Player)
			return new GoldFishingBobberEntity(world, (Player)owner, packet.getPosX(), owner.getEyeY(), packet.getPosZ());

		return null;
	}
}
