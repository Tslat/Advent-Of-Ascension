package net.tslat.aoa3.item.misc;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.tslat.aoa3.capabilities.handlers.AdventPlayerCapability;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;
import net.tslat.aoa3.common.registration.DimensionRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.item.food.BasicFood;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.PlayerUtil;
import net.tslat.aoa3.utils.StringUtil;

import javax.annotation.Nullable;
import java.util.List;

public class ReturnCrystal extends BasicFood {
	public ReturnCrystal() {
		super("ReturnCrystal", "return_crystal", 0, 0);
		setAlwaysEdible();
		setCreativeTab(CreativeTabsRegister.miscTab);
	}

	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World world, EntityLivingBase eater) {
		if (!world.isRemote) {
			if (eater instanceof EntityPlayer) {
				AdventPlayerCapability cap = PlayerUtil.getAdventPlayer(((EntityPlayer)eater));

				if (world.provider.getDimension() == DimensionRegister.dimensionImmortallis.getId()) {
					cap.resetAllTribute();
					ItemUtil.consumeItem((EntityPlayer)eater, new ItemStack(ItemRegister.progressCoin1));
					ItemUtil.consumeItem((EntityPlayer)eater, new ItemStack(ItemRegister.progressCoin2));
					ItemUtil.consumeItem((EntityPlayer)eater, new ItemStack(ItemRegister.progressCoin3));
					ItemUtil.consumeItem((EntityPlayer)eater, new ItemStack(ItemRegister.progressCoin4));

					if (!((EntityPlayer)eater).capabilities.isCreativeMode)
						stack.shrink(1);

					eater.setPositionAndUpdate(-5, 20, 3);
				}
				else {
					cap.sendPlayerMessage(StringUtil.getLocale("message.feedback.item.returnCrystal.wrongDim"));
				}
			}
		}

		return stack;
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(StringUtil.getLocaleString("item.ReturnCrystal.desc.1"));
	}
}
