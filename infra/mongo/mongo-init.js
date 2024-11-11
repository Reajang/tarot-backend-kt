db.auth('root', 'mongopw')

db = db.getSiblingDB('tarot')

db.createUser({
    user: 'tarot-user',
    pwd: 'tarotpw',
    roles: [
        {
            role: 'root',
            db: 'tarot',
        },
    ],
});

