package org.digitalpio.namespace.dao;

import java.util.UUID;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.digitalpio.commons.dao.LocatorAbstractDAO;
import org.digitalpio.commons.dao.converter.Convertable;
import org.digitalpio.core.orgs.Namespaceable;
import org.digitalpio.namespace.entity.NamespaceEntity;


@javax.annotation.concurrent.NotThreadSafe
public class NamespaceDAOImpl
        extends LocatorAbstractDAO<NamespaceEntity, Namespaceable, UUID>
        implements NamespaceDAOSrvc
{    
    @org.digitalpio.commons.logging.Log
    private static org.slf4j.Logger log;

    private final Provider<EntityManager> emPrvdr;
    private final NamespaceConverterSrvc converterSrvc;

    @Inject
    NamespaceDAOImpl(final Provider<EntityManager> emp,
            final NamespaceConverterSrvc ics)
    {
        this.emPrvdr = emp;
        this.converterSrvc = ics;
    }

    @Override
    public Convertable<NamespaceEntity, Namespaceable> getConverterSrvc()
    {
        return converterSrvc;
    }

    @Override
    public EntityManager getEntityManager()
    {
        return emPrvdr.get();
    }

    @Override
    public Class<NamespaceEntity> getEntityClass()
    {
        return NamespaceEntity.class;
    }
    
    @Override
    public TypedQuery<NamespaceEntity> buildCustomQuery(
            final Namespaceable namespace, final EntityManager em)
    {
        return em.createNamedQuery("NamespaceEntity.domain", NamespaceEntity.class)
                .setParameter("domain", namespace.getDomain());
    }
    
    @Override
    public TypedQuery<NamespaceEntity> buildIdQuery(
            final UUID uuid, final EntityManager em)
    {
        return buildUuidQuery(uuid, em);
    }
      
//    @Override
//    @CheckValid
//    public Optional<Namespaceable> find(final UUID id)
//            throws DataAccessException
//    {
//        final Optional<NamespaceEntity> entity = findEntity(id, getEntityManager());
//        if (entity.isPresent()) {
//            return Optional.of(getConverterSrvc().from(entity.get()));
//        }
//        return Optional.absent();        
//    }        
}