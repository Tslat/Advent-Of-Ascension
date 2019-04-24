package net.tslat.aoa3.block.functional.altar;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.entity.boss.primordialfive.EntityKajaros;
import net.tslat.aoa3.utils.StringUtil;

public class PrimordialShrine extends BossAltarBlock {
	public PrimordialShrine() {
		super("PrimordialShrine", "primordial_shrine");
	}

	@Override
	protected boolean checkActivationConditions(EntityPlayer player, EnumHand hand, IBlockState state, BlockPos pos) {
		World world = player.world;
		BlockPos lamp1 = pos.up(1).west(3).north(5);
		BlockPos lamp2 = lamp1.south(10);
		BlockPos lamp3 = pos.up(1).west(1).north(4);
		BlockPos lamp4 = lamp3.south(8);
		BlockPos lamp5 = pos.up(3).north(3).east(1);
		BlockPos lamp6 = lamp5.south(6);
		BlockPos lamp7 = pos.up(5).north(1).east(1);
		BlockPos lamp8 = lamp7.south(2);

		return world.getBlockState(lamp1).getBlock() == BlockRegister.dustopianLamp && world.getBlockState(lamp2).getBlock() == BlockRegister.dustopianLamp
				&& world.getBlockState(lamp3).getBlock() == BlockRegister.dustopianLamp && world.getBlockState(lamp4).getBlock() == BlockRegister.dustopianLamp
				&& world.getBlockState(lamp5).getBlock() == BlockRegister.dustopianLamp && world.getBlockState(lamp6).getBlock() == BlockRegister.dustopianLamp
				&& world.getBlockState(lamp7).getBlock() == BlockRegister.dustopianLamp && world.getBlockState(lamp8).getBlock() == BlockRegister.dustopianLamp;
	}

	@Override
	protected void doActivationEffect(EntityPlayer player, EnumHand hand, IBlockState state, BlockPos blockPos) {
		World world = player.world;
		IBlockState lampOff = BlockRegister.dustopianLampOff.getDefaultState();
		BlockPos lamp1 = blockPos.up(1).west(3).north(5);
		BlockPos lamp2 = lamp1.south(10);
		BlockPos lamp3 = blockPos.up(1).west(1).north(4);
		BlockPos lamp4 = lamp3.south(8);
		BlockPos lamp5 = blockPos.up(3).north(3).east(1);
		BlockPos lamp6 = lamp5.south(6);
		BlockPos lamp7 = blockPos.up(5).north(1).east(1);
		BlockPos lamp8 = lamp7.south(2);

		world.setBlockState(lamp1, lampOff);
		world.setBlockState(lamp2, lampOff);
		world.setBlockState(lamp3, lampOff);
		world.setBlockState(lamp4, lampOff);
		world.setBlockState(lamp5, lampOff);
		world.setBlockState(lamp6, lampOff);
		world.setBlockState(lamp7, lampOff);
		world.setBlockState(lamp8, lampOff);

		EntityKajaros kajaros = new EntityKajaros(player.world);

		kajaros.setLocationAndAngles(blockPos.getX(), blockPos.getY() + 3, blockPos.getZ(), 0, 0);
		player.world.spawnEntity(kajaros);
		sendSpawnMessage(player, StringUtil.getLocaleWithArguments("message.mob.primordialFive.spawn", player.getDisplayNameString()), blockPos);
	}

	@Override
	protected Item getActivationItem() {
		return null;
	}
}