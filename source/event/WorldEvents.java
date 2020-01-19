package net.tslat.aoa3.event;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.tslat.aoa3.common.registration.BlockRegister;
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
		if (ev.isHasVillageGenerated() && ConfigurationUtil.StructureConfig.overworld.ruinedTeleporterFrameSpawnChance > 0 && ev.getRand().nextInt(ConfigurationUtil.StructureConfig.overworld.ruinedTeleporterFrameSpawnChance) == 0) {
			Random rand = ev.getRand();
			int posX = ev.getChunkX() + rand.nextInt(16);
			int posZ = ev.getChunkZ() + rand.nextInt(15);
			int posY = rand.nextInt(10) + 10;
			Block centerBlock = ev.getWorld().getBlockState(new BlockPos(posX + 5, posY, posZ + 7)).getBlock();

			if (centerBlock != Blocks.AIR && centerBlock != BlockRegister.ancientRock)
				StructuresHandler.generateStructure("RuinedTeleporterFrame", ev.getWorld(), rand, new BlockPos(posX, posY, posZ));
		}
	}
}