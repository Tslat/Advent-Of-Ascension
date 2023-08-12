package net.tslat.aoa3.leaderboard.connection;

import net.tslat.aoa3.leaderboard.LeaderboardTask;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.LinkedBlockingQueue;

public class RetrievalConnection extends LeaderboardConnection {
    private PreparedStatement RETRIEVE_TOTAL_PAGE_BY_SELF = null;
    private PreparedStatement RETRIEVE_SKILL_PAGE_DATA = null;

    public RetrievalConnection(LinkedBlockingQueue<LeaderboardTask<RetrievalConnection>> queue, Properties properties) {
        super((LinkedBlockingQueue)queue, properties);
    }

    @Override
    protected void prepareStatements(Connection connection) throws SQLException {
        RETRIEVE_TOTAL_PAGE_BY_SELF = connection.prepareStatement(
                "SELECT FROM Totals " +
                "LIMIT 10");
    }

    @Override
    protected void closeStatements() throws SQLException {
        RETRIEVE_TOTAL_PAGE_BY_SELF.close();
    }
}
