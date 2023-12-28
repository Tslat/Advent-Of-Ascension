package net.tslat.aoa3.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.food.FoodData;
import net.tslat.aoa3.command.argument.AoAResourceArgument;
import net.tslat.aoa3.command.argument.AoASkillArgument;
import net.tslat.aoa3.player.resource.AoAResource;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.NumberUtil;
import net.tslat.aoa3.util.PlayerUtil;

public class PlayerCommand implements Command<CommandSourceStack> {
	private static final PlayerCommand CMD = new PlayerCommand();

	public static ArgumentBuilder<CommandSourceStack, ?> register(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext buildContext) {
		LiteralArgumentBuilder<CommandSourceStack> builder = Commands.literal("player").executes(CMD);

		builder.then(Commands.argument("player", EntityArgument.player())
			.then(Commands.literal("skills")
				.then(Commands.argument("skill", AoASkillArgument.skill(buildContext))
					.then(Commands.literal("level")
						.then(Commands.literal("add").requires(command -> command.hasPermission(4))
							.then(Commands.argument("value", IntegerArgumentType.integer(1, 999))
								.executes((context) -> adjustSkills(context, "add"))))
						.then(Commands.literal("subtract").requires(command -> command.hasPermission(4))
							.then(Commands.argument("value", IntegerArgumentType.integer(1, 999))
								.executes((context) -> adjustSkills(context, "subtract"))))
						.then(Commands.literal("set").requires(command -> command.hasPermission(4))
							.then(Commands.argument("value", IntegerArgumentType.integer(1, 1000))
								.executes((context) -> adjustSkills(context, "set"))))
						.then(Commands.literal("check").executes(PlayerCommand::checkSkill)))
					.then(Commands.literal("xp")
						.then(Commands.literal("add").requires(command -> command.hasPermission(4))
							.then(Commands.argument("value", FloatArgumentType.floatArg(1, 544132359))
								.executes((context) -> adjustXp(context, "add"))))
						.then(Commands.literal("subtract").requires(command -> command.hasPermission(4))
							.then(Commands.argument("value", FloatArgumentType.floatArg(1, 544132359))
								.executes((context) -> adjustXp(context, "subtract"))))
						.then(Commands.literal("set").requires(command -> command.hasPermission(4))
							.then(Commands.argument("value", FloatArgumentType.floatArg(0, 544132358))
								.executes((context) -> adjustXp(context, "set"))))
						.then(Commands.literal("check").executes(PlayerCommand::checkXp)))
					.then(Commands.literal("cycle")
						.then(Commands.literal("add").requires(command -> command.hasPermission(4))
							.then(Commands.argument("value", IntegerArgumentType.integer(1, 10))
								.executes((context) -> adjustCycle(context, "add"))))
						.then(Commands.literal("subtract").requires(command -> command.hasPermission(4))
							.then(Commands.argument("value", IntegerArgumentType.integer(1, 10))
								.executes((context) -> adjustCycle(context, "subtract"))))
						.then(Commands.literal("set").requires(command -> command.hasPermission(4))
							.then(Commands.argument("value", IntegerArgumentType.integer(0, 10))
								.executes((context) -> adjustCycle(context, "set"))))
						.then(Commands.literal("check").executes(PlayerCommand::checkCycle)))))
			.then(Commands.literal("resources")
				.then(Commands.argument("resource", AoAResourceArgument.resource(buildContext))
					.then(Commands.literal("add").requires(command -> command.hasPermission(4))
						.then(Commands.argument("value", FloatArgumentType.floatArg(1, Float.MAX_VALUE))
							.executes((context) -> adjustResources(context, "add"))))
					.then(Commands.literal("subtract").requires(command -> command.hasPermission(4))
						.then(Commands.argument("value", FloatArgumentType.floatArg(1, Float.MAX_VALUE))
							.executes((context) -> adjustResources(context, "subtract"))))
					.then(Commands.literal("set").requires(command -> command.hasPermission(4))
						.then(Commands.argument("value", FloatArgumentType.floatArg(0, Float.MAX_VALUE))
							.executes((context) -> adjustResources(context, "set"))))
					.then(Commands.literal("check").executes(PlayerCommand::checkResource))))
			.then(Commands.literal("stats")
				.then(Commands.literal("experience")
					.then(Commands.literal("add").requires(command -> command.hasPermission(4))
						.then(Commands.argument("amount", IntegerArgumentType.integer(1, Integer.MAX_VALUE))
							.executes((context) -> adjustVanillaXp(context, "add"))))
					.then(Commands.literal("subtract").requires(command -> command.hasPermission(4))
						.then(Commands.argument("amount", IntegerArgumentType.integer(1, Integer.MAX_VALUE))
							.executes((context) -> adjustVanillaXp(context, "subtract"))))
					.then(Commands.literal("check").executes(PlayerCommand::checkVanillaXp)))
				.then(Commands.literal("food")
					.then(Commands.literal("hunger")
						.then(Commands.literal("add").requires(command -> command.hasPermission(4))
							.then(Commands.argument("amount", IntegerArgumentType.integer(1, 20))
								.executes((context) -> adjustFoodStats(context, "hunger", "add"))))
						.then(Commands.literal("subtract").requires(command -> command.hasPermission(4))
							.then(Commands.argument("amount", IntegerArgumentType.integer(1, 20))
								.executes((context) -> adjustFoodStats(context, "hunger", "subtract"))))
						.then(Commands.literal("set").requires(command -> command.hasPermission(4))
							.then(Commands.argument("amount", IntegerArgumentType.integer(0, 20))
								.executes((context) -> adjustFoodStats(context, "hunger", "set"))))
						.then(Commands.literal("check").executes((context) -> checkFoodStats(context, "hunger"))))
					.then(Commands.literal("saturation")
						.then(Commands.literal("add").requires(command -> command.hasPermission(4))
							.then(Commands.argument("amount", IntegerArgumentType.integer(1, 20))
								.executes((context) -> adjustFoodStats(context, "saturation", "add"))))
						.then(Commands.literal("subtract").requires(command -> command.hasPermission(4))
							.then(Commands.argument("amount", IntegerArgumentType.integer(1, 20))
								.executes((context) -> adjustFoodStats(context, "saturation", "subtract"))))
						.then(Commands.literal("set").requires(command -> command.hasPermission(4))
							.then(Commands.argument("amount", IntegerArgumentType.integer(1, 20))
								.executes((context) -> adjustFoodStats(context, "saturation", "set"))))
						.then(Commands.literal("check").executes((context) -> checkFoodStats(context, "saturation")))))
				.then(Commands.literal("health")
					.then(Commands.literal("add").requires(command -> command.hasPermission(4))
						.then(Commands.argument("amount", FloatArgumentType.floatArg(0, Integer.MAX_VALUE))
							.executes((context) -> adjustHealth(context, "add"))))
					.then(Commands.literal("subtract").requires(command -> command.hasPermission(4))
						.then(Commands.argument("amount", FloatArgumentType.floatArg(0, Integer.MAX_VALUE))
							.executes((context) -> adjustHealth(context, "subtract"))))
					.then(Commands.literal("set").requires(command -> command.hasPermission(4))
						.then(Commands.argument("amount", FloatArgumentType.floatArg(0, Integer.MAX_VALUE))
							.executes((context) -> adjustHealth(context, "set"))))
					.then(Commands.literal("check").executes(PlayerCommand::checkHealth)))));

		return builder;
	}

