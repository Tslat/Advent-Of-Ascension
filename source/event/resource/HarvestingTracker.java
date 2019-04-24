package net.nevermine.event.resource;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockOre;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.world.BlockEvent;
import net.nevermine.assist.ConfigurationHelper;
import net.nevermine.assist.StringUtil;
import net.nevermine.block.generation.BlockForagingStone;
import net.nevermine.block.generation.OreBlock;
import net.nevermine.common.nevermine;
import net.nevermine.container.PlayerContainer;
import net.nevermine.item.tool.ExtractionTool;
import net.nevermine.izer.Blockizer;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.Plantizer;
import net.nevermine.izer.equipment.Toolizer;
import net.nevermine.skill.foraging.foragingHelper;
import net.nevermine.skill.logging.loggingHelper;

import java.util.Random;

import static net.nevermine.container.PlayerContainer.Deities.Pluton;
import static net.nevermine.container.PlayerContainer.Skills.Foraging;
import static net.nevermine.container.PlayerContainer.Skills.Logging;

public class HarvestingTracker {
	Random rand = new Random();

	@SubscribeEvent
	public void specialityToolHarvest(final BlockEvent.HarvestDropsEvent e) {
		if (e.world.isRemote || e.harvester == null || e.harvester instanceof FakePlayer || e.harvester.getHeldItem() == null || !(e.harvester.getHeldItem().getItem() instanceof ExtractionTool))
			return;

		Item tool = e.harvester.getHeldItem().getItem();

		if (e.block instanceof BlockOre || e.block instanceof OreBlock) {
			if (EnchantmentHelper.getSilkTouchModifier(e.harvester))
				return;

			if (tool == Toolizer.OrnamytePickaxe) {
				if (e.block.getItemDropped(1, rand, 0) instanceof ItemBlock) {
					if (FurnaceRecipes.smelting().getSmeltingResult(new ItemStack(e.block)) != null) {
						final ItemStack s = FurnaceRecipes.smelting().getSmeltingResult(new ItemStack(e.block));

						s.stackSize = 2;
						e.drops.clear();
						e.drops.add(s);
					}
				}
				else {
					e.drops.add(new ItemStack(e.block.getItemDropped(1, rand, 0), 1, e.world.getBlockMetadata(e.x, e.y, e.z)));
				}
			}
			else if (tool == Toolizer.GoofyPickaxe) {
				if (rand.nextBoolean()) {
					if (e.block.getItemDropped(1, rand, 0) instanceof ItemBlock) {
						if (FurnaceRecipes.smelting().getSmeltingResult(new ItemStack(e.block)) != null) {
							final ItemStack s = FurnaceRecipes.smelting().getSmeltingResult(new ItemStack(e.block));
							s.stackSize = 4;
							e.drops.clear();
							e.drops.add(s);
						}
					}
					else {
						e.drops.add(new ItemStack(e.block.getItemDropped(1, rand, 0), 1, e.world.getBlockMetadata(e.x, e.y, e.z)));
					}
				}
				else {
					e.drops.clear();
				}
			}
		}
		else if (e.block instanceof BlockLog) {
			if (tool == Toolizer.OrnamyteAxe) {
				e.drops.add(new ItemStack(e.block.getItemDropped(1, rand, 0), 1, e.world.getBlockMetadata(e.x, e.y, e.z)));
			}
			else if (tool == Toolizer.GoofyAxe) {
				if (rand.nextBoolean()) {
					e.drops.add(new ItemStack(e.block.getItemDropped(1, rand, 0), 3, e.world.getBlockMetadata(e.x, e.y, e.z)));
				}
				else {
					e.drops.clear();
				}
			}
		}
		else {
			if (tool == Toolizer.OrnamyteShovel) {
				e.drops.add(new ItemStack(e.block.getItemDropped(1, rand, 0), 1, e.world.getBlockMetadata(e.x, e.y, e.z)));
			}
			else if (tool == Toolizer.GoofyShovel) {
				if (rand.nextBoolean()) {
					e.drops.add(new ItemStack(e.block.getItemDropped(1, rand, 0), 3, e.world.getBlockMetadata(e.x, e.y, e.z)));
				}
				else {
					e.drops.clear();
				}
			}
		}
	}

