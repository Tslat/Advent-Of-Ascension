package net.tslat.aoa3.entity.boss.elusive;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.entity.properties.BossEntity;
import net.tslat.aoa3.utils.ModUtil;
import net.tslat.aoa3.utils.StringUtil;

import javax.annotation.Nullable;
import java.util.HashSet;

public class EntityElusive extends AoAMeleeMob implements BossEntity {
	private static final ResourceLocation bossBarTexture = new ResourceLocation("aoa3", "textures/gui/bossbars/elusive.png");
	public static final float entityWidth = 0.7f;

	private final HashSet<EntityElusiveClone> summons = new HashSet<EntityElusiveClone>();

	private int summonCooldown = 300;
	private int teleportCooldown = 300;

	public EntityElusive(World world) {
		super(world, entityWidth, 1.625f);
	}

	@Override
	public float getEyeHeight() {
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
		return SoundsRegister.mobElusiveLiving;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobElusiveDeath;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobElusiveHit;
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityElusive;
	}

	@Override
	public boolean isNonBoss() {
		return false;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();

		if (world.isRemote && ticksExisted == 1)
			playMusic(this);
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (!world.isRemote) {
			if (summonCooldown > 0) {
				summonCooldown--;
			}
			else {
				summonCooldown = 300;

				EntityElusiveClone clone = new EntityElusiveClone(this);

				world.spawnEntity(clone);
				summons.add(clone);
			}

			if (teleportCooldown > 0) {
				teleportCooldown--;
			}
			else if (getAttackTarget() != null) {
				teleportCooldown = 300;

				Vec3d targetLookVec = (new Vec3d(posX - getAttackTarget().posX, (getEntityBoundingBox().minY + height / 2f) - getAttackTarget().posY + getAttackTarget().getEyeHeight(), posZ - getAttackTarget().posZ)).normalize();
				final double x = posX + (rand.nextDouble() - 0.5) * 8 - targetLookVec.x * 16;
				final double y = posY + (rand.nextInt(16) - 8) - targetLookVec.y * 16;
				final double z = posZ + (rand.nextDouble() - 0.5) * 8 - targetLookVec.z * 16;

				if (!world.getBlockState(new BlockPos(x, y, z)).isOpaqueCube() && !world.getBlockState(new BlockPos(x, y + 1, z)).isOpaqueCube())
					setPositionAndUpdate(x, y, z);
			}
		}
	}

	@Override
	public void onDeath(DamageSource cause) {
		super.onDeath(cause);

		if (!world.isRemote) {
			Entity source = cause.getTrueSource();
			EntityPlayer killer = null;

			if (source != null) {
				if (source instanceof EntityPlayer) {
					killer = (EntityPlayer)source;

					if (killer instanceof EntityPlayerMP && killer.isPotionActive(MobEffects.INVISIBILITY)) {
						boolean armourless = true;

						for (ItemStack stack : killer.getArmorInventoryList()) {
							if (stack.getItem() != Items.AIR)
								armourless = false;
						}

						if (armourless)
							ModUtil.completeAdvancement((EntityPlayerMP)killer, "abyss/activated_my_trap_card", "invisible_kill");
					}
				}
				else if (source instanceof EntityTameable && ((EntityTameable)source).getOwner() instanceof EntityPlayer) {
					killer = (EntityPlayer)((EntityTameable)source).getOwner();
				}
			}

			if (killer != null)
				StringUtil.sendMessageWithinRadius(StringUtil.getLocaleWithArguments("message.mob.elusive.kill", killer.getDisplayNameString()), this, 50);

			for (EntityElusiveClone summon : summons) {
				if (summon != null)
					summon.setDead();
			}
		}
	}

	@Override
	public ResourceLocation getBossBarTexture() {
		return bossBarTexture;
	}

	@Nullable
	@Override
	public SoundEvent getBossMusic() {
		return SoundsRegister.musicElusive;
	}

	@Override
	public void setAttackTarget(@Nullable EntityLivingBase target) {
		if (target instanceof BossEntity)
			return;

		super.setAttackTarget(target);
	}
}