	private static int checkFoodStats(CommandContext<CommandSourceStack> context, String subsection) throws CommandSyntaxException {
		ServerPlayer pl = EntityArgument.getPlayer(context, "player");

		if (subsection.equals("hunger")) {
			if (pl != context.getSource().getEntityOrException()) {
				if (!context.getSource().hasPermission(2))
					throw AoACommand.NO_PERMISSION_EXCEPTION.create();

				AoACommand.feedback(context.getSource(), "Player", "--", AoACommand.CommandFeedbackType.INFO);
				AoACommand.feedback(context.getSource(), "Player", "command.aoa.player.food.hunger.check.other", AoACommand.CommandFeedbackType.SUCCESS, pl.getDisplayName(), LocaleUtil.numToComponent(pl.getFoodData().getFoodLevel()));
			}
			else {
				AoACommand.feedback(context.getSource(), "Player", "--", AoACommand.CommandFeedbackType.INFO);
				AoACommand.feedback(context.getSource(), "Player", "command.aoa.player.food.hunger.check.self", AoACommand.CommandFeedbackType.SUCCESS, LocaleUtil.numToComponent(pl.getFoodData().getFoodLevel()));
			}
		}
		else {
			if (pl != context.getSource().getEntityOrException()) {
				if (!context.getSource().hasPermission(2))
					throw AoACommand.NO_PERMISSION_EXCEPTION.create();

				AoACommand.feedback(context.getSource(), "Player", "--", AoACommand.CommandFeedbackType.INFO);
				AoACommand.feedback(context.getSource(), "Player", "command.aoa.player.food.saturation.check.other", AoACommand.CommandFeedbackType.SUCCESS, pl.getDisplayName(), Component.literal(NumberUtil.roundToNthDecimalPlace(pl.getFoodData().getSaturationLevel(), 2)));
			}
			else {
				AoACommand.feedback(context.getSource(), "Player", "--", AoACommand.CommandFeedbackType.INFO);
				AoACommand.feedback(context.getSource(), "Player", "command.aoa.player.food.saturation.check.self", AoACommand.CommandFeedbackType.SUCCESS, Component.literal(NumberUtil.roundToNthDecimalPlace(pl.getFoodData().getSaturationLevel(), 2)));
			}
		}

		return 1;
	}

