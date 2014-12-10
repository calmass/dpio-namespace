package org.digitalpio.namespace.dao;

import java.util.UUID;
import javax.inject.Inject;
import org.digitalpio.commons.annotations.CheckValid;
import org.digitalpio.commons.dao.converter.AbstractConverter;
import org.digitalpio.commons.error.DataAccessException;
import org.digitalpio.commons.validation.SimpleValSrvc;
import org.digitalpio.commons.validation.Validation_Type;
import org.digitalpio.core.orgs.Namespaceable;
import org.digitalpio.namespace.Namespace;
import org.digitalpio.namespace.entity.NamespaceEntity;


@javax.annotation.concurrent.NotThreadSafe
class NamespaceConverterImpl
        extends AbstractConverter<NamespaceEntity, Namespaceable>
        implements NamespaceConverterSrvc
{
    private final SimpleValSrvc valSrvc;

    @Inject
    NamespaceConverterImpl(final SimpleValSrvc vs)
    {
        this.valSrvc = vs;
    }
    
    @Override
    @CheckValid
    public NamespaceEntity to(final Namespaceable impl)
            throws DataAccessException
    {
        final NamespaceEntity entity = new NamespaceEntity();
        entity.setUuid(impl.getUuid().toString());
        setUpdateableFields(entity, impl);
        return entity;
    }
    
    @Override
    @CheckValid
    public void setUpdateableFields(final NamespaceEntity entity, final Namespaceable impl) 
            throws DataAccessException
    {
        entity.setLocator(impl.getLocator());
        entity.setDomain(impl.getDomain());
        entity.setAbbr(impl.getAbbr());
        entity.setFull(impl.getFull());
        entity.setPrivacy(impl.getPrivacy());
    }

    @Override
    @CheckValid
    public Namespaceable from(final NamespaceEntity entity)
            throws DataAccessException
    {        
        final String full = valSrvc.validateFromDB(
                Validation_Type.TITLE, entity.getFull());
        final String abbr = valSrvc.validateFromDB(
                Validation_Type.TITLE, entity.getAbbr());
        final String domain = valSrvc.validateFromDB(
                Validation_Type.DOMAIN, entity.getDomain());
        final UUID id = valSrvc.validateUuidFromDB(entity.getUuid());

        return Namespace.builder()
                .domain(domain)
                .uuid(id)
                .full(full)
                .abbr(abbr)
                .privacy(entity.getPrivacy())
                .build();
    }

}