	@SubscribeEvent
	public void shyreHarvest(final BlockEvent.HarvestDropsEvent e) {
		if (!e.world.isRemote && e.harvester != null && e.harvester.dimension == ConfigurationHelper.shyrelands && e.block == Blockizer.ShyreRock && nevermine.rand.nextInt(25) == 0) {
			int pick = nevermine.rand.nextInt(40);

			if (pick < 3) {
				e.harvester.setPositionAndUpdate((int)e.harvester.posX, e.harvester.worldObj.getHeightValue((int)e.harvester.posX, (int)e.harvester.posZ) + 2, (int)e.harvester.posZ);
				e.harvester.worldObj.playSoundAtEntity(e.harvester, "nevermine:ShyreMineTeleport", 3.85F, 1.0F);
				e.harvester.addChatMessage(StringUtil.getLocale("message.event.shyreMineTeleport"));
			}
			else if (pick < 13) {
				e.harvester.addPotionEffect(new PotionEffect(Potion.wither.id, 60, 1));
				e.harvester.worldObj.playSoundAtEntity(e.harvester, "nevermine:ShyreMineWither", 3.85F, 1.0F);
				e.harvester.addChatMessage(StringUtil.getLocale("message.event.shyreMineWither"));
			}
			else if (pick < 23) {
				e.harvester.addPotionEffect(new PotionEffect(Potion.blindness.id, 140, 2));
				e.harvester.worldObj.playSoundAtEntity(e.harvester, "nevermine:ShyreMineBlind", 3.85F, 1.0F);
				e.harvester.addChatMessage(StringUtil.getLocale("message.event.shyreMineBlind"));
			}
			else if (pick < 33) {
				e.harvester.addPotionEffect(new PotionEffect(Potion.digSlowdown.id, 140, 1));
				e.harvester.worldObj.playSoundAtEntity(e.harvester, "nevermine:ShyreMineBind", 3.85F, 1.0F);
				e.harvester.addChatMessage(StringUtil.getLocale("message.event.shyreMineBind"));
			}
			else {
				e.world.setBlock(e.x, e.y, e.z, Blocks.flowing_lava);
			}
		}
	}

