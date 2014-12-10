package org.digitalpio.namespace.crud;

import com.google.inject.ImplementedBy;
import java.util.UUID;
import org.digitalpio.commons.crud.LocatorCrudSrvc;
import org.digitalpio.core.orgs.Namespaceable;
import org.digitalpio.namespace.entity.NamespaceEntity;


@ImplementedBy(NamespaceCrudImpl.class)
@javax.annotation.concurrent.NotThreadSafe
public interface NamespaceCrudSrvc
        extends LocatorCrudSrvc<Namespaceable, UUID, NamespaceEntity>
{
    
}