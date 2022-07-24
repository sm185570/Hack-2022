import json
import requests

headers = {
    'Authorization': 'Bearer EAAGlT5C3XyQBAOZADjs5ClZCEfF9cwV3ZAUsBw4tRW3n6yZBn5bHBLG7gcznPaPXHwMh2aNEwrdD93ksckOSxFP5YZAZBb50R2kq1MA0JHPUVUOekYy8y0inmzGySLhVFV7yOoY7krM8MNrJY2osMd7kKLL17Dp5qpyPWUsDIIEqhEKC0b9MZAfpCpt7FshMuMKiQoy8lWljfbap9rwFqzKvJ3JTD2JIIIZD',
    'Content-Type': 'application/json'
}

data = {"messaging_product":"whatsapp",

     "to": "918604818820", 
     "type": "template",
     "template": { 
        "name": "hello", 
        "language": { "code": "en_US" },
        "components": [
            {
                "type": "header",
                "parameters": [
                    {
                    "type": "text",
                    "text": "Pulkit"
                }]
            }
            ]
        } }


response = requests.post('https://graph.facebook.com/v13.0/102928602502906/messages', headers=headers, data=json.dumps(data))

print(response.status_code)