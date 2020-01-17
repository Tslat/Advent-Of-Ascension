package net.tslat.aoa3.item.misc.summon;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.boss.rockrider.EntityRockRider;
import net.tslat.aoa3.utils.ConfigurationUtil;
import net.tslat.aoa3.utils.StringUtil;
import net.tslat.aoa3.utils.player.PlayerUtil;

import javax.annotation.Nullable;
import java.util.List;

public class HeavyBoulder extends BossSpawningItem {
	public HeavyBoulder() {
		super("HeavyBoulder", "heavy_boulder", SoundsRegister.mobRockRiderSwitch, EnumParticleTypes.SUSPENDED_DEPTH, EnumParticleTypes.SMOKE_LARGE);
	}

	@Override
	public void spawnBoss(World world, EntityPlayer summoner, double posX, double posY, double posZ) {
		EntityRockRider rockrider = new EntityRockRider(world);

		rockrider.setLocationAndAngles(posX, posY, posZ, itemRand.nextFloat() * 360f, 0f);
		world.spawnEntity(rockrider);
		StringUtil.sendMessageWithinRadius(StringUtil.getLocaleWithArguments("message.mob.rockrider.spawn", summoner.getDisplayNameString()), rockrider, 50);
	}

	@Override
	public boolean canSpawnHere(World world, EntityPlayer player, double posX, double posY, double posZ) {
		if (world.provider.getDimension() != ConfigurationUtil.MainConfig.dimensionIds.haven) {
			PlayerUtil.getAdventPlayer(player).sendThrottledChatMessage("message.mob.rockrider.incorrectDimension");

			return false;
		}

		if (world.collidesWithAnyBlock(new AxisAlignedBB(posX - 1d, posY, posZ - 1d, posX + 1d, posY + 4d, posZ + 1d))) {
			PlayerUtil.getAdventPlayer(player).sendThrottledChatMessage("message.feedback.spawnBoss.noSpace");

			return false;
		}

		return true;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(StringUtil.getColourLocaleString("item.HeavyBoulder.desc.1", TextFormatting.DARK_GRAY));
		tooltip.add(StringUtil.getColourLocaleString("items.description.bossSummonItem.unstable", TextFormatting.DARK_RED));
	}
}
