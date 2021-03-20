package net.tslat.aoa3.entity.animal;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
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
	public ActionResultType mobInteract(PlayerEntity player, Hand hand) {
		ItemStack heldStack = player.getItemInHand(hand);

		if (heldStack.getItem() == Items.BUCKET) {
			if (!player.isCreative())
				heldStack.shrink(1);

			player.playSound(SoundEvents.COW_MILK, 1.0f, 1.0f);

			if (heldStack.isEmpty()) {
				player.setItemInHand(hand, new ItemStack(AoAItems.HALYCON_MILK.get()));
			}
			else {
				ItemUtil.givePlayerItemOrDrop(player, new ItemStack(AoAItems.HALYCON_MILK.get()));
			}

			return ActionResultType.SUCCESS;
		}
		else {
			return super.mobInteract(player, hand);
		}
	}

	@Override
	protected boolean isBreedable() {
		return true;
	}

	@Nullable
	@Override
	protected Item getTemptItem() {
		return Item.byBlock(AoABlocks.HAVEN_GRASS_PLANT.get());
	}

	@Nullable
	@Override
	public AgeableEntity getBreedOffspring(ServerWorld world, AgeableEntity mate) {
		return new HalyconEntity(AoAEntities.Animals.HALYCON.get(), this.level);
	}
}
