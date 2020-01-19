package net.tslat.aoa3.item.misc;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;
import net.tslat.aoa3.entity.boss.cottoncandor.EntityCottonCandor;
import net.tslat.aoa3.item.food.BasicFood;
import net.tslat.aoa3.utils.ConfigurationUtil;
import net.tslat.aoa3.utils.StringUtil;
import net.tslat.aoa3.utils.player.PlayerUtil;

import javax.annotation.Nullable;
import java.util.List;

public class TreatBag extends BasicFood {
	public TreatBag() {
		super("TreatBag", "treat_bag", 0, 0);
		setAlwaysEdible();
		setCreativeTab(CreativeTabsRegister.miscTab);
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 120;
	}

	@Override
	public EnumActionResult onItemUseFirst(EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, EnumHand hand) {
		return EnumActionResult.SUCCESS;
	}

	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World world, EntityLivingBase eater) {
		if (eater instanceof EntityPlayer) {
			EntityPlayer pl = (EntityPlayer)eater;

			if (world.getDifficulty() == EnumDifficulty.PEACEFUL) {
				if (!world.isRemote)
					PlayerUtil.getAdventPlayer(pl).sendThrottledChatMessage("message.feedback.spawnBoss.difficultyFail");
			}
			else if (!world.isRemote) {
				if (world.provider.getDimension() != ConfigurationUtil.MainConfig.dimensionIds.candyland) {
					PlayerUtil.getAdventPlayer(pl).sendThrottledChatMessage("message.mob.cottonCandor.incorrectDimension");
				}
				else {
					EntityCottonCandor cottonCandor = new EntityCottonCandor(world);

					cottonCandor.setPosition(eater.posX, eater.posY + 15, eater.posZ);
					world.spawnEntity(cottonCandor);
					stack.shrink(1);
					StringUtil.sendMessageWithinRadius(StringUtil.getLocaleWithArguments("message.mob.cottonCandor.spawn", ((EntityPlayer)eater).getDisplayNameString()), eater, 50);
				}
			}
		}

		return stack;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(StringUtil.getLocaleString("item.TreatBag.desc.1"));
		tooltip.add(StringUtil.getLocaleString("item.TreatBag.desc.2"));
	}
}
