package org.apache.shardingsphere.sql.parser.sql.segment;

import org.apache.shardingsphere.sql.parser.api.ASTNode;

/**
 * @ClassName SQLSegment
 * @Description
 * @Author wzj
 * @Date 2021/7/14 16:47
 **/

public interface SQLSegment extends ASTNode {

    /**
     * Get start index.
     *
     * @return start index
     */
    int getStartIndex();

    /**
     * Get stop index.
     *
     * @return stop index
     */
    int getStopIndex();
}
