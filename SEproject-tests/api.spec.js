var frisby = require('frisby');
var server = require('./config.json').server;
var host   = server.protocol + '://' + server.ip + ':' + server.port + server.path
var randomstring = require("randomstring");

// Settings
frisby.globalSetup({
  timeout: 10000000
});

// Variables used during the tests
var user = {
    name : randomstring.generate(7),
    size : null,
    id : null
};

var admin = {
    username : 'admin',
    password : 'admin'
}

var secretaries = {
    professors : {
        types : {
            "address": String,
            "department": {
              "id": Number,
              "name": String
            },
            "email": String,
            "firstName": String,
            "id": Number,
            "lastName": String,
            "office": String,
            "phoneNumber": String,
            "position": String,
            "ssn": String
        },
        wrong : {
          "address": 1234,
          "departmentId": "asd",
          "email": "string",
          "firstName": "string",
          "lastName": "string",
          "office": 23,
          "phoneNumber": 1,
          "position": "string",
          "ssn": "string"
        },
        right : {
          "address": "Str. Lalelelor",
          "departmentId": 1,
          "email": "asd@yahoo.com",
          "firstName": "string",
          "lastName": "string",
          "office": "S10",
          "phoneNumber": "0752184492",
          "position": "Asist.",
          "ssn": "1830801160033"
        },
        id : null
    },
    schedules : {
        types : {
            "id": Number,
            "professor": {
              "id": Number,
              "firstName": String,
              "lastName": String
            },
            "subject": {
              "id": Number,
              "name": String
            },
            "day": Number,
            "hour": Number,
            "room": String
        },

        wrong : {
            "day": 15342,
            "hour": 2342,
            "professorId": 0,
            "room": "Asdf",
            "subjectId": "Veronel"
        },

        right : {
            "day": 2,
            "hour": 11,
            "professorId": 2,
            "room": "C20",
            "subjectId": 1
        },
        id : null
    },
    students : {
        types : {
            "id": Number,
            "ssn": String,
            "firstName": String,
            "lastName": String,
            "email": String,
            "phoneNumber": String,
            "address": String,
            "group": {
              "id": Number,
              "name": String,
              "year": Number,
              "specialization": String
            },
            "subgroup": String,
            "paid": Boolean
        },
        wrong : {
            "address": 1234,
            "email": "asdf",
            "firstName": "asd",
            "groupId": 124,
            "lastName": "string",
            "paid": 15,
            "phoneNumber": "string",
            "ssn": "string",
            "subgroup": "string"
        },
        right : {
            "address": "doesn't matter",
            "email": "asdf@yahoo.com",
            "firstName": "Ionel",
            "groupId": 1,
            "lastName": "Vladutu",
            "paid": true,
            "phoneNumber": "0756173376",
            "ssn": "1940826160099",
            "subgroup": "B"
        },
        id : null
    },
    education_plan: {
        types : {
            "id": Number,
            "group": {
                "id": Number,
                "name": String,
                "year": Number,
                "specialization": String
            },
            "subject": {
                "id": Number,
                "name": String
            }
        },
        wrong : {
            "groupId": 100,
            "subjectIds": [
                {
                    "asd" : 1234
                }
            ]
        },
        right : {
            "groupId": 1,
            "subjectIds": [1]
        }
    }
};

var admins = {
    secretaries: {
        types : {
            "ssn": String,
            "firstName": String,
            "lastName": String,
            "email": String,
            "phoneNumber": String,
            "address": String,
            "id": Number,
            "account": {
              "username": String,
              "state": String,
              "type": String
            }
        },
        wrong : {
            "account": {
                "password": 1,
                "username": 123
            },
            "address": "asdf",
            "email": 1234,
            "firstName": "Joseph",
            "lastName": "Cenaaa",
            "phoneNumber": 65176354,
            "ssn": "19408299"
        },
        right : {
            "account": {
                "password": "asdf1234",
                "username": "john.cena"
            },
            "address": "John Cena Street",
            "email": "johnnn@cenaaa.com",
            "firstName": "Joseph",
            "lastName": "Cenaaa",
            "phoneNumber": "0765176354",
            "ssn": "1940826161099"
        }
    }
};

var students = {
    id : null
};

// Connectivity
frisby.create('Pinging server')
    .get(host + '/ping')
    .expectStatus(200)
    .expectBodyContains('Ping test!-Ping test!-Ping test!-Ping test!')
.toss();

// Login-controller
frisby.create('Logging in with fake user')
    .post(host + '/login',
    {
        "password": 'asdf',
        "username": 'asdf'
    }, {json:true})
    .expectStatus(400)
.toss();

/*
frisby.create('Logging in with admin credentials')
    .post(host + '/login',
    {
        "password": admin.password,
        "username": admin.username
    }, {json:true})
    .expectStatus(200)
.toss();
*/

var genericItemVerification = function (module, path, item) {
    frisby.create('Getting all ' + module)
        .get(host + path + module)
        .expectJSONTypes('*',
        item.types)
    .toss();

    // Login-controller
    frisby.create('Inserting fake item in ' + module)
        .post(host + path + module,
        item.wrong, {json:true})
        .expectStatus(400)
    .toss();

    frisby.create('Inserting proper item in ' + module)
        .post(host + path + module,
        item.right, {json:true})
        .expectStatus(201)
    .toss();

    frisby.create('Getting all items to check for the last one we inserted ' + module)
        .get(host + path + module)
        .afterJSON(function(json){
            item.id = json[json.length-1].id;

            frisby.create('Grabbing our item from ' + module)
                .get(host + path + module + '/' + item.id)
                .expectStatus(200)
            .toss();

            frisby.create('Deleting our item')
                .delete(host + path + module + '/' + item.id)
                .expectStatus(200)
            .toss();
        })
    .toss();
} 

// Test secretaries controllers
genericItemVerification('professors'    , '/secretaries/'   , secretaries.professors);
genericItemVerification('schedules'     , '/secretaries/'   , secretaries.schedules);
genericItemVerification('students'      , '/secretaries/'   , secretaries.students);
genericItemVerification('secretaries'   , '/admins/'        , admins.secretaries);
//genericItemVerification('educationPlans', '/secretaries/'   , secretaries.education_plan);

// Student controller
frisby.create('Getting one student for a test')
    .get(host + '/secretaries/students')
    .expectStatus(200)
    .afterJSON(function(json){
        if (json.length > 0) {
            students.id = json[0].id;

            frisby.create('Getting selected user\'s grades')
                .get(host + '/students/' + students.id + '/grades')
                .expectStatus(200)
            .toss();

            frisby.create('Getting selected user\'s info')
                .get(host + '/students/' + students.id + '/info')
                .expectStatus(200)
            .toss();
        }
    })
.toss();



