package net.tslat.aoa3.entity.mobs.abyss;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoARangedMob;
import net.tslat.aoa3.entity.projectiles.mob.BaseMobProjectile;
import net.tslat.aoa3.entity.projectiles.mob.EntityMagicBall;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.StringUtil;

import javax.annotation.Nullable;

public class EntityWebReaper extends AoARangedMob {
	public static float entityWidth = 0.75f;
	public static float entityHeight = 3.5625f;
	private static final DataParameter<Integer> STAGE = EntityDataManager.<Integer>createKey(EntityWebReaper.class, DataSerializers.VARINT);
	private final AttributeModifier STAGE_HEALTH_MOD = new AttributeModifier("StageHealthModifier", 1, 2) {
		@Override
		public double getAmount() {
			return stageMod - 1;
		}
	};
	private final AttributeModifier STAGE_KNOCKBACK_MOD = new AttributeModifier("StageKnockbackModifier", 1, 2) {
		@Override
		public double getAmount() {
			return (stageMod - 1) * 0.83d;
		}
	};

	private int stage = 1;
	private float stageMod = 1;
	private boolean shouldHeal = false;

	public EntityWebReaper(World world) {
		super(world, entityWidth, entityHeight);

		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).applyModifier(STAGE_HEALTH_MOD);
		getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).applyModifier(STAGE_KNOCKBACK_MOD);
	}

	@Override
	protected void entityInit() {
		super.entityInit();

		dataManager.register(STAGE, 1);
	}

	@Override
	public float getEyeHeight() {
		return 2.375f * stageMod;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.6d;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 107;
	}

	@Override
	public double getBaseProjectileDamage() {
		return 13 * stageMod;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.207;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobWebReaperLiving;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobWebReaperDeath;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobWebReaperHit;
	}

	@Nullable
	@Override
	protected SoundEvent getShootSound() {
		return SoundsRegister.shotWebReaperFire;
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityWebReaper;
	}

	public int getStage() {
		return stage;
	}

	public float getStageMod() {
		return stageMod;
	}

	@Override
	protected boolean processInteract(EntityPlayer player, EnumHand hand) {
		ItemStack heldStack = player.getHeldItem(hand);

		if (heldStack.getItem() == ItemRegister.nightmareFlakes) {
			if (stage >= 10)
				return false;

			if (!world.isRemote) {
				heldStack.shrink(1);
				stage++;
				updateStage();
			}

			return true;
		}
		else if (heldStack.getItem() == Items.ENCHANTED_BOOK && stage > 1) {
			if (!world.isRemote) {
				player.setHeldItem(hand, new ItemStack(ItemRegister.bookOfShadows));

				if (stage <= 10) {
					stage += 5;
					updateStage();
				}
			}

			return true;
		}

		return false;
	}

	private void updateStage() {
		if (!world.isRemote) {
			dataManager.set(STAGE, stage);
		}
		else {
			stage = dataManager.get(STAGE);
		}

		stageMod = 1 + ((stage - 1) / 7.5f);

		getAttributeMap().onAttributeModified(getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH));
		getAttributeMap().onAttributeModified(getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE));
		setSize(entityWidth * stageMod, entityHeight * stageMod);
		setXpValue((int)getMaxHealth() / 10 + (int)((getMaxHealth() - getBaseMaxHealth()) * 0.1f));

		shouldHeal = true;
	}

	@Override
	public void notifyDataManagerChange(DataParameter<?> key) {
		if (key == STAGE)
			updateStage();
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound compound) {
		super.readEntityFromNBT(compound);

		if (compound.hasKey("WebReaperStage")) {
			stage = compound.getInteger("WebReaperStage");
			updateStage();
		}
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {
		super.writeEntityToNBT(compound);

		if (stage > 1)
			compound.setInteger("WebReaperStage", stage);
	}

	@Override
	protected BaseMobProjectile getNewProjectileInstance() {
		return new EntityMagicBall(this, Enums.MobProjectileType.MAGIC);
	}

	@Override
	protected void dropLoot(boolean wasRecentlyHit, int lootingModifier, DamageSource source) {
		super.dropLoot(wasRecentlyHit, lootingModifier + (int)stageMod - 1, source);
	}

	@Override
	public void doProjectileImpactEffect(BaseMobProjectile projectile, Entity target) {
		if (stage >= 15 && target instanceof EntityLivingBase)
			((EntityLivingBase)target).addPotionEffect(new PotionEffect(MobEffects.WITHER, 100, 1, false, true));
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (shouldHeal) {
			setHealth(getMaxHealth());

			shouldHeal = false;
		}
	}

	@Override
	public String getName() {
		if (hasCustomName()) {
			return getCustomNameTag();
		}
		else {
			String entityString = EntityList.getEntityString(this);
			String stagePrefix = (stage >= 15 ? "nightmare" : stage >= 5 ? "empowered" : null);

			if (entityString == null)
				entityString = "generic";

			if (stagePrefix != null)
				entityString = entityString + "." + stagePrefix;

			return StringUtil.getLocaleString("entity." + entityString + ".name");
		}
	}
}
