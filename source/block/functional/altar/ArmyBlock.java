package net.tslat.aoa3.block.functional.altar;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.entity.boss.skeletalarmy.*;
import net.tslat.aoa3.utils.StringUtil;

import java.util.Random;

public class ArmyBlock extends BossAltarBlock {
	public ArmyBlock() {
		super("ArmyBlock", "army_block");

		setTickRandomly(true);
	}

	@Override
	protected boolean checkActivationConditions(EntityPlayer player, EnumHand hand, IBlockState state, BlockPos pos) {
		if (!player.world.isRemote && player.world.getEntitiesWithinAABB(EntityMob.class, new AxisAlignedBB(pos).grow(100), entity -> entity instanceof EntitySkeleElder || entity instanceof EntitySkeletron).size() == 0) {
			EntitySkeleElder skeleElder = new EntitySkeleElder(player.world, pos, 0);

			skeleElder.setPosition(pos.getX(), pos.getY() + 1, pos.getZ());

			player.world.spawnEntity(skeleElder);
		}

		return false;
	}

	@Override
	public void randomTick(World world, BlockPos pos, IBlockState state, Random random) {
		if (!world.isRemote && random.nextBoolean() && world.getEntitiesWithinAABB(EntitySkeleElder.class, new AxisAlignedBB(pos).grow(100)).isEmpty())
			world.spawnEntity(new EntitySkeleElder(world, pos, 0));
	}

	@Override
	protected void doActivationEffect(EntityPlayer player, EnumHand hand, IBlockState state, BlockPos blockPos) {
		spawnWave(player.world, blockPos, 1);
		sendSpawnMessage(player, StringUtil.getLocaleWithArguments("message.mob.skeletalArmy.spawn", player.getDisplayNameString()), blockPos);
	}

	private static void spawnWaveEntities(World world, BlockPos armyBlockPos, Entity... entities) {
		for (Entity entity : entities) {
			int posX = armyBlockPos.getX() - 18 + AdventOfAscension.rand.nextInt(24);
			int posZ = armyBlockPos.getZ() - 12 + AdventOfAscension.rand.nextInt(20);
			BlockPos.MutableBlockPos checkPos = new BlockPos.MutableBlockPos(posX, armyBlockPos.getY(), posZ);

			while (checkPos.getY() < world.getHeight() && !world.isAirBlock(checkPos.move(EnumFacing.UP))) {
				;
			}

			entity.setPosition(posX, checkPos.getY(), posZ);
			world.spawnEntity(entity);
		}
	}


