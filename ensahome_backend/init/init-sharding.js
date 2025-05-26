sh.enableSharding('ensahome');

sh.shardCollection("ensahome.users",{ville:1})
