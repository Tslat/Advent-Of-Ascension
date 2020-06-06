package net.tslat.aoa3.block.functional.utility;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;
import net.tslat.aoa3.utils.StringUtil;

import javax.annotation.Nullable;
import java.util.List;

public class AscensionShrine extends Block {
	public AscensionShrine() {
		super(Material.ROCK);
		setTranslationKey("AscensionShrine");
		setRegistryName("aoa3:ascension_shrine");
		setHardness(10.0f);
		setResistance(15.0f);
		setSoundType(SoundType.STONE);
		setCreativeTab(CreativeTabsRegister.FUNCTIONAL_BLOCKS);
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!world.isRemote) {
			// TODO do something here
			/*if (player.getHeldItem(hand).getItem() instanceof AuguryEssence) {
				PlayerDataManager plData = PlayerUtil.getAdventPlayer(player);
				AuguryEssence essence = (AuguryEssence)player.getHeldItem(hand).getItem();

				if (player.capabilities.isCreativeMode || plData.stats().getLevel(Enums.Skills.AUGURY) >= essence.getLvlReq()) {
					int size = player.getHeldItem(hand).getCount();

					if (!player.capabilities.isCreativeMode)
						player.setHeldItem(hand, ItemStack.EMPTY);

					if (player.world.provider.getDimension() == 0 && player.world.isDaytime())
						plData.stats().addTribute(Enums.Deities.LUXON, 4 * size);

					plData.stats().addXp(Enums.Skills.AUGURY, essence.getXp() * size, false);
					world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundsRegister.ascensionShrineUse, SoundCategory.BLOCKS, 1.0f, 1.0f);
					player.inventoryContainer.detectAndSendChanges();
				}
				else if (player instanceof EntityPlayerMP) {
					PlayerUtil.notifyPlayerOfInsufficientLevel((EntityPlayerMP)player, Enums.Skills.AUGURY, essence.getLvlReq());
				}
			}*/
		}

		return true;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced) {
		tooltip.add(StringUtil.getLocaleString("tile.AscensionShrine.desc.1"));
	}
}
