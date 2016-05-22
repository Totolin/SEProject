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
    }
}

var students = {
    id : null
}

// Connectivity
frisby.create('Pinging server')
    .get(host + '/ping')
    .expectStatus(200)
    .expectBodyContains('Ping test!-Ping test!-Ping test!-Ping test!')
.toss();

// Admin-controller
frisby.create('Remember how many users are added so far')
    .get(host + '/admins/users')
    .afterJSON(function(json){
        
        // Save number of users
        user.size = json.length;

        frisby.create('Adding user with fake type')
            .post(host + '/admins/users',
            {
                "password": String,
                "type": String,
                "username": String
            }, {json:true})
            .expectStatus(400)
        .toss();


        /*

        TODO REDO THESE


        frisby.create('Adding a normal user')
            .post(host + '/admins/users',
            {
                "password": "parola",
                "type": "STUDENT",
                "username": user.name
            }, {json:true})
            .expectStatus(201)
        .toss();

        frisby.create('We should see the new user')
            .get(host + '/admins/users')
            .expectJSONLength(user.size + 1)
            .expectJSONTypes('*',
            {
                'id' : Number,
                'username' : String,
                'state' : String,
                'type' : String
            })
            .afterJSON(function(json){
                user.id = json[json.length-1].id;

                frisby.create('Trying to delete a fake user')
                    .delete(host + '/admins/users/' + 99999)
                    .expectStatus(404)
                .toss();

                frisby.create('Deleting the user we added')
                    .delete(host + '/admins/users/' + user.id)
                    .expectStatus(200)
                .toss();

                frisby.create('Trying to get the user we just deleted')
                    .get(host + '/admins/users/' + user.id)
                    .expectStatus(404)
                .toss();
            })
        .toss();

        */
    })
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

frisby.create('Logging in with admin credentials')
    .post(host + '/login',
    {
        "password": admin.password,
        "username": admin.username
    }, {json:true})
    .expectStatus(200)
.toss();


// Secretaries-professors
var genericSecretaryTest = function (module) {
    frisby.create('Getting all ' + module)
        .get(host + '/secretaries/' + module)
        .expectJSONTypes('*',
        secretaries[module].types)
    .toss();

    // Login-controller
    frisby.create('Inserting fake item in ' + module)
        .post(host + '/secretaries/' + module,
        secretaries[module].wrong, {json:true})
        .expectStatus(400)
    .toss();

    frisby.create('Inserting proper item in ' + module)
        .post(host + '/secretaries/' + module,
        secretaries[module].right, {json:true})
        .expectStatus(201)
    .toss();

    frisby.create('Getting all items to check for the last one we inserted ' + module)
        .get(host + '/secretaries/' + module)
        .afterJSON(function(json){
            secretaries[module].id = json[json.length-1].id;

            frisby.create('Grabbing our item from ' + module)
                .get(host + '/secretaries/' + module + '/' + secretaries[module].id)
                .expectStatus(200)
            .toss();

            frisby.create('Deleting our professor')
                .delete(host + '/secretaries/' + module + '/' + secretaries[module].id)
                .expectStatus(200)
            .toss();
        })
    .toss();
} 

genericSecretaryTest('professors');
genericSecretaryTest('schedules');
genericSecretaryTest('students');

frisby.create('Getting one student for a test')
    .get(host + '/secretaries/students')
    .expectStatus(200)
    .afterJSON(function(json){
        if (json.length > 0) {
            console.log(json);
            students.id = json[0].id;

            frisby.create('Getting selected user\'s grades')
                .get(host + '/students/' + students.id + '/grades')
                .expectStatus(200)
            .toss();
        }
    })
.toss();

