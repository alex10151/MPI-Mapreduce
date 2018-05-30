#include "stdio.h"
#include "stdlib.h"
#include "mpi.h"
double fx(double a){
	return (4/(1+a*a));
}

int main(int argc,char* argv[]){
	
	int n,id,size;
	double pi,start_time,end_time;
	
	double interval;
	MPI_Init(&argc, &argv);
	MPI_Comm_size(MPI_COMM_WORLD, &size);
	MPI_Comm_rank(MPI_COMM_WORLD, &id);
	
	if (id == 0) {
		start_time = MPI_Wtime();
		printf("this is thread 0 input a split value:\n");
		scanf("%d",&n);
		interval = 1.0/(double)n;
		printf("the interval is %.10f\n",interval);
		
	}
	MPI_Bcast(&n, 1, MPI_INT, 0, MPI_COMM_WORLD);
	MPI_Bcast(&interval, 1, MPI_DOUBLE, 0, MPI_COMM_WORLD);
	double sum = 0;
	for(int i =id;i<=n;i=i+size){
		double x = ((double)i+0.5)*interval;
		sum = sum + fx(x);
		
	}
	sum = sum*interval;
	MPI_Reduce(&sum, &pi, 1, MPI_DOUBLE, MPI_SUM, 0, MPI_COMM_WORLD);

	if (id == 0) {
		end_time = MPI_Wtime();
		double process_time = end_time - start_time;
		printf(" the pi is : %.20f\n the process time is :%10f\n",pi,process_time);
	}
	MPI_Finalize();
	return  0;
	
}