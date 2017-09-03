package org.jboss.as.quickstarts.kitchensink.cache;

import org.infinispan.notifications.Listener;
import org.infinispan.notifications.cachelistener.annotation.CacheEntryCreated;
import org.infinispan.notifications.cachelistener.annotation.CacheEntryRemoved;
import org.infinispan.notifications.cachelistener.event.CacheEntryCreatedEvent;
import org.infinispan.notifications.cachelistener.event.CacheEntryRemovedEvent;
import org.infinispan.notifications.cachemanagerlistener.annotation.CacheStarted;
import org.infinispan.notifications.cachemanagerlistener.annotation.CacheStopped;
import org.infinispan.notifications.cachemanagerlistener.event.CacheStartedEvent;
import org.infinispan.notifications.cachemanagerlistener.event.CacheStoppedEvent;

@Listener(clustered = true)
public class SimpleListener {

    @CacheEntryCreated
    public void addKey(CacheEntryCreatedEvent event) {
        System.err.println("New entry " + event.getKey() + " created in the cache with value " + event.getValue());
    }

    @CacheEntryRemoved
    public void removeKey(CacheEntryRemovedEvent event) {
        System.err.println("Entry " + event.getKey() + " removed from the cache");
    }

    @CacheStarted
    public void cacheStarted(CacheStartedEvent event) {
        System.err.println("Cache Started");
    }

    @CacheStopped
    public void cacheStopped(CacheStoppedEvent event) {
        System.err.println("Cache Stopped");
    }
}