/*
 * Copyright 2021 VMware, Inc.
 * SPDX-License-Identifier: GPL-3.0-only
 */

package com.vmware.o11n.plugin.redis;

import com.google.inject.AbstractModule;
import com.vmware.o11n.sdk.modeldrivengen.mapping.AbstractMapping;
import com.vmware.o11n.sdk.modeldrivengen.model.Plugin;

import java.util.Collections;

public class CustomModule extends AbstractModule {
    private final Plugin plugin;

    /**
     * Binds the CustomMapping class to the plugin instance
     */
    @Override
    protected void configure() {
        bind(AbstractMapping.class).toInstance(new CustomMapping());
        bind(Plugin.class).toInstance(plugin);
    }

    public CustomModule() {
        this.plugin = new Plugin();

        plugin.setApiPrefix("Redis");
        plugin.setIcon("default-32x32.png");
        plugin.setDescription("Redis");
        plugin.setDisplayName("Redis");
        plugin.setName("Redis");
        plugin.setPackages(Collections.singletonList("o11nplugin-redis-package-${project.version}.package"));
        plugin.setAdaptorClassName(com.vmware.o11n.plugin.redis.RedisPluginAdaptor.class);
        plugin.setClassloaderParent("common");
    }
}