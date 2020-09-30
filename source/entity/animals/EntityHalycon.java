package net.tslat.aoa3.entity.animals;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.entity.base.AoAAnimal;
import net.tslat.aoa3.utils.ItemUtil;

import javax.annotation.Nullable;

public class EntityHalycon extends AoAAnimal {
	public static float entityWidth = 0.9f;

	public EntityHalycon(World world) {
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
		return 15;
	}

	@Override
	public boolean processInteract(EntityPlayer player, EnumHand hand) {
		ItemStack heldStack = player.getHeldItem(hand);

		if (heldStack.getItem() == Items.BUCKET) {
			if (!player.capabilities.isCreativeMode)
				heldStack.shrink(1);

			player.playSound(SoundEvents.ENTITY_COW_MILK, 1.0f, 1.0f);

			if (heldStack.isEmpty()) {
				player.setHeldItem(hand, new ItemStack(ItemRegister.HALYCON_MILK));
			}
			else {
				ItemUtil.givePlayerItemOrDrop(player, new ItemStack(ItemRegister.HALYCON_MILK));
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
		return Item.getItemFromBlock(BlockRegister.HAVEN_GRASS_PLANT);
	}

	@Nullable
	@Override
	public EntityAgeable createChild(EntityAgeable mate) {
		return new EntityHalycon(world);
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityHalycon;
	}
}
