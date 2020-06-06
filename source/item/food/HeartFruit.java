package net.tslat.aoa3.item.food;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.utils.ConfigurationUtil;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.StringUtil;

import javax.annotation.Nullable;
import java.util.List;

public class HeartFruit extends BasicFood {
	public HeartFruit() {
		super("HeartFruit", "heart_fruit", 15, 0.3f);
		BlockRegister.HEART_FRUIT_CROP.setCrop(this);
	}

	@Override
	protected void onFoodEaten(ItemStack stack, World world, EntityPlayer player) {
		super.onFoodEaten(stack, world, player);

		if (!world.isRemote) {
			EntityUtil.dealSelfHarmDamage(player, 7.0f);

			if (player.getHealth() > 0 && world.provider.getDimension() == ConfigurationUtil.MainConfig.dimensionIds.precasia && ItemUtil.consumeItem(player, new ItemStack(ItemRegister.BLANK_REALMSTONE)))
				ItemUtil.givePlayerItemOrDrop(player, new ItemStack(ItemRegister.CANDYLAND_REALMSTONE));
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(StringUtil.getLocaleString("item.HeartFruit.desc.1"));
	}
}
