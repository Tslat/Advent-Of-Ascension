package net.tslat.aoa3.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.FoodStats;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.server.command.EnumArgument;
import net.tslat.aoa3.util.NumberUtil;
import net.tslat.aoa3.util.StringUtil;
import net.tslat.aoa3.util.constant.Deities;
import net.tslat.aoa3.util.constant.Resources;
import net.tslat.aoa3.util.constant.Skills;
import net.tslat.aoa3.util.player.PlayerDataManager;
import net.tslat.aoa3.util.player.PlayerUtil;

public class PlayerCommand implements Command<CommandSource> {
	private static final PlayerCommand CMD = new PlayerCommand();

	public static ArgumentBuilder<CommandSource, ?> register(CommandDispatcher<CommandSource> dispatcher) {
		LiteralArgumentBuilder<CommandSource> builder = Commands.literal("player").executes(CMD);

		builder.then(Commands.argument("player", EntityArgument.player())
			.then(Commands.literal("skills")
				.then(Commands.argument("skill", EnumArgument.enumArgument(Skills.class))
					.then(Commands.literal("level")
						.then(Commands.literal("add").requires(command -> command.hasPermissionLevel(4))
							.then(Commands.argument("value", IntegerArgumentType.integer(1, 999))
								.executes((context) -> adjustSkills(context, "add"))))
						.then(Commands.literal("subtract").requires(command -> command.hasPermissionLevel(4))
							.then(Commands.argument("value", IntegerArgumentType.integer(1, 999))
								.executes((context) -> adjustSkills(context, "subtract"))))
						.then(Commands.literal("set").requires(command -> command.hasPermissionLevel(4))
							.then(Commands.argument("value", IntegerArgumentType.integer(1, 1000))
								.executes((context) -> adjustSkills(context, "set"))))
						.then(Commands.literal("check").executes(PlayerCommand::checkSkill)))
					.then(Commands.literal("xp")
						.then(Commands.literal("add").requires(command -> command.hasPermissionLevel(4))
							.then(Commands.argument("value", FloatArgumentType.floatArg(1, 544132359))
								.executes((context) -> adjustXp(context, "add"))))
						.then(Commands.literal("subtract").requires(command -> command.hasPermissionLevel(4))
							.then(Commands.argument("value", FloatArgumentType.floatArg(1, 544132359))
								.executes((context) -> adjustXp(context, "subtract"))))
						.then(Commands.literal("set").requires(command -> command.hasPermissionLevel(4))
							.then(Commands.argument("value", FloatArgumentType.floatArg(0, 544132358))
								.executes((context) -> adjustXp(context, "set"))))
						.then(Commands.literal("check").executes(PlayerCommand::checkXp)))))
			.then(Commands.literal("resources")
				.then(Commands.argument("resource", EnumArgument.enumArgument(Resources.class))
					.then(Commands.literal("add").requires(command -> command.hasPermissionLevel(4))
						.then(Commands.argument("value", FloatArgumentType.floatArg(1, 200))
							.executes((context) -> adjustResources(context, "add"))))
					.then(Commands.literal("subtract").requires(command -> command.hasPermissionLevel(4))
						.then(Commands.argument("value", FloatArgumentType.floatArg(1, 200))
							.executes((context) -> adjustResources(context, "subtract"))))
					.then(Commands.literal("set").requires(command -> command.hasPermissionLevel(4))
						.then(Commands.argument("value", FloatArgumentType.floatArg(0, 200))
							.executes((context) -> adjustResources(context, "set"))))
					.then(Commands.literal("check").executes(PlayerCommand::checkResource))))
			.then(Commands.literal("tribute")
				.then(Commands.argument("deity", EnumArgument.enumArgument(Deities.class))
					.then(Commands.literal("add").requires(command -> command.hasPermissionLevel(4))
						.then(Commands.argument("value", IntegerArgumentType.integer(1, 200))
							.executes((context) -> adjustTribute(context, "add"))))
					.then(Commands.literal("subtract").requires(command -> command.hasPermissionLevel(4))
						.then(Commands.argument("value", IntegerArgumentType.integer(1, 200))
							.executes((context) -> adjustTribute(context, "subtract"))))
					.then(Commands.literal("set").requires(command -> command.hasPermissionLevel(4))
						.then(Commands.argument("value", IntegerArgumentType.integer(0, 200))
							.executes((context) -> adjustTribute(context, "set"))))
					.then(Commands.literal("check").executes(PlayerCommand::checkTribute))))
			.then(Commands.literal("stats")
				.then(Commands.literal("experience")
					.then(Commands.literal("add").requires(command -> command.hasPermissionLevel(4))
						.then(Commands.argument("amount", IntegerArgumentType.integer(1, Integer.MAX_VALUE))
							.executes((context) -> adjustVanillaXp(context, "add"))))
					.then(Commands.literal("subtract").requires(command -> command.hasPermissionLevel(4))
						.then(Commands.argument("amount", IntegerArgumentType.integer(1, Integer.MAX_VALUE))
							.executes((context) -> adjustVanillaXp(context, "subtract"))))
					.then(Commands.literal("check").executes(PlayerCommand::checkVanillaXp)))
				.then(Commands.literal("food")
					.then(Commands.literal("hunger")
						.then(Commands.literal("add").requires(command -> command.hasPermissionLevel(4))
							.then(Commands.argument("amount", IntegerArgumentType.integer(1, 20))
								.executes((context) -> adjustFoodStats(context, "hunger", "add"))))
						.then(Commands.literal("subtract").requires(command -> command.hasPermissionLevel(4))
							.then(Commands.argument("amount", IntegerArgumentType.integer(1, 20))
								.executes((context) -> adjustFoodStats(context, "hunger", "subtract"))))
						.then(Commands.literal("set").requires(command -> command.hasPermissionLevel(4))
							.then(Commands.argument("amount", IntegerArgumentType.integer(0, 20))
								.executes((context) -> adjustFoodStats(context, "hunger", "set"))))
						.then(Commands.literal("check").executes((context) -> checkFoodStats(context, "hunger"))))
					.then(Commands.literal("saturation")
						.then(Commands.literal("add").requires(command -> command.hasPermissionLevel(4))
							.then(Commands.argument("amount", IntegerArgumentType.integer(1, 20))
								.executes((context) -> adjustFoodStats(context, "saturation", "add"))))
						.then(Commands.literal("subtract").requires(command -> command.hasPermissionLevel(4))
							.then(Commands.argument("amount", IntegerArgumentType.integer(1, 20))
								.executes((context) -> adjustFoodStats(context, "saturation", "subtract"))))
						.then(Commands.literal("set").requires(command -> command.hasPermissionLevel(4))
							.then(Commands.argument("amount", IntegerArgumentType.integer(1, 20))
								.executes((context) -> adjustFoodStats(context, "saturation", "set"))))
						.then(Commands.literal("check").executes((context) -> checkFoodStats(context, "saturation")))))
				.then(Commands.literal("health")
					.then(Commands.literal("add").requires(command -> command.hasPermissionLevel(4))
						.then(Commands.argument("amount", FloatArgumentType.floatArg(0, Integer.MAX_VALUE))
							.executes((context) -> adjustHealth(context, "add"))))
					.then(Commands.literal("subtract").requires(command -> command.hasPermissionLevel(4))
						.then(Commands.argument("amount", FloatArgumentType.floatArg(0, Integer.MAX_VALUE))
							.executes((context) -> adjustHealth(context, "subtract"))))
					.then(Commands.literal("set").requires(command -> command.hasPermissionLevel(4))
						.then(Commands.argument("amount", FloatArgumentType.floatArg(0, Integer.MAX_VALUE))
							.executes((context) -> adjustHealth(context, "set"))))
					.then(Commands.literal("check").executes(PlayerCommand::checkHealth)))));

		return builder;
	}

