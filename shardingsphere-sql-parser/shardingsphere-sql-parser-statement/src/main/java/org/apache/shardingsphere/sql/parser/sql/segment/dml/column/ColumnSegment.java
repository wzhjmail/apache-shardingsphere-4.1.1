package org.apache.shardingsphere.sql.parser.sql.segment.dml.column;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.apache.shardingsphere.sql.parser.sql.segment.SQLSegment;
import org.apache.shardingsphere.sql.parser.sql.segment.dml.predicate.value.PredicateRightValue;
import org.apache.shardingsphere.sql.parser.sql.segment.generic.OwnerAvailable;
import org.apache.shardingsphere.sql.parser.sql.segment.generic.OwnerSegment;
import org.apache.shardingsphere.sql.parser.sql.value.identifier.IdentifierValue;

import java.util.Optional;

/**
 * @ClassName a
 * @Description
 * @Author wzj
 * @Date 2021/7/15 15:21
 **/

@RequiredArgsConstructor
@Getter
@Setter
@ToString
public final class ColumnSegment implements SQLSegment, PredicateRightValue, OwnerAvailable {

    private final int startIndex;

    private final int stopIndex;

    private final IdentifierValue identifier;

    private OwnerSegment owner;

    /**
     * Get qualified name.
     *
     * @return qualified name
     */
    public String getQualifiedName() {
        return null == owner ? identifier.getValue() : owner.getIdentifier().getValue() + "." + identifier.getValue();
    }

    @Override
    public Optional<OwnerSegment> getOwner() {
        return Optional.ofNullable(owner);
    }
}

