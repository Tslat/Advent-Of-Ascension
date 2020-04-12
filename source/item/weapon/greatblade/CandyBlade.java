package net.tslat.aoa3.item.weapon.greatblade;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.item.weapon.AdventWeapon;
import net.tslat.aoa3.item.weapon.LongReachWeapon;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ItemUtil;

import java.util.List;

public class CandyBlade extends BaseGreatblade {
	private double dmg;

	public CandyBlade(double dmg, double speed, int durability) {
		super(dmg, speed, durability);
		this.dmg = dmg;
		setTranslationKey("CandyBlade");
		setRegistryName("aoa3:candy_blade");
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.EAT;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 32;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		ItemStack stack = player.getHeldItem(hand);

		if (player.canEat(false)) {
			player.setActiveHand(hand);

			return ActionResult.newResult(EnumActionResult.SUCCESS, stack);
		}

		return ActionResult.newResult(EnumActionResult.FAIL, stack);
	}

	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entity) {
		if (entity instanceof EntityPlayer) {
			EntityPlayer pl = (EntityPlayer)entity;
			int foodHealAmount = Math.min(20 - pl.getFoodStats().getFoodLevel(), stack.getMaxDamage() - stack.getItemDamage());

			pl.getFoodStats().addStats(foodHealAmount, 20f);
			stack.damageItem(foodHealAmount * 2, pl);
		}

		return stack;
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.CandyBlade.desc.1", Enums.ItemDescriptionType.POSITIVE));
	}
}
