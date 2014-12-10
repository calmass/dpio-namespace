package org.digitalpio.namespace.dao;

import com.google.inject.ImplementedBy;
import org.digitalpio.commons.dao.converter.Convertable;
import org.digitalpio.core.orgs.Namespaceable;
import org.digitalpio.namespace.entity.NamespaceEntity;


@ImplementedBy(NamespaceConverterImpl.class)
@javax.annotation.concurrent.NotThreadSafe
interface NamespaceConverterSrvc
        extends Convertable<NamespaceEntity, Namespaceable>
{    

}
