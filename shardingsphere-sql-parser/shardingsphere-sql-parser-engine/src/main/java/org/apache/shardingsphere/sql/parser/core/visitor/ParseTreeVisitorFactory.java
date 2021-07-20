package org.apache.shardingsphere.sql.parser.core.visitor;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.apache.shardingsphere.spi.NewInstanceServiceLoader;
import org.apache.shardingsphere.sql.parser.api.visitor.SQLVisitorFacade;
import org.apache.shardingsphere.sql.parser.exception.SQLParsingException;
import org.apache.shardingsphere.sql.parser.spi.SQLParserConfiguration;
import org.apache.shardingsphere.sql.parser.sql.statement.SQLStatementType;

/**
 * @ClassName ParseTreeVisitorFactory
 * @Description
 * @Author wzj
 * @Date 2021/7/14 11:17
 **/

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ParseTreeVisitorFactory {

    /**
     * New instance of SQL visitor.
     *
     * @param databaseTypeName name of database type
     * @param visitorRule visitor rule
     * @return parse tree visitor
     */
    public static ParseTreeVisitor newInstance(final String databaseTypeName, final VisitorRule visitorRule) {
        for (SQLParserConfiguration each : NewInstanceServiceLoader.newServiceInstances(SQLParserConfiguration.class)) {
            if (each.getDatabaseTypeName().equals(databaseTypeName)) {
                return createParseTreeVisitor(each, visitorRule.getType());
            }
        }
        throw new UnsupportedOperationException(String.format("Cannot support database type '%s'", databaseTypeName));
    }

    @SneakyThrows
    private static ParseTreeVisitor createParseTreeVisitor(final SQLParserConfiguration configuration, final SQLStatementType type) {
        SQLVisitorFacade visitorFacade = configuration.getVisitorFacadeClass().getConstructor().newInstance();
        switch (type) {
            case DML:
                return (ParseTreeVisitor) visitorFacade.getDMLVisitorClass().getConstructor().newInstance();
            case DDL:
                return (ParseTreeVisitor) visitorFacade.getDDLVisitorClass().getConstructor().newInstance();
            case TCL:
                return (ParseTreeVisitor) visitorFacade.getTCLVisitorClass().getConstructor().newInstance();
            case DCL:
                return (ParseTreeVisitor) visitorFacade.getDCLVisitorClass().getConstructor().newInstance();
            case DAL:
                return (ParseTreeVisitor) visitorFacade.getDALVisitorClass().getConstructor().newInstance();
            case RL:
                return (ParseTreeVisitor) visitorFacade.getRLVisitorClass().getConstructor().newInstance();
            default:
                throw new SQLParsingException("Can not support SQL statement type: `%s`", type);
        }
    }
}

