package org.digitalpio.namespace.crud;

import java.util.UUID;
import javax.inject.Inject;
import javax.inject.Provider;
import org.digitalpio.commons.crud.LocatorAbstractCrudSrvc;
import org.digitalpio.commons.crud.LocatorCacheSrvc;
import org.digitalpio.commons.dao.LocatorDAOable;
import org.digitalpio.commons.logging.Log;
import org.digitalpio.core.orgs.Namespaceable;
import org.digitalpio.namespace.dao.NamespaceDAOSrvc;
import org.digitalpio.namespace.entity.NamespaceEntity;


@javax.annotation.concurrent.NotThreadSafe
public class NamespaceCrudImpl 
        extends LocatorAbstractCrudSrvc<Namespaceable, UUID, NamespaceEntity>
        implements NamespaceCrudSrvc
{    
    @Log
    private static org.slf4j.Logger log;
        
    private final Provider<NamespaceCacheSrvc> cachePrvdr;
    private final Provider<NamespaceDAOSrvc> daoPrvdr;
    
    @Inject
    NamespaceCrudImpl(final Provider<NamespaceCacheSrvc> cp,
                   final Provider<NamespaceDAOSrvc> dp) {
        this.cachePrvdr = cp;
        this.daoPrvdr = dp;
    }

    @Override
    protected LocatorDAOable<NamespaceEntity, Namespaceable, UUID> dao()
    {
        return daoPrvdr.get();
    }

    @Override
    protected LocatorCacheSrvc<Namespaceable, UUID, NamespaceEntity> cache()
    {
        return cachePrvdr.get();
    }
}