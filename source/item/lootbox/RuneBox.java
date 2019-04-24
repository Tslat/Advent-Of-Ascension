package net.tslat.aoa3.item.lootbox;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.tslat.aoa3.capabilities.handlers.AdventPlayerCapability;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.entity.mobs.misc.EntitySeaSkeleton;
import net.tslat.aoa3.entity.mobs.misc.EntitySeaSpider;
import net.tslat.aoa3.entity.mobs.overworld.EntityAmphibiyte;
import net.tslat.aoa3.entity.mobs.overworld.EntitySeaTroll;
import net.tslat.aoa3.entity.mobs.overworld.EntitySpinux;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.PlayerUtil;
import net.tslat.aoa3.utils.StringUtil;
import net.tslat.aoa3.utils.skills.HaulingUtil;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class RuneBox extends Item {
	public RuneBox() {
		setUnlocalizedName("RuneBox");
		setRegistryName("aoa3:rune_box");
		setCreativeTab(CreativeTabsRegister.miscTab);
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!world.isRemote) {
			AdventPlayerCapability cap = PlayerUtil.getAdventPlayer(player);

			if (itemRand.nextInt(7 + (8 / (100 / cap.getLevel(Enums.Skills.HAULING)))) == 0) {
				switch (itemRand.nextInt(5)) {
					case 0:
						world.spawnEntity(new EntitySeaTroll(world, pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5));
						break;
					case 1:
						world.spawnEntity(new EntitySpinux(world, pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5));
						break;
					case 2:
						world.spawnEntity(new EntityAmphibiyte(world, pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5));
						break;
					case 3:
						world.spawnEntity(new EntitySeaSkeleton(world, pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5));
						break;
					case 4:
						world.spawnEntity(new EntitySeaSpider(world, pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5));
						break;
				}
			}
			else {
				if (!player.capabilities.isCreativeMode)
					player.getHeldItem(hand).shrink(1);

				for (ItemStack st : getRandomRunes(HaulingUtil.getRunesLootCount(cap.getLevel(Enums.Skills.HAULING)))) {
					ItemUtil.givePlayerItemOrDrop(player, st);
				}
			}
		}

		return EnumActionResult.PASS;
	}

	private HashSet<ItemStack> getRandomRunes(final int amount) {
		HashMap<Item, Integer> runeMap = new HashMap<Item, Integer>();

		for (int i = 0; i < amount; i++) {
			Item rune = null;

			switch (itemRand.nextInt(14)) {
				case 0:
					rune = ItemRegister.runeWind;
					break;
				case 1:
					rune = ItemRegister.runeFire;
					break;
				case 2:
					rune = ItemRegister.runeWater;
					break;
				case 3:
					rune = ItemRegister.runeWither;
					break;
				case 4:
					rune = ItemRegister.runeEnergy;
					break;
				case 5:
					rune = ItemRegister.runeLunar;
					break;
				case 6:
					rune = ItemRegister.runeKinetic;
					break;
				case 7:
					rune = ItemRegister.runeDistortion;
					break;
				case 8:
					rune = ItemRegister.runePoison;
					break;
				case 9:
					rune = ItemRegister.runeStrike;
					break;
				case 10:
					rune = ItemRegister.runePower;
					break;
				case 11:
					rune = ItemRegister.runeStorm;
					break;
				case 12:
					rune = ItemRegister.runeLife;
					break;
				case 13:
					rune = ItemRegister.runeCompass;
					break;
			}

			if (runeMap.containsKey(rune)) {
				runeMap.put(rune, runeMap.get(rune) + 1);
			}
			else {
				runeMap.put(rune, 1);
			}
		}

		HashSet<ItemStack> stacks = new HashSet<ItemStack>();

		for (Item it : runeMap.keySet()) {
			ItemStack stack = new ItemStack(it, runeMap.get(it));

			stacks.add(stack);
		}

		return stacks;
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(StringUtil.getColourLocaleString("item.RuneBox.desc.1", TextFormatting.GOLD));
	}
}
