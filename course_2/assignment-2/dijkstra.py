def readUndirectedGraph(filename):
    
    adjlist = []
    
    lines = open(filename).read().splitlines()
    
    for line in lines:
        
        adjlist.append([])
        data = line.split()
        v = int(data[0])-1
        
        for tpl in data[1:]:
            
            ts, ws = tpl.split(',')
            t = int(ts)-1
            w = int(ws)
            
            adjlist[v].append((t, w))

    return adjlist


def extract_min(pq, weights):
    
    i = 0
    j = 1
    m = weights[pq[0]]
    
    while j < len(pq):
        if weights[pq[j]] < m:
            i = j
            m = weights[pq[j]]
        j += 1
    
    res = pq[i]
    
    pq[i] = pq[-1]
    pq.pop()
    
    return res


def dijkstraShortestPaths(graph, s):

    infinity = 1000000
    
    weights = [infinity]*len(graph)
    weights[s] = 0
    
    pqueue = [i for i in range(len(graph))]
    
    visited = [False]*len(graph)
    
    while len(pqueue) > 0:
                
        v = extract_min(pqueue, weights)
        visited[v] = True
    
        for inc, w in graph[v]:
            if not visited[inc]:
                weights[inc] = min(weights[inc], weights[v]+w)
                
    return weights


def main():
    
    desired = [7,37,59,82,99,115,133,165,188,197]
    
    graph = readUndirectedGraph('dijkstraData.txt')
    weights = dijkstraShortestPaths(graph, 0)
    
    res = []
    for i in desired:
        res.append(str(weights[i-1]))
        
    print(','.join(res))       
    
    

if __name__ == '__main__':
	main()
	
#2599,2610,2947,2052,2367,2399,2029,2442,2505,3068
