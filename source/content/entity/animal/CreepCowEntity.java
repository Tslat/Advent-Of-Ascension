package net.tslat.aoa3.content.entity.animal;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.content.entity.base.AoAAnimal;
import net.tslat.aoa3.util.AdvancementUtil;
import net.tslat.aoa3.util.WorldUtil;

import javax.annotation.Nullable;

public class CreepCowEntity extends AoAAnimal {
	public CreepCowEntity(EntityType<? extends AnimalEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	public ActionResultType mobInteract(PlayerEntity player, Hand hand) {
		ItemStack heldStack = player.getItemInHand(hand);

		if (heldStack.getItem() == Items.BUCKET) {
			if (!player.isCreative())
				heldStack.shrink(1);

			WorldUtil.createExplosion(this, level, getX(), getY(), getZ(), 1.5f, Explosion.Mode.NONE);

			if (player instanceof ServerPlayerEntity)
				AdvancementUtil.completeAdvancement((ServerPlayerEntity)player, new ResourceLocation(AdventOfAscension.MOD_ID, "creeponia/worst_farmer_ever"), "creep_cow_milk");

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
		return Items.GUNPOWDER;
	}

	@Nullable
	@Override
	public AgeableEntity getBreedOffspring(ServerWorld world, AgeableEntity mate) {
		return new CreepCowEntity(AoAEntities.Animals.CREEP_COW.get(), this.level);
	}
}
