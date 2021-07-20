package org.apache.shardingsphere.sql.parser.api.visitor;

import org.apache.shardingsphere.sql.parser.api.visitor.statement.*;

/**
 * @ClassName SQLVisitorFacade
 * @Description
 * @Author wzj
 * @Date 2021/7/14 11:11
 **/

public interface SQLVisitorFacade {

    /**
     * Get DML visitor class.
     *
     * @return DML visitor class
     */
    Class<? extends DMLVisitor> getDMLVisitorClass();

    /**
     * Get DDL visitor class.
     *
     * @return DDL visitor class
     */
    Class<? extends DDLVisitor> getDDLVisitorClass();

    /**
     * Get TCL visitor class.
     *
     * @return TCL visitor class
     */
    Class<? extends TCLVisitor> getTCLVisitorClass();

    /**
     * Get DCL visitor class.
     *
     * @return DCL visitor class
     */
    Class<? extends DCLVisitor> getDCLVisitorClass();

    /**
     * Get DAL visitor class.
     *
     * @return DAL visitor class
     */
    Class<? extends DALVisitor> getDALVisitorClass();

    /**
     * Get RL visitor class.
     *
     * @return RL visitor class
     */
    Class<? extends RLVisitor> getRLVisitorClass();
}

