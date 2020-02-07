package net.tslat.aoa3.item.tablet;

import net.minecraft.block.Block;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.client.gui.mainwindow.AdventGuiTabPlayer;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;
import net.tslat.aoa3.entity.misc.tablet.EntitySoulTablet;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.StringUtil;
import net.tslat.aoa3.utils.player.PlayerDataManager;
import net.tslat.aoa3.utils.player.PlayerUtil;

import javax.annotation.Nullable;
import java.util.List;

public abstract class TabletItem extends Item {
	private final float initialSoulCost;
	private final float perTickSoulCost;
	private final int animaLevelReq;
	private final int effectRadius;

	public TabletItem(String name, String registryName, float placementCost, float tickSoulDrain, int levelReq, int effectRadius) {
		setTranslationKey(name);
		setRegistryName("aoa3:" + registryName);
		setCreativeTab(CreativeTabsRegister.tabletsTab);
		setMaxStackSize(1);

		this.initialSoulCost = placementCost;
		this.perTickSoulCost = tickSoulDrain;
		this.animaLevelReq = levelReq;
		this.effectRadius = effectRadius;
	}

	public final float getSoulDrain() {
		return perTickSoulCost;
	}

	public int getEffectRadius() {
		return effectRadius;
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		IBlockState targetBlockState = world.getBlockState(pos).getActualState(world, pos);
		Block targetBlock = targetBlockState.getBlock();

		if (facing != EnumFacing.UP || targetBlockState.getBlockFaceShape(world, pos, EnumFacing.UP) != BlockFaceShape.SOLID)
			return EnumActionResult.FAIL;

		if (!world.isRemote) {
			PlayerDataManager plData = PlayerUtil.getAdventPlayer(player);

			if (player.isCreative() || plData.stats().getLevel(Enums.Skills.ANIMA) >= animaLevelReq) {
				float soulCost = initialSoulCost * (1 - ((plData.stats().getLevel(Enums.Skills.ANIMA) - 1) / 200f)) * (PlayerUtil.isWearingFullSet(player, Enums.ArmourSets.ANIMA) ? 0.5f : 1f);
				EntitySoulTablet tabletEntity = getTabletEntity(world, player);
				AxisAlignedBB blockBoundingBox = targetBlockState.getCollisionBoundingBox(world, pos);

				tabletEntity.setPositionAndRotation(pos.getX() + hitX, pos.getY() + (blockBoundingBox == null ? 0 : blockBoundingBox.maxY), pos.getZ() + hitZ, player.rotationYaw, player.rotationPitch);

				if (world.checkNoEntityCollision(tabletEntity.getEntityBoundingBox(), tabletEntity)) {
					if (plData.stats().consumeResource(Enums.Resources.SOUL, soulCost, false)) {
						world.spawnEntity(tabletEntity);

						if (!player.capabilities.isCreativeMode)
							player.getHeldItem(hand).shrink(1);

						return EnumActionResult.SUCCESS;
					}
					else {
						PlayerUtil.notifyPlayerOfInsufficientResources((EntityPlayerMP)player, Enums.Resources.SOUL, soulCost);
					}
				}
			}
			else {
				PlayerUtil.notifyPlayerOfInsufficientLevel((EntityPlayerMP)player, Enums.Skills.ANIMA, animaLevelReq);
			}
		}

		return EnumActionResult.PASS;
	}

	protected abstract EntitySoulTablet getTabletEntity(World world, EntityPlayer placer);

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(StringUtil.getColourLocaleStringWithArguments("items.description.skillRequirement", AdventGuiTabPlayer.getSkillLevel(Enums.Skills.ANIMA) < animaLevelReq ? TextFormatting.RED : TextFormatting.GREEN, String.valueOf(animaLevelReq), StringUtil.getLocaleString("skills.anima.name")));
		tooltip.add(StringUtil.getColourLocaleStringWithArguments("items.description.tablet.placementCost", TextFormatting.AQUA, String.valueOf(initialSoulCost * (1 - ((AdventGuiTabPlayer.getSkillLevel(Enums.Skills.ANIMA) - 1) / 200f)))));
		tooltip.add(StringUtil.getColourLocaleStringWithArguments("items.description.tablet.usageCost", TextFormatting.AQUA, String.valueOf(((int)(perTickSoulCost * 2000f)) / 100f)));
		tooltip.add(StringUtil.getColourLocaleStringWithArguments("items.description.tablet.radius", TextFormatting.AQUA, String.valueOf(effectRadius)));
	}
}
