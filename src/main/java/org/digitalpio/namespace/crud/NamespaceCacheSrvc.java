package org.digitalpio.namespace.crud;

import com.google.inject.ImplementedBy;
import java.util.UUID;
import org.digitalpio.commons.crud.LocatorCacheSrvc;
import org.digitalpio.core.orgs.Namespaceable;
import org.digitalpio.namespace.entity.NamespaceEntity;


@ImplementedBy(NamespaceCacheImpl.class)
@javax.annotation.concurrent.ThreadSafe
interface NamespaceCacheSrvc
        extends LocatorCacheSrvc<Namespaceable, UUID, NamespaceEntity>
{
    
}