	public static void spawnWave(World world, BlockPos blockPos, int wave) {
		if (!world.isRemote) {
			switch (wave) {
				case 1:
					spawnWaveEntities(world, blockPos,
							new EntitySkelePig(world),
							new EntitySkelePig(world),
							new EntitySkeleElder(world, blockPos, wave));
					break;
				case 2:
					spawnWaveEntities(world, blockPos,
							new EntitySkeleHopper(world),
							new EntitySkeleElder(world, blockPos, wave));
					break;
				case 3:
					spawnWaveEntities(world, blockPos,
							new EntitySkeleHopper(world),
							new EntitySkeleHopper(world),
							new EntitySkeleElder(world, blockPos, wave));
					break;
				case 4:
					spawnWaveEntities(world, blockPos,
							new EntitySkeleman(world),
							new EntitySkeleElder(world, blockPos, wave));
					break;
				case 5:
					spawnWaveEntities(world, blockPos,
							new EntitySkeleman(world),
							new EntitySkeleman(world),
							new EntitySkeleElder(world, blockPos, wave));
					break;
				case 6:
					spawnWaveEntities(world, blockPos,
							new EntitySkelePig(world),
							new EntitySkeleHopper(world),
							new EntitySkeleElder(world, blockPos, wave));
					break;
				case 7:
					spawnWaveEntities(world, blockPos,
							new EntitySkelePig(world),
							new EntitySkeleHopper(world),
							new EntitySkeleman(world),
							new EntitySkeleElder(world),
							new EntitySkeleElder(world, blockPos, wave));
					break;
				case 8:
					spawnWaveEntities(world, blockPos,
							new EntitySkelePig(world),
							new EntitySkelePig(world),
							new EntitySkeleHopper(world),
							new EntitySkeleHopper(world),
							new EntitySkeleElder(world, blockPos, wave));
					break;
				case 9:
					spawnWaveEntities(world, blockPos,
							new EntitySkelePig(world),
							new EntitySkelePig(world),
							new EntitySkeleHopper(world),
							new EntitySkeleHopper(world),
							new EntitySkeleman(world),
							new EntitySkeleman(world),
							new EntitySkeleElder(world, blockPos, wave));
					break;
				case 10:
					spawnWaveEntities(world, blockPos,
							new EntitySkeleman(world),
							new EntitySkeleman(world),
							new EntitySkeleman(world),
							new EntitySkeleElder(world, blockPos, wave));
					break;
				case 11:
					spawnWaveEntities(world, blockPos,
							new EntitySkelePig(world),
							new EntitySkelePig(world),
							new EntitySkelePig(world),
							new EntitySkeleman(world),
							new EntitySkeleman(world),
							new EntitySkeleman(world),
							new EntitySkeleElder(world, blockPos, wave));
					break;
				case 12:
					spawnWaveEntities(world, blockPos,
							new EntityStrongSkelePig(world),
							new EntitySkeleElder(world, blockPos, wave));
					break;
				case 13:
					spawnWaveEntities(world, blockPos,
							new EntityStrongSkeleHopper(world),
							new EntitySkeleElder(world, blockPos, wave));
					break;
				case 14:
					spawnWaveEntities(world, blockPos,
							new EntityStrongSkeleman(world),
							new EntitySkeleElder(world, blockPos, wave));
					break;
				case 15:
					spawnWaveEntities(world, blockPos,
							new EntityStrongSkelePig(world),
							new EntityStrongSkelePig(world),
							new EntityStrongSkelePig(world),
							new EntitySkeleElder(world, blockPos, wave));
					break;
				case 16:
					spawnWaveEntities(world, blockPos,
							new EntityStrongSkeleHopper(world),
							new EntityStrongSkeleHopper(world),
							new EntityStrongSkeleHopper(world),
							new EntitySkeleElder(world, blockPos, wave));
					break;
				case 17:
					spawnWaveEntities(world, blockPos,
							new EntityStrongSkeleman(world),
							new EntityStrongSkeleman(world),
							new EntityStrongSkeleman(world),
							new EntitySkeleElder(world, blockPos, wave));
					break;
				case 18:
					spawnWaveEntities(world, blockPos,
							new EntityStrongSkelePig(world),
							new EntityStrongSkelePig(world),
							new EntityStrongSkeleHopper(world),
							new EntityStrongSkeleHopper(world),
							new EntityStrongSkeleman(world),
							new EntityStrongSkeleman(world),
							new EntitySkeleElder(world, blockPos, wave));
					break;
				case 19:
					spawnWaveEntities(world, blockPos,
							new EntitySkelePig(world),
							new EntitySkelePig(world),
							new EntitySkeleHopper(world),
							new EntitySkeleHopper(world),
							new EntitySkeleman(world),
							new EntitySkeleman(world),
							new EntityStrongSkelePig(world),
							new EntityStrongSkelePig(world),
							new EntityStrongSkeleHopper(world),
							new EntityStrongSkeleHopper(world),
							new EntityStrongSkeleman(world),
							new EntityStrongSkeleman(world),
							new EntitySkeleElder(world, blockPos, wave));
					break;
				case 20:
					spawnWaveEntities(world, blockPos,
							new EntityEliteSkelePig(world),
							new EntitySkeleElder(world, blockPos, wave));
					break;
				case 21:
					spawnWaveEntities(world, blockPos,
							new EntityEliteSkeleHopper(world),
							new EntitySkeleElder(world, blockPos, wave));
					break;
				case 22:
					spawnWaveEntities(world, blockPos,
							new EntityEliteSkeleman(world),
							new EntitySkeleElder(world, blockPos, wave));
					break;
				case 23:
					spawnWaveEntities(world, blockPos,
							new EntityEliteSkelePig(world),
							new EntityEliteSkelePig(world),
							new EntityEliteSkelePig(world),
							new EntitySkeleElder(world, blockPos, wave));
					break;
				case 24:
					spawnWaveEntities(world, blockPos,
							new EntityEliteSkeleHopper(world),
							new EntityEliteSkeleHopper(world),
							new EntityEliteSkeleHopper(world),
							new EntitySkeleElder(world, blockPos, wave));
					break;
				case 25:
					spawnWaveEntities(world, blockPos,
							new EntityEliteSkeleman(world),
							new EntityEliteSkeleman(world),
							new EntityEliteSkeleman(world),
							new EntitySkeleElder(world, blockPos, wave));
					break;
				case 26:
					spawnWaveEntities(world, blockPos,
							new EntityEliteSkelePig(world),
							new EntityEliteSkelePig(world),
							new EntityEliteSkeleHopper(world),
							new EntityEliteSkeleHopper(world),
							new EntityEliteSkeleman(world),
							new EntityEliteSkeleman(world),
							new EntitySkeleElder(world, blockPos, wave));
					break;
				case 27:
					spawnWaveEntities(world, blockPos,
							new EntityEliteSkelePig(world),
							new EntityEliteSkelePig(world),
							new EntityEliteSkelePig(world),
							new EntityEliteSkeleHopper(world),
							new EntityEliteSkeleHopper(world),
							new EntityEliteSkeleHopper(world),
							new EntityEliteSkeleman(world),
							new EntityEliteSkeleman(world),
							new EntityEliteSkeleman(world),
							new EntitySkeleElder(world, blockPos, wave));
					break;
				case 28:
					spawnWaveEntities(world, blockPos,
							new EntityEliteSkelePig(world),
							new EntityEliteSkelePig(world),
							new EntityEliteSkelePig(world),
							new EntityEliteSkelePig(world),
							new EntityEliteSkeleman(world),
							new EntityEliteSkeleman(world),
							new EntityEliteSkeleman(world),
							new EntityEliteSkeleman(world),
							new EntitySkeleElder(world, blockPos, wave));
					break;
				case 29:
					spawnWaveEntities(world, blockPos,
							new EntityEliteSkelePig(world),
							new EntityEliteSkelePig(world),
							new EntityEliteSkelePig(world),
							new EntityEliteSkelePig(world),
							new EntityEliteSkelePig(world),
							new EntityEliteSkeleHopper(world),
							new EntityEliteSkeleHopper(world),
							new EntityEliteSkeleHopper(world),
							new EntityEliteSkeleHopper(world),
							new EntityEliteSkeleHopper(world),
							new EntityEliteSkeleman(world),
							new EntityEliteSkeleman(world),
							new EntityEliteSkeleman(world),
							new EntityEliteSkeleman(world),
							new EntityStrongSkeleman(world),
							new EntityStrongSkeleman(world),
							new EntitySkeleElder(world, blockPos, wave));
					break;
				case 30:
					spawnWaveEntities(world, blockPos,
							new EntityEliteSkelePig(world),
							new EntityEliteSkelePig(world),
							new EntityEliteSkelePig(world),
							new EntityEliteSkelePig(world),
							new EntityEliteSkelePig(world),
							new EntityEliteSkelePig(world),
							new EntityEliteSkelePig(world),
							new EntityEliteSkelePig(world),
							new EntitySkeleElder(world, blockPos, wave));
					break;
				case 31:
					spawnWaveEntities(world, blockPos,
							new EntityEliteSkeleHopper(world),
							new EntityEliteSkeleHopper(world),
							new EntityEliteSkeleHopper(world),
							new EntityEliteSkeleHopper(world),
							new EntityEliteSkeleHopper(world),
							new EntityEliteSkeleHopper(world),
							new EntityEliteSkeleHopper(world),
							new EntityEliteSkeleHopper(world),
							new EntitySkeleElder(world, blockPos, wave));
					break;
				case 32:
					spawnWaveEntities(world, blockPos,
							new EntityEliteSkeleman(world),
							new EntityEliteSkeleman(world),
							new EntityEliteSkeleman(world),
							new EntityEliteSkeleman(world),
							new EntityEliteSkeleman(world),
							new EntityEliteSkeleman(world),
							new EntityEliteSkeleman(world),
							new EntityEliteSkeleman(world),
							new EntitySkeleElder(world, blockPos, wave));
					break;
				case 33:
					spawnWaveEntities(world, blockPos,
							new EntityEliteSkelePig(world),
							new EntityEliteSkelePig(world),
							new EntityEliteSkelePig(world),
							new EntityEliteSkelePig(world),
							new EntityEliteSkelePig(world),
							new EntityEliteSkelePig(world),
							new EntityEliteSkelePig(world),
							new EntityEliteSkelePig(world),
							new EntityEliteSkelePig(world),
							new EntityEliteSkelePig(world),
							new EntityEliteSkelePig(world),
							new EntityEliteSkelePig(world),
							new EntitySkeleElder(world, blockPos, wave));
					break;
				case 34:
					spawnWaveEntities(world, blockPos,
							new EntityEliteSkeleHopper(world),
							new EntityEliteSkeleHopper(world),
							new EntityEliteSkeleHopper(world),
							new EntityEliteSkeleHopper(world),
							new EntityEliteSkeleHopper(world),
							new EntityEliteSkeleHopper(world),
							new EntityEliteSkeleHopper(world),
							new EntityEliteSkeleHopper(world),
							new EntityEliteSkeleHopper(world),
							new EntityEliteSkeleHopper(world),
							new EntityEliteSkeleHopper(world),
							new EntityEliteSkeleHopper(world),
							new EntitySkeleElder(world, blockPos, wave));
					break;
				case 35:
					spawnWaveEntities(world, blockPos,
							new EntityEliteSkeleman(world),
							new EntityEliteSkeleman(world),
							new EntityEliteSkeleman(world),
							new EntityEliteSkeleman(world),
							new EntityEliteSkeleman(world),
							new EntityEliteSkeleman(world),
							new EntityEliteSkeleman(world),
							new EntityEliteSkeleman(world),
							new EntityEliteSkeleman(world),
							new EntityEliteSkeleman(world),
							new EntityEliteSkeleman(world),
							new EntityEliteSkeleman(world),
							new EntitySkeleElder(world, blockPos, wave));
					break;
				case 36:
					spawnWaveEntities(world, blockPos,
							new EntityEliteSkelePig(world), 
							new EntityEliteSkelePig(world), 
							new EntityEliteSkelePig(world), 
							new EntityEliteSkelePig(world), 
							new EntityEliteSkelePig(world), 
							new EntityEliteSkelePig(world), 
							new EntityEliteSkeleHopper(world), 
							new EntityEliteSkeleHopper(world), 
							new EntityEliteSkeleHopper(world), 
							new EntityEliteSkeleHopper(world), 
							new EntityEliteSkeleHopper(world), 
							new EntityEliteSkeleHopper(world),  
							new EntityEliteSkeleman(world), 
							new EntityEliteSkeleman(world), 
							new EntityEliteSkeleman(world), 
							new EntityEliteSkeleman(world), 
							new EntityEliteSkeleman(world), 
							new EntityEliteSkeleman(world),
							new EntityStrongSkelePig(world),
							new EntityStrongSkelePig(world),
							new EntityStrongSkeleHopper(world),
							new EntityStrongSkeleHopper(world),
							new EntityStrongSkeleman(world),
							new EntityStrongSkeleman(world),
							new EntitySkeleElder(world, blockPos, wave));
					break;
				case 37:
					spawnWaveEntities(world, blockPos,
							new EntityEliteSkeleman(world),
							new EntityEliteSkeleman(world),
							new EntityEliteSkeleman(world),
							new EntityEliteSkeleman(world),
							new EntityEliteSkeleman(world),
							new EntityEliteSkeleman(world),
							new EntityEliteSkeleman(world),
							new EntityEliteSkeleman(world),
							new EntityEliteSkeleman(world),
							new EntityEliteSkeleman(world),
							new EntityEliteSkeleman(world),
							new EntityEliteSkeleman(world),
							new EntityEliteSkeleman(world),
							new EntityEliteSkeleman(world),
							new EntityEliteSkeleman(world),
							new EntitySkeleElder(world, blockPos, wave));
					break;
				case 38:
					spawnWaveEntities(world, blockPos, 
							new EntityEliteSkelePig(world), 
							new EntityEliteSkelePig(world), 
							new EntityEliteSkelePig(world), 
							new EntityEliteSkelePig(world), 
							new EntityEliteSkelePig(world), 
							new EntityEliteSkelePig(world), 
							new EntityEliteSkelePig(world), 
							new EntityEliteSkelePig(world), 
							new EntityEliteSkelePig(world), 
							new EntityEliteSkelePig(world),
							new EntityEliteSkeleHopper(world), 
							new EntityEliteSkeleHopper(world), 
							new EntityEliteSkeleHopper(world), 
							new EntityEliteSkeleHopper(world), 
							new EntityEliteSkeleHopper(world), 
							new EntityEliteSkeleHopper(world), 
							new EntityEliteSkeleHopper(world), 
							new EntityEliteSkeleHopper(world), 
							new EntityEliteSkeleHopper(world), 
							new EntityEliteSkeleHopper(world),
							new EntityEliteSkeleman(world), 
							new EntityEliteSkeleman(world), 
							new EntityEliteSkeleman(world), 
							new EntityEliteSkeleman(world), 
							new EntityEliteSkeleman(world), 
							new EntityEliteSkeleman(world), 
							new EntityEliteSkeleman(world), 
							new EntityEliteSkeleman(world), 
							new EntityEliteSkeleman(world), 
							new EntityEliteSkeleman(world),
							new EntitySkeleElder(world, blockPos, wave));
					break;
				case 39:
					world.spawnEntity(new EntitySkeletron(world, blockPos));
					break;
				default:
					break;
			}
		}
	}

	@Override
	protected Item getActivationItem() {
		return null;
	}
}
