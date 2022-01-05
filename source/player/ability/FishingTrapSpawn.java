package net.tslat.aoa3.player.ability;

import com.google.gson.JsonObject;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.ItemFishedEvent;
import net.tslat.aoa3.common.registration.custom.AoAAbilities;
import net.tslat.aoa3.common.registration.custom.AoASkills;
import net.tslat.aoa3.data.server.AoAHaulingFishReloadListener;
import net.tslat.aoa3.event.custom.events.HaulingItemFishedEvent;
import net.tslat.aoa3.event.custom.events.PlayerChangeXpEvent;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.NumberUtil;
import net.tslat.aoa3.util.RandomUtil;

import java.util.function.Function;

public class FishingTrapSpawn extends AoAAbility.Instance {
	private static final ListenerType[] LISTENERS = new ListenerType[] {ListenerType.FISHED_ITEM, ListenerType.GAIN_SKILL_XP};

	private final float baseChance;
	private final float perLevelChance;

	public FishingTrapSpawn(AoASkill.Instance skill, JsonObject data) {
		super(AoAAbilities.FISHING_TRAP_SPAWN.get(), skill, data);

		this.baseChance = JSONUtils.getAsFloat(data, "base_chance", 0);
		this.perLevelChance = JSONUtils.getAsFloat(data, "per_level_chance", 0);
	}

	public FishingTrapSpawn(AoASkill.Instance skill, CompoundNBT data) {
		super(AoAAbilities.FISHING_TRAP_SPAWN.get(), skill, data);

		this.baseChance = data.getFloat("base_chance");
		this.perLevelChance = data.getFloat("per_level_chance");
	}

	@Override
	public ListenerType[] getListenerTypes() {
		return LISTENERS;
	}

	@Override
	protected void updateDescription(TranslationTextComponent defaultDescription) {
		super.updateDescription(new TranslationTextComponent(defaultDescription.getKey(),
				LocaleUtil.getAbilityValueDesc(
						baseChance != 0,
						perLevelChance != 0,
						true,
						NumberUtil.roundToNthDecimalPlace(baseChance, 2), NumberUtil.roundToNthDecimalPlace(perLevelChance, 4))));
	}

	@Override
	public void handleItemFished(ItemFishedEvent ev, boolean isHauling) {
		if (ev.getPlayer() instanceof ServerPlayerEntity && RandomUtil.percentChance(baseChance + (perLevelChance > 0 ? perLevelChance * skill.getLevel(false) : 0))) {
			FishingBobberEntity bobber = ev.getHookEntity();
			PlayerEntity player = ev.getPlayer();
			World world = bobber.level;
			BlockPos pos = bobber.blockPosition();
			float luck = isHauling ? ((HaulingItemFishedEvent)ev).getLuck() : bobber.luck;
			Function<World, Entity> trapEntityFunction = AoAHaulingFishReloadListener.getTrapListForBiome(world.getBiome(pos), world.getFluidState(pos).is(FluidTags.LAVA)).getRandomElement((ServerPlayerEntity)player, luck);

			if (trapEntityFunction != null) {
				Entity trapEntity = trapEntityFunction.apply(world);
				double velX = player.getX() - bobber.getX();
				double velY = player.getY() - bobber.getY();
				double velZ = player.getZ() - bobber.getZ();

				trapEntity.setDeltaMovement(velX * 0.1d, velY * 0.1d + Math.sqrt(Math.sqrt(velX * velX + velY * velY + velZ * velZ)) * 0.1d, velZ * 0.1d);
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

	@Override
	public CompoundNBT getSyncData(boolean forClientSetup) {
		CompoundNBT data = super.getSyncData(forClientSetup);

		if (forClientSetup) {
			data.putFloat("base_chance", this.baseChance);
			data.putFloat("per_level_chance", this.perLevelChance);
		}

		return data;
	}
}
