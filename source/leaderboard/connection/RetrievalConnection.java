package net.tslat.aoa3.leaderboard.connection;

import net.tslat.aoa3.leaderboard.LeaderboardTask;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.LinkedBlockingQueue;

public class RetrievalConnection extends LeaderboardConnection {
    public RetrievalConnection(LinkedBlockingQueue<LeaderboardTask<RetrievalConnection>> queue, Properties properties) {
        super((LinkedBlockingQueue)queue, properties);
    }

    @Override
    protected void prepareStatements(Connection connection) throws SQLException {

    }

    @Override
    protected void closeStatements() throws SQLException {
    }
}