	private static int checkFoodStats(CommandContext<CommandSource> context, String subsection) throws CommandSyntaxException {
		ServerPlayerEntity pl = EntityArgument.getPlayer(context, "player");

		if (subsection.equals("hunger")) {
			if (pl != context.getSource().assertIsEntity()) {
				if (!context.getSource().hasPermissionLevel(2))
					throw AoACommand.NO_PERMISSION_EXCEPTION.create();

				AoACommand.feedback(context.getSource(), "Player", "--", AoACommand.CommandFeedbackType.INFO);
				AoACommand.feedback(context.getSource(), "Player", "command.aoa.player.food.hunger.check.other", AoACommand.CommandFeedbackType.SUCCESS, pl.getDisplayName().getFormattedText(), String.valueOf(pl.getFoodStats().getFoodLevel()));
			}
			else {
				AoACommand.feedback(context.getSource(), "Player", "--", AoACommand.CommandFeedbackType.INFO);
				AoACommand.feedback(context.getSource(), "Player", "command.aoa.player.food.hunger.check.self", AoACommand.CommandFeedbackType.SUCCESS, String.valueOf(pl.getFoodStats().getFoodLevel()));
			}
		}
		else {
			if (pl != context.getSource().assertIsEntity()) {
				if (!context.getSource().hasPermissionLevel(2))
					throw AoACommand.NO_PERMISSION_EXCEPTION.create();

				AoACommand.feedback(context.getSource(), "Player", "--", AoACommand.CommandFeedbackType.INFO);
				AoACommand.feedback(context.getSource(), "Player", "command.aoa.player.food.saturation.check.other", AoACommand.CommandFeedbackType.SUCCESS, pl.getDisplayName().getFormattedText(), NumberUtil.roundToNthDecimalPlace(pl.getFoodStats().getSaturationLevel(), 2));
			}
			else {
				AoACommand.feedback(context.getSource(), "Player", "--", AoACommand.CommandFeedbackType.INFO);
				AoACommand.feedback(context.getSource(), "Player", "command.aoa.player.food.saturation.check.self", AoACommand.CommandFeedbackType.SUCCESS, NumberUtil.roundToNthDecimalPlace(pl.getFoodStats().getSaturationLevel(), 2));
			}
		}

		return 1;
	}

