# Continuous integration/Continuous Deployment (CI/CD)

![ci-cd](../images/ci-cd/twitter-scheduler-ci-cd.png)

## GitHub actions workflow

![workflow](../images/ci-cd/github-actions-workflow.png)

## Heroku configuration

Is needed to create an application in Heroku and adding as resource a Heroku Postgres database, also is needed to create these **Config Vars**:

- `API_USERNAME`: user for REST API and OpenApi access. Default value: `admin`
- `API_PASSWORD`: password for REST API and OpenApi user. Default value: `admin1`
- `DB_URL`: database URL, it must have this format: `postgresql://<host>:<port>/<db_name>?user=<username>&password=<password>`
- `DB_USERNAME`: database user
- `DB_PASSWORD`: database password
- `TWITTER_ACCESS_TOKEN`: Twitter API access token
- `TWITTER_ACCESS_TOKEN_SECRET`: Twitter API access token secret
- `TWITTER_CONSUMER_KEY`: Twitter API consumer key
- `TWITTER_CONSUMER_SECRET`: Twitter API consumer secret

> **_NOTE:_** DATABASE_URL is created by default when Heroku Postgres database is created but their format is not valid for the application, so DB_URL is needed

## Available endpoints

Application root path: [https://twitter-scheduler-tfm.herokuapp.com/](https://twitter-scheduler-tfm.herokuapp.com/)

- `/`: welcome message
- `/openapi.html`: OpenAPI documentation
- `/togglz`: feature toggles management
- `/actuator`: actuator endpoints available
```
{
    "_links":{
        "self":{
            "href":"https://twitter-scheduler-tfm.herokuapp.com/actuator",
            "templated":false
        },
        "health":{
            "href":"https://twitter-scheduler-tfm.herokuapp.com/actuator/health",
            "templated":false
        },
        "health-path":{
            "href":"https://twitter-scheduler-tfm.herokuapp.com/actuator/health/{*path}",
            "templated":true
        },
        "info":{
            "href":"https://twitter-scheduler-tfm.herokuapp.com/actuator/info",
            "templated":false
        },
        "togglz-name":{
            "href":"https://twitter-scheduler-tfm.herokuapp.com/actuator/togglz/{name}",
            "templated":true
        },
        "togglz":{
            "href":"https://twitter-scheduler-tfm.herokuapp.com/actuator/togglz",
            "templated":false
        }
    }
}
```
- `/actuator/info`: application information
```
{
    "git":{
        "branch":"main",
        "commit":{
            "id":"752a6d4",
            "time":"2021-12-06T11:53:04Z"
        }
    },
    "build":{
        "artifact":"twitter-scheduler",
        "name":"twitter-scheduler",
        "time":"2021-12-06T11:55:13.320Z",
        "version":"0.0.1-SNAPSHOT",
        "group":"com.mastercloudapps"
    }
}
```
- `/actuator/health`: welcome message
```
{
    "status":"UP"
}
```
- `/actuator/togglz`: feature toggles status:
```
[
    {
        "name":"SCHEDULER",
        "enabled":true,
        "strategy":null,
        "params":{}
    }
]
```