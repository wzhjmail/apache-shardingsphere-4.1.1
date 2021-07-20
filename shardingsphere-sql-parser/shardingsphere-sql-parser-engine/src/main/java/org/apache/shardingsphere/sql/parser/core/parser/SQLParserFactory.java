package org.apache.shardingsphere.sql.parser.core.parser;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.antlr.v4.runtime.*;
import org.apache.shardingsphere.spi.NewInstanceServiceLoader;
import org.apache.shardingsphere.sql.parser.api.parser.SQLParser;
import org.apache.shardingsphere.sql.parser.mysql.MySQLParserConfiguration;
import org.apache.shardingsphere.sql.parser.spi.SQLParserConfiguration;

import java.util.Collection;

/**
 * @ClassName SQLParserFactory
 * @Description
 * @Author wzj
 * @Date 2021/7/14 11:00
 **/

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SQLParserFactory {

    static {
        NewInstanceServiceLoader.register(SQLParserConfiguration.class);
    }

    /**
     * New instance of SQL parser.
     *
     * @param databaseTypeName name of database type
     * @param sql SQL
     * @return SQL parser
     */
    public static SQLParser newInstance(final String databaseTypeName, final String sql) {
        NewInstanceServiceLoader.register(MySQLParserConfiguration.class);
        Collection<SQLParserConfiguration> configurations = NewInstanceServiceLoader.newServiceInstances(SQLParserConfiguration.class);
        for (SQLParserConfiguration each : configurations) {
            if (each.getDatabaseTypeName().equals(databaseTypeName)) {
                return createSQLParser(sql, each);
            }
        }
        throw new UnsupportedOperationException(String.format("Cannot support database type '%s'", databaseTypeName));
    }

    @SneakyThrows
    private static SQLParser createSQLParser(final String sql, final SQLParserConfiguration configuration) {
        Lexer lexer = (Lexer) configuration.getLexerClass().getConstructor(CharStream.class).newInstance(CharStreams.fromString(sql));
        return configuration.getParserClass().getConstructor(TokenStream.class).newInstance(new CommonTokenStream(lexer));
    }
}