	private static int checkVanillaXp(CommandContext<CommandSource> context) throws CommandSyntaxException {
		ServerPlayerEntity pl = EntityArgument.getPlayer(context, "player");

		if (pl != context.getSource().assertIsEntity()) {
			if (!context.getSource().hasPermissionLevel(2))
				throw AoACommand.NO_PERMISSION_EXCEPTION.create();

			AoACommand.feedback(context.getSource(), "Player", "--", AoACommand.CommandFeedbackType.INFO);
			AoACommand.feedback(context.getSource(), "Player", "command.aoa.player.exp.check.other", AoACommand.CommandFeedbackType.SUCCESS, pl.getDisplayName().getFormattedText(), String.valueOf(pl.experienceTotal), String.valueOf(pl.experienceLevel));
		}
		else {
			AoACommand.feedback(context.getSource(), "Player", "--", AoACommand.CommandFeedbackType.INFO);
			AoACommand.feedback(context.getSource(), "Player", "command.aoa.player.exp.check.self", AoACommand.CommandFeedbackType.SUCCESS, String.valueOf(pl.experienceTotal), String.valueOf(pl.experienceLevel));
		}

		return 1;
	}

	private static int checkHealth(CommandContext<CommandSource> context) throws CommandSyntaxException {
		ServerPlayerEntity pl = EntityArgument.getPlayer(context, "player");

		if (pl != context.getSource().assertIsEntity()) {
			if (!context.getSource().hasPermissionLevel(2))
				throw AoACommand.NO_PERMISSION_EXCEPTION.create();

			AoACommand.feedback(context.getSource(), "Player", "--", AoACommand.CommandFeedbackType.INFO);
			AoACommand.feedback(context.getSource(), "Player", "command.aoa.player.health.check.other", AoACommand.CommandFeedbackType.SUCCESS, pl.getDisplayName().getFormattedText(), NumberUtil.roundToNthDecimalPlace(pl.getHealth(), 2), NumberUtil.roundToNthDecimalPlace(pl.getMaxHealth(), 2));
		}
		else {
			AoACommand.feedback(context.getSource(), "Player", "--", AoACommand.CommandFeedbackType.INFO);
			AoACommand.feedback(context.getSource(), "Player", "command.aoa.player.health.check.self", AoACommand.CommandFeedbackType.SUCCESS, NumberUtil.roundToNthDecimalPlace(pl.getHealth(), 2), NumberUtil.roundToNthDecimalPlace(pl.getMaxHealth(), 2));
		}

		return 1;
	}

