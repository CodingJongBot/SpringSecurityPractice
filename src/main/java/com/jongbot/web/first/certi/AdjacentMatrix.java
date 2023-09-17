package com.jongbot.web.first.certi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Subway {
	int id;
	int weight;

	public Subway(int id, int weight) {
		this.id = id;
		this.weight = weight;
	}
}

class Info2 {
	int dest;
	int totalWeight;

	public Info2(int dest, int totalWeight) {
		this.dest = dest;
		this.totalWeight = totalWeight;
	}
}

//가중치가 1이 아니므로 BFS 불가 -> Dijkstra & back traking
public class AdjacentMatrix {
	static int N, M;

	static StringTokenizer st;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	static List<Subway>[] adjList;
	static boolean[] visit;
	static int[] dist;
	static int[] back;

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken()); // 목적지 번호

		visit = new boolean[N + 1];
		dist = new int[N + 1];
		adjList = new ArrayList[N + 1];
		back = new int[N + 1];

		for (int i = 0; i <= N; ++i) {
			adjList[i] = new ArrayList<Subway>();
			visit[i] = false;
		}

		for (int i = 1; i <= N; ++i) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; ++j) {
				adjList[i - 1].add(new Subway(j, Integer.parseInt(st.nextToken())));
			}
		}
		pro();
	}

	static void dikjstra(int start, int end) {
		for (int i = 1; i <= N; i++) {
			dist[i] = Integer.MAX_VALUE;
		}
		dist[1] = 0;
		PriorityQueue<Info2> pq = new PriorityQueue<Info2>(new Comparator<Info2>() {
			@Override
			public int compare(Info2 o1, Info2 o2) {
				if (o1.totalWeight > o2.totalWeight) {
					return 1;

				} else if (o1.totalWeight < o2.totalWeight) {
					return -1;
				}
				return 0;
			}
		});
		pq.add(new Info2(start, 0));

		back[1] = start;

		while (!pq.isEmpty()) {
			Info2 rm = pq.poll();
			if (visit[rm.dest] == true)
				continue;
			for (Subway sb : adjList[rm.dest - 1]) {
				if (dist[sb.id] > dist[rm.dest] + sb.weight) {
					dist[sb.id] = dist[rm.dest] + sb.weight;
					back[sb.id] = rm.dest;
					pq.add(new Info2(sb.id, dist[sb.id]));
				}
			}
			visit[rm.dest] = true;
		}
	}

	static void pro() throws IOException {
		int s = 1;

		dikjstra(s, M);
		bw.write(String.valueOf(dist[M]) + "\n");
		bw.flush();
		int ret = M;
		List<Integer> sol = new ArrayList<Integer>();
		while (ret != s) {
			sol.add(ret);
			ret = back[ret];
		}
		sol.add(s);

		for (int i = sol.size() - 1; i >= 0; --i) {
			bw.write(String.valueOf(sol.get(i)) + " ");
		}
		bw.flush();
	}
}
