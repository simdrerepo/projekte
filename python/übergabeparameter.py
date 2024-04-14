import sys

try:
    print("Der Übergabeparameter lautet",sys.argv[1]) # argv[0] ist der Programmname selbst
except IndexError:
    pass # oder was anderes

if len(sys.argv) < 2:
    print("Der Übergabeparameter lautet",sys.argv[0]) 

for arg in sys.argv:
    print(arg)

if len(sys.argv) < 2:
    sys.exit("Zu wenige Übergabeparameter") # Beendet das Programm

