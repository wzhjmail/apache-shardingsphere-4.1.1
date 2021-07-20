package org.apache.shardingsphere.sql.parser.api.parser;

import org.apache.shardingsphere.sql.parser.api.ASTNode;

/**
 * @ClassName SQLParser
 * @Description
 * @Author wzj
 * @Date 2021/7/14 10:59
 **/

public interface SQLParser {

    /**
     * Parse SQL.
     *
     * @return AST node
     */
    ASTNode parse();
}
