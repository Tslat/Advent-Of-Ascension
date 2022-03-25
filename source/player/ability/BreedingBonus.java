package net.tslat.aoa3.player.ability;

import com.google.gson.JsonObject;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.IServerWorld;
import net.minecraftforge.event.entity.living.BabyEntitySpawnEvent;
import net.tslat.aoa3.common.registration.custom.AoAAbilities;
import net.tslat.aoa3.common.registration.custom.AoASkills;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.util.PlayerUtil;

public class BreedingBonus extends ScalableModAbility {
	private static final ListenerType[] LISTENERS = new ListenerType[] {ListenerType.ANIMAL_BREED};


	public BreedingBonus(AoASkill.Instance skill, JsonObject data) {
		super(AoAAbilities.BREEDING_BONUS.get(), skill, data);
	}

	public BreedingBonus(AoASkill.Instance skill, CompoundNBT data) {
		super(AoAAbilities.BREEDING_BONUS.get(), skill, data);
	}

	@Override
	public ListenerType[] getListenerTypes() {
		return LISTENERS;
	}

	@Override
	public void handleAnimalBreed(BabyEntitySpawnEvent ev) {
		if (testAsChance()) {
			MobEntity parentA = ev.getParentA();
			AgeableEntity childB = (AgeableEntity)ev.getChild().getType().create(parentA.level);

			childB.setBaby(true);
			childB.moveTo(parentA.getX(), parentA.getY(), parentA.getZ(), 0, 0);
			((IServerWorld)parentA.level).addFreshEntityWithPassengers(childB);
			PlayerUtil.giveXpToPlayer((ServerPlayerEntity)ev.getCausedByPlayer(), AoASkills.FARMING.get(), PlayerUtil.getTimeBasedXpForLevel(PlayerUtil.getLevel(ev.getCausedByPlayer(), AoASkills.FARMING.get()), 3), false);
		}
	}
}
