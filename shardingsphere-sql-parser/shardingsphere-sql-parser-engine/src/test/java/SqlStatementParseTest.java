import com.google.common.base.Preconditions;
import org.antlr.v4.runtime.tree.ParseTree;
import org.apache.shardingsphere.spi.NewInstanceServiceLoader;
import org.apache.shardingsphere.sql.parser.SQLParserEngine;
import org.apache.shardingsphere.sql.parser.SQLParserEngineFactory;
import org.apache.shardingsphere.sql.parser.core.parser.SQLParserExecutor;
import org.apache.shardingsphere.sql.parser.core.visitor.ParseTreeVisitorFactory;
import org.apache.shardingsphere.sql.parser.core.visitor.VisitorRule;
import org.apache.shardingsphere.sql.parser.spi.SQLParserConfiguration;
import org.apache.shardingsphere.sql.parser.sql.statement.SQLStatement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @ClassName SqlStatementParseTest
 * @Description
 * @Author wzj
 * @Date 2021/7/14 15:04
 **/

public class SqlStatementParseTest {

    public static void main(String args[]) {

        SQLParserEngine engine = SQLParserEngineFactory.getSQLParserEngine("MySQL");

        SQLParserEngine engine2 = new SQLParserEngine("");

        SQLStatement statement = engine.parse("select id,name from stu where id=?",false);

        //ExecutionContext result = new ExecutionContext(routeContext.getSqlStatementContext());

            /*SchemaMetaData schemaMetaData = null;
            SQLStatementContext sqlStatementContext = null;
            String sql = "select id,name from stu where id=?";
            List<Object> parameters = new ArrayList<>();
*/
        //SQLRewriteContext sqlRewriteContext = new SQLRewriteContext(schemaMetaData, sqlStatementContext, sql, parameters);

        //SQLRewriteResult sqlRewriteResult = new SQLRewriteResult(new DefaultSQLBuilder(sqlRewriteContext).toSQL(), sqlRewriteContext.getParameterBuilder().getParameters());



        // todo
        //System.out.println(sqlRewriteResult.getSql());

        String sql = "select id,name from stu where id=?";
        NewInstanceServiceLoader.register(SQLParserConfiguration.class);
        ParseTree parseTree = (new SQLParserExecutor("MySQL", sql)).execute().getRootNode();
        SQLStatement result = (SQLStatement) ParseTreeVisitorFactory.newInstance("MySQL", VisitorRule.valueOf(parseTree.getClass())).visit(parseTree);
        System.out.println(result);
    }

    /*private SQLUnit getSQLUnit(String sql) {
        SQLParserEngine engine = SQLParserEngineFactory.getSQLParserEngine("MySQL");

        EncryptRule encryptRule = getEncryptRule();
        PreparedQueryPrepareEngine prepareEngine = new PreparedQueryPrepareEngine(Collections.singletonList(encryptRule), this.runtimeContext.getProperties(), this.runtimeContext.getMetaData(), engine);
        ExecutionContext executionContext = prepareEngine.prepare(sql, this.getParameters());

        this.sqlStatementContext = executionContext.getSqlStatementContext();
        //return ((ExecutionUnit)executionContext.getExecutionUnits().iterator().next()).getSqlUnit();



    }*/

    /*private EncryptRule getEncryptRule(){
        EncryptRuleConfiguration configuration = new EncryptRuleConfiguration();
        //todo configuration的初始化

        EncryptRule encryptRule = new EncryptRule(configuration);
        return  encryptRule;

    }*/
}
