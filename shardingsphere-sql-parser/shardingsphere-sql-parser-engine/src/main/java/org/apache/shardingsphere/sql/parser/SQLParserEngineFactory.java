package org.apache.shardingsphere.sql.parser;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName SQLParserEngineFactory
 * @Description
 * @Author wzj
 * @Date 2021/7/14 15:49
 **/

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SQLParserEngineFactory {

    private static final Map<String, SQLParserEngine> ENGINES = new ConcurrentHashMap<>();

    /**
     * Get SQL parser engine.
     *
     * @param databaseTypeName name of database type
     * @return SQL parser engine
     */
    public static SQLParserEngine getSQLParserEngine(final String databaseTypeName) {
        if (ENGINES.containsKey(databaseTypeName)) {
            return ENGINES.get(databaseTypeName);
        }
        synchronized (ENGINES) {
            if (ENGINES.containsKey(databaseTypeName)) {
                return ENGINES.get(databaseTypeName);
            }
            SQLParserEngine result = new SQLParserEngine(databaseTypeName);
            ENGINES.put(databaseTypeName, result);
            return result;
        }
    }
}
