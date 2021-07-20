package org.apache.shardingsphere.sql.parser.core.parser;

import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.BailErrorStrategy;
import org.antlr.v4.runtime.DefaultErrorStrategy;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.atn.PredictionMode;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.apache.shardingsphere.sql.parser.api.parser.SQLParser;
import org.apache.shardingsphere.sql.parser.core.ParseASTNode;
import org.apache.shardingsphere.sql.parser.exception.SQLParsingException;

/**
 * @ClassName SQLParserExecutor
 * @Description
 * @Author wzj
 * @Date 2021/7/14 10:53
 **/

@RequiredArgsConstructor
public final class SQLParserExecutor {

    private final String databaseTypeName;

    private final String sql;

    /**
     * Execute to parse SQL.
     *
     * @return AST node
     */
    public ParseASTNode execute() {
        ParseASTNode result = towPhaseParse();
        if (result.getRootNode() instanceof ErrorNode) {
            throw new SQLParsingException(String.format("Unsupported SQL of `%s`", sql));
        }
        return result;
    }

    private ParseASTNode towPhaseParse() {
        SQLParser sqlParser = SQLParserFactory.newInstance(databaseTypeName, sql);
        try {
            ((Parser) sqlParser).setErrorHandler(new BailErrorStrategy());
            ((Parser) sqlParser).getInterpreter().setPredictionMode(PredictionMode.SLL);
            return (ParseASTNode) sqlParser.parse();
        } catch (final ParseCancellationException ex) {
            ((Parser) sqlParser).reset();
            ((Parser) sqlParser).setErrorHandler(new DefaultErrorStrategy());
            ((Parser) sqlParser).getInterpreter().setPredictionMode(PredictionMode.LL);
            return (ParseASTNode) sqlParser.parse();
        }
    }
}

