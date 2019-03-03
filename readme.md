## How to start

Simply execute `./debug.sh` it will precompile sub project and starts service on port 9001 and debug port 9991

## Request Examples

```
curl -X GET \
  http://localhost:9001/ \
  -H 'Postman-Token: eda2dd34-a7e7-4e54-8f42-742af418dcd3' \
  -H 'Request-Id: index' \
  -H 'X-Correlation-Id: 1111' \
  -H 'X-Tenant: 8888' \
  -H 'cache-control: no-cache'
```

```
curl -X GET \
  http://localhost:9001/verify \
  -H 'Postman-Token: 0e602814-808e-4be8-a1e0-4cb057fe91cd' \
  -H 'Request-Id: verify' \
  -H 'X-Correlation-Id: 2222' \
  -H 'X-Tenant: 9999' \
  -H 'cache-control: no-cache'
```
