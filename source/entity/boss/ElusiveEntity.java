package net.tslat.aoa3.entity.boss;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.Effects;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.BossInfo;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerBossInfo;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.packet.AoAPackets;
import net.tslat.aoa3.common.packet.packets.MusicPacket;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.entity.mob.misc.ElusiveCloneEntity;
import net.tslat.aoa3.util.AdvancementUtil;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.player.PlayerUtil;

import javax.annotation.Nullable;
import java.util.HashSet;

public class ElusiveEntity extends AoAMeleeMob {
	private final ServerBossInfo bossInfo = (ServerBossInfo)(new ServerBossInfo(getType().getDescription().copy().append(getDisplayName()), BossInfo.Color.GREEN, BossInfo.Overlay.NOTCHED_20)).setDarkenScreen(false).setCreateWorldFog(false);

	private final HashSet<ElusiveCloneEntity> summons = new HashSet<ElusiveCloneEntity>();

	private int summonCooldown = 300;
	private int teleportCooldown = 300;

	public ElusiveEntity(EntityType<? extends MonsterEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntitySize size) {
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
	public ActionResultType interactAt(PlayerEntity player, Vector3d vec, Hand hand) {
		if (player.getItemInHand(hand).getItem() == AoAItems.NIGHTMARE_FLAKES.get()) {
			if (!level.isClientSide() && ItemUtil.findInventoryItem(player, new ItemStack(AoAItems.BLANK_REALMSTONE.get()), true, 1)) {
				ItemUtil.givePlayerItemOrDrop(player, new ItemStack(AoAItems.GRECKON_REALMSTONE.get()));
				player.getItemInHand(hand).shrink(1);
			}

			return ActionResultType.SUCCESS;
		}

		return ActionResultType.PASS;
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

				Vector3d targetLookVec = (new Vector3d(getX() - getTarget().getX(), (getBoundingBox().minY + getBbHeight() / 2f) - getTarget().getY() + getTarget().getEyeHeight(), getZ() - getTarget().getZ())).normalize();
				final double x = getX() + (random.nextDouble() - 0.5) * 8 - targetLookVec.x * 16;
				final double y = getY() + (random.nextInt(16) - 8) - targetLookVec.y * 16;
				final double z = getZ() + (random.nextDouble() - 0.5) * 8 - targetLookVec.z * 16;

				if (!level.getBlockState(new BlockPos(x, y, z)).isSolidRender(level, new BlockPos(x, y, z)) && !level.getBlockState(new BlockPos(x, y + 1, z)).isSolidRender(level, new BlockPos(x, y + 1, z)))
					teleportTo(x, y, z);
			}
		}
	}

	@Override
	public void die(DamageSource cause) {
		super.die(cause);

		if (!level.isClientSide) {
			PlayerEntity killer = PlayerUtil.getPlayerOrOwnerIfApplicable(cause.getEntity());

			if (killer != null) {
				PlayerUtil.messageAllPlayersInRange(LocaleUtil.getLocaleMessage("message.mob.elusive.kill", killer.getDisplayName()), level, blockPosition(), 50);

				if (killer instanceof ServerPlayerEntity && killer.hasEffect(Effects.INVISIBILITY)) {
					boolean armourless = true;

					for (ItemStack stack : killer.getArmorSlots()) {
						if (stack.getItem() != Items.AIR)
							armourless = false;
					}

					if (armourless)
						AdvancementUtil.completeAdvancement((ServerPlayerEntity)killer, new ResourceLocation(AdventOfAscension.MOD_ID, "abyss/activated_my_trap_card"), "invisible_armourless_elusive_kill");
				}
			}

			for (ElusiveCloneEntity summon : summons) {
				if (summon != null)
					summon.remove();
			}
		}
	}

	@Override
	public void readAdditionalSaveData(CompoundNBT compound) {
		super.readAdditionalSaveData(compound);

		if (hasCustomName())
			bossInfo.setName(getType().getDescription().copy().append(getDisplayName()));
	}

	@Override
	public void setCustomName(@Nullable ITextComponent name) {
		super.setCustomName(name);

		bossInfo.setName(getType().getDescription().copy().append(getDisplayName()));
	}

	@Override
	protected void customServerAiStep() {
		super.customServerAiStep();

		bossInfo.setPercent(getHealth() / getMaxHealth());
	}

	@Override
	public void startSeenByPlayer(ServerPlayerEntity player) {
		super.startSeenByPlayer(player);

		AoAPackets.messagePlayer(player, new MusicPacket(true, AoASounds.ELUSIVE_MUSIC.getId()));
		bossInfo.addPlayer(player);
	}

	@Override
	public void stopSeenByPlayer(ServerPlayerEntity player) {
		super.stopSeenByPlayer(player);

		AoAPackets.messagePlayer(player, new MusicPacket(false, AoASounds.ELUSIVE_MUSIC.getId()));
		bossInfo.removePlayer(player);
	}

}
