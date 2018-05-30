#include <stdio.h>
#include "mpi.h"
int main(int argc, char *argv[]) {
	
	int rank;
	int size;
	int len;
	char  name[100];
	MPI_Init(&argc, &argv);
	MPI_Comm_rank(MPI_COMM_WORLD, &rank);
	MPI_Comm_size(MPI_COMM_WORLD,&size);
	MPI_Get_processor_name(name ,&len);
	printf("this is process %d ,my name is :%s  hello world!\n",rank, name);
	MPI_Finalize();
	
}