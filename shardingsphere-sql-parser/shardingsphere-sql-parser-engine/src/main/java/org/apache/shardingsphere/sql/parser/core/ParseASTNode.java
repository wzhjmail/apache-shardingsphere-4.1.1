package org.apache.shardingsphere.sql.parser.core;

import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.tree.ParseTree;
import org.apache.shardingsphere.sql.parser.api.ASTNode;

/**
 * @ClassName ParseASTNode
 * @Description
 * @Author wzj
 * @Date 2021/7/14 10:55
 **/

@RequiredArgsConstructor
public final class ParseASTNode implements ASTNode {

    private final ParseTree parseTree;

    /**
     * Get root node.
     *
     * @return root node
     */
    public ParseTree getRootNode() {
        return parseTree.getChild(0);
    }
}