	private static int checkVanillaXp(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
		ServerPlayer pl = EntityArgument.getPlayer(context, "player");

		if (pl != context.getSource().getEntityOrException()) {
			if (!context.getSource().hasPermission(2))
				throw AoACommand.NO_PERMISSION_EXCEPTION.create();

			AoACommand.feedback(context.getSource(), "Player", "--", AoACommand.CommandFeedbackType.INFO);
			AoACommand.feedback(context.getSource(), "Player", "command.aoa.player.exp.check.other", AoACommand.CommandFeedbackType.SUCCESS, pl.getDisplayName(), LocaleUtil.numToComponent(pl.totalExperience), LocaleUtil.numToComponent(pl.experienceLevel));
		}
		else {
			AoACommand.feedback(context.getSource(), "Player", "--", AoACommand.CommandFeedbackType.INFO);
			AoACommand.feedback(context.getSource(), "Player", "command.aoa.player.exp.check.self", AoACommand.CommandFeedbackType.SUCCESS, LocaleUtil.numToComponent(pl.totalExperience), LocaleUtil.numToComponent(pl.experienceLevel));
		}

		return 1;
	}

	private static int checkHealth(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
		ServerPlayer pl = EntityArgument.getPlayer(context, "player");

		if (pl != context.getSource().getEntityOrException()) {
			if (!context.getSource().hasPermission(2))
				throw AoACommand.NO_PERMISSION_EXCEPTION.create();

			AoACommand.feedback(context.getSource(), "Player", "--", AoACommand.CommandFeedbackType.INFO);
			AoACommand.feedback(context.getSource(), "Player", "command.aoa.player.health.check.other", AoACommand.CommandFeedbackType.SUCCESS, pl.getDisplayName(), Component.literal(NumberUtil.roundToNthDecimalPlace(pl.getHealth(), 2)), Component.literal(NumberUtil.roundToNthDecimalPlace(pl.getMaxHealth(), 2)));
		}
		else {
			AoACommand.feedback(context.getSource(), "Player", "--", AoACommand.CommandFeedbackType.INFO);
			AoACommand.feedback(context.getSource(), "Player", "command.aoa.player.health.check.self", AoACommand.CommandFeedbackType.SUCCESS, Component.literal(NumberUtil.roundToNthDecimalPlace(pl.getHealth(), 2)), Component.literal(NumberUtil.roundToNthDecimalPlace(pl.getMaxHealth(), 2)));
		}

		return 1;
	}

