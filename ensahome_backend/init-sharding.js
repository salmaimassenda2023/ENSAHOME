// Wait for the mongos to be fully initialized
sleep(30000);

// Connect to mongos
db = connect("mongodb://mongos:27017/admin");

// Enable sharding for the database if not already enabled
sh.enableSharding("ensahome");

// Shard the users collection
sh.shardCollection("ensahome.users", { ville: 1 });

// Create splits at specific city boundaries
sh.splitAt("ensahome.users", { ville: "Agadir" });
sh.splitAt("ensahome.users", { ville: "Khouribga" });
sh.splitAt("ensahome.users", { ville: "Marrakech" });

// Move chunks to their appropriate shards
sh.moveChunk("ensahome.users", { ville: "Agadir" }, "agadirRS");
sh.moveChunk("ensahome.users", { ville: "Khouribga" }, "khouribgaRS");
sh.moveChunk("ensahome.users", { ville: "Marrakech" }, "marrakechRS");

// Wait for migrations to complete
while (sh.isBalancerRunning()) {
    print("Waiting for chunk migrations to complete...");
    sleep(1000);
}

// Verify the distribution
printjson(db.getSiblingDB("ensahome").users.getShardDistribution());
printjson(sh.status()); 