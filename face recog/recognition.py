import glob
import os
import time
from cv2 import VideoCapture
import cv2
import training
from urllib.parse import urlparse
from io import BytesIO
from PIL import Image, ImageDraw
from azure.cognitiveservices.vision.face import FaceClient
from msrest.authentication import CognitiveServicesCredentials
from azure.cognitiveservices.vision.face.models import TrainingStatusType, Person

def image_capture():
    videoCapture = cv2.VideoCapture(0)
    faceCascadeClassifier = cv2.CascadeClassifier('haarcascade_frontalface_default.xml')
    face_detected = False
    while True:
        ret, frame = videoCapture.read()
        gray = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)
        faces = faceCascadeClassifier.detectMultiScale(
            gray,
            scaleFactor=1.1,
            minNeighbors=5,
            minSize=(30, 30)
        )
        # Draw a rectangle around the faces
        for (x, y, w, h) in faces:
            cv2.rectangle(frame, (x, y), (x+w, y+h), (0, 255, 0), 2)
            face_detected=True     
            time.sleep(0.2)

        # Display the resulting frame
        cv2.imshow('Video', frame)

        if cv2.waitKey(1) & 0xFF == ord('q'):
            break
        elif face_detected:
            cv2.imwrite("Test_image.jpg",frame)
            break

    # When everything is done, release the capture
    videoCapture.release()
    cv2.destroyAllWindows()

def identify(KEY,ENDPOINT,PERSON_GROUP_ID):
    face_client = FaceClient(ENDPOINT, CognitiveServicesCredentials(KEY))
    test_image_array = glob.glob('Test_image.jpg')
    image = open(test_image_array[0], 'r+b')

    print('Pausing for 60 seconds to avoid triggering rate limit on free account...')
    #time.sleep (60)
    
    # Detect faces
    face_ids = []
    # We use detection model 3 to get better performance.
    faces = face_client.face.detect_with_stream(image, detection_model='detection_03')
    for face in faces:
        face_ids.append(face.face_id)
        print(face.face_id)
 
    identified_person=""
    results = face_client.face.identify(face_ids, PERSON_GROUP_ID)
    print('Identifying faces in {}'.format(os.path.basename(image.name)))
    if not results:
        print('No person identified in the person group for faces from {}.'.format(os.path.basename(image.name)))
    for person in results:
        if len(person.candidates) > 0:
            print('Person for face ID {} is identified in {} with a confidence of {}.'.format(person.face_id, os.path.basename(image.name), person.candidates[0].confidence)) # Get topmost confidence score
            person_info=training.get_person_info(ENDPOINT,KEY,PERSON_GROUP_ID,person.candidates[0].person_id)
            identified_person=person_info['name']
            print("Name: {}".format(person_info['name']))
        else:
            print('No person identified for face ID {} in {}.'.format(person.face_id, os.path.basename(image.name)))
    # </snippet_identify>
    print()
    return identified_person


if __name__=='__main__':
    KEY = "e918ff0d463a409a9728b968990d188d"
    ENDPOINT = "https://hackathon-2022.cognitiveservices.azure.com/"
    PERSON_GROUP_ID='adcd21cb-650c-4d60-8c8d-4f94f53faa1e'
    image_capture()
    identify(KEY,ENDPOINT,PERSON_GROUP_ID)

        