	private static int checkSkill(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
		ServerPlayer pl = EntityArgument.getPlayer(context, "player");

		if (pl != context.getSource().getEntityOrException()) {
			if (!context.getSource().hasPermission(2))
				throw AoACommand.NO_PERMISSION_EXCEPTION.create();

			AoASkill skill = AoASkillArgument.getSkill(context, "skill");
			int level = PlayerUtil.getLevel(pl, skill);

			AoACommand.feedback(context.getSource(), "Player", "--", AoACommand.CommandFeedbackType.INFO);
			AoACommand.feedback(context.getSource(), "Player", "command.aoa.player.skill.check.other", AoACommand.CommandFeedbackType.SUCCESS, pl.getDisplayName(), skill.getName(), LocaleUtil.numToComponent(level));
		}
		else {
			AoASkill skill = AoASkillArgument.getSkill(context, "skill");
			int level = PlayerUtil.getLevel(pl, skill);

			AoACommand.feedback(context.getSource(), "Player", "--", AoACommand.CommandFeedbackType.INFO);
			AoACommand.feedback(context.getSource(), "Player", "command.aoa.player.skill.check.self", AoACommand.CommandFeedbackType.SUCCESS, skill.getName(), LocaleUtil.numToComponent(level));
		}

		return 1;
	}

	private static int checkXp(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
		ServerPlayer pl = EntityArgument.getPlayer(context, "player");

		if (pl != context.getSource().getEntityOrException()) {
			if (!context.getSource().hasPermission(2))
				throw AoACommand.NO_PERMISSION_EXCEPTION.create();

			AoASkill skill = AoASkillArgument.getSkill(context, "skill");
			float xp = PlayerUtil.getAdventPlayer(pl).getSkill(skill).getXp();

			AoACommand.feedback(context.getSource(), "Player", "--", AoACommand.CommandFeedbackType.INFO);
			AoACommand.feedback(context.getSource(), "Player", "command.aoa.player.xp.check.other", AoACommand.CommandFeedbackType.SUCCESS, pl.getDisplayName(), skill.getName(), LocaleUtil.numToComponent(xp));
		}
		else {
			AoASkill skill = AoASkillArgument.getSkill(context, "skill");
			float xp = PlayerUtil.getAdventPlayer(pl).getSkill(skill).getXp();

			AoACommand.feedback(context.getSource(), "Player", "--", AoACommand.CommandFeedbackType.INFO);
			AoACommand.feedback(context.getSource(), "Player", "command.aoa.player.xp.check.self", AoACommand.CommandFeedbackType.SUCCESS, skill.getName(), LocaleUtil.numToComponent(xp));
		}

		return 1;
	}

	private static int checkCycle(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
		ServerPlayer pl = EntityArgument.getPlayer(context, "player");

		if (pl != context.getSource().getEntityOrException()) {
			if (!context.getSource().hasPermission(2))
				throw AoACommand.NO_PERMISSION_EXCEPTION.create();

			AoASkill skill = AoASkillArgument.getSkill(context, "skill");
			int cycles = PlayerUtil.getAdventPlayer(pl).getSkill(skill).getCycles();

			AoACommand.feedback(context.getSource(), "Player", "--", AoACommand.CommandFeedbackType.INFO);
			AoACommand.feedback(context.getSource(), "Player", "command.aoa.player.cycle.check.other", AoACommand.CommandFeedbackType.SUCCESS, pl.getDisplayName(), skill.getName(), LocaleUtil.numToComponent(cycles));
		}
		else {
			AoASkill skill = AoASkillArgument.getSkill(context, "skill");
			int cycles = PlayerUtil.getAdventPlayer(pl).getSkill(skill).getCycles();

			AoACommand.feedback(context.getSource(), "Player", "--", AoACommand.CommandFeedbackType.INFO);
			AoACommand.feedback(context.getSource(), "Player", "command.aoa.player.cycle.check.self", AoACommand.CommandFeedbackType.SUCCESS, skill.getName(), LocaleUtil.numToComponent(cycles));
		}

		return 1;
	}

