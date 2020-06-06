package net.tslat.aoa3.item.lootbox;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.utils.LootUtil;
import net.tslat.aoa3.utils.StringUtil;

import javax.annotation.Nullable;
import java.util.List;

public class FishCase extends Item {
	public FishCase() {
		setTranslationKey("FishCase");
		setRegistryName("aoa3:fish_case");
		setCreativeTab(CreativeTabsRegister.MISC);
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!world.isRemote) {
			LootUtil.generateAndProvideLootDirectly((EntityPlayerMP)player, LootSystemRegister.itemFishCase);

			if (!player.capabilities.isCreativeMode)
				player.getHeldItem(hand).shrink(1);

			player.inventoryContainer.detectAndSendChanges();

			return EnumActionResult.SUCCESS;
		}

		return EnumActionResult.PASS;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(StringUtil.getColourLocaleString("item.FishCase.desc.1", TextFormatting.GOLD));
	}
}
