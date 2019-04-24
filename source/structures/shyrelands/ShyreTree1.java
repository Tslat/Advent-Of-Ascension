package net.nevermine.structures.shyrelands;

import net.minecraft.block.Block;
import net.nevermine.izer.Blockizer;
import net.nevermine.structures.voxponds.IVoxpondsStructure;

public class ShyreTree1 implements IVoxpondsStructure {
	public void generate(Block[][][] chunk, int i, int j, int k) {
		chunk[(i + 0)][(j + 5)][(k + 0)] = Blockizer.ShyreLeaves;
		chunk[(i + 0)][(j + 5)][(k + 1)] = Blockizer.ShyreLeaves;
		chunk[(i + 0)][(j + 5)][(k + 2)] = Blockizer.ShyreLeaves;
		chunk[(i + 0)][(j + 6)][(k + 0)] = Blockizer.ShyreLeaves;
		chunk[(i + 0)][(j + 6)][(k + 1)] = Blockizer.ShyreLeaves;
		chunk[(i + 0)][(j + 6)][(k + 2)] = Blockizer.ShyreLeaves;
		chunk[(i + 0)][(j + 7)][(k + 1)] = Blockizer.ShyreLeaves;
		chunk[(i + 1)][(j + 0)][(k + 1)] = Blockizer.ShyreWood;
		chunk[(i + 1)][(j + 1)][(k + 1)] = Blockizer.ShyreWood;
		chunk[(i + 1)][(j + 2)][(k + 1)] = Blockizer.ShyreWood;
		chunk[(i + 1)][(j + 3)][(k + 1)] = Blockizer.ShyreWood;
		chunk[(i + 1)][(j + 4)][(k + 1)] = Blockizer.ShyreWood;
		chunk[(i + 1)][(j + 5)][(k + 0)] = Blockizer.ShyreLeaves;
		chunk[(i + 1)][(j + 5)][(k + 1)] = Blockizer.ShyreLeaves;
		chunk[(i + 1)][(j + 5)][(k + 2)] = Blockizer.ShyreLeaves;
		chunk[(i + 1)][(j + 6)][(k + 0)] = Blockizer.ShyreLeaves;
		chunk[(i + 1)][(j + 6)][(k + 1)] = Blockizer.ShyreLeaves;
		chunk[(i + 1)][(j + 6)][(k + 2)] = Blockizer.ShyreLeaves;
		chunk[(i + 1)][(j + 7)][(k + 0)] = Blockizer.ShyreLeaves;
		chunk[(i + 1)][(j + 7)][(k + 1)] = Blockizer.ShyreLeaves;
		chunk[(i + 1)][(j + 7)][(k + 2)] = Blockizer.ShyreLeaves;
		chunk[(i + 2)][(j + 5)][(k + 0)] = Blockizer.ShyreLeaves;
		chunk[(i + 2)][(j + 5)][(k + 1)] = Blockizer.ShyreLeaves;
		chunk[(i + 2)][(j + 5)][(k + 2)] = Blockizer.ShyreLeaves;
		chunk[(i + 2)][(j + 6)][(k + 0)] = Blockizer.ShyreLeaves;
		chunk[(i + 2)][(j + 6)][(k + 1)] = Blockizer.ShyreLeaves;
		chunk[(i + 2)][(j + 6)][(k + 2)] = Blockizer.ShyreLeaves;
		chunk[(i + 2)][(j + 7)][(k + 1)] = Blockizer.ShyreLeaves;
	}
}
