hardware = ["GPU","CPU","RAM","Soundkarte","Netzwerkkarte","Netzteil","SSD","Festplatte"]

for item in hardware:
    print(item)

for i in range(len(hardware)): # so gehts auch
    print(i,hardware[i])

for i in hardware[1:]: #slice() funktion. Starte bei 1 und gehe bis zum Ende
    print(i)

for i in hardware[1:-1]: # Negative Zahl lässt Elemente am Ende der Liste weg. -1 ist das letzte Element
    print(i)