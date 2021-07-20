package org.apache.shardingsphere.sql.parser.sql.value.identifier;

import lombok.Getter;
import org.apache.shardingsphere.sql.parser.sql.constant.QuoteCharacter;
import org.apache.shardingsphere.sql.parser.sql.util.SQLUtil;
import org.apache.shardingsphere.sql.parser.sql.value.ValueASTNode;

/**
 * @ClassName ab
 * @Description
 * @Author wzj
 * @Date 2021/7/14 16:49
 **/

@Getter
public final class IdentifierValue implements ValueASTNode<String> {

    private final String value;

    private final QuoteCharacter quoteCharacter;

    public IdentifierValue(final String text) {
        value = SQLUtil.getExactlyValue(text);
        quoteCharacter = QuoteCharacter.getQuoteCharacter(text);
    }
}

