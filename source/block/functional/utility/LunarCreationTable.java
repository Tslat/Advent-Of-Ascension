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
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ArmourRegister;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;
import net.tslat.aoa3.common.registration.ItemRegister;

import java.util.HashSet;

public class LunarCreationTable extends Block {
	public LunarCreationTable() {
		super(Material.ROCK);
		setTranslationKey("LunarCreationTable");
		setRegistryName("aoa3:lunar_creation_table");
		setHardness(-1f);
		setResistance(999999999f);
		setSoundType(SoundType.STONE);
		setCreativeTab(CreativeTabsRegister.functionalBlocksTab);
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!world.isRemote) {
			BlockPos lunarPos = null;
			BlockPos moonlightPos = null;
			BlockPos darklightPos = null;
			BlockPos sunfirePos = null;
			BlockPos duskPos = null;

			for (int x = -2; x <= 2; x += 2) {
				for (int z = -2; z <= 2; z += 2) {
					BlockPos checkPos = pos.add(x, 0, z);
					IBlockState block = world.getBlockState(checkPos);

					if (block.getBlock() == BlockRegister.lunarOrbLunar) {
						lunarPos = checkPos;
					}
					else if (block.getBlock() == BlockRegister.lunarOrbMoonlight) {
						moonlightPos = checkPos;
					}
					else if (block.getBlock() == BlockRegister.lunarOrbDarklight) {
						darklightPos = checkPos;
					}
					else if (block.getBlock() == BlockRegister.lunarOrbDusk) {
						duskPos = checkPos;
					}
					else if (block.getBlock() == BlockRegister.lunarOrbSunfire) {
						sunfirePos = checkPos;
					}
				}
			}

			if (lunarPos != null && moonlightPos != null) {
				if (darklightPos != null && sunfirePos != null && duskPos != null) {
					HashSet<Item> armours = new HashSet<Item>(4);

					armours.add(ArmourRegister.lunarBoots);
					armours.add(ArmourRegister.lunarLegs);
					armours.add(ArmourRegister.lunarBody);
					armours.add(ArmourRegister.lunarHelmet);

					for (ItemStack stack : player.inventory.mainInventory) {
						armours.removeIf(item -> item == stack.getItem());
					}

					for (ItemStack stack : player.inventory.armorInventory) {
						armours.removeIf(item -> item == stack.getItem());
					}

					for (ItemStack stack : player.inventory.armorInventory) {
						armours.removeIf(item -> item == stack.getItem());
					}

					if (armours.isEmpty()) {
						armours.add(ArmourRegister.lunarBoots);
						armours.add(ArmourRegister.lunarLegs);
						armours.add(ArmourRegister.lunarBody);
						armours.add(ArmourRegister.lunarHelmet);
					}

					Item armourPiece = (Item)armours.toArray()[player.getRNG().nextInt(armours.size())];

					world.spawnEntity(new EntityItem(world, pos.getX() + 0.5d, pos.getY() + 1.5d, pos.getZ() + 0.5d, new ItemStack(armourPiece)));
					world.setBlockToAir(lunarPos);
					world.setBlockToAir(moonlightPos);
					world.setBlockToAir(duskPos);
					world.setBlockToAir(sunfirePos);
					world.setBlockToAir(darklightPos);
				}
				else {
					world.spawnEntity(new EntityItem(world, pos.getX() + 0.5d, pos.getY() + 1.5d, pos.getZ() + 0.5d, new ItemStack(ItemRegister.observingEye)));
					world.setBlockToAir(lunarPos);
					world.setBlockToAir(moonlightPos);
				}
			}
		}

		return true;
	}
}
