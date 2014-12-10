package org.digitalpio.namespace.crud;

import java.util.UUID;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import org.digitalpio.commons.crud.LocatorAbstractCacheSrvc;
import org.digitalpio.commons.dao.LocatorDAOable;
import org.digitalpio.commons.logging.Log;
import org.digitalpio.core.orgs.Namespaceable;
import org.digitalpio.namespace.dao.NamespaceDAOSrvc;
import org.digitalpio.namespace.entity.NamespaceEntity;
import org.slf4j.Logger;


@Singleton
@javax.annotation.concurrent.ThreadSafe
class NamespaceCacheImpl
        extends LocatorAbstractCacheSrvc<Namespaceable, UUID, NamespaceEntity>
        implements NamespaceCacheSrvc
{
    @Log 
    private static org.slf4j.Logger log;
    
    private static final int EXPIRY = 60 * 60 * 24 * 3; // 3 days
        
    private final Provider<NamespaceDAOSrvc> daoPrvdr;
    
    @Inject
    NamespaceCacheImpl(final Provider<NamespaceDAOSrvc> dp) {
        this.daoPrvdr = dp;
    }

    @Override
    protected LocatorDAOable<NamespaceEntity, Namespaceable, UUID> dao()
    {
        return daoPrvdr.get();
    }

    @Override
    protected int expirySecs()
    {
        return EXPIRY;
    }

    @Override
    protected Logger log()
    {
        return log;
    }
}