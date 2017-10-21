import sys, pygame
import math

fps = 30
pygame.init()
size = width, height = 1200, 700
screen = pygame.display.set_mode(size)
objects = []
mousex, mousey = pygame.mouse.get_pos()
WHITE = (255, 255, 255)
rotationspeed = 5
rad = 50
spacepressed = True
myfont = pygame.font.SysFont("monospace", 15)
label = myfont.render(
    "Mouse click to spawn boids. Space to toggle personal space. Press c to clear boids. Escape to exit", 1,
    (255, 0, 0))


# rotationspeed = raw_input('Enter the rotation speed of the triangles')
# rad = raw_input('Enter the radius of the triangles private space')
class boid:
    def __init__(self, x, y):
        global screen
        global rotationspeed
        global rad
        self.rotspeed = rotationspeed
        self.color = pygame.Color.r
        self.defrotspeed = rotationspeed
        self.speed = 10
        self.xPos = x
        self.yPos = y
        self.angle = -90
        self.radius = rad
        self.circlec = (0, 255, 0)
        self.points = [(self.xPos, self.yPos - 20), (self.xPos - 10, self.yPos + 10), (self.xPos + 10, self.yPos + 10)]
        self.fogradius = 90

    '''def infog(self,(x,y)):
        if(math.pow((x-self.xPos), 2)+ math.pow((y-self.yPos), 2)<math.pow(self.fogradius, 2)):
            return True
        else:
            return False'''

    def incircle(self, x, y):
        if math.pow((x - self.xPos), 2) + math.pow((y - self.yPos), 2) < math.pow(self.radius, 2):
            return True
        else:
            return False

    '''def getboidsinfog(self):#not tested
        global objects
        boidsinfog = []
        for o in objects:
            if self.infog((o.xPos, o.yPos)):
                boidsinfog.append(o)
        boidsinfog.remove(self)
        return boidsinfog'''

    def getboidsincircle(self):  # not tested
        global objects
        boidsincircle = []
        for o in objects:
            if self.incircle(o.xPos, o.yPos):
                boidsincircle.append(o)
        boidsincircle.remove(self)
        return boidsincircle

    def pointdirection(self, x3, y3):
        x2, y2 = self.points[0]
        x1, y1 = self.xPos, self.yPos
        value = (x2 - x1) * (y3 - y1) - (y2 - y1) * (x3 - x1)
        if value > 0:
            return True  # left
        if value < 0:
            return False  # right
        else:
            return None  # centre

    def mousedirection(self):
        return self.pointdirection(pygame.mouse.get_pos()[0], pygame.mouse.get_pos()[1])

    def seperation(self):
        objectslist = self.getboidsincircle()
        if len(objectslist) == 0:
            return None
        self.circlec = (255, 0, 0)
        avgx = 0
        avgy = 0
        objectlen = len(objectslist)
        for o in objectslist:
            avgx += o.xPos
            avgy += o.yPos
        avgx /= objectlen
        avgy /= objectlen
        if not self.pointdirection(avgx, avgy):  # if it's to its right
            return True  # Turn Left
        else:
            return False  # Turn Right

    def setRotspeed(self):
        isleft = self.mousedirection()
        if self.seperation() is not None:
            if self.seperation():
                self.rotspeed = self.defrotspeed
                return
            else:
                self.rotspeed = -self.rotspeed
                return
        if isleft:
            self.rotspeed = self.defrotspeed
            return
        elif not isleft:
            self.rotspeed = -self.defrotspeed
            return
        else:
            self.rotspeed = 0
            return

    def rotate(self):
        self.circlec = (0, 255, 0)
        self.setRotspeed()
        rotrad = math.radians(self.rotspeed)
        new_points = []
        for x in self.points:
            oldx, oldy = x
            altx = oldx - self.xPos
            alty = oldy - self.yPos
            newx = altx * math.cos(rotrad) - alty * math.sin(rotrad)
            newy = altx * math.sin(rotrad) + alty * math.cos(rotrad)
            x = (newx + self.xPos, newy + self.yPos)
            new_points.append(x)
        self.points = new_points
        self.angle += self.rotspeed
        if self.angle > 180:
            self.angle -= 360
        if self.angle < -180:
            self.angle += 360

    def move(self):
        xvel = int(self.speed * math.cos(math.radians(self.angle)))
        yvel = int(self.speed * math.sin(math.radians(self.angle)))
        new_points = []
        for x in self.points:
            newx, newy = x
            x = (newx + xvel, newy + yvel)
            new_points.append(x)
        self.points = new_points
        self.xPos += xvel
        self.yPos += yvel

    def draw(self):
        global spacepressed
        global screen
        if spacepressed:
            pygame.draw.circle(screen, self.circlec, (self.xPos, self.yPos), self.radius, 1)
        pygame.draw.polygon(screen, (255, 0, 0), self.points, 0)
        # pygame.draw.circle(screen, (0,0,255), (self.xPos, self.yPos), self.fogradius, 1)


while 1:
    mousex, mousey = pygame.mouse.get_pos()
    for event in pygame.event.get():
        if event.type == pygame.QUIT: sys.exit()
        if event.type == pygame.MOUSEBUTTONDOWN:
            o = boid(pygame.mouse.get_pos()[0], pygame.mouse.get_pos()[1])
            objects.append(o)
        if event.type == pygame.KEYDOWN and event.key == pygame.K_SPACE:
            if not spacepressed:
                spacepressed = True
            else:
                spacepressed = False
        if event.type == pygame.KEYDOWN and event.key == pygame.K_c:
            for o in objects:
                objects = []
        if event.type == pygame.KEYDOWN and event.key == pygame.K_ESCAPE:
            sys.exit()
    screen.fill(WHITE)
    screen.blit(label, (10, 10))
    for o in objects:
        o.move()
        o.rotate()
        o.draw()
    pygame.display.update()

    pygame.time.delay(int(1000 / fps))
