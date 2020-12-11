package net.tslat.aoa3.entity.misc;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.stats.Stats;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fml.hooks.BasicEventHooks;
import net.minecraftforge.fml.network.NetworkHooks;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.item.misc.summoning.BossSpawningItem;

public class BossItemEntity extends ItemEntity {
	public static final int lifetime = 200;
	private final PlayerEntity player;

	public BossItemEntity(World world, double x, double y, double z, ItemStack stack, PlayerEntity player) {
		super(AoAEntities.Misc.BOSS_ITEM.get(), world);

		this.lifespan = lifetime;
		this.player = player;
		this.rotationYaw = rand.nextFloat() * 360f;

		setPosition(x, y, z);
		setItem(stack);
		setMotion(rand.nextDouble() * 0.2d - 0.1d, 0.2d, rand.nextDouble() * 0.2d - 0.1d);
	}

	public BossItemEntity(EntityType<? extends ItemEntity> entityType, World world) {
		super(entityType, world);

		lifespan = lifetime;
		player = null;
	}

	@Override
	public void onCollideWithPlayer(PlayerEntity player) {
		if (!world.isRemote && !cannotPickup() && player.getUniqueID().equals(getOwnerId())) {
			ItemStack stack = this.getItem();
			int stackSize = stack.getCount();

			int hookResult = ForgeEventFactory.onItemPickup(this, player);

			if (hookResult < 0)
				return;

			ItemStack stackCopy = stack.copy();

			if (hookResult == 1 || stackSize <= 0 || player.inventory.addItemStackToInventory(stack) || stackCopy.getCount() > getItem().getCount()) {
				stackCopy.setCount(stackCopy.getCount() - getItem().getCount());
				BasicEventHooks.firePlayerItemPickupEvent(player, this, stackCopy);

				if (stack.isEmpty()) {
					player.onItemPickup(this, stackSize);
					remove();
					stack.setCount(stackSize);
				}

				player.addStat(Stats.ITEM_USED.get(getItem().getItem()), stackSize);
			}
		}
	}

	@Override
	public void tick() {
		super.tick();

		if (lifespan == 6000)
			return;

		if (!world.isRemote && (player == null || !(getItem().getItem() instanceof BossSpawningItem))) {
			remove();

			return;
		}

		BossSpawningItem bossItem = (BossSpawningItem)getItem().getItem();

		if (player instanceof ServerPlayerEntity) {
			if (ticksExisted == lifespan - 1) {
				if (bossItem.canSpawnHere(world, (ServerPlayerEntity)player, getPosX(), getPosY(), getPosZ())) {
					bossItem.spawnBoss(world, (ServerPlayerEntity)player, getPosX(), getPosY(), getPosZ());

					remove();
				}
				else {
					lifespan = 6000;
				}
			}

			return;
		}

		if (ticksExisted < lifespan)
			bossItem.handleTimerParticles(this, getPosX(), getPosY(), getPosZ(), lifespan, ticksExisted);
	}

	@Override
	public IPacket<?> createSpawnPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}
