# List of dicts
dict = [
    {"Name":"Hans Peter","Alter":"69","Status":"Sigmamale","Haustier":"Schlange"},
    {"Name":"Dietrich","Alter":"31","Status":"Lauch","Haustier":"Spinne"}
]

for mensch in dict:
    print(mensch["Name"]) # print value of key 

for mensch in dict: # alles ausgeben
    print(mensch) 