	private static int checkSkill(CommandContext<CommandSource> context) throws CommandSyntaxException {
		ServerPlayerEntity pl = EntityArgument.getPlayer(context, "player");

		if (pl != context.getSource().assertIsEntity()) {
			if (!context.getSource().hasPermissionLevel(2))
				throw AoACommand.NO_PERMISSION_EXCEPTION.create();

			Skills skill = context.getArgument("skill", Skills.class);
			int level = PlayerUtil.getAdventPlayer(pl).stats().getLevel(skill);

			AoACommand.feedback(context.getSource(), "Player", "--", AoACommand.CommandFeedbackType.INFO);
			AoACommand.feedback(context.getSource(), "Player", "command.aoa.player.skill.check.other", AoACommand.CommandFeedbackType.SUCCESS, pl.getDisplayName().getFormattedText(), StringUtil.toTitleCase(skill.toString()), String.valueOf(level));
		}
		else {
			Skills skill = context.getArgument("skill", Skills.class);
			int level = PlayerUtil.getAdventPlayer(pl).stats().getLevel(skill);

			AoACommand.feedback(context.getSource(), "Player", "--", AoACommand.CommandFeedbackType.INFO);
			AoACommand.feedback(context.getSource(), "Player", "command.aoa.player.skill.check.self", AoACommand.CommandFeedbackType.SUCCESS, StringUtil.toTitleCase(skill.toString()), String.valueOf(level));
		}

		return 1;
	}

	private static int checkXp(CommandContext<CommandSource> context) throws CommandSyntaxException {
		ServerPlayerEntity pl = EntityArgument.getPlayer(context, "player");

		if (pl != context.getSource().assertIsEntity()) {
			if (!context.getSource().hasPermissionLevel(2))
				throw AoACommand.NO_PERMISSION_EXCEPTION.create();

			Skills skill = context.getArgument("skill", Skills.class);
			float xp = PlayerUtil.getAdventPlayer(pl).stats().getExp(skill);

			AoACommand.feedback(context.getSource(), "Player", "--", AoACommand.CommandFeedbackType.INFO);
			AoACommand.feedback(context.getSource(), "Player", "command.aoa.player.xp.check.other", AoACommand.CommandFeedbackType.SUCCESS, pl.getDisplayName().getFormattedText(), StringUtil.toTitleCase(skill.toString()), String.valueOf(xp));
		}
		else {
			Skills skill = context.getArgument("skill", Skills.class);
			float xp = PlayerUtil.getAdventPlayer(pl).stats().getExp(skill);

			AoACommand.feedback(context.getSource(), "Player", "--", AoACommand.CommandFeedbackType.INFO);
			AoACommand.feedback(context.getSource(), "Player", "command.aoa.player.xp.check.self", AoACommand.CommandFeedbackType.SUCCESS, StringUtil.toTitleCase(skill.toString()), String.valueOf(xp));
		}

		return 1;
	}

	private static int checkResource(CommandContext<CommandSource> context) throws CommandSyntaxException {
		ServerPlayerEntity pl = EntityArgument.getPlayer(context, "player");

		if (pl != context.getSource().assertIsEntity()) {
			if (!context.getSource().hasPermissionLevel(2))
				throw AoACommand.NO_PERMISSION_EXCEPTION.create();

			Resources resource = context.getArgument("resource", Resources.class);
			float value = PlayerUtil.getAdventPlayer(pl).stats().getResourceValue(resource);

			AoACommand.feedback(context.getSource(), "Player", "--", AoACommand.CommandFeedbackType.INFO);
			AoACommand.feedback(context.getSource(), "Player", "command.aoa.player.resource.check.other", AoACommand.CommandFeedbackType.SUCCESS, pl.getDisplayName().getFormattedText(), String.valueOf(value), StringUtil.toTitleCase(resource.toString()));
		}
		else {
			Resources resource = context.getArgument("resource", Resources.class);
			float value = PlayerUtil.getAdventPlayer(pl).stats().getResourceValue(resource);

			AoACommand.feedback(context.getSource(), "Player", "--", AoACommand.CommandFeedbackType.INFO);
			AoACommand.feedback(context.getSource(), "Player", "command.aoa.player.resource.check.self", AoACommand.CommandFeedbackType.SUCCESS, StringUtil.toTitleCase(resource.toString()), String.valueOf(value));
		}

		return 1;
	}

