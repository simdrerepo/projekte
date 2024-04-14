print("Hello World!")

name = " simon dreckmann "

name = name.strip() # Leerzeichen vor und nachdem string entfernen
name = name.capitalize() # Der erste char wird groß
name = name.title() # Der erste char jedes "wortes" wird groß
name = name.strip().title() # chaining

print("hello",name) # space wird automatisch eingefügt (siehe Parameter der print methode)
print("hello "+name) # so gehts auch
print(f"hello {name}") # format string