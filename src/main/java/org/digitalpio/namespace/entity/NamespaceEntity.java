package org.digitalpio.namespace.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.digitalpio.commons.dao.LocatorAbstractEntity;
import org.digitalpio.commons.misc.Privacy;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.Fields;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;
import org.hibernate.search.bridge.builtin.EnumBridge;


@Entity
@Table(name = "namespaces")
@Indexed
@javax.annotation.concurrent.NotThreadSafe
@lombok.Data
@lombok.EqualsAndHashCode(callSuper=true, of = {"domain"})
@NamedQueries({
    @NamedQuery(
            name = "NamespaceEntity.domain",
            query = "select e from NamespaceEntity e where e.domain = :domain"
    )
})
public class NamespaceEntity extends LocatorAbstractEntity
{    
    @NotNull
    @Fields({
        @Field(name = "full_standard_search", 
               index = Index.YES, analyze = Analyze.YES, store = Store.NO,
               analyzer = @Analyzer(definition = "dpiostandard")),
        @Field(name = "full_ngram_search", 
               index = Index.YES, analyze = Analyze.YES, store = Store.NO,
               analyzer = @Analyzer(definition = "dpiongram")),
        @Field(name = "full_edgengram_search", 
               index = Index.YES, analyze = Analyze.YES, store = Store.NO,
               analyzer = @Analyzer(definition = "dpioedgengram"))
    })    
    @Column(name = "full_name", nullable = false, updatable = true, unique=true)
    private String full;
    
    @NotNull
    @Fields({
        @Field(name = "abbr_standard_search", 
               index = Index.YES, analyze = Analyze.YES, store = Store.NO,
               analyzer = @Analyzer(definition = "dpiostandard")),
        @Field(name = "abbr_ngram_search", 
               index = Index.YES, analyze = Analyze.YES, store = Store.NO,
               analyzer = @Analyzer(definition = "dpiongram")),
        @Field(name = "abbr_edgengram_search", 
               index = Index.YES, analyze = Analyze.YES, store = Store.NO,
               analyzer = @Analyzer(definition = "dpioedgengram"))
    })    
    @Column(name = "abbr_name", nullable = false, updatable = true)
    private String abbr;
    
    @NotNull
    @Fields({
        @Field(name = "domain_standard_search", 
               index = Index.YES, analyze = Analyze.YES, store = Store.NO,
               analyzer = @Analyzer(definition = "dpiostandard")),
        @Field(name = "domain_ngram_search", 
               index = Index.YES, analyze = Analyze.YES, store = Store.NO,
               analyzer = @Analyzer(definition = "dpiongram")),
        @Field(name = "domain_edgengram_search", 
               index = Index.YES, analyze = Analyze.YES, store = Store.NO,
               analyzer = @Analyzer(definition = "dpioedgengram"))
    })    
    @Pattern(regexp="^[a-z]{1,64}$")
    @Column(name = "domain_namespace", nullable = false, updatable = true, unique=true)
    private String domain;
        
    @Field(
            bridge=@FieldBridge(impl=EnumBridge.class), 
            index = Index.YES, analyze = Analyze.NO, store = Store.NO
    )
    @Column(name="privacy", updatable=true, unique=false, nullable=false)
    @Enumerated(EnumType.STRING)
    private Privacy privacy;
}
