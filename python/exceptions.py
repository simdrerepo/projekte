

try:
    input = int(input("input = ?"))
except ValueError: # catch equivalent
    pass # Die exception catchen, aber nichts tun
else:
    print("input =",input)