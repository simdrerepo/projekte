def sayhello(to="World!"):
    print("Hello",to)

def is_even(n):
    if n%2 == 0:
        return True
    else:
        return False
    
def is_even_better(n):
    # eleganter
    return True if n%2==0 else False

def is_even_even_better(n):
    # noch eleganter
    return (n%2==0)

if is_even(2):
    print("Even")
else:
    print("Odd")

sayhello()
sayhello("Simon")

x = 3
y = 2

if x < y:
    print(x,"ist kleiner als",y)

elif x > y:
    print(x,"ist grüßer als",y) # elif wird aufgerufen wenn vorheriger abfrage falsch ist

elif x == y:
    print(x,"ist gleich",y) # # elif wird aufgerufen wenn vorheriger abfrage falsch ist


# Implementiert man das obige Programm nur mit if's, dann werden immer 3 Fragen gestellt also alle if Bedingungen werden gecheckt
# Mit elif's werden die Bedingungen nur gecheckt wenn die vorherige Bedingung falsch ist, es müssen also nicht 
# immer alle Bedingungen gecheckt werden