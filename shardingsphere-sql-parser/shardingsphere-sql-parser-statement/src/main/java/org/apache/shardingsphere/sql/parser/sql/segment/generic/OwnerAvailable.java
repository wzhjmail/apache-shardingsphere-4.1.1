package org.apache.shardingsphere.sql.parser.sql.segment.generic;

import org.apache.shardingsphere.sql.parser.sql.segment.SQLSegment;

import java.util.Optional;

/**
 * @ClassName OwnerAvailable
 * @Description
 * @Author wzj
 * @Date 2021/7/14 17:03
 **/

public interface OwnerAvailable extends SQLSegment {

    /**
     * Get owner.
     *
     * @return owner
     */
    Optional<OwnerSegment> getOwner();

    /**
     * Set owner.
     *
     * @param owner owner
     */
    void setOwner(OwnerSegment owner);
}