	private static int checkResource(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
		ServerPlayer pl = EntityArgument.getPlayer(context, "player");

		if (pl != context.getSource().getEntityOrException()) {
			if (!context.getSource().hasPermission(2))
				throw AoACommand.NO_PERMISSION_EXCEPTION.create();

			AoAResource resource = AoAResourceArgument.getResource(context, "resource");
			float value = PlayerUtil.getResourceValue(pl, resource);

			AoACommand.feedback(context.getSource(), "Player", "--", AoACommand.CommandFeedbackType.INFO);
			AoACommand.feedback(context.getSource(), "Player", "command.aoa.player.resource.check.other", AoACommand.CommandFeedbackType.SUCCESS, pl.getDisplayName(), LocaleUtil.numToComponent(value), resource.getName());
		}
		else {
			AoAResource resource = AoAResourceArgument.getResource(context, "resource");
			float value = PlayerUtil.getResourceValue(pl, resource);

			AoACommand.feedback(context.getSource(), "Player", "--", AoACommand.CommandFeedbackType.INFO);
			AoACommand.feedback(context.getSource(), "Player", "command.aoa.player.resource.check.self", AoACommand.CommandFeedbackType.SUCCESS, resource.getName(), LocaleUtil.numToComponent(value));
		}

		return 1;
	}

	private static int adjustSkills(CommandContext<CommandSourceStack> context, String operation) throws CommandSyntaxException {
		int value = IntegerArgumentType.getInteger(context, "value");
		AoASkill skill = AoASkillArgument.getSkill(context, "skill");
		ServerPlayer pl = EntityArgument.getPlayer(context, "player");
		AoASkill.Instance instance = PlayerUtil.getSkill(pl, skill);

		int adjustment = 0;

		switch (operation) {
			case "add" -> {
				value = Math.abs(value);
				adjustment = Math.min(value, 1000 - instance.getLevel(true));
				instance.setLevel(instance.getLevel(true) + value);
			}
			case "subtract" -> {
				value = Math.abs(value);
				adjustment = -Math.min(value, instance.getLevel(true) - 1);
				instance.setLevel(instance.getLevel(true) - value);
			}
			case "set" -> {
				value = Mth.clamp(value, 1, 1000);
				adjustment = value - instance.getLevel(true);
				instance.setLevel(value);
			}
		}

		AoACommand.feedback(context.getSource(), "Player", "--", AoACommand.CommandFeedbackType.INFO);
		AoACommand.feedback(context.getSource(), "Player", "command.aoa.player.skill.adjust", AoACommand.CommandFeedbackType.SUCCESS, pl.getDisplayName(), skill.getName(), LocaleUtil.numToComponent(adjustment), LocaleUtil.numToComponent(instance.getLevel(true)));

		return 1;
	}

	private static int adjustXp(CommandContext<CommandSourceStack> context, String operation) throws CommandSyntaxException {
		float value = FloatArgumentType.getFloat(context, "value");
		AoASkill skill = AoASkillArgument.getSkill(context, "skill");
		ServerPlayer pl = EntityArgument.getPlayer(context, "player");
		AoASkill.Instance instance = PlayerUtil.getSkill(pl, skill);
		float adjustment = value;

		switch (operation) {
			case "add" -> {
				value = Math.abs(value);
				instance.adjustXp(value, true, true);
			}
			case "subtract" -> {
				value = Math.abs(value);
				instance.adjustXp(-value, true, true);
			}
			case "set" -> {
				float maxXpForLevel = PlayerUtil.getXpRequiredForNextLevel(instance.getLevel(true));
				float curXp = instance.getXp();
				value = Mth.clamp(value, 0, maxXpForLevel);
				adjustment = value - curXp;
				if (adjustment > 0) {
					instance.adjustXp(adjustment, true, true);
				}
				else {
					instance.adjustXp(-adjustment, true, true);
				}
			}
		}

		AoACommand.feedback(context.getSource(), "Player", "--", AoACommand.CommandFeedbackType.INFO);
		AoACommand.feedback(context.getSource(), "Player", "command.aoa.player.xp.adjust", AoACommand.CommandFeedbackType.SUCCESS, pl.getDisplayName(), skill.getName(), LocaleUtil.numToComponent(adjustment), LocaleUtil.numToComponent(instance.getXp()));

		return 1;
	}

