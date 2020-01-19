package net.tslat.aoa3.item.food;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.utils.StringUtil;

import javax.annotation.Nullable;
import java.util.List;

public class TrilliadLeaves extends BasicFood {
	public TrilliadLeaves() {
		super("TrilliadLeaves", "trilliad_leaves", 0, 0);
		setAlwaysEdible();
		BlockRegister.cropTrilliads.setCrop(this);
	}

	@Override
	protected void onFoodEaten(ItemStack stack, World world, EntityPlayer player) {
		super.onFoodEaten(stack, world, player);

		if (!world.isRemote) {
			player.removePotionEffect(MobEffects.NAUSEA);
			player.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 130, 0, true, false));
			player.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 100, 10, true, true));
			player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 100, 2, true, false));
			player.addPotionEffect(new PotionEffect(MobEffects.POISON, 100, 7, true, true));
			player.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 100, 128, true, false));
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(StringUtil.getLocaleString("item.TrilliadLeaves.desc.1"));
	}
}
