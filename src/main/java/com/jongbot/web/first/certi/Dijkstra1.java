package com.jongbot.web.first.certi;

import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

//Vertex(객체)에 연결된 Edge의 개수별로 담아 주머니에 그리고, 해당 경로까지의 최소 길이를 저장해두고 갱신
//dist[i] 시작점부터 i번 V까지 최단거리   
//D (v,d)   주머니 시작점부터 v에서 d로 갈 수 있음 .최단거리

/*
* 1) dist 배열을 초기화 0 or 무한대
* (S,0)을 D에 저장 //시작점은 무조건 시작점으로부터 0
* 2) D가 비었나?
* 3) D에 들어있는 min(v,d) 추출
* 4) (v,d)의 가치? ★ dist[v]와 비교 & 가치가 없다면 폐기
* 5) v,d를 통해 새로운 정보를 D에 추가
* 6) 종료 
*  초기화 -> 시작점을 D에 추가
*/

/*
* 마을은 N개가 존재하고 -> Vertex 가 N개
* M개의 Edge가 있다. + 단방향+ 음수가중치 없음 + 최단거리 -> 다익스트라
* 
*  #주의사항
*  1. 가는것 뿐 아니라 오는것도 계산해야함
*  2. 모든 꿀꿀이들의 소요시간을 구해야함 최대치
*  3. 결국 각 마을을 시작으로 dist[x]를 구해야하고 dist[N][X] : n에서 X로 가는 최소거리
*  
*  3번의 알고리즘을 사용해서 반대를 구하기
*  4. x를 시작점으로 dist[자기집]을 구해서 오고 가는 경로의 시간 합을 구해서 max를 찾아야함
*  5. time[마을] = dist[N][X] + dist[X][N];
*  6. time[마을]에서의 최대값을 찾기  
*/

class Edge {
	int start;
	int end;
	int weight;
}

class Info {
	int destVertex;
	int totalDistance;

	public Info(int destVertex, int totalDistance) {
		this.destVertex = destVertex;
		this.totalDistance = totalDistance;
	}
}

/*
 * Info (v,d) // 시작점부터 V까지 걸리는 거리 d
 * 
 */

public class Dijkstra1 {
	static StringTokenizer st;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int N, M, X;

	static int[][] dist;
	static int[][] reverseDist;
	static ArrayList<Edge>[] adjList;
	static ArrayList<Edge>[] reverseAdjList;

	public static void main(String[] args) {
		try {
			st = new StringTokenizer(br.readLine());
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());

		dist = new int[N + 1][N + 1];
		reverseDist = new int[N + 1][N + 1];
		adjList = new ArrayList[N + 1];// adjList[a][] a에 연결된 vertex들
		reverseAdjList = new ArrayList[N + 1];// adjList[a][] a에 연결된 vertex들 뒤집음

		Edge[] edges = new Edge[M + 1];
		int[] time = new int[N + 1];

		for (int i = 0; i <= N; ++i) {
			adjList[i] = new ArrayList<Edge>();
			reverseAdjList[i] = new ArrayList<Edge>();
		}

		for (int i = 1; i <= M; ++i) {
			try {
				st = new StringTokenizer(br.readLine());
			} catch (Exception e) {
				System.out.println(e.toString());
			}
			edges[i] = new Edge();
			edges[i].start = Integer.parseInt(st.nextToken()); // 없어도 됨
			edges[i].end = Integer.parseInt(st.nextToken());
			edges[i].weight = Integer.parseInt(st.nextToken());
			adjList[edges[i].start].add(edges[i]);
			reverseAdjList[edges[i].end].add(edges[i]);
		}

		int maxTime = 0;
		// X까지 가는방법 찾기 for(1~N)
		getReverseMinPath(X); // X까지 갈때걸리는시간
		getMinPath(X); // X에서 돌아올떄 걸리는 시간
		for (int i = 1; i <= N; ++i) {
			time[i] = reverseDist[X][i] + dist[X][i];
			if (time[i] > maxTime) {
				maxTime = time[i];
			}
		}
		try {
			bw.write(String.valueOf(maxTime));
			bw.flush();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	static void getReverseMinPath(int start) {
		for (int i = 1; i <= N; ++i) {
			reverseDist[start][i] = Integer.MAX_VALUE;
		}
		reverseDist[start][start] = 0; // 초기화

		PriorityQueue<Info> pq = new PriorityQueue<Info>(new Comparator<Info>() {
			@Override
			public int compare(Info o1, Info o2) {
				if (o1.totalDistance > o2.totalDistance) {
					return 1;
				} else if (o1.totalDistance < o2.totalDistance) {
					return -1;
				}
				return 0;
			}
		});
		Info info = new Info(start, 0);
		pq.add(info);

		while (!pq.isEmpty()) {
			Info rm = pq.poll();
			for (Edge adj : reverseAdjList[rm.destVertex]) {
				if (reverseDist[start][adj.start] > reverseDist[start][adj.end] + adj.weight) {
					reverseDist[start][adj.start] = reverseDist[start][adj.end] + adj.weight;
					pq.add(new Info(adj.start, reverseDist[start][adj.start]));
				}
			}
		}
	}

	static void getMinPath(int start) {
		for (int i = 1; i <= N; ++i) {
			dist[start][i] = Integer.MAX_VALUE;
		}
		dist[start][start] = 0; // 초기화

		PriorityQueue<Info> pq = new PriorityQueue<Info>(new Comparator<Info>() {
			@Override
			public int compare(Info o1, Info o2) {
				if (o1.totalDistance > o2.totalDistance) {
					return 1;
				} else if (o1.totalDistance < o2.totalDistance) {
					return -1;
				}
				return 0;
			}
		});
		Info info = new Info(start, 0);
		pq.add(info);

		while (!pq.isEmpty()) {
			Info rm = pq.poll();
			for (Edge adj : adjList[rm.destVertex]) {
				if (dist[start][adj.end] > dist[start][adj.start] + adj.weight) {
					dist[start][adj.end] = dist[start][adj.start] + adj.weight;
					pq.add(new Info(adj.end, dist[start][adj.end]));
				}
			}
		}
	}

}
