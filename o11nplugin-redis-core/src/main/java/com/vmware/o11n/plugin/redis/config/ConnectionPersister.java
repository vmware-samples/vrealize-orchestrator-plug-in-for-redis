/*
 * Copyright 2021 VMware, Inc.
 * SPDX-License-Identifier: GPL-3.0-only
 */

package com.vmware.o11n.plugin.redis.config;

import com.vmware.o11n.plugin.redis.model.ConnectionInfo;
import com.vmware.o11n.sdk.modeldriven.Sid;

import java.util.List;

public interface ConnectionPersister {
    /**
     * Returns a collection of all stored configurations (resources under a
     * folder with the plug-in name)
     */
    List<ConnectionInfo> findAll();

    /**
     * Returns a collection by its ID or null if not found
     */
    ConnectionInfo findById(Sid id);

    /**
     * Stores a connection info or updates it if already available. The
     * persister checks the availability of a connection by its ID
     */
    ConnectionInfo save(ConnectionInfo connection);

    /**
     * Deletes a connection info. The persister will use the ID of the
     * connection
     */
    void delete(ConnectionInfo connectionInfo);

    /**
     * Allows us to subscribe to the events of the persister. For example, if a
     * connection is deleted, the persister will trigger an event, notifying all
     * subscribers. This is an implementation of the observer pattern.
     */
    void addChangeListener(ConfigurationChangeListener listener);

    /**
     * Forces the persister to read all the configurations and trigger the
     * events. This method is invoked when the plug-in is loaded on server
     * start-up.
     */
    void load();
}
