package net.tslat.aoa3.object.entity.animal;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.passive.SquidEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.custom.AoASkills;
import net.tslat.aoa3.player.ServerPlayerDataManager;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.util.PlayerUtil;

import java.util.Random;

public class ShinySquidEntity extends SquidEntity {
	public ShinySquidEntity(EntityType<? extends ShinySquidEntity> entityType, World world) {
		super(entityType, world);
	}

	public static boolean checkShinySquidSpawnRules(EntityType<MobEntity> squid, IWorld world, SpawnReason reason, BlockPos position, Random rand) {
		return position.getY() > 45 && position.getY() < world.getSeaLevel() && rand.nextInt(1000) == 0;
	}

	@Override
	public void die(DamageSource source) {
		super.die(source);

		if (!removed && dead) {
			Entity killer = source.getEntity();

			if (killer instanceof ServerPlayerEntity) {
				ServerPlayerEntity player = (ServerPlayerEntity)killer;

				if (player.fishing != null && player.fishing.getHookedIn() == this) {
					ServerPlayerDataManager plData = PlayerUtil.getAdventPlayer(player);
					AoASkill.Instance hauling = plData.getSkill(AoASkills.HAULING.get());

					hauling.adjustXp(PlayerUtil.getXpForFractionOfLevel(hauling.getLevel(true), 0.1f), false, false);
				}
			}
		}
	}
}
