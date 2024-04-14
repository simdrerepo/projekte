from random import choice # importiert nur die choice methode aus random
import random # importiert die gesamte library

c = choice(["kopf","zahl"]) # random.choice(...) mit import random
print(c)

number = random.randint(1,10) # Zahl zwischen 1 und 10
print(number)

karten = ["König","Dame","Bube"]
random.shuffle(karten) # Durchmischen in place

print(karten)

import statistics

print(statistics.mean([3.6,2.7,4.0,1.7])) # Durchschnitt