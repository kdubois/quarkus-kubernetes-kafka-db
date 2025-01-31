

curl -X 'POST' \
  "https://$(oc get route kubenative --output jsonpath={.spec.host})/power" \
  -H 'accept: text/plain' \
  -H 'Content-Type: application/json' \
  -d '{
  "device": "mydevice",
  "power": 121
}'

sleep 1;

curl -X GET https://"$(oc get route kubenative --output jsonpath={.spec.host})"/power