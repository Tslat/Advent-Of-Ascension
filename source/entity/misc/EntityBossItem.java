package net.tslat.aoa3.entity.misc;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.tslat.aoa3.item.misc.summon.BossSpawningItem;

public class EntityBossItem extends EntityItem {
	public static final int lifetime = 200;
	private final EntityPlayer player;

	public EntityBossItem(World world, double x, double y, double z, ItemStack stack, EntityPlayer player) {
		super(world, x, y, z, stack);

		lifespan = lifetime;
		this.player = player;
	}

	public EntityBossItem(World world) {
		super(world);

		lifespan = lifetime;
		player = null;
	}

	@Override
	public void onCollideWithPlayer(EntityPlayer player) {
		if (!this.world.isRemote && !cannotPickup() && player.getName().equals(getThrower())) {
			ItemStack stack = this.getItem();
			int stackSize = stack.getCount();

			int hookResult = ForgeEventFactory.onItemPickup(this, player);

			if (hookResult < 0)
				return;

			ItemStack stackCopy = stack.copy();

			if (hookResult == 1 || stackSize <= 0 || player.inventory.addItemStackToInventory(stack) || stackCopy.getCount() > getItem().getCount()) {
				stackCopy.setCount(stackCopy.getCount() - getItem().getCount());
				FMLCommonHandler.instance().firePlayerItemPickupEvent(player, this, stackCopy);

				if (stack.isEmpty()) {
					player.onItemPickup(this, stackSize);
					setDead();
					stack.setCount(stackSize);
				}

				player.addStat(StatList.getObjectUseStats(stack.getItem()), stackSize);
			}
		}
	}

	@Override
	public void onUpdate() {
		super.onUpdate();

		if (lifespan == 6000)
			return;

		if (!world.isRemote && (player == null || !(getItem().getItem() instanceof BossSpawningItem))) {
			setDead();

			return;
		}

		BossSpawningItem bossItem = (BossSpawningItem)getItem().getItem();

		if (!world.isRemote) {
			if (ticksExisted == lifespan) {
				if (bossItem.canSpawnHere(world, player, posX, posY, posZ)) {
					bossItem.spawnBoss(world, player, posX, posY, posZ);

					setDead();
				}
				else {
					lifespan = 6000;
					isDead = false;
				}
			}

			return;
		}

		if (ticksExisted < lifespan)
			bossItem.handleTimerParticles(this, posX, posY, posZ, lifespan, ticksExisted);
	}
}
