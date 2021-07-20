package org.apache.shardingsphere.underlying.rewrite.sql.token.generator.aware;

import java.util.List;

/**
 * @ClassName ParametersAware
 * @Description
 * @Author wzj
 * @Date 2021/7/15 15:35
 **/

public interface ParametersAware {

    /**
     * Set parameters.
     *
     * @param parameters parameters
     */
    void setParameters(List<Object> parameters);
}

