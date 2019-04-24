package net.tslat.aoa3.entity.base;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.INpc;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.tslat.aoa3.utils.PlayerUtil;

import javax.annotation.Nullable;

public abstract class AoAAmbientNPC extends EntityCreature implements INpc {
	public AoAAmbientNPC(World world, float entityWidth, float entityHeight) {
		super(world);
		setSize(entityWidth, entityHeight);
	}

	@Override
	protected void initEntityAI() {
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIPanic(this, 1.0d));
		this.tasks.addTask(2, new EntityAIAvoidEntity(this, AoAMeleeMob.class, 8.0f, 0.8f, 1.0f));
		this.tasks.addTask(3, new EntityAIMoveIndoors(this));
		this.tasks.addTask(4, new EntityAIWanderAvoidWater(this, 0.6));
		this.tasks.addTask(5, new EntityAIWatchClosest(this, EntityPlayer.class, 3.0f, 1.0f));
		this.tasks.addTask(5, new EntityAILookIdle(this));
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return null;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return null;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return null;
	}

	@Override
	public boolean isCreatureType(EnumCreatureType type, boolean forSpawnCount) {
		return type == EnumCreatureType.AMBIENT;
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(getBaseMaxHealth());
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(getBaseMovementSpeed());
		getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(16);
	}

	protected abstract double getBaseMaxHealth();

	protected abstract double getBaseMovementSpeed();

	@Nullable
	protected abstract ITextComponent getInteractMessage();

	protected boolean isFixedTradesList() {
		return false;
	}

	@Override
	public boolean getCanSpawnHere() {
		return checkSpawnChance() && isValidLightLevel() && canSpawnOnBlock(world.getBlockState(getPosition().down()));
	}

	protected boolean canSpawnOnBlock(IBlockState block) {
		return block.canEntitySpawn(this);
	}

	protected int getSpawnChanceFactor() {
		return 1;
	}

	private boolean checkSpawnChance() {
		return getSpawnChanceFactor() <= 1 || rand.nextInt(getSpawnChanceFactor()) == 0;
	}

	protected boolean isValidLightLevel() {
		BlockPos blockpos = new BlockPos(this.posX, this.getEntityBoundingBox().minY, this.posZ);

		if (this.world.getLightFor(EnumSkyBlock.SKY, blockpos) > this.rand.nextInt(32)) {
			return true;
		}
		else {
			int i = this.world.getLightFromNeighbors(blockpos);

			if (this.world.isThundering()) {
				int j = this.world.getSkylightSubtracted();
				this.world.setSkylightSubtracted(10);
				i = this.world.getLightFromNeighbors(blockpos);
				this.world.setSkylightSubtracted(j);
			}

			return i > this.rand.nextInt(8);
		}
	}

	@Override
	protected boolean processInteract(EntityPlayer player, EnumHand hand) {
		ItemStack heldStack = player.getHeldItem(hand);

		if (heldStack.getItem() == Items.NAME_TAG) {
			heldStack.interactWithEntity(player, this, hand);

			return true;
		}

		if (!world.isRemote) {
			if (hand == EnumHand.MAIN_HAND) {
				ITextComponent msg = getInteractMessage();

				if (msg != null) {
					msg.setStyle(new Style().setColor(TextFormatting.GRAY));
					PlayerUtil.getAdventPlayer(player).sendPlayerMessage(msg);
				}
			}
		}

		return super.processInteract(player, hand);
	}

	@Override
	protected boolean canDespawn() {
		return world.provider.getDimension() == 0;
	}
}
