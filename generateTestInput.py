import random

f= open('testInput','w')
for i in range(0,100):
	f.write(str(i)+",Student,No."+str(i)+","+str(random.randint(1990,2012))+"-"+str(random.randint(1,12))+"-"+str(random.randint(1,28))+","+str(random.randint(0,8))+","+str(random.randint(0,8))+","+str(random.randint(0,8))+","+str(random.randint(1,3))+"\n")
f.close()