	private static int adjustCycle(CommandContext<CommandSourceStack> context, String operation) throws CommandSyntaxException {
		int value = IntegerArgumentType.getInteger(context, "value");
		AoASkill skill = AoASkillArgument.getSkill(context, "skill");
		ServerPlayer pl = EntityArgument.getPlayer(context, "player");
		AoASkill.Instance instance = PlayerUtil.getSkill(pl, skill);
		float adjustment = value;

		switch (operation) {
			case "add" -> {
				value = Mth.clamp(value, 1, 10 - instance.getCycles());
				adjustment = Math.min(value, 10 - instance.getCycles());
				instance.setCycle(instance.getCycles() + value);
			}
			case "subtract" -> {
				value = Mth.clamp(value, 1, instance.getCycles());
				adjustment = Math.min(value, instance.getCycles());
				instance.setCycle(instance.getCycles() - value);
			}
			case "set" -> {
				value = Mth.clamp(value, 0, 10);
				adjustment = value - instance.getCycles();
				instance.setCycle(value);
			}
		}

		AoACommand.feedback(context.getSource(), "Player", "--", AoACommand.CommandFeedbackType.INFO);
		AoACommand.feedback(context.getSource(), "Player", "command.aoa.player.cycle.adjust", AoACommand.CommandFeedbackType.SUCCESS, pl.getDisplayName(), skill.getName(), LocaleUtil.numToComponent(adjustment), LocaleUtil.numToComponent(instance.getCycles()));

		return 1;
	}

	private static int adjustResources(CommandContext<CommandSourceStack> context, String operation) throws CommandSyntaxException {
		float value = FloatArgumentType.getFloat(context, "value");
		AoAResource resource = AoAResourceArgument.getResource(context, "resource");
		ServerPlayer pl = EntityArgument.getPlayer(context, "player");
		AoAResource.Instance instance = PlayerUtil.getResource(pl, resource);
		float adjustment = 0;

		switch (operation) {
			case "add" -> {
				adjustment = Math.min(value, instance.getMaxValue() - instance.getCurrentValue());
				instance.addValue(adjustment);
			}
			case "subtract" -> {
				adjustment = -Math.min(value, instance.getCurrentValue());
				instance.consume(adjustment, true);
			}
			case "set" -> instance.setValue(value);
		}

		AoACommand.feedback(context.getSource(), "Player", "--", AoACommand.CommandFeedbackType.INFO);
		AoACommand.feedback(context.getSource(), "Player", "command.aoa.player.resource.adjust", AoACommand.CommandFeedbackType.SUCCESS, pl.getDisplayName(), resource.getName(), LocaleUtil.numToComponent(adjustment), LocaleUtil.numToComponent(instance.getCurrentValue()));

		return 1;
	}

	private static int adjustHealth(CommandContext<CommandSourceStack> context, String operation) throws CommandSyntaxException {
		float value = FloatArgumentType.getFloat(context, "amount");
		ServerPlayer pl = EntityArgument.getPlayer(context, "player");
		float adjustment = 0;

		switch (operation) {
			case "add" -> {
				adjustment = Math.min(value, pl.getMaxHealth() - pl.getHealth());
				pl.setHealth(pl.getHealth() + adjustment);
			}
			case "subtract" -> {
				adjustment = -Math.min(value, pl.getHealth());
				pl.setHealth(pl.getHealth() + adjustment);
			}
			case "set" -> {
				float newHealth = Mth.clamp(value, 0, pl.getMaxHealth());
				adjustment = newHealth - pl.getHealth();
				pl.setHealth(newHealth);
			}
		}

		AoACommand.feedback(context.getSource(), "Player", "--", AoACommand.CommandFeedbackType.INFO);
		AoACommand.feedback(context.getSource(), "Player", "command.aoa.player.health.adjust", AoACommand.CommandFeedbackType.SUCCESS, pl.getDisplayName(), Component.literal(NumberUtil.roundToNthDecimalPlace(adjustment, 2)), Component.literal(NumberUtil.roundToNthDecimalPlace(pl.getHealth(), 2)), Component.literal(NumberUtil.roundToNthDecimalPlace(pl.getMaxHealth(), 2)));

		return 1;
	}

