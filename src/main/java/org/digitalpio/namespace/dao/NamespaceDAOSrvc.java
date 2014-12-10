package org.digitalpio.namespace.dao;

import com.google.inject.ImplementedBy;
import java.util.UUID;
import org.digitalpio.commons.dao.LocatorDAOable;
import org.digitalpio.core.orgs.Namespaceable;
import org.digitalpio.namespace.entity.NamespaceEntity;


@ImplementedBy(NamespaceDAOImpl.class)
@javax.annotation.concurrent.NotThreadSafe
public interface NamespaceDAOSrvc
        extends LocatorDAOable<NamespaceEntity, Namespaceable, UUID>
{    
    
}