	private static int checkTribute(CommandContext<CommandSource> context) throws CommandSyntaxException {
		ServerPlayerEntity pl = EntityArgument.getPlayer(context, "player");

		if (pl != context.getSource().assertIsEntity()) {
			if (!context.getSource().hasPermissionLevel(2))
				throw AoACommand.NO_PERMISSION_EXCEPTION.create();

			Deities deity = context.getArgument("deity", Deities.class);
			int value = PlayerUtil.getAdventPlayer(pl).stats().getTribute(deity);

			AoACommand.feedback(context.getSource(), "Player", "--", AoACommand.CommandFeedbackType.INFO);
			AoACommand.feedback(context.getSource(), "Player", "command.aoa.player.tribute.check.other", AoACommand.CommandFeedbackType.SUCCESS, pl.getDisplayName().getFormattedText(), String.valueOf(value), StringUtil.toTitleCase(deity.toString()));
		}
		else {
			Deities deity = context.getArgument("deity", Deities.class);
			int value = PlayerUtil.getAdventPlayer(pl).stats().getTribute(deity);

			AoACommand.feedback(context.getSource(), "Player", "--", AoACommand.CommandFeedbackType.INFO);
			AoACommand.feedback(context.getSource(), "Player", "command.aoa.player.tribute.check.self", AoACommand.CommandFeedbackType.SUCCESS, StringUtil.toTitleCase(deity.toString()), String.valueOf(value));
		}

		return 1;
	}

	private static int adjustSkills(CommandContext<CommandSource> context, String operation) throws CommandSyntaxException {
		int value = IntegerArgumentType.getInteger(context, "value");
		Skills skill = context.getArgument("skill", Skills.class);
		ServerPlayerEntity pl = EntityArgument.getPlayer(context, "player");
		PlayerDataManager.PlayerStats stats = PlayerUtil.getAdventPlayer(pl).stats();

		int adjustment = 0;

		switch (operation) {
			case "add":
				adjustment = Math.min(value, 1000 - stats.getLevelForDisplay(skill));

				stats.setLevel(skill, stats.getLevelForDisplay(skill) + value);
				break;
			case "subtract":
				adjustment = -Math.min(value, stats.getLevelForDisplay(skill) - 1);

				stats.setLevel(skill, stats.getLevelForDisplay(skill) - value);
				break;
			case "set":
				adjustment = value - stats.getLevelForDisplay(skill);

				stats.setLevel(skill, value);
				break;
		}

		AoACommand.feedback(context.getSource(), "Player", "--", AoACommand.CommandFeedbackType.INFO);
		AoACommand.feedback(context.getSource(), "Player", "command.aoa.player.skill.adjust", AoACommand.CommandFeedbackType.SUCCESS, pl.getDisplayName().getFormattedText(), StringUtil.toTitleCase(skill.toString()), String.valueOf(adjustment), String.valueOf(stats.getLevelForDisplay(skill)));

		return 1;
	}

	private static int adjustXp(CommandContext<CommandSource> context, String operation) throws CommandSyntaxException {
		float value = FloatArgumentType.getFloat(context, "value");
		Skills skill = context.getArgument("skill", Skills.class);
		ServerPlayerEntity pl = EntityArgument.getPlayer(context, "player");
		PlayerDataManager.PlayerStats stats = PlayerUtil.getAdventPlayer(pl).stats();
		float adjustment = value;

		switch (operation) {
			case "add":
				stats.addXp(skill, value, true, true);
				break;
			case "subtract":
				stats.subtractXp(skill, value);
				break;
			case "set":
				float maxXpForLevel = PlayerUtil.getXpRequiredForNextLevel(stats.getLevel(skill));
				float curXp = stats.getExp(skill);
				value = MathHelper.clamp(value, 0, maxXpForLevel);
				adjustment = value - curXp;

				if (adjustment > 0) {
					stats.addXp(skill, adjustment, true, true);
				}
				else {
					stats.subtractXp(skill, adjustment);
				}

				break;
		}

		AoACommand.feedback(context.getSource(), "Player", "--", AoACommand.CommandFeedbackType.INFO);
		AoACommand.feedback(context.getSource(), "Player", "command.aoa.player.xp.adjust", AoACommand.CommandFeedbackType.SUCCESS, pl.getDisplayName().getFormattedText(), StringUtil.toTitleCase(skill.toString()), String.valueOf(adjustment), String.valueOf(stats.getExp(skill)));

		return 1;
	}

