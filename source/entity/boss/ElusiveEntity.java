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
import net.minecraft.util.math.Vec3d;
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
	private final ServerBossInfo bossInfo = (ServerBossInfo)(new ServerBossInfo(getType().getName().deepCopy().appendSibling(getDisplayName()), BossInfo.Color.GREEN, BossInfo.Overlay.NOTCHED_20)).setDarkenSky(false).setCreateFog(false);

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

	@Override
	protected double getBaseKnockbackResistance() {
		return 1;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 2000;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 15;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.2875;
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
	public boolean isNonBoss() {
		return false;
	}

	@Override
	public ActionResultType applyPlayerInteraction(PlayerEntity player, Vec3d vec, Hand hand) {
		if (player.getHeldItem(hand).getItem() == AoAItems.NIGHTMARE_FLAKES.get()) {
			if (!world.isRemote() && ItemUtil.findInventoryItem(player, new ItemStack(AoAItems.BLANK_REALMSTONE.get()), true, 1)) {
				ItemUtil.givePlayerItemOrDrop(player, new ItemStack(AoAItems.GRECKON_REALMSTONE.get()));
				player.getHeldItem(hand).shrink(1);
			}

			return ActionResultType.SUCCESS;
		}

		return ActionResultType.PASS;
	}

	@Override
	public void tick() {
		super.tick();

		if (!world.isRemote) {
			if (summonCooldown > 0) {
				summonCooldown--;
			}
			else {
				summonCooldown = 300;

				ElusiveCloneEntity clone = new ElusiveCloneEntity(this);

				world.addEntity(clone);
				summons.add(clone);
			}

			if (teleportCooldown > 0) {
				teleportCooldown--;
			}
			else if (getAttackTarget() != null) {
				teleportCooldown = 300;

				Vec3d targetLookVec = (new Vec3d(getPosX() - getAttackTarget().getPosX(), (getBoundingBox().minY + getHeight() / 2f) - getAttackTarget().getPosY() + getAttackTarget().getEyeHeight(), getPosZ() - getAttackTarget().getPosZ())).normalize();
				final double x = getPosX() + (rand.nextDouble() - 0.5) * 8 - targetLookVec.x * 16;
				final double y = getPosY() + (rand.nextInt(16) - 8) - targetLookVec.y * 16;
				final double z = getPosZ() + (rand.nextDouble() - 0.5) * 8 - targetLookVec.z * 16;

				if (!world.getBlockState(new BlockPos(x, y, z)).isOpaqueCube(world, new BlockPos(x, y, z)) && !world.getBlockState(new BlockPos(x, y + 1, z)).isOpaqueCube(world, new BlockPos(x, y + 1, z)))
					setPositionAndUpdate(x, y, z);
			}
		}
	}

	@Override
	public void onDeath(DamageSource cause) {
		super.onDeath(cause);

		if (!world.isRemote) {
			PlayerEntity killer = PlayerUtil.getPlayerOrOwnerIfApplicable(cause.getTrueSource());

			if (killer != null) {
				PlayerUtil.messageAllPlayersInRange(LocaleUtil.getLocaleMessage("message.mob.elusive.kill", killer.getDisplayName().getFormattedText()), world, getPosition(), 50);

				if (killer instanceof ServerPlayerEntity && killer.isPotionActive(Effects.INVISIBILITY)) {
					boolean armourless = true;

					for (ItemStack stack : killer.getArmorInventoryList()) {
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
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);

		if (hasCustomName())
			bossInfo.setName(getType().getName().deepCopy().appendSibling(getDisplayName()));
	}

	@Override
	public void setCustomName(@Nullable ITextComponent name) {
		super.setCustomName(name);

		bossInfo.setName(getType().getName().deepCopy().appendSibling(getDisplayName()));
	}

	@Override
	protected void updateAITasks() {
		super.updateAITasks();

		bossInfo.setPercent(getHealth() / getMaxHealth());
	}

	@Override
	public void addTrackingPlayer(ServerPlayerEntity player) {
		super.addTrackingPlayer(player);

		AoAPackets.messagePlayer(player, new MusicPacket(true, AoASounds.ELUSIVE_MUSIC.getId()));
		bossInfo.addPlayer(player);
	}

	@Override
	public void removeTrackingPlayer(ServerPlayerEntity player) {
		super.removeTrackingPlayer(player);

		AoAPackets.messagePlayer(player, new MusicPacket(false, AoASounds.ELUSIVE_MUSIC.getId()));
		bossInfo.removePlayer(player);
	}

}
