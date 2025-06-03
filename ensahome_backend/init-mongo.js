print("Starting MongoDB sharding configuration...");

try {
    print("Waiting for shards to be ready...");
    sleep(30000);  // Wait 30 seconds for all shards to be ready

    // Add shards
    print("Adding Khouribga shard...");
    let result = sh.addShard("khouribgaRS/khouribga_shard1:27031,khouribga_shard2:27032,khouribga_shard3:27033");
    if (!result.ok) {
        throw new Error("Failed to add Khouribga shard: " + tojson(result));
    }

    print("Adding Marrakech shard...");
    result = sh.addShard("marrakechRS/marrakech_shard1:27041,marrakech_shard2:27042,marrakech_shard3:27043");
    if (!result.ok) {
        throw new Error("Failed to add Marrakech shard: " + tojson(result));
    }

    print("Adding Agadir shard...");
    result = sh.addShard("agadirRS/agadir_shard1:27051,agadir_shard2:27052,agadir_shard3:27053");
    if (!result.ok) {
        throw new Error("Failed to add Agadir shard: " + tojson(result));
    }

    // Enable sharding for the database
    print("Enabling sharding for ensahome database...");
    result = sh.enableSharding("ensahome");
    if (!result.ok) {
        throw new Error("Failed to enable sharding for ensahome database: " + tojson(result));
    }

    // Create sharded collections
    print("Creating sharded collections...");
    result = sh.shardCollection("ensahome.users", { "ville": "hashed" });
    if (!result.ok) {
        throw new Error("Failed to shard users collection: " + tojson(result));
    }

    // Verify sharding status
    print("Verifying sharding status...");
    printjson(sh.status());

    print("Sharding configuration completed successfully!");
    quit(0);
} catch (e) {
    print("Error during sharding configuration: " + e);
    printjson(e);
    quit(1);
} 