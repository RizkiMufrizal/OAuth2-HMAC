# OAuth2-HMAC

# How To Use

1. Run the project using this command

```bash
gradle clean bootRun
```

2. Generate the access token using this command

```bash
curl -X POST -vu clientid:secret http://localhost:8080/oauth/token -H "Accept: application/json" -d "client_id=clientid&grant_type=client_credentials"
```

3. You can generate the HMAC Signature from [HMAC Generator Online](https://www.liavaag.org/English/SHA-Generator/HMAC/). for the input, use this combination

```text
access_token:timestamp:payload
```

4. Access The API Using this command

```bash
curl "http://127.0.0.1:8080/api/barangs" \
  -H "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzY29wZSI6WyJyZWFkIiwid3JpdGUiXSwiZXhwIjoxNTEyMzM2MDMxLCJhdXRob3JpdGllcyI6WyJBRE1JTklTVFJBVE9SIiwiQ0xJRU5UIiwiQURNSU4iXSwianRpIjoiNWVhMTEwN2UtOWU0ZC00ZmM0LWE2OTItN2Q5NmU4MjEyMzQ0IiwiY2xpZW50X2lkIjoiY2xpZW50aWQifQ.1YWFx39W2ua4-wcofYM69ybhneMt9vjfKm04fdwy1T8" \
  -H "Content-Type: application/json" \
  -H "HMAC-Signature: n1ZFRydj1UCvK68Ub3oL1aE8a48GotOTGUHqlj/mBR838DM8CAFJ02Z8G2m6p2ML+vV+XkGw9Be34jcL1axOUA==" \
  -H "HMAC-Timestamp: 2017-02-14T10:48:01.000+07:00"
```

## Technologi

* Access Token Using JWT RSA
* HMAC with SHA512
* Spring Security
* Spring Session
* Spring OAuth2
* Spring Data Cassandra
* Spring Data Redis
* Spring Boot
* Spring hateoas
