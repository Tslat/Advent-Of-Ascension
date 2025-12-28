package net.tslat.aoa3.player.ability.farming;

import com.google.gson.JsonObject;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.tslat.aoa3.common.registration.custom.AoAAbilities;
import net.tslat.aoa3.common.registration.entity.AoANpcs;
import net.tslat.aoa3.content.entity.npc.ambient.DryadSpriteEntity;
import net.tslat.aoa3.event.dynamic.DynamicEventSubscriber;
import net.tslat.aoa3.player.ability.generic.ScalableModAbility;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.util.EntitySpawningUtil;
import net.tslat.tme.api.particle.ParticleBuilder;
import net.tslat.tme.internal.networking.packet.TMEParticlePacket;
import net.tslat.tme.api.util.RandomUtil;

import java.util.List;

public class DryadSpriteSpawn extends ScalableModAbility {
	private final List<DynamicEventSubscriber<?>> eventSubscribers = List.of(
			listener(BlockEvent.BreakEvent.class, BlockEvent.BreakEvent::getPlayer, serverOnly(this::handleBlockBreak)));

	public DryadSpriteSpawn(AoASkill.Instance skill, JsonObject data) {
		super(AoAAbilities.DRYAD_SPRITE_SPAWN.get(), skill, data);
	}

	public DryadSpriteSpawn(AoASkill.Instance skill, CompoundTag data) {
		super(AoAAbilities.DRYAD_SPRITE_SPAWN.get(), skill, data);
	}

	@Override
	public List<DynamicEventSubscriber<?>> getEventSubscribers() {
		return this.eventSubscribers;
	}

	private void handleBlockBreak(BlockEvent.BreakEvent ev) {
		if (ev.getLevel() instanceof ServerLevel serverLevel && testAsChance()) {
			if (!(ev.getState().getBlock() instanceof CropBlock crop) || !crop.isMaxAge(ev.getState()) || getPlayer().getAbilities().instabuild)
				return;

			BlockPos pos = ev.getPos();

			if (serverLevel.getBlockState(pos.above()).isAir()) {
				DryadSpriteEntity dryad = EntitySpawningUtil.spawnEntity(serverLevel, AoANpcs.DRYAD_SPRITE.get(), new Vec3(pos.getX() + 0.5f, pos.getY() + 0.1d, pos.getZ() + 0.5f), MobSpawnType.TRIGGERED);

				if (dryad != null) {
					dryad.setOwner((ServerPlayer)getPlayer());

					TMEParticlePacket packet = new TMEParticlePacket();

					for(int i = 0; i < 20; ++i) {
						packet.particle(ParticleBuilder.forRandomPosInEntity(ParticleTypes.HAPPY_VILLAGER, dryad)
								.velocity(RandomUtil.scaledGaussianValue(0.02d), RandomUtil.scaledGaussianValue(0.02d), RandomUtil.scaledGaussianValue(0.02d)));
					}

					packet.sendToAllPlayersTrackingEntity(ev.getPlayer());
				}
			}
		}
	}
}
