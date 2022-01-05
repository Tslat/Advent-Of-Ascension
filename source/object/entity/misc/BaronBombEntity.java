package net.tslat.aoa3.object.entity.misc;

import net.minecraft.entity.*;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.Difficulty;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.common.registration.AoAGameRules;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.object.entity.boss.BaronessEntity;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.WorldUtil;

import java.util.UUID;

public class BaronBombEntity extends Entity {
	private int timer = 150;
	private UUID ownerUUID = null;
	private PlayerEntity ownerPlayer = null;
	private BaronessEntity baroness = null;

	public BaronBombEntity(EntityType<? extends Entity> entityType, World world) {
		super(entityType, world);
	}

	public BaronBombEntity(PlayerEntity pl) {
		this(AoAEntities.Misc.BARON_BOMB.get(), pl.level);

		ownerUUID = pl.getUUID();
		teleportTo(pl.getX(), pl.getY(), pl.getZ());
	}

	public BaronBombEntity(BaronessEntity baroness) {
		this(AoAEntities.Misc.BARON_BOMB.get(), baroness.level);

		this.baroness = baroness;

		teleportTo(baroness.getX(), baroness.getY(), baroness.getZ());
	}

	@Override
	protected float getEyeHeight(Pose pose, EntitySize size) {
		return 0.25f;
	}

	@Override
	public void push(double x, double y, double z) {}

	@Override
	public boolean isPushable() {
		return isAlive();
	}

	@Override
	public boolean canChangeDimensions() {
		return false;
	}

	@Override
	protected void defineSynchedData() {}

	@Override
	public void playerTouch(PlayerEntity entity) {
		if (!level.isClientSide && checkEntityCollision(entity)) {
			if (ownerUUID == null) {
				WorldUtil.createExplosion(baroness, level, getX(), getY() + 1, getZ(), 3.5f, AoAGameRules.checkStrongerMobGriefing(level, this) ? Explosion.Mode.DESTROY : Explosion.Mode.NONE, false);
			}
			else {
				WorldUtil.createExplosion(ownerPlayer, level, getX(), getY() + 1, getZ(), 2.3f);
			}

			remove();
		}
	}

	@Override
	public boolean isPickable() {
		return isAlive();
	}

	@Override
	public void push(Entity entity) {
		if (!level.isClientSide && checkEntityCollision(entity)) {
			if (ownerPlayer != null) {
				WorldUtil.createExplosion(ownerPlayer, level, getX(), getY() + 1, getZ(), 2.3f);
			}
			else {
				WorldUtil.createExplosion(baroness, level, getX(), getY() + 1, getZ(), 3.5f, AoAGameRules.checkStrongerMobGriefing(level, this) ? Explosion.Mode.DESTROY : Explosion.Mode.NONE, false);
			}

			remove();
		}
	}

	@Override
	public boolean hurt(DamageSource source, float amount) {
		if (!level.isClientSide) {
			remove();

			if (!source.isExplosion() && source.getEntity() != baroness && source.getEntity() != ownerPlayer) {
				if (ownerPlayer != null) {
					WorldUtil.createExplosion(ownerPlayer, level, getX(), getY() + 1, getZ(), 2.3f);
				}
				else {
					WorldUtil.createExplosion(baroness, level, getX(), getY() + 1, getZ(), 3.5f, AoAGameRules.checkStrongerMobGriefing(level, this) ? Explosion.Mode.DESTROY : Explosion.Mode.NONE, false);
				}
			}

			return false;
		}

		return super.hurt(source, amount);
	}

	@Override
	public boolean skipAttackInteraction(Entity attacker) {
		if (!level.isClientSide && checkEntityCollision(attacker))
			remove();

		return true;
	}

	private boolean checkEntityCollision(Entity target) {
		if (ownerUUID == null) {
			return !(target instanceof BaronessEntity);
		}
		else {
			if (target instanceof PlayerEntity) {
				updateOwner();

				if (ownerPlayer != null && !EntityUtil.canPvp(ownerPlayer, (PlayerEntity)target))
					return false;
			}
			else if (target instanceof TameableEntity) {
				UUID ownerUUID = ((TameableEntity)target).getOwnerUUID();

				if (ownerUUID != null && ownerUUID.equals(this.ownerUUID))
					return false;
			}
		}

		return true;
	}

	private void updateOwner() {
		ownerPlayer = ownerPlayer == null ? level.getServer().getPlayerList().getPlayer(ownerUUID) : ownerPlayer;
	}

	@Override
	public void tick() {
		super.tick();

		BlockPos pos = new BlockPos(getX(), 0, getZ());
		Vector3d motion = getDeltaMovement();
		double motionY = motion.y();

		if (!level.isClientSide || level.hasChunkAt(pos) && level.isAreaLoaded(pos, 1)) {
			if (!isNoGravity())
				motionY -= 0.08D;
		}
		else if (this.getY() > 0.0D) {
			motionY = -0.1D;
		}
		else {
			motionY = 0.0D;
		}

		move(MoverType.SELF, new Vector3d(0, motionY, 0));

		if (!level.isClientSide) {
			if (ownerUUID == null && level.getDifficulty() == Difficulty.PEACEFUL) {
				remove();

				return;
			}

			timer--;

			if (timer == 16) {
				level.playSound(null, getX(), getY(), getZ(), AoASounds.BARON_BOMB_PRIME.get(), SoundCategory.HOSTILE, 1.0f, 1.0f);
			}
			else if (timer <= 0) {
				updateOwner();

				if (ownerPlayer != null) {
					WorldUtil.createExplosion(ownerPlayer, level, getX(), getY() + 1, getZ(), 2.3f);
				}
				else {
					WorldUtil.createExplosion(baroness, level, getX(), getY() + 1, getZ(), 4f);
				}

				remove();
			}
		}
	}

	@Override
	protected void readAdditionalSaveData(CompoundNBT compound) {}

	@Override
	protected void addAdditionalSaveData(CompoundNBT compound) {}

	@Override
	public IPacket<?> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}
