package org.digitalpio.namespace;

import com.google.common.base.Joiner;
import java.util.UUID;
import javax.annotation.Nonnull;
import lombok.NonNull;
import org.apache.commons.codec.digest.DigestUtils;
import org.digitalpio.commons.misc.Privacy;
import org.digitalpio.core.orgs.Namespaceable;


@javax.annotation.concurrent.Immutable
@lombok.Value
@lombok.experimental.Builder
public final class Namespace implements Namespaceable
{
    @Nonnull @NonNull private final String domain;
    @Nonnull @NonNull private final UUID uuid;
    @Nonnull @NonNull private final String full;
    @Nonnull @NonNull private final String abbr;    
    @Nonnull @NonNull private final Privacy privacy;
    
    @Override
    public String buildSearchString()
    {
        final String[] searches = {
            domain,
            full,
            abbr
        };
        return Joiner.on(" ").join(searches);
    }

    @Override
    public UUID key()
    {
        return getUuid();
    }

    @Override
    public String getLocator()
    {
        return DigestUtils.sha1Hex(getDomain());
    }    

    @Override
    public Privacy getPrivacy()
    {
        return privacy;
    }

    @Override
    public boolean isPrivate()
    {
        return Privacy.PRIVATE.equals(privacy);
    }
}
