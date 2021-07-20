package org.apache.shardingsphere.underlying.rewrite.sql.impl;

import org.apache.shardingsphere.underlying.rewrite.context.SQLRewriteContext;
import org.apache.shardingsphere.underlying.rewrite.sql.token.pojo.SQLToken;

/**
 * @ClassName DefaultSQLBuilder
 * @Description
 * @Author wzj
 * @Date 2021/7/15 15:09
 **/

public final class DefaultSQLBuilder extends AbstractSQLBuilder {

    public DefaultSQLBuilder(final SQLRewriteContext context) {
        super(context);
    }

    @Override
    protected String getSQLTokenText(final SQLToken sqlToken) {
        return sqlToken.toString();
    }
}
