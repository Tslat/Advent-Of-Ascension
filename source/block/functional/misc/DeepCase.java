package net.tslat.aoa3.block.functional.misc;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.ArmourRegister;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.WeaponRegister;

import java.util.Random;

public class DeepCase extends Block {
	public DeepCase() {
		super(Material.ROCK);
		setUnlocalizedName("DeepCase");
		setRegistryName("aoa3:deep_case");
		setHardness(5.0f);
		setResistance(3f);
		setSoundType(SoundType.STONE);
		setCreativeTab(CreativeTabsRegister.functionalBlocksTab);
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Items.AIR;
	}

	@Override
	protected ItemStack getSilkTouchDrop(IBlockState state) {
		return super.getSilkTouchDrop(state);
	}

	@Override
	public void onBlockHarvested(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
		if (!world.isRemote) {
			ItemStack heldStack = player.getHeldItem(EnumHand.MAIN_HAND);

			if (EnchantmentHelper.getEnchantmentLevel(Enchantments.SILK_TOUCH, heldStack) == 0 && heldStack.canHarvestBlock(state)){
				ItemStack lootStack;
				EntityItem lootEntity;

				switch (AdventOfAscension.rand.nextInt(20)) {
					case 0:
						lootStack = new ItemStack(ItemRegister.tokensDeeplands, 50);
						break;
					case 1:
						lootStack = new ItemStack(WeaponRegister.bowDeep);
						break;
					case 2:
						lootStack = new ItemStack(ArmourRegister.SubterraneanBoots);
						break;
					case 3:
						lootStack = new ItemStack(ArmourRegister.SubterraneanHelmet);
						break;
					case 4:
						lootStack = new ItemStack(ArmourRegister.SubterraneanLegs);
						break;
					case 5:
						lootStack = new ItemStack(ArmourRegister.SubterraneanBody);
						break;
					case 6:
						lootStack = new ItemStack(ItemRegister.tokensDeeplands, 10 + AdventOfAscension.rand.nextInt(11));
						break;
					case 7:
						lootStack = new ItemStack(ItemRegister.coinGold, 2);
						break;
					default:
						lootStack = new ItemStack(ItemRegister.coinCopper, 2 + AdventOfAscension.rand.nextInt(25));
						break;
				}

				lootEntity = new EntityItem(world, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, lootStack);
				world.spawnEntity(lootEntity);
			}
		}
	}
}
