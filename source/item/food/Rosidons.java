package net.tslat.aoa3.item.food;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.tslat.aoa3.capabilities.handlers.AdventPlayerCapability;
import net.tslat.aoa3.capabilities.providers.AdventPlayerProvider;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.utils.ConfigurationUtil;
import net.tslat.aoa3.utils.StringUtil;
import net.tslat.aoa3.utils.WorldUtil;

import javax.annotation.Nullable;
import java.util.List;

public class Rosidons extends BasicFood {
	public Rosidons() {
		super("Rosidons", "rosidons", 0, 0);
		setAlwaysEdible();
		BlockRegister.cropRosidons.setCrop(this);
	}

	@Override
	protected void onFoodEaten(ItemStack stack, World world, EntityPlayer player) {
		super.onFoodEaten(stack, world, player);

		if (!world.isRemote) {
			AdventPlayerCapability cap = (AdventPlayerCapability)player.getCapability(AdventPlayerProvider.ADVENT_PLAYER, null);

			if (player.dimension == ConfigurationUtil.dimAncientCavern || player.dimension == ConfigurationUtil.dimImmortallis) {
				cap.sendPlayerMessage(StringUtil.getLocale("message.feedback.item.rosidons.dimFail"));
				return;
			}

			int calculatedY = WorldUtil.getTrueWorldHeight(world, (int)player.posX, (int)player.posZ);

			if (calculatedY == 0) {
				cap.sendPlayerMessage(StringUtil.getLocale("message.feedback.item.rosidons.noHeightFail"));
				return;
			}

			player.setPositionAndUpdate(player.posX, calculatedY + 2, player.posZ);
		}
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(StringUtil.getLocaleString("item.Rosidons.desc.1"));
	}
}
