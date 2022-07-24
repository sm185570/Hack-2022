import io
import glob
import os
import sys
import time
import uuid
import requests
from urllib.parse import urlparse
from io import BytesIO
from PIL import Image, ImageDraw
from azure.cognitiveservices.vision.face import FaceClient
from msrest.authentication import CognitiveServicesCredentials
from azure.cognitiveservices.vision.face.models import TrainingStatusType, Person

def person_group(face_client):
    #PERSON_GROUP_ID = str(uuid.uuid4())
    #print('Person group ID:', PERSON_GROUP_ID)
    PERSON_GROUP_ID='adcd21cb-650c-4d60-8c8d-4f94f53faa1e'
    #face_client.person_group.create(person_group_id=PERSON_GROUP_ID, name=PERSON_GROUP_ID)
    '''neeraj=face_client.person_group_person.create(PERSON_GROUP_ID, "Neeraj")
    utkarsh=face_client.person_group_person.create(PERSON_GROUP_ID, "Utkarsh")
    shivani=face_client.person_group_person.create(PERSON_GROUP_ID, "Shivani")
    pulkit=face_client.person_group_person.create(PERSON_GROUP_ID, "Pulkit")
    urvashi=face_client.person_group_person.create(PERSON_GROUP_ID, "Urvashi")
    ajay=face_client.person_group_person.create(PERSON_GROUP_ID, "Ajay")
    '''
    surya=face_client.person_group_person.create(PERSON_GROUP_ID,"Surya")
    harsha=face_client.person_group_person.create(PERSON_GROUP_ID,"Harsha")
    '''neeraj_images=[file for file in glob.glob('*.jpg') if file.startswith("N")]
    utkarsh_images=[file for file in glob.glob('*.jpg') if file.startswith("Ut")]
    shivani_images=[file for file in glob.glob('*.jpg') if file.startswith("S")]
    pulkit_images=[file for file in glob.glob('*.jpg') if file.startswith("P")]
    urvashi_images=[file for file in glob.glob('*.jpg') if file.startswith("Ur")]
    ajay_images=[file for file in glob.glob('*.jpg') if file.startswith("Aj")]
    '''
    surya_images=[file for file in glob.glob('*.jpg') if file.startswith("Su")]
    harsha_images=[file for file in glob.glob('*.jpg') if file.startswith("Ha")]
    '''
    for image in neeraj_images:
        n = open(image, 'r+b')
        face_client.person_group_person.add_face_from_stream(PERSON_GROUP_ID, neeraj.person_id,n)
    print("a")
    time.sleep(60)

    for image in utkarsh_images:
        u = open(image, 'r+b')
        face_client.person_group_person.add_face_from_stream(PERSON_GROUP_ID, utkarsh.person_id,u)

    print("b")
    time.sleep(60)

    for image in shivani_images:
        s = open(image, 'r+b')
        face_client.person_group_person.add_face_from_stream(PERSON_GROUP_ID, shivani.person_id,s)
    
    print("c")
    time.sleep(60)

    for image in pulkit_images:
        p = open(image, 'r+b')
        face_client.person_group_person.add_face_from_stream(PERSON_GROUP_ID, pulkit.person_id,p)

    print("d")
    time.sleep(60)

    for image in urvashi_images:
        ur = open(image,'r+b')
        face_client.person_group_person.add_face_from_stream(PERSON_GROUP_ID,urvashi.person_id,ur)

    print("e")
    time.sleep(60)
    for image in ajay_images:
        aj = open(image,'r+b')
        face_client.person_group_person.add_face_from_stream(PERSON_GROUP_ID,ajay.person_id,aj)

    print("f")

    time.sleep(60)
    '''

    for image in harsha_images:
        ha = open(image,'r+b')
        face_client.person_group_person.add_face_from_stream(PERSON_GROUP_ID,harsha.person_id,ha)

    time.sleep(30)

    for image in surya_images:
        su=open(image,'r+b')
        face_client.person_group_person.add_face_from_stream(PERSON_GROUP_ID,surya.person_id,su)

    time.sleep(30)
    print('Training the person group...')

    face_client.person_group.train(PERSON_GROUP_ID)

    while (True):
        training_status = face_client.person_group.get_training_status(PERSON_GROUP_ID)
        print("Training status: {}.".format(training_status.status))
        print()
        if (training_status.status is TrainingStatusType.succeeded):
            break
        elif (training_status.status is TrainingStatusType.failed):
            face_client.person_group.delete(person_group_id=PERSON_GROUP_ID)
            sys.exit('Training the person group has failed.')
        time.sleep(5)

    return PERSON_GROUP_ID

def get_person_info(endpoint, key, group_id, candidate_id):
    """GET Request to retrieve the person info identified"""
    face_api_url = '{0}/face/v1.0/persongroups/{1}/persons/{2}'.format(endpoint, group_id, candidate_id)
    headers = {'Ocp-Apim-Subscription-Key': key}
    response = requests.get(face_api_url, headers=headers)
    return response.json()

if __name__=='__main__':
    #face_client=init()
    KEY = "e918ff0d463a409a9728b968990d188d"
    ENDPOINT = "https://hackathon-2022.cognitiveservices.azure.com/"
    face_client = FaceClient(ENDPOINT, CognitiveServicesCredentials(KEY))
    PERSON_GROUP_ID=person_group(face_client)
