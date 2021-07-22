# vRealize Orchestrator Plug-in for Redis

The for vRealize Orchestrator Plug-in for Redis is a plug-in which provides integration capabilities with multiple
[Redis](http://redis.io/) instances.

Plug-in features:

* Operations with hashes, lists, sets, sorted sets, geolocations
* Operations for scripting with Lua
* Cluster configurations are currently not supported

### Plugin download
[o11nplugin-redis.vmoapp](https://github.com/vmware-samples/vrealize-orchestrator-plug-in-for-redis/blob/main/dist/o11nplugin-redis.vmoapp?raw=true)

### Supported platform version
The Redis plug-in supports vRO 8.4.2 and later.

### Building the plug-in

The plug-in can be built using standard Maven commands:

```bash
mvn clean install
```

This will result in `vmoapp` and `dar` files in the `o11nplugin-redis/target` folder.

### Sample scripting

##### Basic operations
```javascript
//Store a simple key-value in redis
var statusCode = connection.defaultDatabase.set("test-key", "test-value");
//Retrieve value
var value = connection.defaultDatabase.get("test-key");
```

#####Scripting
```javascript
var script = "return {KEYS[1],KEYS[2],ARGV[1],ARGV[2]}";
//Evaluate script
var result = connection.defaultDatabase.eval(script, ["key1", "key2"], ["arg1", "arg2"]);
//Load and evaluate script by sha
var sha = connection.defaultDatabase.scriptLoad(script);
result = connection.defaultDatabase.evalsha(sha, ["key1", "key2"], ["arg1", "arg2"]);
```

### Dependencies
The Redis plug-in for vRO uses the following third-party libraries:
* [Jedis](https://github.com/xetorthio/jedis) - a Java-based Redis client.
* [Google Guava](https://github.com/google/guava) - core libraries for Java-based projects, distributed under the Apache License, Version 2.0
* [Apache Commons Pool](https://commons.apache.org/proper/commons-pool/download_pool.cgi) - a library for object pooling

## Contributing

The vrealize-orchestrator-plug-in-for-redis project team welcomes contributions from the community. Before you start working with vrealize-orchestrator-plug-in-for-redis, please
read our [Developer Certificate of Origin](https://cla.vmware.com/dco). All contributions to this repository must be
signed as described on that page. Your signature certifies that you wrote the patch or have the right to pass it on
as an open-source patch. For more detailed information, refer to [CONTRIBUTING.md](CONTRIBUTING.md).

## License