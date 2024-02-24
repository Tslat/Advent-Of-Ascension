package net.tslat.aoa3.player.ability.farming;

import com.google.gson.JsonObject;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.animal.Animal;
import net.neoforged.neoforge.event.entity.living.BabyEntitySpawnEvent;
import net.tslat.aoa3.common.registration.custom.AoAAbilities;
import net.tslat.aoa3.common.registration.custom.AoASkills;
import net.tslat.aoa3.player.ability.generic.ScalableModAbility;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.util.PlayerUtil;

public class BreedingBonus extends ScalableModAbility {
	private static final ListenerType[] LISTENERS = new ListenerType[] {ListenerType.ANIMAL_BREED};

	public BreedingBonus(AoASkill.Instance skill, JsonObject data) {
		super(AoAAbilities.BREEDING_BONUS.get(), skill, data);
	}

	public BreedingBonus(AoASkill.Instance skill, CompoundTag data) {
		super(AoAAbilities.BREEDING_BONUS.get(), skill, data);
	}

	@Override
	public ListenerType[] getListenerTypes() {
		return LISTENERS;
	}

	@Override
	public void handleAnimalBreed(BabyEntitySpawnEvent ev) {
		if (testAsChance()) {
			Mob parentA = ev.getParentA();
			Mob parentB = ev.getParentB();
			ServerLevel level = (ServerLevel)parentA.level();
			AgeableMob newChild;

			if (parentA instanceof AgeableMob ageableParentA && parentB instanceof AgeableMob ageableParentB) {
				newChild = ageableParentA.getBreedOffspring(level, ageableParentB);
			}
			else {
				newChild = ((EntityType<AgeableMob>)ev.getChild().getType()).create(level, null, null, parentA.blockPosition(), MobSpawnType.BREEDING, false, false);
			}

			if (newChild != null) {
				newChild.setBaby(true);
				newChild.moveTo(parentA.getX(), parentA.getY(), parentA.getZ(), 0, 0);

				if (parentA instanceof Animal parentAnimalA && parentB instanceof Animal parentAnimalB)
					parentAnimalA.finalizeSpawnChildFromBreeding(level, parentAnimalB, newChild);

				level.addFreshEntityWithPassengers(newChild);

				PlayerUtil.giveXpToPlayer((ServerPlayer)ev.getCausedByPlayer(), AoASkills.FARMING.get(), PlayerUtil.getTimeBasedXpForLevel(PlayerUtil.getLevel(ev.getCausedByPlayer(), AoASkills.FARMING.get()), 3), false);
			}
		}
	}
}
