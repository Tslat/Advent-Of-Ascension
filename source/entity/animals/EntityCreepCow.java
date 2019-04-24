package net.tslat.aoa3.entity.animals;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.utils.ItemUtil;

public class EntityCreepCow extends EntityCow {
	public static float entityWidth = 0.9f;

	public EntityCreepCow(World world) {
		super(world);
	}

	@Override
	protected void initEntityAI() {
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIPanic(this, 2.0D));
		this.tasks.addTask(3, new EntityAITempt(this, 1.25D, Items.WHEAT, false));
		this.tasks.addTask(5, new EntityAIWanderAvoidWater(this, 1.0D));
		this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		this.tasks.addTask(7, new EntityAILookIdle(this));
	}

	@Override
	public boolean getCanSpawnHere() {
		return rand.nextInt(13) == 0 && super.getCanSpawnHere();
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20);
	}

	@Override
	public boolean processInteract(EntityPlayer player, EnumHand hand) {
		ItemStack heldStack = player.getHeldItem(hand);

		if (heldStack.getItem() == Items.BUCKET) {
			if (!player.capabilities.isCreativeMode)
				heldStack.shrink(1);

			world.createExplosion(this, posX, posY, posZ, 1.5f, false);
			player.playSound(SoundEvents.ENTITY_COW_MILK, 1.0f, 1.0f);

			if (heldStack.isEmpty()) {
				player.setHeldItem(hand, new ItemStack(ItemRegister.halyconMilk));
			}
			else {
				ItemUtil.givePlayerItemOrDrop(player, new ItemStack(ItemRegister.halyconMilk));
			}

			return true;
		}
		else {
			return super.processInteract(player, hand);
		}
	}

	@Override
	public EntityCow createChild(EntityAgeable ageable) {
		return null;
	}
}
