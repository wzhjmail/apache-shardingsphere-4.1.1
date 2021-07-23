import com.google.common.base.Preconditions;
import org.antlr.v4.runtime.tree.ParseTree;
import org.apache.shardingsphere.spi.NewInstanceServiceLoader;
import org.apache.shardingsphere.sql.parser.SQLParserEngine;
import org.apache.shardingsphere.sql.parser.SQLParserEngineFactory;
import org.apache.shardingsphere.sql.parser.binder.SQLStatementContextFactory;
import org.apache.shardingsphere.sql.parser.binder.statement.SQLStatementContext;
import org.apache.shardingsphere.sql.parser.core.parser.SQLParserExecutor;
import org.apache.shardingsphere.sql.parser.core.visitor.ParseTreeVisitorFactory;
import org.apache.shardingsphere.sql.parser.core.visitor.VisitorRule;
import org.apache.shardingsphere.sql.parser.mysql.MySQLParserConfiguration;
import org.apache.shardingsphere.sql.parser.spi.SQLParserConfiguration;
import org.apache.shardingsphere.sql.parser.sql.statement.SQLStatement;
import org.apache.shardingsphere.underlying.rewrite.context.SQLRewriteContext;
import org.apache.shardingsphere.underlying.rewrite.engine.SQLRewriteEngine;
import org.apache.shardingsphere.underlying.rewrite.engine.SQLRewriteResult;
import org.apache.shardingsphere.underlying.route.context.RouteContext;
import org.apache.shardingsphere.underlying.route.context.RouteResult;

import java.util.Collections;

/**
 * @ClassName SqlStatementParseTest
 * @Description
 * @Author wzj
 * @Date 2021/7/14 15:04
 **/

public class SqlStatementParseTest {
    public static void main(String args[]) {

        String sql = "select id,name from stu where id=?";
        //NewInstanceServiceLoader.register(MySQLParserConfiguration.class);
        ParseTree parseTree = (new SQLParserExecutor("MySQL", sql)).execute(MySQLParserConfiguration.class).getRootNode();
        SQLStatement result = (SQLStatement) ParseTreeVisitorFactory.newInstance(MySQLParserConfiguration.class, VisitorRule.valueOf(parseTree.getClass())).visit(parseTree);
        System.out.println(result);
    }

    private String getRes(String sql) {
        SQLStatementContext sqlStatementContext = SQLStatementContextFactory.newInstance(this.metaData.getSchema(), sql, parameters, sqlStatement);
        RouteContext routeContext = new RouteContext(sqlStatementContext, parameters, new RouteResult());

        this.registerRewriteDecorator();
        SQLRewriteContext sqlRewriteContext = this.rewriter.createSQLRewriteContext(sql, parameters, routeContext.getSqlStatementContext(), routeContext);

        routeContext.getRouteResult().getRouteUnits().isEmpty() ? this.rewrite(sqlRewriteContext) : this.rewrite(routeContext, sqlRewriteContext);

        SQLRewriteResult sqlRewriteResult = (new SQLRewriteEngine()).rewrite(sqlRewriteContext);

        return sqlRewriteResult.getSql();
    }

}
