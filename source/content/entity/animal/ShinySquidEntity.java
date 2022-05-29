package net.tslat.aoa3.content.entity.animal;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Squid;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.custom.AoASkills;
import net.tslat.aoa3.player.ServerPlayerDataManager;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.util.PlayerUtil;

public class ShinySquidEntity extends Squid {
	public ShinySquidEntity(EntityType<? extends ShinySquidEntity> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	public void die(DamageSource source) {
		super.die(source);

		if (!isRemoved() && dead) {
			Entity killer = source.getEntity();

			if (killer instanceof ServerPlayer player && player.fishing != null && player.fishing.getHookedIn() == this) {
				ServerPlayerDataManager plData = PlayerUtil.getAdventPlayer(player);
				AoASkill.Instance hauling = plData.getSkill(AoASkills.HAULING.get());

				hauling.adjustXp(PlayerUtil.getXpForFractionOfLevel(hauling.getLevel(true), 0.1f), false, false);
			}
		}
	}
}
