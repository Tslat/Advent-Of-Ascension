package net.tslat.aoa3.content.entity.misc;

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
import net.tslat.aoa3.content.item.misc.summoning.BossSpawningItem;

public class BossItemEntity extends ItemEntity {
	public static final int lifetime = 200;
	private final PlayerEntity player;

	public BossItemEntity(World world, double x, double y, double z, ItemStack stack, PlayerEntity player) {
		super(AoAEntities.Misc.BOSS_ITEM.get(), world);

		this.lifespan = lifetime;
		this.player = player;
		this.yRot = random.nextFloat() * 360f;

		setPos(x, y, z);
		setItem(stack);
		setDeltaMovement(random.nextDouble() * 0.2d - 0.1d, 0.2d, random.nextDouble() * 0.2d - 0.1d);
	}

	public BossItemEntity(EntityType<? extends ItemEntity> entityType, World world) {
		super(entityType, world);

		lifespan = lifetime;
		player = null;
	}

	@Override
	public void playerTouch(PlayerEntity player) {
		if (!level.isClientSide && !hasPickUpDelay() && player.getUUID().equals(getOwner())) {
			ItemStack stack = this.getItem();
			int stackSize = stack.getCount();

			int hookResult = ForgeEventFactory.onItemPickup(this, player);

			if (hookResult < 0)
				return;

			ItemStack stackCopy = stack.copy();

			if (hookResult == 1 || stackSize <= 0 || player.inventory.add(stack) || stackCopy.getCount() > getItem().getCount()) {
				stackCopy.setCount(stackCopy.getCount() - getItem().getCount());
				BasicEventHooks.firePlayerItemPickupEvent(player, this, stackCopy);

				if (stack.isEmpty()) {
					player.take(this, stackSize);
					remove();
					stack.setCount(stackSize);
				}

				player.awardStat(Stats.ITEM_USED.get(getItem().getItem()), stackSize);
			}
		}
	}

	@Override
	public boolean canChangeDimensions() {
		return false;
	}

	@Override
	public void tick() {
		super.tick();

		if (lifespan == 6000)
			return;

		if (!(getItem().getItem() instanceof BossSpawningItem)) {
			remove();

			return;
		}

		if (!level.isClientSide && player == null) {
			remove();

			return;
		}

		BossSpawningItem bossItem = (BossSpawningItem)getItem().getItem();

		if (player instanceof ServerPlayerEntity) {
			if (tickCount == lifespan - 1) {
				if (bossItem.canSpawnHere(level, (ServerPlayerEntity)player, getX(), getY(), getZ())) {
					bossItem.spawnBoss(level, (ServerPlayerEntity)player, getX(), getY(), getZ());

					remove();
				}
				else {
					lifespan = 6000;
				}
			}

			return;
		}

		if (tickCount < lifespan)
			bossItem.handleTimerParticles(this, getX(), getY(), getZ(), lifespan, tickCount);
	}

	@Override
	public IPacket<?> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}
