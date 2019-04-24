package net.tslat.aoa3.block.functional.utility;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.common.registration.WeaponRegister;

public class LunarCreationTable extends Block {
	public LunarCreationTable() {
		super(Material.ROCK);
		setUnlocalizedName("LunarCreationTable");
		setRegistryName("aoa3:lunar_creation_table");
		setHardness(-1f);
		setResistance(999999999f);
		setSoundType(SoundType.STONE);
		setCreativeTab(CreativeTabsRegister.functionalBlocksTab);
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!world.isRemote) {
			Item weaponItem = null;

			if (world.getBlockState(pos.add(-2, 0, -2)).getBlock() == BlockRegister.lunarOrbDusk) {
				if (world.getBlockState(pos.add(2, 0, 2)).getBlock() == BlockRegister.lunarOrbLunar) {
					world.setBlockToAir(pos.add(-2, 0, -2));
					world.setBlockToAir(pos.add(2, 0, 2));
					weaponItem = WeaponRegister.gunIonRevolver;
				}
				else if (world.getBlockState(pos.add(-2, 0, 2)).getBlock() == BlockRegister.lunarOrbLunar) {
					world.setBlockToAir(pos.add(-2, 0, -2));
					world.setBlockToAir(pos.add(-2, 0, 2));
					weaponItem = WeaponRegister.blasterIonBlaster;
				}
			}
			else if (world.getBlockState(pos.add(-2, 0, -2)).getBlock() == BlockRegister.lunarOrbDarklight) {
				if (world.getBlockState(pos.add(2, 0, 2)).getBlock() == BlockRegister.lunarOrbLunar) {
					world.setBlockToAir(pos.add(-2, 0, -2));
					world.setBlockToAir(pos.add(2, 0, 2));
					weaponItem = WeaponRegister.staffLunar;
				}
			}
			else if (world.getBlockState(pos.add(-2, 0, -2)).getBlock() == BlockRegister.lunarOrbMoonlight) {
				if (world.getBlockState(pos.add(2, 0, 2)).getBlock() == BlockRegister.lunarOrbLunar) {
					if (world.getBlockState(pos.add(2, 0, -2)).getBlock() == BlockRegister.lunarOrbMoonlight) {
						world.setBlockToAir(pos.add(-2, 0, -2));
						world.setBlockToAir(pos.add(2, 0, 2));
						world.setBlockToAir(pos.add(2, 0, -2));
						weaponItem = WeaponRegister.staffMoonlight;
					}
					else {
						world.setBlockToAir(pos.add(-2, 0, -2));
						world.setBlockToAir(pos.add(2, 0, 2));
						weaponItem = WeaponRegister.blasterAtomizer;
					}
				}
			}
			else if (world.getBlockState(pos.add(-2, 0, -2)).getBlock() == BlockRegister.lunarOrbLunar) {
				if (world.getBlockState(pos.add(2, 0, 2)).getBlock() == BlockRegister.lunarOrbLunar) {
					if (world.getBlockState(pos.add(-2, 0, 2)).getBlock() == BlockRegister.lunarOrbLunar && world.getBlockState(pos.add(2, 0, -2)).getBlock() == BlockRegister.lunarOrbLunar) {
						world.setBlockToAir(pos.add(-2, 0, -2));
						world.setBlockToAir(pos.add(2, 0, 2));
						world.setBlockToAir(pos.add(-2, 0, 2));
						world.setBlockToAir(pos.add(2, 0, -2));
						weaponItem = WeaponRegister.staffMeteor;
					}
					else {
						world.setBlockToAir(pos.add(-2, 0, -2));
						world.setBlockToAir(pos.add(2, 0, 2));
						weaponItem = WeaponRegister.staffCelestial;
					}
				}
			}
			else if (world.getBlockState(pos.add(2, 0, -2)).getBlock() == BlockRegister.lunarOrbMoonlight) {
				if (world.getBlockState(pos.add(2, 0, 2)).getBlock() == BlockRegister.lunarOrbLunar) {
					world.setBlockToAir(pos.add(2, 0, -2));
					world.setBlockToAir(pos.add(2, 0, 2));
					weaponItem = WeaponRegister.cannonEnergyCannon;
				}
			}
			else if (world.getBlockState(pos.add(-2, 0, -2)).getBlock() == BlockRegister.lunarOrbSunfire) {
				if (world.getBlockState(pos.add(2, 0, 2)).getBlock() == BlockRegister.lunarOrbLunar) {
					world.setBlockToAir(pos.add(-2, 0, -2));
					world.setBlockToAir(pos.add(2, 0, 2));
					weaponItem = WeaponRegister.staffSun;
				}
			}

			if (weaponItem != null) {
				EntityItem weaponEntity = new EntityItem(world, pos.getX(), pos.getY() + 0.5, pos.getZ(), new ItemStack(weaponItem));

				weaponEntity.motionX = 0;
				weaponEntity.motionY = 0.3;
				weaponEntity.motionZ = 0;

				world.spawnEntity(weaponEntity);
				world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundsRegister.lunarCreationTableSuccess, SoundCategory.BLOCKS, 1.0f, 1.0f);

				return true;
			}
		}

		return true;
	}
}
