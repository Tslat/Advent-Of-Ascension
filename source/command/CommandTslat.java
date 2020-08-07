package net.tslat.aoa3.command;

import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.potion.Potion;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.GameRules;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.storage.WorldInfo;
import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.registries.GameData;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.StringUtil;
import net.tslat.aoa3.utils.WorldUtil;
import net.tslat.aoa3.utils.player.PlayerDataManager;
import net.tslat.aoa3.utils.player.PlayerUtil;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class CommandTslat extends CommandBase {
    private BlockPos structureMinPos = null;
    private BlockPos structureMaxPos = null;

    @Override
    public String getName() {
        return "tslat";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "Only Tslat can use this command. It's just for debugging purposes, don't worry";
    }

    @Override
    public List<String> getAliases() {
        return Collections.EMPTY_LIST;
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		if (sender instanceof EntityPlayer && (sender.getName().equals("Tslat") || sender.getName().equals("Ursun_"))) {
			EntityPlayer player;

            if (args.length == 0) {
                sender.sendMessage(new TextComponentString("Welcome back."));
                return;
            }

            switch (args[0]) {
                case "pos1":
                    structureMinPos = sender.getPosition().down();
                    sender.sendMessage(new TextComponentString("Set lower structure coordinate bounds to: " + structureMinPos.getX() + ", " + structureMinPos.getY() + ", " + structureMinPos.getZ()));
                    break;
                case "pos2":
                    structureMaxPos = sender.getPosition().down();

                    sender.sendMessage(new TextComponentString("Set upper structure coordinate bounds to: " + structureMaxPos.getX() + ", " + structureMaxPos.getY() + ", " + structureMaxPos.getZ()));
                    break;
                case "capture":
                    if (structureMinPos == null) {
                        sender.sendMessage(new TextComponentString("Missing lower structure coordinate bounds"));
                    }
                    else if (structureMaxPos == null) {
                        sender.sendMessage(new TextComponentString("Missing upper structure coordinate bounds"));
                    }
                    else {
						int minX = Math.min(structureMinPos.getX(), structureMaxPos.getX());
						int minY = Math.min(structureMinPos.getY(), structureMaxPos.getY());
						int minZ = Math.min(structureMinPos.getZ(), structureMaxPos.getZ());
						int maxX = Math.max(structureMinPos.getX(), structureMaxPos.getX());
						int maxY = Math.max(structureMinPos.getY(), structureMaxPos.getY());
						int maxZ = Math.max(structureMinPos.getZ(), structureMaxPos.getZ());
                    	structureMinPos = new BlockPos(minX, minY, minZ);
                    	structureMaxPos = new BlockPos(maxX, maxY, maxZ);
                        EntityPlayer pl = (EntityPlayer)sender;
                        Block fillBlock = null;

                        if (pl.getHeldItem(EnumHand.MAIN_HAND).getItem() instanceof ItemBlock)
                            fillBlock = Block.getBlockFromItem(pl.getHeldItem(EnumHand.MAIN_HAND).getItem());

                        System.out.print("    @Override\n");
                        System.out.print("    protected void build(World world, Random rand, BlockPos basePos) {\n");

                        int blockCount = 0;
                        int fillBlockCount = 0;
                        int height = 1 + structureMaxPos.getY() - structureMinPos.getY();
                        int width = 1 + structureMaxPos.getX() - structureMinPos.getX();
                        int depth = 1 + structureMaxPos.getZ() - structureMinPos.getZ();
                        HashSet<IBlockState> capturedBlocks = new HashSet<IBlockState>();

						for (int y = structureMinPos.getY(); y <= structureMaxPos.getY(); y++) {
                        	for (int x = structureMinPos.getX(); x <= structureMaxPos.getX(); x++) {
								for (int z = structureMinPos.getZ(); z <= structureMaxPos.getZ(); z++) {
                                    IBlockState blockState = pl.world.getBlockState(new BlockPos(x, y, z));
                                    Block block = blockState.getBlock();

                                    if (block != Blocks.AIR && (block != Blocks.WATER || block.getMetaFromState(blockState) == 0) && (block != Blocks.LAVA || block.getMetaFromState(blockState) == 0)) {
										if (fillBlock != null && block == fillBlock) {
											block = Blocks.AIR;
											fillBlockCount++;
											capturedBlocks.add(Blocks.AIR.getDefaultState());
										}
										else {
											capturedBlocks.add(blockState);
										}

										blockCount++;

										StringBuilder builder = new StringBuilder(" // ");
										if (!blockState.getProperties().isEmpty() && blockState != block.getDefaultState()) {
											for (Map.Entry<IProperty<?>, Comparable<?>> stateMap : blockState.getProperties().entrySet()) {
												builder.append(stateMap.getKey().getValueClass().toString()).append(" ## ");
												builder.append(stateMap.getKey().getName()).append(" :: ");
												builder.append(stateMap.getValue().toString()).append("; ");
											}
										}

										if (block.getMetaFromState(blockState) != 0)
											block.getMetaFromState(blockState);

										String comment = builder.length() > 4 ? builder.toString() : "";

										System.out.print("        addBlock(world, basePos, " + (x - structureMinPos.getX()) + ", " + (y - structureMinPos.getY()) + ", " + (z - structureMinPos.getZ()) + ", " + block.getTranslationKey().replace("tile.", "") + "); " + comment + "\n");
									}
                                }
                            }
                        }

                        System.out.print("    }\n");
                        System.out.print("\n");

                        for (IBlockState capturedBlock : capturedBlocks) {
                            String blockName = capturedBlock.getBlock().getTranslationKey().replace("tile.","").toUpperCase();

                            System.out.print("    private static final IBlockState " + blockName + " = " + (capturedBlock.getBlock().getRegistryName().getNamespace().equals("minecraft") ? "Blocks" : "BlockRegister") + "." + blockName + ".getDefaultState();\n");
                        }

                        sender.sendMessage(new TextComponentString("Finished capturing structure. Stats:"));
						sender.sendMessage(new TextComponentString(fillBlock == null ? "No fill block found, skipping air gaps." : "Found " + fillBlock.getLocalizedName() + " air gap marker."));
                        sender.sendMessage(new TextComponentString("Size (X,Y,Z): " + width + "x" + height + "x" + depth));
                        sender.sendMessage(new TextComponentString("Captured " + blockCount + " blocks." + (fillBlockCount > 0 ? " Includes " + fillBlockCount + " air gap blocks" : "")));
                        sender.sendMessage(new TextComponentString("Contains " + capturedBlocks.size() + " unique blocks"));
                    }
                    break;
                case "checkversions":
                    sender.sendMessage(new TextComponentString("Forge Version: " + ForgeVersion.getVersion()));
                    sender.sendMessage(new TextComponentString("Minecraft Version: " + ForgeVersion.mcVersion));
                    sender.sendMessage(new TextComponentString("Mod Version: " + AdventOfAscension.version));
                    break;
                case "idregistercheck":
                    int maxBlocks = 0;
                    int maxItems = 0;
                    int maxPotions = 0;
                    int maxBiomes = 0;
                    int maxEntities = 0;
                    int maxEnchantments = 0;

                    int aoaBlocks = 0;
                    int aoaItems = 0;
                    int aoaPotions = 0;
                    int aoaBiomes = 0;
                    int aoaEntities = 0;
                    int aoaEnchantments = 0;

                    try {
                        GameData dataClass = new GameData();
                        Field maxBlocksField = GameData.class.getDeclaredField("MAX_BLOCK_ID");
                        Field maxItemsField = GameData.class.getDeclaredField("MAX_ITEM_ID");
                        Field maxPotionsField = GameData.class.getDeclaredField("MAX_POTION_ID");
                        Field maxBiomesField = GameData.class.getDeclaredField("MAX_BIOME_ID");
                        Field maxEntitiesField = GameData.class.getDeclaredField("MAX_ENTITY_ID");
                        Field maxEnchantmentsField = GameData.class.getDeclaredField("MAX_ENCHANTMENT_ID");

                        if (maxBlocksField != null) {
                            maxBlocksField.setAccessible(true);
                            maxBlocks = maxBlocksField.getInt(dataClass);
                        }

                        if (maxItemsField != null) {
                            maxItemsField.setAccessible(true);
                            maxItems = maxItemsField.getInt(dataClass);
                        }

						if (maxPotionsField != null) {
							maxPotionsField.setAccessible(true);
							maxPotions = maxPotionsField.getInt(dataClass);
						}

						if (maxBiomesField != null) {
							maxBiomesField.setAccessible(true);
							maxBiomes = maxBiomesField.getInt(dataClass);
						}

						if (maxEntitiesField != null) {
							maxEntitiesField.setAccessible(true);
							maxEntities = maxEntitiesField.getInt(dataClass);
						}

						if (maxEnchantmentsField != null) {
							maxEnchantmentsField.setAccessible(true);
							maxEnchantments = maxEnchantmentsField.getInt(dataClass);
						}
                    }
                    catch (Exception e) {}

                    for (Block bl : ForgeRegistries.BLOCKS) {
                        if (bl.getRegistryName() != null && bl.getRegistryName().getNamespace().equals("aoa3"))
                            aoaBlocks++;
                    }

                    for (Item item : ForgeRegistries.ITEMS) {
                        if (item.getRegistryName() != null && item.getRegistryName().getNamespace().equals("aoa3"))
                            aoaItems++;
                    }

                    for (Potion potion : ForgeRegistries.POTIONS) {
                        if (potion.getRegistryName() != null && potion.getRegistryName().getNamespace().equals("aoa3"))
                            aoaPotions++;
                    }

                    for (Biome biome : ForgeRegistries.BIOMES) {
                        if (biome.getRegistryName() != null && biome.getRegistryName().getNamespace().equals("aoa3"))
                            aoaBiomes++;
                    }

                    for (EntityEntry entity : ForgeRegistries.ENTITIES) {
                        if (entity.getRegistryName() != null && entity.getRegistryName().getNamespace().equals("aoa3"))
                            aoaEntities++;
                    }

                    for (Enchantment enchantment : ForgeRegistries.ENCHANTMENTS) {
                        if (enchantment.getRegistryName() != null && enchantment.getRegistryName().getNamespace().equals("aoa3"))
                            aoaEnchantments++;
                    }

                    sender.sendMessage(new TextComponentString("Total blocks registered: " + ForgeRegistries.BLOCKS.getValuesCollection().size() + "/" + maxBlocks + " (" + aoaBlocks + " from AoA)"));
                    sender.sendMessage(new TextComponentString("Total items registered: " + ForgeRegistries.ITEMS.getValuesCollection().size() + "/" + maxItems + " (" + aoaItems + " from AoA)"));
                    sender.sendMessage(new TextComponentString("Total potions registered: " + ForgeRegistries.POTIONS.getValuesCollection().size() + "/" + maxPotions + " (" + aoaPotions + " from AoA)"));
                    sender.sendMessage(new TextComponentString("Total biomes registered: " + ForgeRegistries.BIOMES.getValuesCollection().size() + "/" + maxBiomes + " (" + aoaBiomes + " from AoA)"));
                    sender.sendMessage(new TextComponentString("Total entities registered: " + ForgeRegistries.ENTITIES.getValuesCollection().size() + "/" + maxEntities + " (" + aoaEntities + " from AoA)"));
                    sender.sendMessage(new TextComponentString("Total enchantments registered: " + ForgeRegistries.ENTITIES.getValuesCollection().size() + "/" + maxEnchantments + " (" + aoaEnchantments + " from AoA)"));
                    break;
				case "heal":
					player = (EntityPlayer)sender;

					if (!player.world.isRemote) {
						player.heal(player.getMaxHealth());
						player.getFoodStats().addStats(999, 1f);
						player.extinguish();

						PlayerDataManager plData = PlayerUtil.getAdventPlayer(player);

						plData.stats().regenResource(Enums.Resources.RAGE, 200);
						plData.stats().regenResource(Enums.Resources.SOUL, 200);
						plData.stats().regenResource(Enums.Resources.ENERGY, 200);
						plData.stats().regenResource(Enums.Resources.CREATION, 200);
					}
					break;
				case "explode":
					player = (EntityPlayer)sender;

					if (!player.world.isRemote) {
						float strength = 7f;

						if (args.length > 1) {
							float altStrength = StringUtil.toFloat(args[1]);

							if (altStrength > 0)
								strength = altStrength;
						}

						WorldUtil.createExplosion(player, player.world, strength);
					}
					break;
				case "toggledebugsetup":
					player = (EntityPlayer)sender;
					GameRules gameRules = player.world.getGameRules();
					WorldInfo worldInfo = player.world.getWorldInfo();

					if (gameRules.getBoolean("doMobSpawning")) {
						gameRules.setOrCreateGameRule("doMobSpawning", "false");
						gameRules.setOrCreateGameRule("doWeatherCycle", "false");
						gameRules.setOrCreateGameRule("doDaylightCycle", "false");
						worldInfo.setCleanWeatherTime(20000000);
						worldInfo.setRainTime(0);
						worldInfo.setThunderTime(0);
						worldInfo.setRaining(false);
						worldInfo.setThundering(false);
						player.world.setWorldTime(1000);
						player.sendMessage(new TextComponentString("Enabled Debug settings"));
					}
					else {
						gameRules.setOrCreateGameRule("doMobSpawning", "true");
						gameRules.setOrCreateGameRule("doWeatherCycle", "true");
						gameRules.setOrCreateGameRule("doDaylightCycle", "true");
						worldInfo.setCleanWeatherTime(((300 + AdventOfAscension.rand.nextInt(600)) * 20));
						player.world.setWorldTime(1000);
						player.sendMessage(new TextComponentString("Disabled Debug settings"));
					}
					break;
                default:
                    break;
            }
        }
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 4;
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
		return sender instanceof EntityPlayer && (AdventOfAscension.instance().isTslat(((EntityPlayer)sender).getGameProfile().getId()) || AdventOfAscension.instance().isUrsun(((EntityPlayer)sender).getGameProfile().getId()));
	}
}
