package org.apache.shardingsphere.sql.parser.sql.value;

import org.apache.shardingsphere.sql.parser.api.ASTNode;

/**
 * @ClassName s
 * @Description
 * @Author wzj
 * @Date 2021/7/14 16:51
 **/

public interface ValueASTNode<T> extends ASTNode {

    /**
     * Get value.
     *
     * @return value
     */
    T getValue();
}

