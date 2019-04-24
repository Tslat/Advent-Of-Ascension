package net.tslat.aoa3.block.functional.lamps;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.registries.IForgeRegistry;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.utils.EntityUtil;

import java.util.Random;

public class LifeLampBlock extends LampBlock {
	public LifeLampBlock(String name, String registryName, Material material, float luminosity, float hardness, float resistance) {
		super(name, registryName, material, luminosity, hardness, resistance);
		setTickRandomly(true);
	}

	@Override
	public LampBlock registerOffLamp(IForgeRegistry<Block> registry) {
		if (offLamp != null)
			return offLamp;

		LifeLampBlock offLamp = new LifeLampBlock(getUnlocalizedName().replace("tile.", "") + "Off", getRegistryName().getResourcePath() + "_off",getDefaultState().getMaterial(), 0.0f, blockHardness, blockResistance);
		offLamp.turnedOn = false;
		this.offLamp = offLamp;
		this.onLamp = this;
		offLamp.offLamp = offLamp;
		offLamp.onLamp = onLamp;

		onLamp.setCreativeTab(null);
		registry.register(offLamp);
		return this;
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return ItemRegister.ingotEmberstone;
	}

	@Override
	public int quantityDropped(Random random) {
		return 1 + random.nextInt(4);
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
		super.updateTick(world, pos, state, rand);

		if (!world.isRemote && turnedOn) {
			for (EntityLivingBase entity : world.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(pos).grow(4), entity -> !EntityUtil.isHostileMob(entity))) {
				entity.heal(1.0f);
			}
		}
	}
}
