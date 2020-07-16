package References;

public class FastFourierTransform {
/*
const double pi=acos(-1);
struct cp
{
    double r,i;
    cp(double r=0,double i=0):r(r),i(i){}
    cp operator+(cp b){return cp(r+b.r,i+b.i);}
    cp operator-(cp b){return cp(r-b.r,i-b.i);}
    cp operator*(cp b){return cp(r*b.r-i*b.i,r*b.i+i*b.r);}
}w[2][MN+5],F[MN+5],G[MN+5];
int N,R[MN+5],u[MN+5][6][6];
void init(int n)
{
    for(N=1;N<=n;N<<=1);
    cp g(cos(2*pi/N),sin(2*pi/N));int i,j,k;
    for(i=w[0][0].r=1;i<N;++i)w[0][i]=w[0][i-1]*g;
    for(i=w[1][0].r=1;i<N;++i)w[1][i]=w[0][N-i];
    for(i=j=0;i<N;R[++i]=j)for(k=N>>1;(j^=k)<k;k>>=1);
}
void fft(cp*x,int v)
{
    int i,j,k;
    for(i=0;i<N;++i)if(i<R[i])swap(x[i],x[R[i]]);
    for(i=1;i<N;i<<=1)for(j=0;j<N;j+=i<<1)for(k=0;k<i;++k)
    {
        cp p=x[i+j+k]*w[v][N/(i<<1)*k];
        x[i+j+k]=x[j+k]-p;x[j+k]=x[j+k]+p;
    }
    if(v)for(i=0;i<N;++i)x[i].r/=N,x[i].i/=N;
}
 */
}
