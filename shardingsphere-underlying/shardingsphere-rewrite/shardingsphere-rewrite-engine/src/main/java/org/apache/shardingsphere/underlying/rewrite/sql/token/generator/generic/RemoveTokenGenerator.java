package org.apache.shardingsphere.underlying.rewrite.sql.token.generator.generic;

import com.google.common.base.Preconditions;
import org.apache.shardingsphere.sql.parser.binder.statement.SQLStatementContext;
import org.apache.shardingsphere.sql.parser.sql.segment.generic.RemoveAvailable;
import org.apache.shardingsphere.sql.parser.sql.statement.dal.dialect.mysql.ShowColumnsStatement;
import org.apache.shardingsphere.sql.parser.sql.statement.dal.dialect.mysql.ShowTableStatusStatement;
import org.apache.shardingsphere.sql.parser.sql.statement.dal.dialect.mysql.ShowTablesStatement;
import org.apache.shardingsphere.underlying.rewrite.sql.token.generator.CollectionSQLTokenGenerator;
import org.apache.shardingsphere.underlying.rewrite.sql.token.pojo.generic.RemoveToken;

import java.util.Collection;
import java.util.Collections;

/**
 * @ClassName RemoveTokenGenerator
 * @Description
 * @Author wzj
 * @Date 2021/7/15 15:48
 **/

public final class RemoveTokenGenerator implements CollectionSQLTokenGenerator {

    @Override
    public boolean isGenerateSQLToken(final SQLStatementContext sqlStatementContext) {
        if (sqlStatementContext.getSqlStatement() instanceof ShowTablesStatement) {
            return ((ShowTablesStatement) sqlStatementContext.getSqlStatement()).getFromSchema().isPresent();
        }
        if (sqlStatementContext.getSqlStatement() instanceof ShowTableStatusStatement) {
            return ((ShowTableStatusStatement) sqlStatementContext.getSqlStatement()).getFromSchema().isPresent();
        }
        if (sqlStatementContext.getSqlStatement() instanceof ShowColumnsStatement) {
            return ((ShowColumnsStatement) sqlStatementContext.getSqlStatement()).getFromSchema().isPresent();
        }
        return false;
    }

    @Override
    public Collection<RemoveToken> generateSQLTokens(final SQLStatementContext sqlStatementContext) {
        if (sqlStatementContext.getSqlStatement() instanceof ShowTablesStatement) {
            Preconditions.checkState(((ShowTablesStatement) sqlStatementContext.getSqlStatement()).getFromSchema().isPresent());
            RemoveAvailable removeAvailable = ((ShowTablesStatement) sqlStatementContext.getSqlStatement()).getFromSchema().get();
            return Collections.singletonList(new RemoveToken(removeAvailable.getStartIndex(), removeAvailable.getStopIndex()));
        }
        if (sqlStatementContext.getSqlStatement() instanceof ShowTableStatusStatement) {
            Preconditions.checkState(((ShowTableStatusStatement) sqlStatementContext.getSqlStatement()).getFromSchema().isPresent());
            RemoveAvailable removeAvailable = ((ShowTableStatusStatement) sqlStatementContext.getSqlStatement()).getFromSchema().get();
            return Collections.singletonList(new RemoveToken(removeAvailable.getStartIndex(), removeAvailable.getStopIndex()));
        }
        if (sqlStatementContext.getSqlStatement() instanceof ShowColumnsStatement) {
            Preconditions.checkState(((ShowColumnsStatement) sqlStatementContext.getSqlStatement()).getFromSchema().isPresent());
            RemoveAvailable removeAvailable = ((ShowColumnsStatement) sqlStatementContext.getSqlStatement()).getFromSchema().get();
            return Collections.singletonList(new RemoveToken(removeAvailable.getStartIndex(), removeAvailable.getStopIndex()));
        }
        return Collections.emptyList();
    }
}

