package net.tslat.aoa3.block.functional.utility;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.capabilities.handlers.AdventPlayerCapability;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.item.misc.AuguryEssence;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.PlayerUtil;

public class AscensionShrine extends Block {
	public AscensionShrine() {
		super(Material.ROCK);
		setUnlocalizedName("AscensionShrine");
		setRegistryName("aoa3:ascension_shrine");
		setHardness(10.0f);
		setResistance(15.0f);
		setSoundType(SoundType.STONE);
		setCreativeTab(CreativeTabsRegister.functionalBlocksTab);
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!world.isRemote && player.getHeldItem(hand).getItem() instanceof AuguryEssence) {
			AdventPlayerCapability cap = PlayerUtil.getAdventPlayer(player);
			AuguryEssence essence = (AuguryEssence)player.getHeldItem(hand).getItem();

			if (player.capabilities.isCreativeMode || cap.getLevel(Enums.Skills.AUGURY) >= essence.getLvlReq()) {
				if (!player.capabilities.isCreativeMode)
					player.getHeldItem(hand).shrink(1);

				if (player.world.provider.getDimension() == 0 && player.world.isDaytime())
					cap.addTribute(Enums.Deities.LUXON, 4);

				cap.addXp(Enums.Skills.AUGURY, essence.getXp(), false);
				world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundsRegister.ascensionShrineUse, SoundCategory.BLOCKS, 1.0f, 1.0f);
				player.inventoryContainer.detectAndSendChanges();
			}
		}

		return true;
	}
}
