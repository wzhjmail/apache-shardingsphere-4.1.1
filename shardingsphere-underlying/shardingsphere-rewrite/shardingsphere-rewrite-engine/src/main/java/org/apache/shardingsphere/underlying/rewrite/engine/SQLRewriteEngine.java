package org.apache.shardingsphere.underlying.rewrite.engine;

import org.apache.shardingsphere.underlying.rewrite.context.SQLRewriteContext;
import org.apache.shardingsphere.underlying.rewrite.sql.impl.DefaultSQLBuilder;

/**
 * @ClassName SQLRewriteEngine
 * @Description
 * @Author wzj
 * @Date 2021/7/14 16:16
 **/

public final class SQLRewriteEngine {

    /**
     * Rewrite SQL and parameters.
     *
     * @param sqlRewriteContext SQL rewrite context
     * @return SQL rewrite result
     */
    public SQLRewriteResult rewrite(final SQLRewriteContext sqlRewriteContext) {
        return new SQLRewriteResult(new DefaultSQLBuilder(sqlRewriteContext).toSQL(), sqlRewriteContext.getParameterBuilder().getParameters());
    }
}