	private static int adjustResources(CommandContext<CommandSource> context, String operation) throws CommandSyntaxException {
		float value = FloatArgumentType.getFloat(context, "value");
		Resources resource = context.getArgument("resource", Resources.class);
		ServerPlayerEntity pl = EntityArgument.getPlayer(context, "player");
		PlayerDataManager.PlayerStats stats = PlayerUtil.getAdventPlayer(pl).stats();
		float adjustment = 0;

		switch (operation) {
			case "add":
				adjustment = Math.min(value, 200 - stats.getResourceValue(resource));

				stats.regenResource(resource, adjustment);
				break;
			case "subtract":
				adjustment = -Math.min(value, stats.getResourceValue(resource));

				stats.consumeResource(resource, adjustment, true);
				break;
			case "set":
				adjustment = value - stats.getResourceValue(resource);

				if (adjustment >= 0) {
					stats.regenResource(resource, adjustment);
				}
				else {
					stats.consumeResource(resource, -adjustment, true);
				}
				break;
		}

		AoACommand.feedback(context.getSource(), "Player", "--", AoACommand.CommandFeedbackType.INFO);
		AoACommand.feedback(context.getSource(), "Player", "command.aoa.player.resource.adjust", AoACommand.CommandFeedbackType.SUCCESS, pl.getDisplayName().getFormattedText(), StringUtil.toTitleCase(resource.toString()), String.valueOf(adjustment), String.valueOf(stats.getResourceValue(resource)));

		return 1;
	}

	private static int adjustTribute(CommandContext<CommandSource> context, String operation) throws CommandSyntaxException {
		int value = IntegerArgumentType.getInteger(context, "value");
		Deities deity = context.getArgument("deity", Deities.class);
		ServerPlayerEntity pl = EntityArgument.getPlayer(context, "player");
		PlayerDataManager.PlayerStats stats = PlayerUtil.getAdventPlayer(pl).stats();
		int adjustment = 0;

		switch (operation) {
			case "add":
				adjustment = Math.min(value, 200 - stats.getTribute(deity));

				stats.addTribute(deity, value);
				break;
			case "subtract":
				adjustment = -Math.min(value, stats.getTribute(deity));

				stats.consumeTribute(deity, value);
				break;
			case "set":
				adjustment = value - stats.getTribute(deity);

				if (adjustment > 0) {
					stats.addTribute(deity, adjustment);
				}
				else {
					stats.consumeTribute(deity, adjustment);
				}
				break;
		}

		AoACommand.feedback(context.getSource(), "Player", "--", AoACommand.CommandFeedbackType.INFO);
		AoACommand.feedback(context.getSource(), "Player", "command.aoa.player.tribute.adjust", AoACommand.CommandFeedbackType.SUCCESS, pl.getDisplayName().getFormattedText(), StringUtil.toTitleCase(deity.toString()), String.valueOf(adjustment), String.valueOf(stats.getTribute(deity)));

		return 1;
	}

	private static int adjustHealth(CommandContext<CommandSource> context, String operation) throws CommandSyntaxException {
		float value = FloatArgumentType.getFloat(context, "amount");
		ServerPlayerEntity pl = EntityArgument.getPlayer(context, "player");
		float adjustment = 0;

		switch (operation) {
			case "add":
				adjustment = Math.min(value, pl.getMaxHealth() - pl.getHealth());

				pl.setHealth(pl.getHealth() + adjustment);
				break;
			case "subtract":
				adjustment = -Math.min(value, pl.getHealth());

				pl.setHealth(pl.getHealth() + adjustment);
				break;
			case "set":
				float newHealth = MathHelper.clamp(value, 0, pl.getMaxHealth());
				adjustment = newHealth - pl.getHealth();

				pl.setHealth(newHealth);
				break;
		}

		AoACommand.feedback(context.getSource(), "Player", "--", AoACommand.CommandFeedbackType.INFO);
		AoACommand.feedback(context.getSource(), "Player", "command.aoa.player.health.adjust", AoACommand.CommandFeedbackType.SUCCESS, pl.getDisplayName().getFormattedText(), NumberUtil.roundToNthDecimalPlace(adjustment, 2), NumberUtil.roundToNthDecimalPlace(pl.getHealth(), 2), NumberUtil.roundToNthDecimalPlace(pl.getMaxHealth(), 2));

		return 1;
	}

