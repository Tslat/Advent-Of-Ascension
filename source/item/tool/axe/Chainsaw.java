package net.tslat.aoa3.item.tool.axe;

import net.minecraft.block.BlockLog;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.*;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.item.tool.SpecialHarvestTool;
import net.tslat.aoa3.utils.StringUtil;

import java.util.List;

public class Chainsaw extends ItemAxe implements SpecialHarvestTool {
	public Chainsaw(ToolMaterial material) {
		super(material, material.getAttackDamage(), -2f);
		setUnlocalizedName("Chainsaw");
		setRegistryName("aoa3:chainsaw");
		setCreativeTab(CreativeTabsRegister.toolsTab);
	}

	@Override
	public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack) {
		return true;
	}

	public void doHarvestEffect(BlockEvent.HarvestDropsEvent e) {
		if (e.getState().getBlock() instanceof BlockLog && itemRand.nextInt(4) == 0) {
			Item drop = e.getDrops().get(0).getItem();

			if (drop instanceof ItemBlock && ((ItemBlock)drop).getBlock() == e.getState().getBlock()) {
				e.getDrops().set(0, ItemStack.EMPTY);
			}
		}
	}

	@Override
	public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, EntityPlayer pl) {
		if (!pl.world.isRemote)
			pl.world.playSound(null, pl.posX, pl.posY, pl.posZ, SoundsRegister.chainsawUse, SoundCategory.PLAYERS, 1.0f, 1.0f);

		return false;
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		if (!attacker.world.isRemote) {
			attacker.world.playSound(null, attacker.posX, attacker.posY, attacker.posZ, SoundsRegister.chainsawUse, SoundCategory.PLAYERS, 1.0f, 1.0f);
			stack.damageItem(1, attacker);
		}

		return true;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.NONE;
	}

	@Override
	public boolean getIsRepairable(ItemStack stack, ItemStack repairMaterial) {
		return OreDictionary.itemMatches(repairMaterial, new ItemStack(ItemRegister.ingotRosite), false) || super.getIsRepairable(stack, repairMaterial);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(StringUtil.getColourLocaleString("item.Chainsaw.desc.1", TextFormatting.DARK_GREEN));
	}
}
