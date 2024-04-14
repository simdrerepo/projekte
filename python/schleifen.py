i = 3

while i >= 0:
    print(i)
    i = i - 1

while i <= 3:
    print(i)
    i = i + 1

for i in [0,1,2,3]:
    print(i)

for i in range(3): # range(3) entspricht i < 3
    print(i)

for _ in range(3): # _ wenn i nicht gebraucht wird
    print("muh")

print("muh\n" * 3,end="") # so gehts auch