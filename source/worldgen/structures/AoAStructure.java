package net.tslat.aoa3.worldgen.structures;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.tileentity.MobSpawnerTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.tslat.aoa3.command.StructureCommand;
import net.tslat.aoa3.worldgen.WorldGenerator;

import java.util.Random;

public abstract class AoAStructure extends WorldGenerator {
	private final String name;
	private boolean isWorldGen = true;

	public AoAStructure(String name) {
	    this.name = name;

	    StructuresHandler.registerStructure(this);
    }

    private AoAStructure() {
	    this.name = "EMPTY";
    }

    public final String getName() {
	    return name;
    }

    protected boolean replacesBlocks() {
		return false;
	}

    public boolean doPreChecks(IWorld world, Random rand, BlockPos basePos, BlockState... groundBlocks) {
	    return true;
    }

    @Override
    public final boolean generate(IWorld world, Random rand, BlockPos position) {
		isWorldGen = rand != null;

		if (rand == null)
			rand = new Random();

		if (StructureCommand.isConverting) {
			rand = new Random() {
				@Override
				public boolean nextBoolean() {
					return true;
				}

				@Override
				public int nextInt(int val) {
					return 0;
				}
			};
		}

        build(world, rand, new BlockPos.Mutable(position));
        doPostBuildOps(world, rand, position);
        spawnEntities(world, rand, position);

        return true;
    }

    protected abstract void build(IWorld world, Random rand, BlockPos basePos);

    protected void doPostBuildOps(IWorld world, Random rand, BlockPos basePos) {}

    protected void spawnEntities(IWorld world, Random rand, BlockPos basePos) {}

    public final void addBlock(IWorld world, BlockPos pos, int xCoordOffset, int yCoordOffset, int zCoordOffset, BlockState block) {
    	if (StructureCommand.isConverting) {
			StructureCommand.conversionStateConsumer.accept(block, new BlockPos(xCoordOffset, yCoordOffset, zCoordOffset));

			return;
	    }

    	if (replacesBlocks() || world.isAirBlock(pos.add(xCoordOffset, yCoordOffset, zCoordOffset)))
    		world.setBlockState(pos.add(xCoordOffset, yCoordOffset, zCoordOffset), block, isWorldGen ? 2 : 3);
    }

    protected void initSpawner(IWorld world, BlockPos pos, EntityType<?> entityType) {
    	if (StructureCommand.isConverting) {
    		StructureCommand.conversionSpawnerConsumer.accept(entityType, pos);

    		return;
	    }

    	TileEntity spawnerTileEntity = world.getTileEntity(pos);

    	if (spawnerTileEntity instanceof MobSpawnerTileEntity)
		    ((MobSpawnerTileEntity)spawnerTileEntity).getSpawnerBaseLogic().setEntityType(entityType);
    }

    protected void assignLootChests(IWorld world, Random rand, ResourceLocation lootTable, BlockPos... positions) {
    	for (BlockPos pos : positions) {
    		if (StructureCommand.isConverting) {
    			StructureCommand.conversionChestConsumer.accept(lootTable, pos);

    			continue;
		    }

    		TileEntity tileEntity = world.getTileEntity(pos);

    		if (tileEntity instanceof ChestTileEntity)
				((ChestTileEntity)tileEntity).setLootTable(lootTable, rand.nextLong());
		}
	}

    static final class EmptyStructure extends AoAStructure {
        EmptyStructure() {
            super();
        }

        @Override
        protected void build(IWorld world, Random rand, BlockPos basePos) {}
    }
}
