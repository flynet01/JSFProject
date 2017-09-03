package org.jboss.as.quickstarts.kitchensink.data;

import org.infinispan.Cache;
    import org.infinispan.manager.EmbeddedCacheManager;
import org.jboss.as.quickstarts.kitchensink.cache.SimpleListener;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.util.Collections;

@ApplicationScoped
public class InfinispanState {

    @Resource(lookup = "java:jboss/infinispan/container/web")
    private EmbeddedCacheManager container;

    @Resource(lookup = "java:jboss/infinispan/container/server")
    private EmbeddedCacheManager containerb;

    @Resource(lookup = "java:jboss/infinispan/container/ejb")
    private EmbeddedCacheManager containerc;

    private Cache<String, String> cache;

    @Inject
    SimpleListener listener;

    @PostConstruct
    public void initCache() {

        this.cache = container.getCache("jboss-as-kitchensink.war");
        System.err.println("Got cache " + cache.getName());
        cache.addListener(listener);

    }

    @Produces
    public Cache<String, String> getCache() {
        return cache;
    }

    @Produces
    @Named
    public String getCacheString() {
        System.err.println("Get cache content");
        StringBuffer sb = new StringBuffer();
        System.err.println("cache count : " + cache.size());
        cache.entrySet().stream().forEach(cs -> sb.append("key: " + cs.getKey() + ", value: " + cs.getValue() + "<br />"));
        return sb.toString();
    }

    @Produces
    @Named
    public String getCacheSize() {
        return String.valueOf(container.getCache().size() + " " + containerb.getCache().size() + " " + containerc.getCache().size());
    }

    public void setCache(Cache<String, String> cache) {
        this.cache = cache;
    }

    @Produces
    @Named
    public String getSessionString(){
        System.err.println("Get session");
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        StringBuffer sb = new StringBuffer();
        Collections.list(session.getAttributeNames()).forEach(n -> sb.append("key: " + n + ", value: " + session.getAttribute(n).toString() + "<br />"));
        return sb.toString();
    }

}
