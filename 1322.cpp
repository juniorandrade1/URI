#include <bits/stdc++.h>
 
using namespace std;
 
typedef long long ll;
typedef pair<int,int> ii;
typedef vector<int> vi;
typedef vector< ii > vii;
 
#define INF 0x3F3F3F3F
#define LINF 0x3F3F3F3F3F3F3F3FLL
#define pb push_back
#define mp make_pair
#define pq priority_queue
#define MAXN 100001
#define LSONE(s) ((s)&(-s)) //LASTBIT
 
map<int,int>id;
int pset[MAXN];
int cont[MAXN];
set< pair<int,int> > g;
set< pair<int,int> > ::iterator it;
 
 
void init(int n){ for(register int i=0;i<n;++i){ pset[i] = i; cont[i] = 0; } }
int find(int i){ return (pset[i] == i)?i:(pset[i] = find(pset[i])); }
void uniset(int i,int j){ pset[find(i)] = find(pset[j]); }
 
int n,m;
 
int main()
{  
    while( scanf("%d %d",&n,&m)!=EOF )
    {
        if(!n&&!m) break;
        id.clear();
        g.clear();
        int cnt = 1;
        int u,v;
        for(int i=0;i<m;++i)
        {
            scanf("%d %d",&u,&v);
            if( id[u] == 0 ) id[u] = cnt++;
            if( id[v] == 0 ) id[v] = cnt++;
            u = id[u]-1;
            v = id[v]-1;
            g.insert(mp(min(u,v),max(u,v)));
        }
        cnt--;
        init(cnt);
        int flag = 0;
        int numcycle = 0;
        int tamciclo = 1;
        for(it = g.begin(); it!=g.end();++it)
        {
            pair<int,int>aux = *it;
            u = aux.first;
            v = aux.second;
            if( find(u)!= find(v) )
            {
                tamciclo++;
                uniset(u,v);
            }
            else numcycle++;
            cont[v]++;
            cont[u]++;
            if(cont[v] > 2 || cont[u] > 2) flag = 1;
        }
        if( flag )
        {
            printf("N\n");
            continue;
        }
        if( numcycle > 1 )
        {
            printf("N\n");
            continue;
        }
        else 
        {
            if( numcycle == 1 )
            {
                if( n == tamciclo )
                {
                    printf("Y\n");
                    continue;
                }
                else
                {
                    printf("N\n");
                    continue;
                }
            }
 
        }
         
        printf("Y\n");
    }
    return 0;
}
