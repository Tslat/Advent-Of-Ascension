/*
package net.tslat.aoa3.content.entity.boss;

import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.util.*;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.Vec3;

import net.minecraft.world.BossInfo;
import net.minecraft.world.level.Level;
import net.minecraft.world.server.ServerBossInfo;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.packet.AoAPackets;
import net.tslat.aoa3.common.packet.packets.MusicPacket;

import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;
import net.tslat.aoa3.content.entity.mob.misc.ElusiveCloneEntity;
import net.tslat.aoa3.util.AdvancementUtil;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.PlayerUtil;

import javax.annotation.Nullable;
import java.util.HashSet;

public class ElusiveEntity extends AoAMeleeMob {
	private final ServerBossInfo bossInfo = (ServerBossInfo)(new ServerBossInfo(getType().getDescription().copy().append(getDisplayName()), BossInfo.Color.GREEN, BossInfo.Overlay.NOTCHED_20)).setDarkenScreen(false).setCreateWorldFog(false);

	private final HashSet<ElusiveCloneEntity> summons = new HashSet<ElusiveCloneEntity>();

	private int summonCooldown = 300;
	private int teleportCooldown = 300;

	public ElusiveEntity(EntityType<? extends Monster> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions size) {
		return 1.5f;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_ELUSIVE_AMBIENT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_ELUSIVE_DEATH.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_ELUSIVE_HURT.get();
	}

	@Override
	public boolean canChangeDimensions() {
		return false;
	}

	@Override
	public InteractionResult interactAt(Player player, Vec3 vec, InteractionHand hand) {
		if (player.getItemInHand(hand).getItem() == AoAItems.NIGHTMARE_FLAKES.get()) {
			if (!level.isClientSide() && ItemUtil.findInventoryItem(player, new ItemStack(AoAItems.BLANK_REALMSTONE.get()), true, 1)) {
				ItemUtil.givePlayerItemOrDrop(player, new ItemStack(AoAItems.GRECKON_REALMSTONE.get()));
				player.getItemInHand(hand).shrink(1);
			}

			return InteractionResult.SUCCESS;
		}

		return InteractionResult.PASS;
	}

	@Override
	public void tick() {
		super.tick();

		if (!level.isClientSide) {
			if (summonCooldown > 0) {
				summonCooldown--;
			}
			else {
				summonCooldown = 300;

				ElusiveCloneEntity clone = new ElusiveCloneEntity(this);

				level.addFreshEntity(clone);
				summons.add(clone);
			}

			if (teleportCooldown > 0) {
				teleportCooldown--;
			}
			else if (getTarget() != null) {
				teleportCooldown = 300;

				Vec3 targetLookVec = (new Vec3(getX() - getTarget().getX(), (getBoundingBox().minY + getBbHeight() / 2f) - getTarget().getY() + getTarget().getEyeHeight(), getZ() - getTarget().getZ())).normalize();
				final double x = getX() + (random.nextDouble() - 0.5) * 8 - targetLookVec.x * 16;
				final double y = getY() + (random.nextInt(16) - 8) - targetLookVec.y * 16;
				final double z = getZ() + (random.nextDouble() - 0.5) * 8 - targetLookVec.z * 16;

				if (!level.getBlockState(new BlockPos(x, y, z)).isSolidRender(level, new BlockPos(x, y, z)) && !level.getBlockState(new BlockPos(x, y + 1, z)).isSolidRender(level, new BlockPos(x, y + 1, z)))
					teleportTo(x, y, z);
			}

			float healthPercent = getHealth() / getMaxHealth();

			if (healthPercent != bossInfo.getPercent())
				bossInfo.setPercent(healthPercent);
		}
	}

	@Override
	public void die(DamageSource cause) {
		super.die(cause);

		if (!level.isClientSide) {
			Player killer = PlayerUtil.getPlayerOrOwnerIfApplicable(cause.getEntity());

			if (killer != null) {
				PlayerUtil.messageAllPlayersInRange(LocaleUtil.getLocaleMessage(AoAMobs.ELUSIVE.get().getDescriptionId() + ".kill", killer.getDisplayName()), level, blockPosition(), 50);

				if (killer instanceof ServerPlayer && killer.hasEffect(MobEffects.INVISIBILITY)) {
					boolean armourless = true;

					for (ItemStack stack : killer.getArmorSlots()) {
						if (stack.getItem() != Items.AIR)
							armourless = false;
					}

					if (armourless)
						AdvancementUtil.completeAdvancement((ServerPlayer)killer, new ResourceLocation(AdventOfAscension.MOD_ID, "abyss/activated_my_trap_card"), "invisible_armourless_elusive_kill");
				}
			}

			for (ElusiveCloneEntity summon : summons) {
				if (summon != null)
					summon.remove();
			}
		}
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);

		if (hasCustomName())
			bossInfo.setName(getType().getDescription().copy().append(getDisplayName()));
	}

	@Override
	public void setCustomName(@Nullable TextComponent name) {
		super.setCustomName(name);

		bossInfo.setName(getType().getDescription().copy().append(getDisplayName()));
	}

	@Override
	public void startSeenByPlayer(ServerPlayer player) {
		super.startSeenByPlayer(player);

		AoAPackets.messagePlayer(player, new MusicPacket(true, AoASounds.ELUSIVE_MUSIC.getId()));
		bossInfo.addPlayer(player);
	}

	@Override
	public void stopSeenByPlayer(ServerPlayer player) {
		super.stopSeenByPlayer(player);

		AoAPackets.messagePlayer(player, new MusicPacket(false, AoASounds.ELUSIVE_MUSIC.getId()));
		bossInfo.removePlayer(player);
	}

}
*/
