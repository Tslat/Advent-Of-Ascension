package net.tslat.aoa3.player.ability;

import com.google.gson.JsonObject;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.ItemFishedEvent;
import net.tslat.aoa3.common.registration.custom.AoAAbilities;
import net.tslat.aoa3.common.registration.custom.AoASkills;
import net.tslat.aoa3.data.server.AoAHaulingFishReloadListener;
import net.tslat.aoa3.event.custom.events.HaulingItemFishedEvent;
import net.tslat.aoa3.event.custom.events.PlayerChangeXpEvent;
import net.tslat.aoa3.content.entity.misc.HaulingFishingBobberEntity;
import net.tslat.aoa3.player.skill.AoASkill;

import java.util.function.Function;

public class FishingTrapSpawn extends ScalableModAbility {
	private static final ListenerType[] LISTENERS = new ListenerType[] {ListenerType.FISHED_ITEM, ListenerType.GAIN_SKILL_XP};

	public FishingTrapSpawn(AoASkill.Instance skill, JsonObject data) {
		super(AoAAbilities.FISHING_TRAP_SPAWN.get(), skill, data);
	}

	public FishingTrapSpawn(AoASkill.Instance skill, CompoundNBT data) {
		super(AoAAbilities.FISHING_TRAP_SPAWN.get(), skill, data);
	}

	@Override
	public ListenerType[] getListenerTypes() {
		return LISTENERS;
	}

	@Override
	public void handleItemFished(ItemFishedEvent ev, boolean isHauling) {
		if (ev.getPlayer() instanceof ServerPlayerEntity && testAsChance()) {
			FishingBobberEntity bobber = ev.getHookEntity();
			PlayerEntity player = ev.getPlayer();
			World world = bobber.level;
			BlockPos pos = bobber.blockPosition();
			float luck = bobber.luck;
			boolean isLava = false;

			if (isHauling) {
				luck = ((HaulingItemFishedEvent)ev).getLuck();
				isLava = ((HaulingFishingBobberEntity)ev.getHookEntity()).getApplicableFluid().contains(Fluids.LAVA);
			}

			Function<World, Entity> trapEntityFunction = AoAHaulingFishReloadListener.getTrapListForBiome(world.getBiome(pos), isLava).getRandomElement((ServerPlayerEntity)player, luck);

			if (trapEntityFunction != null) {
				Entity trapEntity = trapEntityFunction.apply(world);
				double velX = player.getX() - bobber.getX();
				double velY = player.getY() - bobber.getY();
				double velZ = player.getZ() - bobber.getZ();

				trapEntity.setDeltaMovement(velX * 0.1d, velY * 0.1d + Math.sqrt(Math.sqrt(velX * velX + velY * velY + velZ * velZ)) * 0.15d, velZ * 0.1d);
				trapEntity.setPos(bobber.getX(), bobber.getY(), bobber.getZ());
				world.addFreshEntity(trapEntity);
			}
		}
	}

	@Override
	public void handleSkillXpGain(PlayerChangeXpEvent ev) {
		if (ev.getSkill().type() == AoASkills.HAULING.get())
			ev.setXpGain(ev.getNewXpGain() * 1.25f);
	}
}
