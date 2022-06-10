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
        INSERT_OR_UPDATE_PLAYER_TOTAL = connection.prepareStatement("IF EXISTS (SELECT 1 FROM Totals WHERE Uuid=?) " +
                "BEGIN " +
                "UPDATE Totals SET Total=?, LastUpdate=CURRENT_DATE WHERE Uuid=? " +
                "END " +
                "ELSE " +
                "BEGIN " +
                "INSERT INTO Totals (Uuid, Username, Total, LastUpdate) VALUES (?, ?, ?, CURRENT_DATE) " +
                "END");

        for (AoASkill skill : AoARegistries.AOA_SKILLS.getAllRegisteredObjects()) {
            String tableName = LeaderboardTask.idToTableName(AoARegistries.AOA_SKILLS.getId(skill));

            INSERT_OR_UPDATE_PLAYER_SKILL.put(skill, connection.prepareStatement(
                    "IF EXISTS (SELECT 1 FROM " + tableName + " WHERE Uuid=?) " +
                            "BEGIN " +
                            "UPDATE " + tableName + " SET Level=?, LastUpdate=CURRENT_DATE WHERE Uuid=? " +
                            "END " +
                            "ELSE " +
                            "BEGIN " +
                            "INSERT INTO " + tableName + " (Uuid, Username, Level, LastUpdate) VALUES (?, ?, ?, CURRENT_DATE) " +
                            "END"));
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
        INSERT_OR_UPDATE_PLAYER_TOTAL.setNString(1, uuid);
        INSERT_OR_UPDATE_PLAYER_TOTAL.setInt(2, totalLevel);
        INSERT_OR_UPDATE_PLAYER_TOTAL.setNString(3, uuid);
        INSERT_OR_UPDATE_PLAYER_TOTAL.setString(4, playerName);
        INSERT_OR_UPDATE_PLAYER_TOTAL.setInt(5, totalLevel);

        runPreparedStatement(connection, INSERT_OR_UPDATE_PLAYER_TOTAL);
    }

    public void updatePlayerLevel(Connection connection, String uuid, String playerName, AoASkill skill, short level) throws SQLException {
        PreparedStatement statement = INSERT_OR_UPDATE_PLAYER_SKILL.get(skill);

        statement.setNString(1, uuid);
        statement.setShort(2, level);
        statement.setNString(3, uuid);
        statement.setNString(4, uuid);
        statement.setString(5, playerName);
        statement.setShort(6, level);

        runPreparedStatement(connection, statement);
    }
}
