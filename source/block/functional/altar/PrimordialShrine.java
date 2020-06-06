package net.tslat.aoa3.block.functional.altar;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.entity.boss.primordialfive.EntityKajaros;
import net.tslat.aoa3.utils.StringUtil;
import net.tslat.aoa3.utils.player.PlayerUtil;

public class PrimordialShrine extends BossAltarBlock {
	public PrimordialShrine() {
		super("PrimordialShrine", "primordial_shrine");
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!world.isRemote && world.getDifficulty() == EnumDifficulty.PEACEFUL) {
			PlayerUtil.getAdventPlayer(player).sendThrottledChatMessage("message.feedback.spawnBoss.difficultyFail");

			return false;
		}

		if (!world.isRemote && checkActivationConditions(player, hand, state, pos))
			doActivationEffect(player, hand, state, pos);

		return true;
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

		return world.getBlockState(lamp1).getBlock() == BlockRegister.DUSTOPIAN_LAMP && world.getBlockState(lamp2).getBlock() == BlockRegister.DUSTOPIAN_LAMP
				&& world.getBlockState(lamp3).getBlock() == BlockRegister.DUSTOPIAN_LAMP && world.getBlockState(lamp4).getBlock() == BlockRegister.DUSTOPIAN_LAMP
				&& world.getBlockState(lamp5).getBlock() == BlockRegister.DUSTOPIAN_LAMP && world.getBlockState(lamp6).getBlock() == BlockRegister.DUSTOPIAN_LAMP
				&& world.getBlockState(lamp7).getBlock() == BlockRegister.DUSTOPIAN_LAMP && world.getBlockState(lamp8).getBlock() == BlockRegister.DUSTOPIAN_LAMP;
	}

	@Override
	protected void doActivationEffect(EntityPlayer player, EnumHand hand, IBlockState state, BlockPos blockPos) {
		World world = player.world;
		IBlockState lampOff = BlockRegister.DUSTOPIAN_LAMP_OFF.getDefaultState();

		switch (player.getRNG().nextInt(8)) {
			case 0:
				world.setBlockState(blockPos.up().west(3).north(5), lampOff);
				break;
			case 1:
				world.setBlockState(blockPos.up().west(3).south(5), lampOff);
				break;
			case 2:
				world.setBlockState(blockPos.up().west().north(4), lampOff);
				break;
			case 3:
				world.setBlockState(blockPos.up().west().south(4), lampOff);
				break;
			case 4:
				world.setBlockState(blockPos.up(3).north(3).east(), lampOff);
				break;
			case 5:
				world.setBlockState(blockPos.up(3).south(3).east(), lampOff);
				break;
			case 6:
				world.setBlockState(blockPos.up(5).north().east(), lampOff);
				break;
			case 7:
				world.setBlockState(blockPos.up(5).south().east(), lampOff);
				break;
		}

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