	@SubscribeEvent
	public void onLogging(final BlockEvent.HarvestDropsEvent e) {
		if (e.world.isRemote || !(e.block instanceof BlockLog) || e.harvester == null || e.harvester instanceof FakePlayer)
			return;

		PlayerContainer cont = PlayerContainer.getProperties(e.harvester);
		int lvl = cont.getLevel(Logging);

		if (rand.nextInt(loggingHelper.getLootChance(lvl)) > 0)
			return;

		if (rand.nextBoolean()) {
			switch (loggingHelper.getLootPick(cont.getLevel(Logging))) {
				case 0:
					e.drops.add(new ItemStack(Items.stick, 16));
					cont.addExperience(15.0f, Logging);
				case 1:
					e.drops.add(new ItemStack(Items.apple, 4));
					cont.addExperience(25.0f, Logging);
					break;
				case 2:
					e.drops.add(new ItemStack(Plantizer.TeaSeeds, 1));
					cont.addExperience(30.0f, Logging);
					break;
				case 3:
					e.drops.add(new ItemStack(Items.experience_bottle, 3 + rand.nextInt(7)));
					cont.addExperience(40.0f, Logging);
					break;
				case 4:
					e.drops.add(new ItemStack(Itemizer.EssenceAncient, 16));
					cont.addExperience(120.0f, Logging);
					break;
				case 5:
					e.drops.add(new ItemStack(Itemizer.FragmentedAnimaStone, 1));
					cont.addExperience(360.0f, Logging);
					break;
				case 6:
					e.drops.add(new ItemStack(Itemizer.GemBag, 1 + rand.nextInt(3)));
					cont.addExperience(500.0f, Logging);
					break;
				case 7:
					e.drops.add(new ItemStack(Itemizer.EssenceDark, loggingHelper.isWearingLoggingArmor(e.harvester) ? 32 : 16));
					cont.addExperience(950.0f, Logging);
					break;
				case 8:
					e.drops.add(new ItemStack(Itemizer.EssenceEthereal, loggingHelper.isWearingLoggingArmor(e.harvester) ? 32 : 16));
					cont.addExperience(1650.0f, Logging);
					break;
				case 9:
					e.drops.add(new ItemStack(Itemizer.EssenceDivine, loggingHelper.isWearingLoggingArmor(e.harvester) ? 32 : 16));
					cont.addExperience(2540.0f, Logging);
					break;
				case 10:
					e.drops.add(new ItemStack(Itemizer.SkillCrystalMedium, loggingHelper.isWearingLoggingArmor(e.harvester) ? 2 : 1));
					cont.addExperience(6200.0f, Logging);
					break;
				case 11:
					e.drops.add(new ItemStack(Itemizer.IngotRosite, loggingHelper.isWearingLoggingArmor(e.harvester) ? 2 : 1));
					cont.addExperience(9500.0f, Logging);
					break;
				default:
					break;
			}

			e.harvester.worldObj.playSoundAtEntity(e.harvester, "nevermine:ForagingItem", 1.0f, 1.0f);

			if (e.harvester.dimension == 0 && e.harvester.worldObj.isDaytime())
				cont.adjustTribute(Pluton, 11 - lvl / 10);
		}
		else {
			for (ItemStack stack : e.drops) {
				if (stack.getItem() == Item.getItemFromBlock(e.block)) {
					e.drops.add(new ItemStack(stack.getItem(), cont.getLevel(Logging) < 50 ? 1 : 2, stack.getItemDamage()));
					return;
				}
			}

			if (e.harvester.dimension == 0 && e.harvester.worldObj.isDaytime())
				cont.adjustTribute(Pluton, 11 - lvl / 10);

			cont.addExperience(((float)Math.pow(cont.getLevel(Logging), 1.65d)) * 3, Logging);
		}
	}

