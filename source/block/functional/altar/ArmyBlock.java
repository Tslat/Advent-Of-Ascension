package net.tslat.aoa3.block.functional.altar;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
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
		if (!player.world.isRemote && player.world.getEntitiesWithinAABB(EntityMob.class, new AxisAlignedBB(pos).grow(100), entity -> entity instanceof EntitySkeleElder || entity instanceof EntitySkeletron).size() == 0)
			player.world.spawnEntity(new EntitySkeleElder(player.world, pos, 0));

		return false;
	}

	@Override
	public void randomTick(World worldIn, BlockPos pos, IBlockState state, Random random) {
		if (!worldIn.isRemote && random.nextBoolean() && worldIn.getEntitiesWithinAABB(EntitySkeleElder.class, new AxisAlignedBB(pos).grow(100)).isEmpty())
			worldIn.spawnEntity(new EntitySkeleElder(worldIn, pos, 0));
	}

	@Override
	protected void doActivationEffect(EntityPlayer player, EnumHand hand, IBlockState state, BlockPos blockPos) {
		spawnWave(player.world, blockPos, 1);
		sendSpawnMessage(player, StringUtil.getLocaleWithArguments("message.mob.skeletalArmy.spawn", player.getDisplayNameString()), blockPos);
	}

	public static void spawnWave(World world, BlockPos blockPos, int wave) {
		if (!world.isRemote) {
			switch (wave) {
				case 1:
					world.spawnEntity(new EntitySkelePig(world, blockPos));
					world.spawnEntity(new EntitySkelePig(world, blockPos));
					world.spawnEntity(new EntitySkeleElder(world, blockPos, wave));
					break;
				case 2:
					world.spawnEntity(new EntitySkeleHopper(world, blockPos));
					world.spawnEntity(new EntitySkeleElder(world, blockPos, wave));
					break;
				case 3:
					world.spawnEntity(new EntitySkeleHopper(world, blockPos));
					world.spawnEntity(new EntitySkeleHopper(world, blockPos));
					world.spawnEntity(new EntitySkeleElder(world, blockPos, wave));
					break;
				case 4:
					world.spawnEntity(new EntitySkeleman(world, blockPos));
					world.spawnEntity(new EntitySkeleElder(world, blockPos, wave));
					break;
				case 5:
					world.spawnEntity(new EntitySkeleman(world, blockPos));
					world.spawnEntity(new EntitySkeleman(world, blockPos));
					world.spawnEntity(new EntitySkeleElder(world, blockPos, wave));
					break;
				case 6:
					world.spawnEntity(new EntitySkelePig(world, blockPos));
					world.spawnEntity(new EntitySkeleHopper(world, blockPos));
					world.spawnEntity(new EntitySkeleElder(world, blockPos, wave));
					break;
				case 7:
					world.spawnEntity(new EntitySkelePig(world, blockPos));
					world.spawnEntity(new EntitySkeleHopper(world, blockPos));
					world.spawnEntity(new EntitySkeleman(world, blockPos));
					world.spawnEntity(new EntitySkeleElder(world, blockPos, wave));
					break;
				case 8:
					world.spawnEntity(new EntitySkelePig(world, blockPos));
					world.spawnEntity(new EntitySkelePig(world, blockPos));
					world.spawnEntity(new EntitySkeleHopper(world, blockPos));
					world.spawnEntity(new EntitySkeleHopper(world, blockPos));
					world.spawnEntity(new EntitySkeleElder(world, blockPos, wave));
					break;
				case 9:
					world.spawnEntity(new EntitySkelePig(world, blockPos));
					world.spawnEntity(new EntitySkelePig(world, blockPos));
					world.spawnEntity(new EntitySkeleHopper(world, blockPos));
					world.spawnEntity(new EntitySkeleHopper(world, blockPos));
					world.spawnEntity(new EntitySkeleman(world, blockPos));
					world.spawnEntity(new EntitySkeleman(world, blockPos));
					world.spawnEntity(new EntitySkeleElder(world, blockPos, wave));
					break;
				case 10:
					world.spawnEntity(new EntitySkeleman(world, blockPos));
					world.spawnEntity(new EntitySkeleman(world, blockPos));
					world.spawnEntity(new EntitySkeleman(world, blockPos));
					world.spawnEntity(new EntitySkeleElder(world, blockPos, wave));
					break;
				case 11:
					world.spawnEntity(new EntitySkelePig(world, blockPos));
					world.spawnEntity(new EntitySkelePig(world, blockPos));
					world.spawnEntity(new EntitySkelePig(world, blockPos));
					world.spawnEntity(new EntitySkeleman(world, blockPos));
					world.spawnEntity(new EntitySkeleman(world, blockPos));
					world.spawnEntity(new EntitySkeleman(world, blockPos));
					world.spawnEntity(new EntitySkeleElder(world, blockPos, wave));
					break;
				case 12:
					world.spawnEntity(new EntityStrongSkelePig(world, blockPos));
					world.spawnEntity(new EntitySkeleElder(world, blockPos, wave));
					break;
				case 13:
					world.spawnEntity(new EntityStrongSkeleHopper(world, blockPos));
					world.spawnEntity(new EntitySkeleElder(world, blockPos, wave));
					break;
				case 14:
					world.spawnEntity(new EntityStrongSkeleman(world, blockPos));
					world.spawnEntity(new EntitySkeleElder(world, blockPos, wave));
					break;
				case 15:
					world.spawnEntity(new EntityStrongSkelePig(world, blockPos));
					world.spawnEntity(new EntityStrongSkelePig(world, blockPos));
					world.spawnEntity(new EntityStrongSkelePig(world, blockPos));
					world.spawnEntity(new EntitySkeleElder(world, blockPos, wave));
					break;
				case 16:
					world.spawnEntity(new EntityStrongSkeleHopper(world, blockPos));
					world.spawnEntity(new EntityStrongSkeleHopper(world, blockPos));
					world.spawnEntity(new EntityStrongSkeleHopper(world, blockPos));
					world.spawnEntity(new EntitySkeleElder(world, blockPos, wave));
					break;
				case 17:
					world.spawnEntity(new EntityStrongSkeleman(world, blockPos));
					world.spawnEntity(new EntityStrongSkeleman(world, blockPos));
					world.spawnEntity(new EntityStrongSkeleman(world, blockPos));
					world.spawnEntity(new EntitySkeleElder(world, blockPos, wave));
					break;
				case 18:
					world.spawnEntity(new EntityStrongSkelePig(world, blockPos));
					world.spawnEntity(new EntityStrongSkelePig(world, blockPos));
					world.spawnEntity(new EntityStrongSkeleHopper(world, blockPos));
					world.spawnEntity(new EntityStrongSkeleHopper(world, blockPos));
					world.spawnEntity(new EntityStrongSkeleman(world, blockPos));
					world.spawnEntity(new EntityStrongSkeleman(world, blockPos));
					world.spawnEntity(new EntitySkeleElder(world, blockPos, wave));
					break;
				case 19:
					world.spawnEntity(new EntitySkelePig(world, blockPos));
					world.spawnEntity(new EntitySkelePig(world, blockPos));
					world.spawnEntity(new EntitySkeleHopper(world, blockPos));
					world.spawnEntity(new EntitySkeleHopper(world, blockPos));
					world.spawnEntity(new EntitySkeleman(world, blockPos));
					world.spawnEntity(new EntitySkeleman(world, blockPos));
					world.spawnEntity(new EntityStrongSkelePig(world, blockPos));
					world.spawnEntity(new EntityStrongSkelePig(world, blockPos));
					world.spawnEntity(new EntityStrongSkeleHopper(world, blockPos));
					world.spawnEntity(new EntityStrongSkeleHopper(world, blockPos));
					world.spawnEntity(new EntityStrongSkeleman(world, blockPos));
					world.spawnEntity(new EntityStrongSkeleman(world, blockPos));
					world.spawnEntity(new EntitySkeleElder(world, blockPos, wave));
					break;
				case 20:
					world.spawnEntity(new EntityEliteSkelePig(world, blockPos));
					world.spawnEntity(new EntitySkeleElder(world, blockPos, wave));
					break;
				case 21:
					world.spawnEntity(new EntityEliteSkeleHopper(world, blockPos));
					world.spawnEntity(new EntitySkeleElder(world, blockPos, wave));
					break;
				case 22:
					world.spawnEntity(new EntityEliteSkeleman(world, blockPos));
					world.spawnEntity(new EntitySkeleElder(world, blockPos, wave));
					break;
				case 23:
					world.spawnEntity(new EntityEliteSkelePig(world, blockPos));
					world.spawnEntity(new EntityEliteSkelePig(world, blockPos));
					world.spawnEntity(new EntityEliteSkelePig(world, blockPos));
					world.spawnEntity(new EntitySkeleElder(world, blockPos, wave));
					break;
				case 24:
					world.spawnEntity(new EntityEliteSkeleHopper(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleHopper(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleHopper(world, blockPos));
					world.spawnEntity(new EntitySkeleElder(world, blockPos, wave));
					break;
				case 25:
					world.spawnEntity(new EntityEliteSkeleman(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleman(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleman(world, blockPos));
					world.spawnEntity(new EntitySkeleElder(world, blockPos, wave));
					break;
				case 26:
					world.spawnEntity(new EntityEliteSkelePig(world, blockPos));
					world.spawnEntity(new EntityEliteSkelePig(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleHopper(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleHopper(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleman(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleman(world, blockPos));
					world.spawnEntity(new EntitySkeleElder(world, blockPos, wave));
					break;
				case 27:
					world.spawnEntity(new EntityEliteSkelePig(world, blockPos));
					world.spawnEntity(new EntityEliteSkelePig(world, blockPos));
					world.spawnEntity(new EntityEliteSkelePig(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleHopper(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleHopper(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleHopper(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleman(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleman(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleman(world, blockPos));
					world.spawnEntity(new EntitySkeleElder(world, blockPos, wave));
					break;
				case 28:
					world.spawnEntity(new EntityEliteSkelePig(world, blockPos));
					world.spawnEntity(new EntityEliteSkelePig(world, blockPos));
					world.spawnEntity(new EntityEliteSkelePig(world, blockPos));
					world.spawnEntity(new EntityEliteSkelePig(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleman(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleman(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleman(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleman(world, blockPos));
					world.spawnEntity(new EntitySkeleElder(world, blockPos, wave));
					break;
				case 29:
					world.spawnEntity(new EntityEliteSkelePig(world, blockPos));
					world.spawnEntity(new EntityEliteSkelePig(world, blockPos));
					world.spawnEntity(new EntityEliteSkelePig(world, blockPos));
					world.spawnEntity(new EntityEliteSkelePig(world, blockPos));
					world.spawnEntity(new EntityEliteSkelePig(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleHopper(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleHopper(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleHopper(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleHopper(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleHopper(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleman(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleman(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleman(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleman(world, blockPos));
					world.spawnEntity(new EntityStrongSkeleman(world, blockPos));
					world.spawnEntity(new EntityStrongSkeleman(world, blockPos));
					world.spawnEntity(new EntitySkeleElder(world, blockPos, wave));
					break;
				case 30:
					world.spawnEntity(new EntityEliteSkelePig(world, blockPos));
					world.spawnEntity(new EntityEliteSkelePig(world, blockPos));
					world.spawnEntity(new EntityEliteSkelePig(world, blockPos));
					world.spawnEntity(new EntityEliteSkelePig(world, blockPos));
					world.spawnEntity(new EntityEliteSkelePig(world, blockPos));
					world.spawnEntity(new EntityEliteSkelePig(world, blockPos));
					world.spawnEntity(new EntityEliteSkelePig(world, blockPos));
					world.spawnEntity(new EntityEliteSkelePig(world, blockPos));
					world.spawnEntity(new EntitySkeleElder(world, blockPos, wave));
					break;
				case 31:
					world.spawnEntity(new EntityEliteSkeleHopper(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleHopper(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleHopper(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleHopper(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleHopper(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleHopper(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleHopper(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleHopper(world, blockPos));
					world.spawnEntity(new EntitySkeleElder(world, blockPos, wave));
					break;
				case 32:
					world.spawnEntity(new EntityEliteSkeleman(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleman(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleman(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleman(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleman(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleman(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleman(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleman(world, blockPos));
					world.spawnEntity(new EntitySkeleElder(world, blockPos, wave));
					break;
				case 33:
					world.spawnEntity(new EntityEliteSkelePig(world, blockPos));
					world.spawnEntity(new EntityEliteSkelePig(world, blockPos));
					world.spawnEntity(new EntityEliteSkelePig(world, blockPos));
					world.spawnEntity(new EntityEliteSkelePig(world, blockPos));
					world.spawnEntity(new EntityEliteSkelePig(world, blockPos));
					world.spawnEntity(new EntityEliteSkelePig(world, blockPos));
					world.spawnEntity(new EntityEliteSkelePig(world, blockPos));
					world.spawnEntity(new EntityEliteSkelePig(world, blockPos));
					world.spawnEntity(new EntityEliteSkelePig(world, blockPos));
					world.spawnEntity(new EntityEliteSkelePig(world, blockPos));
					world.spawnEntity(new EntityEliteSkelePig(world, blockPos));
					world.spawnEntity(new EntityEliteSkelePig(world, blockPos));
					world.spawnEntity(new EntitySkeleElder(world, blockPos, wave));
					break;
				case 34:
					world.spawnEntity(new EntityEliteSkeleHopper(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleHopper(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleHopper(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleHopper(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleHopper(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleHopper(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleHopper(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleHopper(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleHopper(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleHopper(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleHopper(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleHopper(world, blockPos));
					world.spawnEntity(new EntitySkeleElder(world, blockPos, wave));
					break;
				case 35:
					world.spawnEntity(new EntityEliteSkeleman(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleman(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleman(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleman(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleman(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleman(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleman(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleman(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleman(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleman(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleman(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleman(world, blockPos));
					world.spawnEntity(new EntitySkeleElder(world, blockPos, wave));
					break;
				case 36:
					world.spawnEntity(new EntityEliteSkelePig(world, blockPos));
					world.spawnEntity(new EntityEliteSkelePig(world, blockPos));
					world.spawnEntity(new EntityEliteSkelePig(world, blockPos));
					world.spawnEntity(new EntityEliteSkelePig(world, blockPos));
					world.spawnEntity(new EntityEliteSkelePig(world, blockPos));
					world.spawnEntity(new EntityEliteSkelePig(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleHopper(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleHopper(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleHopper(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleHopper(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleHopper(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleHopper(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleman(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleman(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleman(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleman(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleman(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleman(world, blockPos));
					world.spawnEntity(new EntityStrongSkelePig(world, blockPos));
					world.spawnEntity(new EntityStrongSkelePig(world, blockPos));
					world.spawnEntity(new EntityStrongSkeleHopper(world, blockPos));
					world.spawnEntity(new EntityStrongSkeleHopper(world, blockPos));
					world.spawnEntity(new EntityStrongSkeleman(world, blockPos));
					world.spawnEntity(new EntityStrongSkeleman(world, blockPos));
					world.spawnEntity(new EntitySkeleElder(world, blockPos, wave));
					break;
				case 37:
					world.spawnEntity(new EntityEliteSkeleman(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleman(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleman(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleman(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleman(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleman(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleman(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleman(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleman(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleman(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleman(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleman(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleman(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleman(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleman(world, blockPos));
					world.spawnEntity(new EntitySkeleElder(world, blockPos, wave));
					break;
				case 38:
					world.spawnEntity(new EntityEliteSkelePig(world, blockPos));
					world.spawnEntity(new EntityEliteSkelePig(world, blockPos));
					world.spawnEntity(new EntityEliteSkelePig(world, blockPos));
					world.spawnEntity(new EntityEliteSkelePig(world, blockPos));
					world.spawnEntity(new EntityEliteSkelePig(world, blockPos));
					world.spawnEntity(new EntityEliteSkelePig(world, blockPos));
					world.spawnEntity(new EntityEliteSkelePig(world, blockPos));
					world.spawnEntity(new EntityEliteSkelePig(world, blockPos));
					world.spawnEntity(new EntityEliteSkelePig(world, blockPos));
					world.spawnEntity(new EntityEliteSkelePig(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleHopper(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleHopper(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleHopper(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleHopper(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleHopper(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleHopper(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleHopper(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleHopper(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleHopper(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleHopper(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleman(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleman(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleman(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleman(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleman(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleman(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleman(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleman(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleman(world, blockPos));
					world.spawnEntity(new EntityEliteSkeleman(world, blockPos));
					world.spawnEntity(new EntitySkeleElder(world, blockPos, wave));
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
