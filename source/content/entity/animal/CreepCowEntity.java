package net.tslat.aoa3.content.entity.animal;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
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
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.entity.AoAAnimals;
import net.tslat.aoa3.content.entity.base.AoAAnimal;
import net.tslat.aoa3.util.AdvancementUtil;
import net.tslat.aoa3.util.WorldUtil;

import javax.annotation.Nullable;

public class CreepCowEntity extends AoAAnimal {
	public CreepCowEntity(EntityType<? extends Animal> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	public InteractionResult mobInteract(Player player, InteractionHand hand) {
		ItemStack heldStack = player.getItemInHand(hand);

		if (heldStack.getItem() == Items.BUCKET) {
			if (!player.isCreative())
				heldStack.shrink(1);

			WorldUtil.createExplosion(this, level(), getX(), getY(), getZ(), 1.5f, Level.ExplosionInteraction.NONE);

			if (player instanceof ServerPlayer)
				AdvancementUtil.completeAdvancement((ServerPlayer)player, new ResourceLocation(AdventOfAscension.MOD_ID, "creeponia/worst_farmer_ever"), "creep_cow_milk");

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
		return Items.GUNPOWDER;
	}

	@Nullable
	@Override
	public AgeableMob getBreedOffspring(ServerLevel world, AgeableMob mate) {
		return new CreepCowEntity(AoAAnimals.CREEP_COW.get(), this.level());
	}
}
