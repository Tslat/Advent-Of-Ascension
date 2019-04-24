package net.tslat.aoa3.command;

import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.registries.GameData;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.entity.base.*;
import net.tslat.aoa3.entity.properties.BossEntity;
import net.tslat.aoa3.item.misc.RuneItem;
import net.tslat.aoa3.item.weapon.AdventWeapon;
import net.tslat.aoa3.item.weapon.archergun.BaseArchergun;
import net.tslat.aoa3.item.weapon.blaster.BaseBlaster;
import net.tslat.aoa3.item.weapon.bow.BaseBow;
import net.tslat.aoa3.item.weapon.cannon.BaseCannon;
import net.tslat.aoa3.item.weapon.greatblade.BaseGreatblade;
import net.tslat.aoa3.item.weapon.gun.BaseGun;
import net.tslat.aoa3.item.weapon.maul.BaseMaul;
import net.tslat.aoa3.item.weapon.shotgun.BaseShotgun;
import net.tslat.aoa3.item.weapon.sniper.BaseSniper;
import net.tslat.aoa3.item.weapon.staff.BaseStaff;
import net.tslat.aoa3.item.weapon.sword.BaseSword;
import net.tslat.aoa3.item.weapon.thrown.BaseThrownWeapon;
import net.tslat.aoa3.item.weapon.vulcane.BaseVulcane;
import net.tslat.aoa3.utils.ItemUtil;

