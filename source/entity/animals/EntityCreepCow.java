package net.tslat.aoa3.entity.animals;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.entity.base.AoAAnimal;
import net.tslat.aoa3.utils.ModUtil;
import net.tslat.aoa3.utils.WorldUtil;

import javax.annotation.Nullable;

public class EntityCreepCow extends AoAAnimal {
	public static float entityWidth = 0.9f;

	public EntityCreepCow(World world) {
		super(world, entityWidth, 1.4f);
	}

	@Override
	protected double getBaseMaxHealth() {
		return 20d;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.2d;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0;
	}

	@Override
	protected int getSpawnChanceFactor() {
		return 13;
	}

	@Override
	public boolean processInteract(EntityPlayer player, EnumHand hand) {
		ItemStack heldStack = player.getHeldItem(hand);

		if (heldStack.getItem() == Items.BUCKET) {
			if (!player.capabilities.isCreativeMode)
				heldStack.shrink(1);

			WorldUtil.createExplosion(this, world, posX, posY, posZ, 1.5f, false);

			if (player instanceof EntityPlayerMP)
				ModUtil.completeAdvancement((EntityPlayerMP)player, "creeponia/worst_farmer_ever", "creep_cow_milk");

			return true;
		}
		else {
			return super.processInteract(player, hand);
		}
	}

	@Override
	protected boolean isBreedable() {
		return true;
	}

	@Nullable
	@Override
	protected Item getTemptItem() {
		return Items.GUNPOWDER;
	}

	@Nullable
	@Override
	public EntityAgeable createChild(EntityAgeable mate) {
		return new EntityCreepCow(world);
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityCreepCow;
	}
}
