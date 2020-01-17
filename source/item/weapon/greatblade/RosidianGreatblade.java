package net.tslat.aoa3.item.weapon.greatblade;

import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.item.weapon.AdventWeapon;
import net.tslat.aoa3.item.weapon.LongReachWeapon;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ItemUtil;

import java.util.List;

public class RosidianGreatblade extends BaseGreatblade implements AdventWeapon, LongReachWeapon {
	public RosidianGreatblade(double dmg, double speed, int durability) {
		super(dmg, speed, durability);
		setTranslationKey("RosidianGreatblade");
		setRegistryName("aoa3:rosidian_greatblade");
	}

	@Override
	public boolean onBlockStartBreak(ItemStack stack, BlockPos pos, EntityPlayer player) {
		if (player.world.isRemote || player.capabilities.isCreativeMode)
			return false;

		Block block = player.world.getBlockState(pos).getBlock();

		if (block instanceof IShearable) {
			if (((IShearable)block).isShearable(stack, player.world, pos)) {
				List<ItemStack> drops;

				for (int x = pos.getX() - 1; x <= pos.getX() + 1; x++) {
					for (int y = pos.getY() - 1; y <= pos.getY() + 1; y++) {
						for (int z = pos.getZ() - 1; z <= pos.getZ() + 1; z++) {
							BlockPos newPos = new BlockPos(x, y, z);
							Block newBlock = player.world.getBlockState(newPos).getBlock();

							if (!(newBlock instanceof IShearable) || !((IShearable)newBlock).isShearable(stack, player.world, newPos))
								continue;

							drops = ((IShearable)block).onSheared(stack, player.world, newPos, EnchantmentHelper.getEnchantmentLevel(Enchantments.FORTUNE, stack));

							for (ItemStack drop : drops) {
								double xMod = itemRand.nextFloat() * 0.7f + 0.15f;
								double yMod = itemRand.nextFloat() * 0.7f + 0.15f;
								double zMod = itemRand.nextFloat() * 0.7f + 0.15f;
								EntityItem item = new EntityItem(player.world, x + xMod, y + yMod, z + zMod, drop);

								item.setDefaultPickupDelay();
								player.world.spawnEntity(item);
							}

							stack.damageItem(1, player);
							player.addStat(StatList.getBlockStats(newBlock));
							player.world.setBlockState(newPos, Blocks.AIR.getDefaultState(), 11);
						}
					}
				}

				return true;
			}
		}

		return false;
	}

	@Override
	public boolean canDestroyBlockInCreative(World world, BlockPos pos, ItemStack stack, EntityPlayer player) {
		return world.getBlockState(pos) instanceof IShearable;
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.RosidianGreatblade.desc.1", Enums.ItemDescriptionType.POSITIVE));
	}
}
