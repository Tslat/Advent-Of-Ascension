package net.tslat.aoa3.leaderboard.connection;

import net.tslat.aoa3.common.registration.AoARegistries;
import net.tslat.aoa3.leaderboard.LeaderboardTask;
import net.tslat.aoa3.player.skill.AoASkill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.LinkedBlockingQueue;

public class InsertionConnection extends LeaderboardConnection {
    private PreparedStatement INSERT_OR_UPDATE_PLAYER_TOTAL = null;
    private final HashMap<AoASkill, PreparedStatement> INSERT_OR_UPDATE_PLAYER_SKILL = new HashMap<>(10);

    public InsertionConnection(LinkedBlockingQueue<LeaderboardTask<InsertionConnection>> queue, Properties properties) {
        super((LinkedBlockingQueue)queue, properties);
    }

    @Override
    protected void prepareStatements(Connection connection) throws SQLException {
        INSERT_OR_UPDATE_PLAYER_TOTAL = connection.prepareStatement(
                "MERGE INTO Totals " +
                    "Using (VALUES ?, ?, ?, CURRENT_DATE) I (UUID, USER, TOTAL, CURDATE) " +
                    "ON (Totals.UUID=I.UUID)" +
                    "WHEN MATCHED THEN UPDATE SET Totals.Total=I.TOTAL, Totals.LastUpdate = I.CURDATE " +
                    "WHEN NOT MATCHED THEN INSERT (Uuid, Username, Total, LastUpdate) VALUES (I.UUID, I.USER, I.TOTAL, I.CURDATE)");

        for (AoASkill skill : AoARegistries.AOA_SKILLS.getAllRegisteredObjects()) {
            String tableName = LeaderboardTask.idToTableName(AoARegistries.AOA_SKILLS.getId(skill));

            INSERT_OR_UPDATE_PLAYER_SKILL.put(skill, connection.prepareStatement(
                    "MERGE INTO " + tableName + " " +
                            "Using (VALUES ?, ?, ?, CURRENT_DATE) I (UUID, USER, LEVEL, CURDATE) " +
                            "ON (" + tableName + ".Uuid=I.UUID)" +
                            "WHEN MATCHED THEN UPDATE SET " + tableName + ".Level=I.LEVEL, " + tableName + ".LastUpdate = I.CURDATE " +
                            "WHEN NOT MATCHED THEN INSERT (Uuid, Username, Level, LastUpdate) VALUES (I.UUID, I.USER, I.LEVEL, I.CURDATE)"));
        }
    }

    @Override
    protected void closeStatements() throws SQLException {
        INSERT_OR_UPDATE_PLAYER_TOTAL.close();

        for (PreparedStatement preparedStatement : INSERT_OR_UPDATE_PLAYER_SKILL.values()) {
            preparedStatement.close();
        }
    }

    public void updatePlayerTotal(Connection connection, String uuid, String playerName, int totalLevel) throws SQLException {
        INSERT_OR_UPDATE_PLAYER_TOTAL.setString(1, uuid);
        INSERT_OR_UPDATE_PLAYER_TOTAL.setNString(2, playerName);
        INSERT_OR_UPDATE_PLAYER_TOTAL.setInt(3, totalLevel);

        runPreparedStatement(connection, INSERT_OR_UPDATE_PLAYER_TOTAL);
    }

    public void updatePlayerLevel(Connection connection, String uuid, String playerName, AoASkill skill, short level) throws SQLException {
        PreparedStatement statement = INSERT_OR_UPDATE_PLAYER_SKILL.get(skill);

        statement.setString(1, uuid);
        statement.setNString(2, playerName);
        statement.setShort(3, level);

        runPreparedStatement(connection, statement);
    }
}
