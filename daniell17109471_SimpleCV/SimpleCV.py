from SimpleCV import Display, Image, Color
import time
imagelist = ['faces/face_test_1.jpg','faces/face_test_2.jpg','faces/face_test_3.jpg','faces/face_test_4.jpg','faces/face_test_5.jpg','faces/face_test_6.jpg']
imagecycle = 0
image = Image(imagelist[0])
disp = Display(image.size())
apple = Image('Apple.jpg')
mask = apple.createAlphaMask(0)
print 'Right click to cycle images. Left to remove apple.'
def cycle():
    global imagecycle
    global image
    x = len(imagelist)
    if(imagecycle+1==x):
        imagecycle = 0
    else:
        imagecycle = imagecycle + 1
    image = Image(imagelist[imagecycle]).scale(600,480)
while disp.isNotDone():
    x = None
    y = None
    width = None
    height = None
    coordinates = (x,y)
    tempApple = None
    exist = True
    if disp.mouseLeft:
        exist = not exist
    if disp.mouseRight:
        cycle()
    # Look for a face
    tempImage = image
    faces = image.findHaarFeatures('face')
    if faces is not None:
        # Get the largest face
        faces = faces.sortArea()
        bigFace = faces[-1]
        # Draw a green box around the face
        bigFace.draw()
        width = bigFace.width()
        height = bigFace.height()
        (x,y) = bigFace.coordinates()
        coordinates = (x-width/2, y-height/2)
    if exist == True:
        tempApple = apple.scale(width, height)
        mask = tempApple.invert().invert()
        tempImage = tempImage.blit(img = tempApple,pos = coordinates,mask =mask)
    tempImage.save(disp)
    time.sleep(1/30)



