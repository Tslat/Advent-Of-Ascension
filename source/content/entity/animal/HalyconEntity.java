package net.tslat.aoa3.content.entity.animal;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.block.AoABlocks;
import net.tslat.aoa3.common.registration.entity.AoAAnimals;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.content.entity.base.AoAAnimalOld;
import net.tslat.aoa3.util.ItemUtil;
import org.jetbrains.annotations.Nullable;


public class HalyconEntity extends AoAAnimalOld {
	public HalyconEntity(EntityType<? extends Animal> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	public InteractionResult mobInteract(Player player, InteractionHand hand) {
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

			return InteractionResult.SUCCESS;
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
	public AgeableMob getBreedOffspring(ServerLevel world, AgeableMob mate) {
		return new HalyconEntity(AoAAnimals.HALYCON.get(), this.level());
	}
}
