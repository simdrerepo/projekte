dict = {"Harry":"Gryffindor",
        "Ron":"Gryffindor",
        "Draco":"Slytherin"
        }

# key,value pairs

print(dict["Ron"]) # Suche nach value mit key

#################################################

for student in dict: # value ausgeben mit key 
    print(dict[student])

for student in dict:    # key ausgabe by default
    print(student)

for student in dict:
    print(student,dict[student]) # key und value ausgeben