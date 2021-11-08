# Example Spring WebFlux Application with LDAP+JWT Security

Demo using an embedded LDAP server to test multiple authentication managers.

User / Password (groups):
* user1 / user1 (users)
* admin / admin (admins, users)

LDAP groups are mapped into the JWT token as roles which are used to authorize the endpoints.

API:
* /auth/login (issues JWT tokens)
* /api/hello (accessible as admin and user1)
* /api/hello-admin (accessible only as admin)

## Example logins and API calls
```
$ TOKEN=$(curl -s http://user1:user1@localhost:8080/auth/login | jq -r '.token')
$ curl -H "Authorization: Bearer ${TOKEN}" http://localhost:8080/api/hello
hello

$ curl -H "Authorization: Bearer ${TOKEN}" http://localhost:8080/api/hello-admin
Access Denied

$ TOKEN=$(curl -s http://admin:admin@localhost:8080/auth/login | jq -r '.token')
$ curl -H "Authorization: Bearer ${TOKEN}" http://localhost:8080/api/hello-admin
hello admin
```

## Example search in embedded LDAP
```
$ ldapsearch -x -b "dc=xmpl,dc=com" -H ldap://localhost:8389 "(&(objectclass=person)(uid=user1))"
# user1, people, xmpl.com
dn: uid=user1,ou=people,dc=xmpl,dc=com
...
```

## TODO
* JWT token renewal

## Links
* https://github.com/thmshmm/spring-security-ldap-jwt-example
* https://devconnected.com/how-to-search-ldap-using-ldapsearch-examples/