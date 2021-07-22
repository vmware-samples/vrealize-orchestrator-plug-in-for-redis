/*
 * Copyright 2021 VMware, Inc.
 * SPDX-License-Identifier: GPL-3.0-only
 */

package com.vmware.o11n.plugin.redis.relater;

import com.vmware.o11n.plugin.redis.config.ConnectionRepository;
import com.vmware.o11n.plugin.redis.model.Connection;
import com.vmware.o11n.plugin.redis.model.Database;
import com.vmware.o11n.sdk.modeldriven.ObjectRelater;
import com.vmware.o11n.sdk.modeldriven.PluginContext;
import com.vmware.o11n.sdk.modeldriven.Sid;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;

public class ConnectionHasDatabases implements ObjectRelater<Database> {
    @Autowired
    private ConnectionRepository connectionRepository;

    @Override
    public List<Database> findChildren(PluginContext ctx, String relation, String parentType, Sid parentId) {
        Connection connection = connectionRepository.findLiveConnection(parentId);
        if (connection != null) {
            return connection.getDatabases();
        }
        return Collections.emptyList();
    }
}