	private static int adjustVanillaXp(CommandContext<CommandSourceStack> context, String operation) throws CommandSyntaxException {
		int value = IntegerArgumentType.getInteger(context, "amount");
		ServerPlayer pl = EntityArgument.getPlayer(context, "player");
		int adjustment = 0;

		switch (operation) {
			case "add" -> {
				adjustment = Math.min(value, Integer.MAX_VALUE - pl.totalExperience);
				pl.giveExperiencePoints(adjustment);
			}
			case "subtract" -> {
				adjustment = -Math.min(value, pl.totalExperience);
				pl.giveExperiencePoints(adjustment);
			}
			case "set" -> {
				adjustment = value - pl.totalExperience;
				pl.giveExperiencePoints(adjustment);
			}
		}

		AoACommand.feedback(context.getSource(), "Player", "--", AoACommand.CommandFeedbackType.INFO);
		AoACommand.feedback(context.getSource(), "Player", "command.aoa.player.exp.adjust", AoACommand.CommandFeedbackType.SUCCESS, pl.getDisplayName(), LocaleUtil.numToComponent(adjustment), LocaleUtil.numToComponent(pl.totalExperience), LocaleUtil.numToComponent(pl.experienceLevel));

		return 1;
	}

	private static int adjustFoodStats(CommandContext<CommandSourceStack> context, String operation, String subsection) throws CommandSyntaxException {
		int value = IntegerArgumentType.getInteger(context, "amount");
		ServerPlayer pl = EntityArgument.getPlayer(context, "player");
		FoodData food = pl.getFoodData();

		if (operation.equals("hunger")) {
			int adjustment = 0;

			switch (subsection) {
				case "add" -> {
					adjustment = Math.min(value, 20 - food.getFoodLevel());
					food.eat(adjustment, 0);
				}
				case "subtract" -> {
					adjustment = -Math.min(value, food.getFoodLevel());
					food.setFoodLevel(food.getFoodLevel() + adjustment);
				}
				case "set" -> {
					int newHunger = Mth.clamp(value, 0, 20);
					adjustment = newHunger - food.getFoodLevel();
					food.setFoodLevel(newHunger);
				}
			}

			AoACommand.feedback(context.getSource(), "Player", "--", AoACommand.CommandFeedbackType.INFO);
			AoACommand.feedback(context.getSource(), "Player", "command.aoa.player.food.adjust", AoACommand.CommandFeedbackType.SUCCESS, pl.getDisplayName(), LocaleUtil.numToComponent(adjustment), LocaleUtil.numToComponent(food.getFoodLevel()));
		}
		else {
			float adjustment = 0;

			switch (subsection) {
				case "add" -> {
					adjustment = value;
					food.eat(1, value / 2f);
				}
				case "subtract" -> {
					adjustment = -Math.min(value, food.getSaturationLevel());
					food.eat(1, adjustment / 2f);
				}
				case "set" -> {
					adjustment = value - food.getSaturationLevel();
					food.eat(1, adjustment / 2f);
				}
			}

			AoACommand.feedback(context.getSource(), "Player", "--", AoACommand.CommandFeedbackType.INFO);
			AoACommand.feedback(context.getSource(), "Player", "command.aoa.player.saturation.adjust", AoACommand.CommandFeedbackType.SUCCESS, pl.getDisplayName(), LocaleUtil.numToComponent(adjustment), LocaleUtil.numToComponent(food.getSaturationLevel()));
		}

		return 1;
	}

	@Override
	public int run(CommandContext<CommandSourceStack> context) {
		AoACommand.feedback(context.getSource(), "Player", "command.aoa.player.desc", AoACommand.CommandFeedbackType.INFO);

		return 1;
	}

	public static final DynamicCommandExceptionType ERROR_SKILL_INVALID = new DynamicCommandExceptionType(obj -> Component.translatable(LocaleUtil.createGenericLocaleKey("argument", "skill.notFound"), obj));
	public static final DynamicCommandExceptionType ERROR_RESOURCE_INVALID = new DynamicCommandExceptionType(obj -> Component.translatable(LocaleUtil.createGenericLocaleKey("argument", "resource.notFound"), obj));
}