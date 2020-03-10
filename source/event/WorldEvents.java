package net.tslat.aoa3.event;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.entity.base.AoATrader;
import net.tslat.aoa3.entity.npcs.trader.*;
import net.tslat.aoa3.event.dimension.OverworldEvents;
import net.tslat.aoa3.structure.StructuresHandler;
import net.tslat.aoa3.utils.ConfigurationUtil;

import java.util.Random;

public class WorldEvents {
	@SubscribeEvent
	public void onWorldTick(TickEvent.WorldTickEvent ev) {
		if (ev.phase != TickEvent.Phase.END || ev.world.isRemote)
			return;

		int dim = ev.world.provider.getDimension();

		if (dim == 0)
			OverworldEvents.doTickCheck(ev);
	}

	@SubscribeEvent
	public void onChunkPopulate(PopulateChunkEvent.Post ev) {
		if (ev.isHasVillageGenerated()) {
			Random rand = ev.getRand();
			World world = ev.getWorld();

			if (ConfigurationUtil.StructureConfig.overworld.ruinedTeleporterFrameSpawnChance > 0 && rand.nextInt((int)Math.ceil(ConfigurationUtil.StructureConfig.overworld.ruinedTeleporterFrameSpawnChance / 8f)) == 0) {
				int posX = ev.getChunkX() + rand.nextInt(16);
				int posZ = ev.getChunkZ() + rand.nextInt(15);
				int posY = rand.nextInt(10) + 10;
				Block centerBlock = world.getBlockState(new BlockPos(posX + 5, posY, posZ + 7)).getBlock();

				if (centerBlock != Blocks.AIR && centerBlock != BlockRegister.ancientRock)
					StructuresHandler.generateStructure("RuinedTeleporterFrame", world, rand, new BlockPos(posX, posY, posZ));
			}

			if (rand.nextInt(20) == 0) {
				int posX = ev.getChunkX() + rand.nextInt(16);
				int posZ = ev.getChunkZ() + rand.nextInt(15);
				int posY = ev.getWorld().getHeight(posX, posZ);
				AoATrader trader;

				switch (rand.nextInt(1)) {
					case 0:
						trader = new EntityAssassin(world);
						break;
					case 1:
						trader = new EntityNaturalist(world);
						break;
					case 2:
						trader = new EntityRealmshifter(world);
						break;
					case 3:
						trader = new EntityTrollTrader(world);
						break;
					case 4:
					default:
						trader = new EntityUndeadHerald(world);
						break;
				}

				trader.setPosition(posX, posY, posZ);
				ev.getWorld().spawnEntity(trader);
			}
		}
	}
}