import java.lang.reflect.Field;
import java.util.*;

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
		if (sender instanceof EntityPlayer && sender.getName().equals("Tslat")) {
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

										System.out.print("        addBlock(world, basePos, " + (x - structureMinPos.getX()) + ", " + (y - structureMinPos.getY()) + ", " + (z - structureMinPos.getZ()) + ", " + block.getUnlocalizedName().replace("tile.", "") + "); " + comment + "\n");
									}
                                }
                            }
                        }

                        System.out.print("    }\n");
                        System.out.println();

                        for (IBlockState capturedBlock : capturedBlocks) {
                            String blockName = capturedBlock.getBlock().getUnlocalizedName().replace("tile.","").toUpperCase();

                            System.out.print("    private static final IBlockState " + blockName + " = " + (capturedBlock.getBlock().getRegistryName().getResourceDomain().equals("minecraft") ? "Blocks" : "BlockRegister") + "." + blockName + ".getDefaultState();\n");
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

                    sender.sendMessage(new TextComponentString("Total blocks registered: " + ForgeRegistries.BLOCKS.getValuesCollection().size() + "/" + maxBlocks));
                    sender.sendMessage(new TextComponentString("Total items registered: " + ForgeRegistries.ITEMS.getValuesCollection().size() + "/" + maxItems));
                    sender.sendMessage(new TextComponentString("Total potions registered: " + ForgeRegistries.POTIONS.getValuesCollection().size() + "/" + maxPotions));
                    sender.sendMessage(new TextComponentString("Total biomes registered: " + ForgeRegistries.BIOMES.getValuesCollection().size() + "/" + maxBiomes));
                    sender.sendMessage(new TextComponentString("Total entities registered: " + ForgeRegistries.ENTITIES.getValuesCollection().size() + "/" + maxEntities));
                    sender.sendMessage(new TextComponentString("Total enchantments registered: " + ForgeRegistries.ENTITIES.getValuesCollection().size() + "/" + maxEnchantments));
                    break;
				case "heal":
					if (!((EntityPlayer)sender).world.isRemote) {
						((EntityPlayer)sender).heal(((EntityPlayer)sender).getMaxHealth());
						((EntityPlayer)sender).getFoodStats().addStats(999, 1f);
					}
					break;
				case "weaponsprintout":
					sender.sendMessage(new TextComponentString("Printing out all AoA weapon details to the launcher log..."));
					System.out.print("AoA v" + AdventOfAscension.version + " weapons data printout below: \n");
					System.out.print("\n");
					System.out.print("---~~~---~~~---~~~\n");

					HashSet<BaseSword> swords = new HashSet<BaseSword>();
					HashSet<BaseGreatblade> greatblades = new HashSet<BaseGreatblade>();
					HashSet<BaseMaul> mauls = new HashSet<BaseMaul>();
					HashSet<BaseShotgun> shotguns = new HashSet<BaseShotgun>();
					HashSet<BaseSniper> snipers = new HashSet<BaseSniper>();
					HashSet<BaseCannon> cannons = new HashSet<BaseCannon>();
					HashSet<BaseBlaster> blasters = new HashSet<BaseBlaster>();
					HashSet<BaseArchergun> archerguns = new HashSet<BaseArchergun>();
					HashSet<BaseThrownWeapon> thrownWeapons = new HashSet<BaseThrownWeapon>();
					HashSet<BaseGun> guns = new HashSet<BaseGun>();
					HashSet<BaseVulcane> vulcanes = new HashSet<BaseVulcane>();
					HashSet<BaseBow> bows = new HashSet<BaseBow>();
					HashSet<BaseStaff> staves = new HashSet<BaseStaff>();

					for (Item item : ForgeRegistries.ITEMS) {
						if (item instanceof AdventWeapon) {
							if (item instanceof BaseSword) {
								swords.add((BaseSword)item);
							}
							else if (item instanceof BaseGreatblade) {
								greatblades.add((BaseGreatblade)item);
							}
							else if (item instanceof BaseMaul) {
								mauls.add((BaseMaul)item);
							}
							else if (item instanceof BaseShotgun) {
								shotguns.add((BaseShotgun)item);
							}
							else if (item instanceof BaseSniper) {
								snipers.add((BaseSniper)item);
							}
							else if (item instanceof BaseCannon) {
								cannons.add((BaseCannon)item);
							}
							else if (item instanceof BaseBlaster) {
								blasters.add((BaseBlaster)item);
							}
							else if (item instanceof BaseArchergun) {
								archerguns.add((BaseArchergun)item);
							}
							else if (item instanceof BaseThrownWeapon) {
								thrownWeapons.add((BaseThrownWeapon)item);
							}
							else if (item instanceof BaseGun) {
								guns.add((BaseGun)item);
							}
							else if (item instanceof BaseVulcane) {
								vulcanes.add((BaseVulcane)item);
							}
							else if (item instanceof BaseBow) {
								bows.add((BaseBow)item);
							}
							else if (item instanceof BaseStaff) {
								staves.add((BaseStaff)item);
							}
						}
					}

					System.out.print("Swords: ---~~~---~~~---~~~\n");

					for (BaseSword sword : swords) {
						System.out.print(new ItemStack(sword).getDisplayName() + "\n");
						System.out.print("ID: " + sword.getRegistryName().toString() + "\n");
						System.out.print("Stats: \n");
						System.out.print("    Damage: " + sword.getAttackDamage() + "\n");
						System.out.print("    Durability: " + sword.getMaxDamage() + "\n");
						System.out.print("    Attack Speed: " + ItemUtil.getStackAttributeSpeedValue(new ItemStack(sword), SharedMonsterAttributes.ATTACK_SPEED, (EntityPlayer)sender, EntityEquipmentSlot.MAINHAND, UUID.fromString("FA233E1C-4180-4865-B01B-BCCE9785ACA3")) + "\n");
						System.out.print("---~~~---~~~---~~~\n");
					}

					System.out.print("Greatblades: ---~~~---~~~---~~~\n");

					for (BaseGreatblade greatblade : greatblades) {
						System.out.print(new ItemStack(greatblade).getDisplayName() + "\n");
						System.out.print("ID: " + greatblade.getRegistryName().toString() + "\n");
						System.out.print("Stats: \n");
						System.out.print("    Damage: " + greatblade.getDamage() + "\n");
						System.out.print("    Durability: " + greatblade.getMaxDamage() + "\n");
						System.out.print("    Attack Speed: " + ItemUtil.getStackAttributeSpeedValue(new ItemStack(greatblade), SharedMonsterAttributes.ATTACK_SPEED, (EntityPlayer)sender, EntityEquipmentSlot.MAINHAND, UUID.fromString("FA233E1C-4180-4865-B01B-BCCE9785ACA3")) + "\n");
						System.out.print("    Reach: " + ((int)greatblade.getReach()) + " blocks\n");
						System.out.print("---~~~---~~~---~~~\n");
					}

					System.out.print("Mauls: ---~~~---~~~---~~~\n");

					for (BaseMaul maul : mauls) {
						System.out.print(new ItemStack(maul).getDisplayName() + "\n");
						System.out.print("ID: " + maul.getRegistryName().toString() + "\n");
						System.out.print("Stats: \n");
						System.out.print("    Damage: " + maul.getDamage() + "\n");
						System.out.print("    Durability: " + maul.getMaxDamage() + "\n");
						System.out.print("    Attack Speed: " + ItemUtil.getStackAttributeSpeedValue(new ItemStack(maul), SharedMonsterAttributes.ATTACK_SPEED, (EntityPlayer)sender, EntityEquipmentSlot.MAINHAND, UUID.fromString("FA233E1C-4180-4865-B01B-BCCE9785ACA3")) + "\n");
						System.out.print("    Knockback: " + (maul.getBaseKnockback()) + "\n");
						System.out.print("---~~~---~~~---~~~\n");
					}

					System.out.print("Shotguns: ---~~~---~~~---~~~\n");

					for (BaseShotgun shotgun : shotguns) {
						System.out.print(new ItemStack(shotgun).getDisplayName() + "\n");
						System.out.print("ID: " + shotgun.getRegistryName().toString() + "\n");
						System.out.print("Stats: \n");
						System.out.print("    Damage per pellet: " + shotgun.getDamage() + "\n");
						System.out.print("    Pellets: " + shotgun.getPelletCount() + "\n");
						System.out.print("    Durability: " + shotgun.getMaxDamage() + "\n");
						System.out.print("    Firing Speed: " + (2000 / shotgun.getFiringDelay()) / (double)100 + "/sec\n");
						System.out.print("    Recoil: " + shotgun.getRecoil() + "\n");
						System.out.print("    Unholster Time: " + shotgun.getDrawTime() + "\n");
						System.out.print("---~~~---~~~---~~~\n");
					}

					System.out.print("Snipers: ---~~~---~~~---~~~\n");

					for (BaseSniper sniper : snipers) {
						System.out.print(new ItemStack(sniper).getDisplayName() + "\n");
						System.out.print("ID: " + sniper.getRegistryName().toString() + "\n");
						System.out.print("Stats: \n");
						System.out.print("    Damage per shot: " + sniper.getDamage() + "\n");
						System.out.print("    Durability: " + sniper.getMaxDamage() + "\n");
						System.out.print("    Firing Speed: " + (2000 / sniper.getFiringDelay()) / (double)100 + "/sec\n");
						System.out.print("    Recoil: " + sniper.getRecoil() + "\n");
						System.out.print("    Unholster Time: " + sniper.getDrawTime() + "\n");
						System.out.print("---~~~---~~~---~~~\n");
					}

					System.out.print("Cannons: ---~~~---~~~---~~~\n");

					for (BaseCannon cannon : cannons) {
						System.out.print(new ItemStack(cannon).getDisplayName() + "\n");
						System.out.print("ID: " + cannon.getRegistryName().toString() + "\n");
						System.out.print("Stats: \n");
						System.out.print("    Damage per shot: " + cannon.getDamage() + "\n");
						System.out.print("    Durability: " + cannon.getMaxDamage() + "\n");
						System.out.print("    Firing Speed: " + (2000 / cannon.getFiringDelay()) / (double)100 + "/sec\n");
						System.out.print("    Recoil: " + cannon.getRecoil() + "\n");
						System.out.print("    Unholster Time: " + cannon.getDrawTime() + "\n");
						System.out.print("---~~~---~~~---~~~\n");
					}

					System.out.print("Blasters: ---~~~---~~~---~~~\n");

					for (BaseBlaster blaster : blasters) {
						System.out.print(new ItemStack(blaster).getDisplayName() + "\n");
						System.out.print("ID: " + blaster.getRegistryName().toString() + "\n");
						System.out.print("Stats: \n");
						System.out.print("    Damage: " + blaster.getDamage() + "\n");
						System.out.print("    Durability: " + blaster.getMaxDamage() + "\n");
						System.out.print("    Firing Speed: " + (2000 / blaster.getFiringDelay()) / (double)100 + "/sec\n");
						System.out.print("    Energy Cost: " + blaster.getEnergyCost() + "\n");
						System.out.print("---~~~---~~~---~~~\n");
					}

					System.out.print("Archerguns: ---~~~---~~~---~~~\n");

					for (BaseArchergun archergun : archerguns) {
						System.out.print(new ItemStack(archergun).getDisplayName() + "\n");
						System.out.print("ID: " + archergun.getRegistryName().toString() + "\n");
						System.out.print("Stats: \n");
						System.out.print("    Damage per shot: " + archergun.getDamage() + "\n");
						System.out.print("    Durability: " + archergun.getMaxDamage() + "\n");
						System.out.print("    Firing Speed: " + (2000 / archergun.getFiringDelay()) / (double)100 + "/sec\n");
						System.out.print("    Recoil: " + archergun.getRecoil() + "\n");
						System.out.print("    Unholster Time: " + archergun.getDrawTime() + "\n");
						System.out.print("---~~~---~~~---~~~\n");
					}

					System.out.print("Bows: ---~~~---~~~---~~~\n");

					for (BaseBow bow : bows) {
						System.out.print(new ItemStack(bow).getDisplayName() + "\n");
						System.out.print("ID: " + bow.getRegistryName().toString() + "\n");
						System.out.print("Stats: \n");
						System.out.print("    Damage per arrow: " + bow.getDamage() + "\n");
						System.out.print("    Durability: " + bow.getMaxDamage() + "\n");
						System.out.print("    Draw Time: " + (((int)(72000 / bow.getDrawSpeedMultiplier()) / 720) / (double)100) + "s\n");
						System.out.print("---~~~---~~~---~~~\n");
					}

					System.out.print("Thrown Weapons: ---~~~---~~~---~~~\n");

					for (BaseThrownWeapon throwable : thrownWeapons) {
						System.out.print(new ItemStack(throwable).getDisplayName() + "\n");
						System.out.print("ID: " + throwable.getRegistryName().toString() + "\n");
						System.out.print("Stats: \n");
						System.out.print("    Damage: " + throwable.getDamage() + "\n");
						System.out.print("    Throw Speed: " + (2000 / throwable.getFiringDelay()) / (double)100 + "/sec\n");
						System.out.print("---~~~---~~~---~~~\n");
					}

					System.out.print("Vulcanes: ---~~~---~~~---~~~\n");

					for (BaseVulcane vulcane : vulcanes) {
						System.out.print(new ItemStack(vulcane).getDisplayName() + "\n");
						System.out.print("ID: " + vulcane.getRegistryName().toString() + "\n");
						System.out.print("Stats: \n");
						System.out.print("    Damage: " + vulcane.getDamage() + "\n");
						System.out.print("    Durability: " + vulcane.getMaxDamage() + "\n");
						System.out.print("---~~~---~~~---~~~\n");
					}

					System.out.print("Staves: ---~~~---~~~---~~~\n");

					for (BaseStaff staff : staves) {
						System.out.print(new ItemStack(staff).getDisplayName() + "\n");
						System.out.print("ID: " + staff.getRegistryName().toString() + "\n");
						System.out.print("Stats: \n");
						System.out.print("    Durability: " + staff.getMaxDamage() + "\n");
						System.out.print("    Runes: " + "\n");

						for (Map.Entry<RuneItem, Integer> runeEntry : staff.getRunes().entrySet()) {
							System.out.print("        " + runeEntry.getValue() + "x " + new ItemStack(runeEntry.getKey()).getDisplayName() + "\n");
						}

						System.out.print("---~~~---~~~---~~~\n");
					}


					System.out.print("Weapons printout complete, stats: \n");
					System.out.print("    Swords: " + swords.size() + "\n");
					System.out.print("    Greatblades: " + greatblades.size() + "\n");
					System.out.print("    Mauls: " + mauls.size() + "\n");
					System.out.print("    Shotguns: " + shotguns.size() + "\n");
					System.out.print("    Snipers: " + snipers.size() + "\n");
					System.out.print("    Cannons: " + cannons.size() + "\n");
					System.out.print("    Blasters: " + blasters.size() + "\n");
					System.out.print("    Archerguns: " + archerguns.size() + "\n");
					System.out.print("    Thrown Weapons: " + thrownWeapons.size() + "\n");
					System.out.print("    Guns: " + guns.size() + "\n");
					System.out.print("    Vulcanes: " + vulcanes.size() + "\n");
					System.out.print("    Bows: " + bows.size() + "\n");
					System.out.print("    Staves: " + staves.size() + "\n");
					System.out.print("\n");
					System.out.print("Total: " + (swords.size() + greatblades.size() + mauls.size() + shotguns.size() + snipers.size() + cannons.size() + blasters.size() + archerguns.size() + thrownWeapons.size() + guns.size() + vulcanes.size() + bows.size() + staves.size()) + "\n");
					System.out.print("---~~~---~~~---~~~\n");
					break;
				case "mobsprintout":
					sender.sendMessage(new TextComponentString("Printing out all AoA entity details to the launcher log..."));
					System.out.print("AoA v" + AdventOfAscension.version + " entity data printout below: \n");
					System.out.print("\n");
					System.out.print("---~~~---~~~---~~~\n");
					HashMap<String, Integer> mobCounts = new HashMap<String, Integer>();

					for (EntityEntry entry : ForgeRegistries.ENTITIES) {
						if (!entry.getName().contains("aoa3"))
							continue;

						Entity entity = entry.newInstance(((EntityPlayer)sender).world);

						System.out.print("Entity: " + entity.getName() + "\n");
						System.out.print("Id: " + entry.getName().replace("aoa3.", "aoa3:") + "\n");

						String type = "Other";

						if (entity instanceof BossEntity) {
							type = "Boss";
							mobCounts.merge("Boss", 1, Integer::sum);
						}
						else if (entity instanceof AoAMeleeMob) {
							if (entity instanceof AoARangedAttacker) {
								type = "Hybrid Melee/Ranged Mob";
								mobCounts.merge("Hybrid Melee/Ranged Mob", 1, Integer::sum);
							}
							else {
								type = "Melee Mob";
								mobCounts.merge("Melee Mob", 1, Integer::sum);
							}
						}
						else if (entity instanceof AoARangedMob) {
							type = "Ranged Mob";
							mobCounts.merge("Ranged Mob", 1, Integer::sum);
						}
						else if (entity instanceof AoAFlyingMeleeMob) {
							type = "Flying Melee Mob";
							mobCounts.merge("Flying Melee Mob", 1, Integer::sum);
						}
						else if (entity instanceof AoAFlyingRangedMob) {
							type = "Flying Ranged Mob";
							mobCounts.merge("Flying Ranged Mob", 1, Integer::sum);
						}
						else if (entity instanceof EntityAnimal) {
							type = "Animal";
							mobCounts.merge("Animal", 1, Integer::sum);
						}
						else if (entity instanceof AoAAmbientNPC) {
							type = "NPC";
							mobCounts.merge("NPC", 1, Integer::sum);
						}
						else if (entity instanceof AoATrader) {
							type = "Trader";
							mobCounts.merge("Trader", 1, Integer::sum);
						}
						else if (entity instanceof IProjectile) {
							type = "Projectile";
							mobCounts.merge("Projectile", 1, Integer::sum);
						}
						else {
							mobCounts.merge("Other", 1, Integer::sum);
						}

						System.out.print("Type: " + type + "\n");

						if (entity instanceof EntityLivingBase) {
							EntityLivingBase livingEntity = (EntityLivingBase)entity;

							String creatureType = "None";

							switch (((EntityLivingBase)entity).getCreatureAttribute()) {
								case ARTHROPOD:
									creatureType = "Arthropod";
									break;
								case ILLAGER:
									creatureType = "Illager";
									break;
								case UNDEAD:
									creatureType = "Undead";
									break;
								default:
									break;
							}

							System.out.print("Creature Type: " + creatureType + (entity instanceof BossEntity ? " (Boss)\n" : "\n"));
							System.out.print("Size: " + entity.width + "W x " + entity.height + "H\n");
							System.out.print("Stats:\n");
							System.out.print("    Health: " + livingEntity.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).getBaseValue() + "\n");
							System.out.print("    Armour: " + livingEntity.getEntityAttribute(SharedMonsterAttributes.ARMOR).getBaseValue() + "\n");
							System.out.print("    Knockback Resistance: " + livingEntity.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).getBaseValue() + "\n");
							System.out.print("    Movement Speed: " + livingEntity.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getBaseValue() + "\n");

							if (livingEntity.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE) != null)
								System.out.print("    Strength: " + livingEntity.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getBaseValue() + "\n");
						}
						else {
							System.out.print("Size: " + entity.width + "W x " + entity.height + "H\n");
						}

						System.out.print("---~~~---~~~---~~~\n");
					}

					System.out.print("Entity printout complete, stats: \n");

					int total = 0;

					for (Map.Entry<String, Integer> entry : mobCounts.entrySet()) {
						System.out.print("    " + entry.getKey() + ": " + entry.getValue() + "\n");
						total += entry.getValue();
					}

					System.out.print("    Total: " + total + "\n");
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
		return sender instanceof EntityPlayer && ((EntityPlayer)sender).getGameProfile().getId().equals(UUID.fromString("2459b511-ca45-43d8-808d-f0eb30a63be4"));
	}
}
