/*
 * Copyright 2021 VMware, Inc.
 * SPDX-License-Identifier: GPL-3.0-only
 */

package com.vmware.o11n.plugin.redis;

import com.vmware.o11n.plugin.redis.finder.ConnectionFinder;
import com.vmware.o11n.plugin.redis.finder.DatabaseFinder;
import com.vmware.o11n.plugin.redis.model.Connection;
import com.vmware.o11n.plugin.redis.model.Database;
import com.vmware.o11n.plugin.redis.relater.ConnectionHasDatabases;
import com.vmware.o11n.plugin.redis.relater.RootHasConnections;
import com.vmware.o11n.plugin.redis.singleton.ConnectionManager;
import com.vmware.o11n.sdk.modeldrivengen.mapping.AbstractMapping;
import redis.clients.jedis.BitPosParams;
import redis.clients.jedis.GeoCoordinate;
import redis.clients.jedis.GeoUnit;
import redis.clients.jedis.ListPosition;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;
import redis.clients.jedis.SortingParams;
import redis.clients.jedis.Tuple;
import redis.clients.jedis.params.ZAddParams;
import redis.clients.jedis.params.ZIncrByParams;

public class CustomMapping extends AbstractMapping {
    @Override
    public void define() {
        convertWellKnownTypes();

        singleton(ConnectionManager.class);

        wrap(Connection.class)
                .andFind()
                .using(ConnectionFinder.class)
                .withIcon("connection.png");
        wrap(Database.class)
                .andFind()
                .using(DatabaseFinder.class)
                .withIcon("database.png");
        wrap(ZAddParams.class);
        wrap(ZIncrByParams.class);
        wrap(Tuple.class).hiding(Tuple::getBinaryElement);
        wrap(SortingParams.class).hiding(sortingParams -> sortingParams.get(new byte[0][0]));
        wrap(BitPosParams.class);
        wrap(ScanParams.class);
        wrap(ScanResult.class);
        wrap(GeoUnit.class);
        wrap(GeoCoordinate.class);

        enumerate(ListPosition.class).as("ListPosition");

        relateRoot()
                .to(Connection.class)
                .using(RootHasConnections.class)
                .as("connections");
        relate(Connection.class)
                .to(Database.class)
                .using(ConnectionHasDatabases.class)
                .as("databases");
    }
}