	private static int adjustVanillaXp(CommandContext<CommandSource> context, String operation) throws CommandSyntaxException {
		int value = IntegerArgumentType.getInteger(context, "amount");
		ServerPlayerEntity pl = EntityArgument.getPlayer(context, "player");
		int adjustment = 0;

		switch (operation) {
			case "add":
				adjustment = Math.min(value, Integer.MAX_VALUE - pl.experienceTotal);

				pl.giveExperiencePoints(adjustment);
				break;
			case "subtract":
				adjustment = -Math.min(value, pl.experienceTotal);

				pl.giveExperiencePoints(adjustment);
				break;
			case "set":
				adjustment = value - pl.experienceTotal;

				pl.giveExperiencePoints(adjustment);
				break;
		}

		AoACommand.feedback(context.getSource(), "Player", "--", AoACommand.CommandFeedbackType.INFO);
		AoACommand.feedback(context.getSource(), "Player", "command.aoa.player.exp.adjust", AoACommand.CommandFeedbackType.SUCCESS, pl.getDisplayName().getFormattedText(), String.valueOf(adjustment), String.valueOf(pl.experienceTotal), String.valueOf(pl.experienceLevel));

		return 1;
	}

	private static int adjustFoodStats(CommandContext<CommandSource> context, String operation, String subsection) throws CommandSyntaxException {
		int value = IntegerArgumentType.getInteger(context, "amount");
		ServerPlayerEntity pl = EntityArgument.getPlayer(context, "player");
		FoodStats food = pl.getFoodStats();

		if (operation.equals("hunger")) {
			int adjustment = 0;

			switch (subsection) {
				case "add":
					adjustment = Math.min(value, 20 - food.getFoodLevel());

					food.addStats(adjustment, 0);
					break;
				case "subtract":
					adjustment = -Math.min(value, food.getFoodLevel());

					food.setFoodLevel(food.getFoodLevel() + adjustment);
					break;
				case "set":
					int newHunger = MathHelper.clamp(value, 0, 20);
					adjustment = newHunger - food.getFoodLevel();

					food.setFoodLevel(newHunger);
					break;
			}

			AoACommand.feedback(context.getSource(), "Player", "--", AoACommand.CommandFeedbackType.INFO);
			AoACommand.feedback(context.getSource(), "Player", "command.aoa.player.food.adjust", AoACommand.CommandFeedbackType.SUCCESS, pl.getDisplayName().getFormattedText(), String.valueOf(adjustment), String.valueOf(food.getFoodLevel()));
		}
		else {
			float adjustment = 0;

			switch (subsection) {
				case "add":
					adjustment = value;

					food.addStats(0, value);
					break;
				case "subtract":
					adjustment = -Math.min(value, food.getSaturationLevel());

					food.addStats(0, adjustment);
					break;
				case "set":
					adjustment = value - food.getSaturationLevel();

					food.addStats(0, adjustment);
					break;
			}

			AoACommand.feedback(context.getSource(), "Player", "--", AoACommand.CommandFeedbackType.INFO);
			AoACommand.feedback(context.getSource(), "Player", "command.aoa.player.saturation.adjust", AoACommand.CommandFeedbackType.SUCCESS, pl.getDisplayName().getFormattedText(), String.valueOf(adjustment), String.valueOf(food.getSaturationLevel()));
		}

		return 1;
	}

	@Override
	public int run(CommandContext<CommandSource> context) {
		AoACommand.feedback(context.getSource(), "Player", "command.aoa.player.desc", AoACommand.CommandFeedbackType.INFO);

		return 1;
	}
}
