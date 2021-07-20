package org.apache.shardingsphere.sql.parser.spi;

import org.apache.shardingsphere.sql.parser.api.lexer.SQLLexer;
import org.apache.shardingsphere.sql.parser.api.parser.SQLParser;
import org.apache.shardingsphere.sql.parser.api.visitor.SQLVisitorFacade;

/**
 * @ClassName Sq
 * @Description
 * @Author wzj
 * @Date 2021/7/14 11:09
 **/

public interface SQLParserConfiguration {

    /**
     * Get name of database type.
     *
     * @return name of database type
     */
    String getDatabaseTypeName();

    /**
     * Get SQL lexer class type.
     *
     * @return SQL lexer class type
     */
    Class<? extends SQLLexer> getLexerClass();

    /**
     * Get SQL parser class type.
     *
     * @return SQL parser class type
     */
    Class<? extends SQLParser> getParserClass();

    /**
     * Get SQL visitor facade class.
     *
     * @return SQL visitor facade class
     */
    Class<? extends SQLVisitorFacade> getVisitorFacadeClass();
}

