import requests
import json
response_API = requests.get('https://apis.garmin.com/userPermissions/')
print(response_API)