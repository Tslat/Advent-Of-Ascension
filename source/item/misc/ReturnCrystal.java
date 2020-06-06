package net.tslat.aoa3.item.misc;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;
import net.tslat.aoa3.common.registration.DimensionRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.item.food.BasicFood;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.StringUtil;
import net.tslat.aoa3.utils.player.PlayerDataManager;
import net.tslat.aoa3.utils.player.PlayerUtil;

import javax.annotation.Nullable;
import java.util.List;

public class ReturnCrystal extends BasicFood {
	public ReturnCrystal() {
		super("ReturnCrystal", "return_crystal", 0, 0);
		setAlwaysEdible();
		setCreativeTab(CreativeTabsRegister.MISC);
	}

	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World world, EntityLivingBase eater) {
		if (!world.isRemote) {
			if (eater instanceof EntityPlayer) {
				PlayerDataManager plData = PlayerUtil.getAdventPlayer(((EntityPlayer)eater));

				if (world.provider.getDimension() == DimensionRegister.DIM_IMMORTALLIS.getId()) {
					plData.stats().resetAllTribute();
					ItemUtil.consumeItem((EntityPlayer)eater, new ItemStack(ItemRegister.PROGRESS_COIN1));
					ItemUtil.consumeItem((EntityPlayer)eater, new ItemStack(ItemRegister.PROGRESS_COIN2));
					ItemUtil.consumeItem((EntityPlayer)eater, new ItemStack(ItemRegister.PROGRESS_COIN3));
					ItemUtil.consumeItem((EntityPlayer)eater, new ItemStack(ItemRegister.PROGRESS_COIN4));

					if (!((EntityPlayer)eater).capabilities.isCreativeMode)
						stack.shrink(1);

					eater.setPositionAndUpdate(-5, 20, 3);
				}
				else {
					plData.sendThrottledChatMessage("message.feedback.item.returnCrystal.wrongDim");
				}
			}
		}

		return stack;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(StringUtil.getLocaleString("item.ReturnCrystal.desc.1"));
	}
}
