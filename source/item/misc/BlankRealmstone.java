package net.tslat.aoa3.item.misc;

import net.minecraft.block.BlockCrops;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityHusk;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.capabilities.interfaces.CapabilityBaseMiscStack;
import net.tslat.aoa3.capabilities.providers.AdventMiscStackProvider;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.entity.base.AoATrader;
import net.tslat.aoa3.entity.mobs.precasia.EntityPrimitiveCarrotop;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ConfigurationUtil;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.StringUtil;
import net.tslat.aoa3.utils.player.PlayerUtil;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;

public class BlankRealmstone extends Item {
	public BlankRealmstone() {
		setTranslationKey("BlankRealmstone");
		setRegistryName("aoa3:blank_realmstone");
		setMaxStackSize(1);
		setCreativeTab(CreativeTabsRegister.miscTab);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		if (world.isRemote)
			player.openGui(AdventOfAscension.instance(), Enums.ModGuis.REALMSTONE_MENU.guiId, world, (int)player.posX, (int)player.posY, (int)player.posZ);

		return ActionResult.newResult(EnumActionResult.SUCCESS, player.getHeldItem(hand));
	}

	@Override
	public boolean onEntityItemUpdate(EntityItem entityItem) {
		if (!entityItem.world.isRemote) {
			if (entityItem.world.provider.getDimension() == ConfigurationUtil.MainConfig.dimensionIds.precasia) {
				BlockPos pos = entityItem.getPosition();
				IBlockState state = entityItem.world.getBlockState(pos);

				if (state.getBlock() == Blocks.CARROTS && ((BlockCrops)state.getBlock()).isMaxAge(state)) {
					EntityPrimitiveCarrotop carrotop = new EntityPrimitiveCarrotop(entityItem.world);

					carrotop.setPosition(pos.getX() + 0.5, pos.getY() + 0.2, pos.getZ() + 0.5);
					entityItem.world.spawnEntity(carrotop);
					entityItem.setDead();
					entityItem.world.setBlockToAir(entityItem.getPosition());
				}
			}
		}

		return false;
	}

	@Override
	public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer player, EntityLivingBase target, EnumHand hand) {
		if (player.world.provider.getDimension() == ConfigurationUtil.MainConfig.dimensionIds.creeponia && target instanceof AoATrader &&
				PlayerUtil.getAdventPlayer(player).equipment().getCurrentFullArmourSet() == Enums.ArmourSets.HAZMAT && player.getHeldItem(hand).getItem() == ItemRegister.realmstoneBlank) {

			if (!player.world.isRemote) {
				player.setHeldItem(hand, ItemStack.EMPTY);
				ItemUtil.givePlayerItemOrDrop(player, new ItemStack(ItemRegister.realmstoneVoxPonds));
				PlayerUtil.notifyPlayer((EntityPlayerMP)player, ((AoATrader)target).getInteractMessage(player.getHeldItem(hand)));
			}

			return true;
		}

		return false;
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		if (!attacker.world.isRemote && target.getHealth() <= 0 && target instanceof EntityHusk && attacker instanceof EntityPlayer)
			attacker.setHeldItem(EnumHand.MAIN_HAND, new ItemStack(ItemRegister.realmstoneBarathos));

		return super.hitEntity(stack, target, attacker);
	}

	@Nullable
	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable NBTTagCompound nbt) {
		return new AdventMiscStackProvider();
	}

	public static void handleAncientCavernTask(ItemStack stack, EntityLivingBase construct, EntityPlayer player) {
		CapabilityBaseMiscStack cap = stack.getCapability(AdventMiscStackProvider.MISC_STACK, null);

		if (cap != null) {
			HashMap<Class<? extends EntityLivingBase>, Long> constructKillMap;
			long currentWorldTime = construct.world.getTotalWorldTime();

			if (cap.getObject() == null) {
				constructKillMap = new HashMap<Class<? extends EntityLivingBase>, Long>(5);
			}
			else {
				constructKillMap = (HashMap<Class<? extends EntityLivingBase>, Long>)cap.getObject();
			}

			constructKillMap.entrySet().removeIf(entry -> entry.getValue() < currentWorldTime - 600);
			constructKillMap.put(construct.getClass(), currentWorldTime);

			if (constructKillMap.size() >= 5) {
				stack.shrink(1);
				ItemUtil.givePlayerItemOrDrop(player, new ItemStack(ItemRegister.realmstoneAncientCavern));
			}
			else {
				cap.setObject(constructKillMap);
			}
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(StringUtil.getLocaleString("item.BlankRealmstone.desc.1"));
		tooltip.add(StringUtil.getLocaleString("item.BlankRealmstone.desc.2"));
	}
}
