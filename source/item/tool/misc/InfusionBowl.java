package net.tslat.aoa3.item.tool.misc;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.misc.pixon.EntityPixon;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.StringUtil;
import net.tslat.aoa3.utils.player.PlayerDataManager;
import net.tslat.aoa3.utils.player.PlayerUtil;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class InfusionBowl extends Item {
	private final int harvestAmount;
	private final int harvestLevelModifier;

	public InfusionBowl(String name, String registryName, int durability, int harvestAmount, int harvestLevelModifier) {
		setTranslationKey(name);
		setRegistryName("aoa3:" + registryName);
		setCreativeTab(CreativeTabsRegister.TOOLS);
		setMaxDamage(durability);
		setMaxStackSize(1);

		this.harvestAmount = harvestAmount;
		this.harvestLevelModifier = harvestLevelModifier;
	}

	@Override
	public boolean isEnchantable(ItemStack stack) {
		return false;
	}

	public int getHarvestAmount() {
		return harvestAmount;
	}

	public int getHarvestReqModifier() {
		return harvestLevelModifier;
	}

	public void handlePixonHarvest(EntityPlayer player, EntityPixon pixon, ItemStack bowlItemStack) {
		LootTable harvestTable = player.world.getLootTableManager().getLootTableFromLocation(pixon.getHarvestLootTable());

		int harvestCount = 0;
		List<ItemStack> harvestStacks = new ArrayList<ItemStack>();
		LootContext lootContext = (new LootContext.Builder((WorldServer)player.world).withPlayer(player).withLootedEntity(pixon)).build();
		PlayerDataManager plData = PlayerUtil.getAdventPlayer(player);

		while (harvestCount < getHarvestAmount() && pixon.getHealth() > 0) {
			if (!player.capabilities.isCreativeMode)
				bowlItemStack.damageItem(1, player);

			harvestStacks.addAll(harvestTable.generateLootForPools(player.getRNG(), lootContext));

			if (plData.equipment().getCurrentFullArmourSet() == Enums.ArmourSets.INFUSION)
				harvestStacks.addAll(harvestTable.generateLootForPools(player.getRNG(), lootContext));

			pixon.setHealth(pixon.getHealth() - 7 + itemRand.nextInt(6));

			harvestCount++;
		}

		if (!harvestStacks.isEmpty())
			ItemUtil.givePlayerMultipleItems(player, harvestStacks);

		if (pixon.world.provider.getDimension() == 0 && pixon.world.isDaytime())
			plData.stats().addTribute(Enums.Deities.LUXON, 4 * harvestCount);

		if (pixon.isEntityAlive()) {
			pixon.setRevengeTarget(player);
		}
		else {
			player.onKillEntity(pixon);
		}

		player.addStat(StatList.getObjectUseStats(bowlItemStack.getItem()));
		player.world.playSound(null, pixon.getPosition().getX(), pixon.getPosition().getY(), pixon.getPosition().getZ(), SoundsRegister.ENTITY_PIXON_HARVEST, SoundCategory.MASTER, 1.0f, 1.0f);
	}

	@Override
	public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer player, EntityLivingBase target, EnumHand hand) {
		return target instanceof EntityPixon;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(StringUtil.getLocaleString("item.InfusionBowl.desc.1"));
	}
}
