jobsFile = open('jobs.txt','r')
lines = jobsFile.readlines()[1:]

jobs = []
length,weight = 0,0
for line in lines:
    weight = int(line.split()[0])
    length = int(line.split()[1])
    jobs.append([weight,length,float(weight) / float(length)])

jobs = sorted(jobs,key = lambda x:x[2])
jobs = jobs[-1::-1]
sumTime = 0
sumLength = 0 
for job in jobs:
    sumLength += job[1]
    sumTime += job[0] * sumLength
print(sumTime)
