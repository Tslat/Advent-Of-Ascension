package net.tslat.aoa3.item.weapon.vulcane;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.item.weapon.AdventWeapon;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.utils.StringUtil;
import net.tslat.aoa3.utils.player.PlayerDataManager;
import net.tslat.aoa3.utils.player.PlayerUtil;

import java.util.List;

public abstract class BaseVulcane extends Item implements AdventWeapon {
	protected double baseDmg;

	public BaseVulcane(double dmg, int durability) {
		setCreativeTab(CreativeTabsRegister.vulcanesTab);
		this.baseDmg = dmg;
		setMaxDamage(durability);
		setFull3D();
		setMaxStackSize(1);
		setNoRepair();
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.BOW;
	}

	public double getDamage() {
		return baseDmg;
	}

	@Override
	public boolean getIsRepairable(ItemStack stack, ItemStack repairMaterial) {
		return repairMaterial.getItem() != Items.ENCHANTED_BOOK && OreDictionary.itemMatches(repairMaterial, new ItemStack(ItemRegister.magicRepairDust), false);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		ItemStack stack = player.getHeldItem(hand);

		if (world.isRemote)
			return ActionResult.newResult(EnumActionResult.FAIL, stack);

		PlayerDataManager plData = PlayerUtil.getAdventPlayer(player);

		if (!plData.isRevengeActive())
			return ActionResult.newResult(EnumActionResult.FAIL, stack);

		return activate(plData, stack);
	}

	public ActionResult<ItemStack> activate(PlayerDataManager plData, ItemStack vulcane) {
		EntityPlayer pl = plData.player();

		if (EntityUtil.dealVulcaneDamage(plData.getRevengeTarget(), pl, (float)baseDmg)) {
			doAdditionalEffect(plData.getRevengeTarget(), pl);
			pl.world.playSound(null, pl.posX, pl.posY, pl.posZ, SoundsRegister.vulcaneUse, SoundCategory.PLAYERS, 1.0f, 1.0f);
			vulcane.damageItem(1, pl);
			plData.disableRevenge();
			return ActionResult.newResult(EnumActionResult.SUCCESS, vulcane);
		}

		return ActionResult.newResult(EnumActionResult.FAIL, vulcane);
	}

	public void doAdditionalEffect(EntityLivingBase target, EntityPlayer player) {}

	@Override
	public int getItemEnchantability() {
		return 8;
	}

	@Override
	public boolean isFull3D() {
		return true;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(1, StringUtil.getColourLocaleStringWithArguments("items.description.damage.true", TextFormatting.DARK_RED, Double.toString(baseDmg)));
		tooltip.add(StringUtil.getColourLocaleString("items.description.vulcane.use", TextFormatting.AQUA));
	}
}
