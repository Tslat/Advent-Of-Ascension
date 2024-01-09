package net.tslat.aoa3.player.ability;

import com.google.gson.JsonObject;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.tslat.aoa3.common.registration.custom.AoAAbilities;
import net.tslat.aoa3.common.registration.entity.AoANpcs;
import net.tslat.aoa3.content.entity.npc.ambient.DryadSpriteEntity;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.util.EntitySpawningUtil;
import net.tslat.effectslib.api.particle.ParticleBuilder;
import net.tslat.effectslib.networking.packet.TELParticlePacket;
import net.tslat.smartbrainlib.util.RandomUtil;

public class DryadSpriteSpawn extends ScalableModAbility {
	private static final ListenerType[] LISTENERS = new ListenerType[] {ListenerType.BLOCK_BREAK};

	public DryadSpriteSpawn(AoASkill.Instance skill, JsonObject data) {
		super(AoAAbilities.DRYAD_SPRITE_SPAWN.get(), skill, data);
	}

	public DryadSpriteSpawn(AoASkill.Instance skill, CompoundTag data) {
		super(AoAAbilities.DRYAD_SPRITE_SPAWN.get(), skill, data);
	}

	@Override
	public ListenerType[] getListenerTypes() {
		return LISTENERS;
	}

	@Override
	public void handleBlockBreak(BlockEvent.BreakEvent ev) {
		if (ev.getLevel() instanceof ServerLevel serverLevel && testAsChance()) {
			if (!(ev.getState().getBlock() instanceof CropBlock crop) || !crop.isMaxAge(ev.getState()) || getPlayer().getAbilities().instabuild)
				return;

			BlockPos pos = ev.getPos();

			if (serverLevel.getBlockState(pos.above()).isAir()) {
				DryadSpriteEntity dryad = EntitySpawningUtil.spawnEntity(serverLevel, AoANpcs.DRYAD_SPRITE.get(), new Vec3(pos.getX() + 0.5f, pos.getY() + 0.1d, pos.getZ() + 0.5f), MobSpawnType.TRIGGERED);
				TELParticlePacket packet = new TELParticlePacket();

				for(int i = 0; i < 20; ++i) {
					packet.particle(ParticleBuilder.forRandomPosInEntity(ParticleTypes.HAPPY_VILLAGER, dryad)
							.velocity(RandomUtil.randomScaledGaussianValue(0.02d), RandomUtil.randomScaledGaussianValue(0.02d), RandomUtil.randomScaledGaussianValue(0.02d)));
				}

				packet.sendToAllPlayersTrackingEntity(serverLevel, ev.getPlayer());
			}
		}
	}
}
