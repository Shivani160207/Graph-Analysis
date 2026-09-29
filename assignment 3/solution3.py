#!/bin/python3

import os
import sys
import heapq

def shortestReach(n, adj, s):
    dist = [float('inf')] * (n + 1)
    dist[s] = 0
    pq = [(0, s)]

    while pq:
        d, u = heapq.heappop(pq)

        if d > dist[u]:
            continue

        for v, weight in adj[u].items():
            new_dist = d + weight
            if new_dist < dist[v]:
                dist[v] = new_dist
                heapq.heappush(pq, (new_dist, v))

    return [
        (dist[i] if dist[i] != float('inf') else -1)
        for i in range(1, n + 1)
        if i != s
    ]

if __name__ == '__main__':
    fptr = open(os.environ['OUTPUT_PATH'], 'w')
    input = sys.stdin.readline

    t = int(input().strip())

    for _ in range(t):
        n, m = map(int, input().split())

        # Build adjacency list directly while reading input to avoid MemoryError
        adj = [{} for _ in range(n + 1)]
        for _ in range(m):
            u, v, w = map(int, input().split())
            if v not in adj[u] or w < adj[u][v]:
                adj[u][v] = w
                adj[v][u] = w

        s = int(input().strip())

        result = shortestReach(n, adj, s)

        fptr.write(' '.join(map(str, result)))
        fptr.write('\n')

    fptr.close()
