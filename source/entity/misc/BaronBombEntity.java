package net.tslat.aoa3.entity.misc;

import net.minecraft.entity.*;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Difficulty;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.common.registration.AoAGameRules;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.boss.BaronessEntity;
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
		this(AoAEntities.Misc.BARON_BOMB.get(), pl.world);

		ownerUUID = pl.getUniqueID();
		setPositionAndUpdate(pl.getPosX(), pl.getPosY(), pl.getPosZ());
	}

	public BaronBombEntity(BaronessEntity baroness) {
		this(AoAEntities.Misc.BARON_BOMB.get(), baroness.world);

		this.baroness = baroness;

		setPositionAndUpdate(baroness.getPosX(), baroness.getPosY(), baroness.getPosZ());
	}

	@Override
	protected float getEyeHeight(Pose pose, EntitySize size) {
		return 0.25f;
	}

	@Override
	public void addVelocity(double x, double y, double z) {}

	@Override
	public boolean canBePushed() {
		return isAlive();
	}

	@Override
	protected void registerData() {}

	@Override
	public void onCollideWithPlayer(PlayerEntity entity) {
		if (!world.isRemote && checkEntityCollision(entity)) {
			if (ownerUUID == null) {
				WorldUtil.createExplosion(baroness, world, getPosX(), getPosY() + 1, getPosZ(), 3.5f, WorldUtil.checkGameRule(world, AoAGameRules.STRONGER_MOB_GRIEFING) ? Explosion.Mode.DESTROY : Explosion.Mode.NONE, false);
			}
			else {
				WorldUtil.createExplosion(ownerPlayer, world, getPosX(), getPosY() + 1, getPosZ(), 2f);
			}

			remove();
		}
	}

	@Override
	public boolean canBeCollidedWith() {
		return isAlive();
	}

	@Override
	public void applyEntityCollision(Entity entity) {
		if (!world.isRemote && checkEntityCollision(entity)) {
			if (ownerPlayer != null) {
				WorldUtil.createExplosion(ownerPlayer, world, getPosX(), getPosY() + 1, getPosZ(), 2f);
			}
			else {
				WorldUtil.createExplosion(baroness, world, getPosX(), getPosY() + 1, getPosZ(), 3.5f, WorldUtil.checkGameRule(world, AoAGameRules.STRONGER_MOB_GRIEFING) ? Explosion.Mode.DESTROY : Explosion.Mode.NONE, false);
			}

			remove();
		}
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (!world.isRemote) {
			remove();

			if (!source.isExplosion() && source.getTrueSource() != baroness && source.getTrueSource() != ownerPlayer) {
				if (ownerPlayer != null) {
					WorldUtil.createExplosion(ownerPlayer, world, getPosX(), getPosY() + 1, getPosZ(), 2f);
				}
				else {
					WorldUtil.createExplosion(baroness, world, getPosX(), getPosY() + 1, getPosZ(), 3.5f, WorldUtil.checkGameRule(world, AoAGameRules.STRONGER_MOB_GRIEFING) ? Explosion.Mode.DESTROY : Explosion.Mode.NONE, false);
				}
			}

			return false;
		}

		return super.attackEntityFrom(source, amount);
	}

	@Override
	public boolean hitByEntity(Entity attacker) {
		if (!world.isRemote && checkEntityCollision(attacker))
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
				UUID ownerUUID = ((TameableEntity)target).getOwnerId();

				if (ownerUUID != null && ownerUUID.equals(this.ownerUUID))
					return false;
			}
		}

		return true;
	}

	private void updateOwner() {
		ownerPlayer = ownerPlayer == null ? world.getServer().getPlayerList().getPlayerByUUID(ownerUUID) : ownerPlayer;
	}

	@Override
	public void tick() {
		super.tick();

		BlockPos pos = new BlockPos(getPosX(), 0, getPosZ());
		Vec3d motion = getMotion();
		double motionX = motion.getX();
		double motionY = motion.getY();
		double motionZ = motion.getZ();

		if (!world.isRemote || world.isBlockLoaded(pos) && world.isAreaLoaded(pos, 1)) {
			if (!hasNoGravity())
				motionY -= 0.08D;
		}
		else if (this.getPosY() > 0.0D) {
			motionY = -0.1D;
		}
		else {
			motionY = 0.0D;
		}

		motionX = 0;
		motionZ = 0;

		move(MoverType.SELF, new Vec3d(motionX, motionY, motionZ));

		if (!world.isRemote) {
			if (ownerUUID == null && world.getDifficulty() == Difficulty.PEACEFUL) {
				remove();

				return;
			}

			timer--;

			if (timer == 16) {
				world.playSound(null, getPosX(), getPosY(), getPosZ(), AoASounds.BARON_BOMB_PRIME.get(), SoundCategory.HOSTILE, 1.0f, 1.0f);
			}
			else if (timer <= 0) {
				updateOwner();

				if (ownerPlayer != null) {
					WorldUtil.createExplosion(ownerPlayer, world, getPosX(), getPosY() + 1, getPosZ(), 2f);
				}
				else {
					WorldUtil.createExplosion(baroness, world, getPosX(), getPosY() + 1, getPosZ(), 4f);
				}

				remove();
			}
		}
	}

	@Override
	protected void readAdditional(CompoundNBT compound) {}

	@Override
	protected void writeAdditional(CompoundNBT compound) {}

	@Override
	public IPacket<?> createSpawnPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}