	@SubscribeEvent
	public void onForagingBlock(final BlockEvent.HarvestDropsEvent e) {
		if (!e.world.isRemote && e.harvester != null && (e.block == Blocks.stone || e.block instanceof BlockForagingStone) && !(e.harvester instanceof FakePlayer)) {
			PlayerContainer cont = PlayerContainer.getProperties(e.harvester);
			final int lvl = cont.getLevel(Foraging);

			if (!foragingHelper.doForagingLootTest(lvl))
				return;

			final int lootPick = foragingHelper.getLootPick(lvl);

			switch (lootPick) {
				case 0:
					e.drops.add(new ItemStack(Items.coal, 3));
					cont.addExperience(20.0f, Foraging);
					break;
				case 1:
					e.drops.add(new ItemStack(Items.gold_nugget, 1));
					cont.addExperience(20.0f, Foraging);
					break;
				case 2:
					e.drops.add(new ItemStack(Itemizer.EssenceWeak, 16));
					cont.addExperience(30.0f, Foraging);
					break;
				case 3:
					e.drops.add(new ItemStack(Itemizer.EssenceMolten, 16));
					cont.addExperience(60.0f, Foraging);
					break;
				case 4:
					e.drops.add(new ItemStack(Items.ender_pearl, 1));
					cont.addExperience(100.0f, Foraging);
					break;
				case 5:
					e.drops.add(new ItemStack(Items.gunpowder, 16));
					cont.addExperience(150.0f, Foraging);
					break;
				case 6:
					e.drops.add(new ItemStack(Itemizer.EssenceCharged, 16));
					cont.addExperience(270.0f, Foraging);
					break;
				case 7:
					e.drops.add(new ItemStack(Itemizer.EssenceOminous, 16));
					cont.addExperience(400.0f, Foraging);
					break;
				case 8:
					e.drops.add(new ItemStack(Items.diamond, 2));
					cont.addExperience(650.0f, Foraging);
					break;
				case 9:
					e.drops.add(new ItemStack(Itemizer.EssenceEmpowered, 16));
					cont.addExperience(850.0f, Foraging);
					break;
				case 10:
					e.drops.add(new ItemStack(Items.experience_bottle, 4));
					cont.addExperience(1000.0f, Foraging);
					break;
				case 11:
					e.drops.add(new ItemStack(Itemizer.RealmstoneIromine, 1));
					cont.addExperience(1500.0f, Foraging);
					break;
				case 12:
					e.drops.add(new ItemStack(Itemizer.EssenceLuminate, 16));
					cont.addExperience(2250.0f, Foraging);
					break;
				case 13:
					cont.addExperience(3550.0f, Foraging);

					switch (e.block.getUnlocalizedName()) {
						case "tile.stoneIromine":
							e.drops.add(new ItemStack(Itemizer.IngotLyon, 1));
							break;
						case "tile.deeplandsRock":
							e.drops.add(new ItemStack(Blockizer.Deepcase, 1));
							break;
						case "tile.stonePrecasiaLow":
							e.drops.add(new ItemStack(Itemizer.IngotSkeletal, 1));
							break;
						case "tile.stoneGreckon":
							e.drops.add(new ItemStack(Itemizer.IngotGhastly, 2));
							break;
						case "tile.baronStone":
							e.drops.add(new ItemStack(Itemizer.IngotBaronyte, 1));
							break;
						case "tile.barathosHellstone":
							e.drops.add(new ItemStack(Itemizer.IngotBlazium, 1));
							break;
						case "tile.stoneAbyss":
							e.drops.add(new ItemStack(Itemizer.Bloodstone, 4));
							break;
						case "tile.stoneMysterium":
							e.drops.add(new ItemStack(Itemizer.IngotMystite, 2));
							break;
						case "tile.crysteviaRock":
							e.drops.add(new ItemStack(Itemizer.CrystalsBlue, 4));
							break;
						default:
							e.drops.add(new ItemStack(Itemizer.IngotJade, 2));
							break;
					}
					break;
				case 14:
					e.drops.add(new ItemStack(Itemizer.SkillCrystalSmall, 2));
					cont.addExperience(4000.0f, Foraging);
					break;
				case 15:
					e.drops.add(new ItemStack(Itemizer.EssenceAncient, 16));
					cont.addExperience(6500.0f, Foraging);
					break;
				case 16:
					cont.addExperience(8400.0f, Foraging);

					if (foragingHelper.isWearingForagingArmor(e.harvester)) {
						e.drops.add(new ItemStack(Itemizer.EssenceDark, 32));
					}
					else {
						e.drops.add(new ItemStack(Itemizer.EssenceDark, 16));
					}
					break;
				case 17:
					cont.addExperience(10100.0f, Foraging);

					if (foragingHelper.isWearingForagingArmor(e.harvester)) {
						e.drops.add(new ItemStack(Itemizer.IngotRosite, 2));
					}
					else {
						e.drops.add(new ItemStack(Itemizer.IngotRosite, 1));
					}
					break;
				case 18:
					cont.addExperience(14950.0f, Foraging);

					if (foragingHelper.isWearingForagingArmor(e.harvester)) {
						e.drops.add(new ItemStack(Itemizer.EssenceEthereal, 32));
					}
					else {
						e.drops.add(new ItemStack(Itemizer.EssenceEthereal, 16));
					}
					break;
				case 19:
					cont.addExperience(19800.0f, Foraging);

					if (foragingHelper.isWearingForagingArmor(e.harvester)) {
						e.drops.add(new ItemStack(Itemizer.GoldCoin, 2));
					}
					else {
						e.drops.add(new ItemStack(Itemizer.GoldCoin, 1));
					}
					break;
				case 20:
					cont.addExperience(24050, Foraging);

					if (foragingHelper.isWearingForagingArmor(e.harvester)) {
						e.drops.add(new ItemStack(Itemizer.EssenceDivine, 32));
					}
					else {
						e.drops.add(new ItemStack(Itemizer.EssenceDivine, 16));
					}
					break;
				default:
					break;
			}

			if (e.harvester.dimension == 0)
				cont.adjustTribute(Pluton, 11 - lvl / 10);

			e.harvester.worldObj.playSoundAtEntity(e.harvester, "nevermine:ForagingItem", 1.0f, 1.0f);
		}
	}
}
