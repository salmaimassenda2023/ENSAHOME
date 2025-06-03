print("Starting shard replica set initialization...");

// Get the hostname from the connection string to determine which replica set to initialize
const hostname = db.getMongo().host.split(':')[0];
let replicaSetConfig;

// Configure the appropriate replica set based on the hostname
if (hostname.includes('khouribga')) {
    replicaSetConfig = {
        _id: "khouribgaRS",
        members: [
            { _id: 0, host: "khouribga_shard1:27031", priority: 2 },
            { _id: 1, host: "khouribga_shard2:27032", priority: 1 },
            { _id: 2, host: "khouribga_shard3:27033", priority: 1 }
        ]
    };
} else if (hostname.includes('marrakech')) {
    replicaSetConfig = {
        _id: "marrakechRS",
        members: [
            { _id: 0, host: "marrakech_shard1:27041", priority: 2 },
            { _id: 1, host: "marrakech_shard2:27042", priority: 1 },
            { _id: 2, host: "marrakech_shard3:27043", priority: 1 }
        ]
    };
} else if (hostname.includes('agadir')) {
    replicaSetConfig = {
        _id: "agadirRS",
        members: [
            { _id: 0, host: "agadir_shard1:27051", priority: 2 },
            { _id: 1, host: "agadir_shard2:27052", priority: 1 },
            { _id: 2, host: "agadir_shard3:27053", priority: 1 }
        ]
    };
} else {
    throw new Error("Unknown shard type: " + hostname);
}

try {
    // Check if replica set is already initialized
    try {
        let status = rs.status();
        if (status.ok === 1) {
            print("Replica set is already initialized");
            printjson(status);
            quit(0);
        }
    } catch (e) {
        print("Initializing replica set for " + hostname);
    }

    // Initialize the replica set
    rs.initiate(replicaSetConfig);
    print("Replica set initialization command sent");

    // Wait for replica set to initialize
    let maxAttempts = 30;
    let attempts = 0;
    while (attempts < maxAttempts) {
        try {
            let status = rs.status();
            if (status.ok === 1 && status.members && status.members.some(m => m.stateStr === "PRIMARY")) {
                print("Replica set is ready!");
                printjson(status);
                quit(0);
            }
        } catch (e) {
            print("Waiting for replica set to initialize...");
        }
        sleep(1000);
        attempts++;
    }
    throw new Error("Timeout waiting for replica set to initialize");
} catch (e) {
    print("Error initializing replica set: " + e);
    printjson(e);
    quit(1);
} 