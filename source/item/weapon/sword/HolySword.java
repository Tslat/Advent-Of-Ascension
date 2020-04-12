package net.tslat.aoa3.item.weapon.sword;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.capabilities.interfaces.CapabilityBaseMiscStack;
import net.tslat.aoa3.capabilities.providers.AdventMiscStackProvider;
import net.tslat.aoa3.item.weapon.AdventWeapon;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ItemUtil;

import java.util.List;

public class HolySword extends BaseSword {
	public HolySword(final ToolMaterial material, final double speed) {
		super(material, speed);
		setTranslationKey("HolySword");
		setRegistryName("aoa3:holy_sword");
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		ItemStack stack = player.getHeldItem(hand);
		CapabilityBaseMiscStack cap = stack.getCapability(AdventMiscStackProvider.MISC_STACK, null);

		if (cap != null && cap.getValue() < world.getTotalWorldTime()) {
			world.addWeatherEffect(new EntityLightningBolt(world, player.posX, player.posY, player.posZ, true));
			cap.setValue(world.getTotalWorldTime() + 100);

			return ActionResult.newResult(EnumActionResult.SUCCESS, stack);
		}

		return super.onItemRightClick(world, player, hand);
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.HolySword.desc.1", Enums.ItemDescriptionType.UNIQUE));
	}
}
