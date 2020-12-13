package net.tslat.aoa3.entity.animal;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.entity.base.AoAAnimal;
import net.tslat.aoa3.util.ItemUtil;

import javax.annotation.Nullable;

public class HalyconEntity extends AoAAnimal {
	public HalyconEntity(EntityType<? extends AnimalEntity> entityType, World world) {
		super(entityType, world);
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
		return 15;
	}

	@Override
	public boolean processInteract(PlayerEntity player, Hand hand) {
		ItemStack heldStack = player.getHeldItem(hand);

		if (heldStack.getItem() == Items.BUCKET) {
			if (!player.isCreative())
				heldStack.shrink(1);

			player.playSound(SoundEvents.ENTITY_COW_MILK, 1.0f, 1.0f);

			if (heldStack.isEmpty()) {
				player.setHeldItem(hand, new ItemStack(AoAItems.HALYCON_MILK.get()));
			}
			else {
				ItemUtil.givePlayerItemOrDrop(player, new ItemStack(AoAItems.HALYCON_MILK.get()));
			}

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
		return Item.getItemFromBlock(AoABlocks.HAVEN_GRASS_PLANT.get());
	}

	@Nullable
	@Override
	public AgeableEntity createChild(AgeableEntity mate) {
		return new HalyconEntity(AoAEntities.Animals.HALYCON.get(), world);
	}
}
