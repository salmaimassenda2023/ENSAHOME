print("Starting config servers replica set initialization...");

try {
    // Check if replica set is already initialized
    try {
        let status = rs.status();
        if (status.ok === 1) {
            print("Config replica set is already initialized");
            printjson(status);
            quit(0);
        }
    } catch (e) {
        print("Initializing config replica set...");
    }

    // Initialize the replica set
    let config = {
        _id: "configrs",
        configsvr: true,
        members: [
            { _id: 0, host: "config1:27019", priority: 2 },
            { _id: 1, host: "config2:27019", priority: 1 },
            { _id: 2, host: "config3:27019", priority: 1 }
        ]
    };

    rs.initiate(config);
    print("Config replica set initialized successfully");

    // Wait for replica set to initialize
    let maxAttempts = 30;
    let attempts = 0;
    while (attempts < maxAttempts) {
        try {
            let status = rs.status();
            if (status.ok === 1 && status.members && status.members.some(m => m.stateStr === "PRIMARY")) {
                print("Config replica set is ready!");
                printjson(status);
                quit(0);
            }
        } catch (e) {
            print("Waiting for replica set to initialize...");
        }
        sleep(1000);
        attempts++;
    }
    throw new Error("Timeout waiting for config replica set to initialize");
} catch (e) {
    print("Error initializing config replica set: " + e);
    printjson(e);
    quit(1);
}