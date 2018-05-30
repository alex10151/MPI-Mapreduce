#include <stdio.h>
#include "mpi.h"
#include "stdlib.h"
int main(int argc, char *argv[]) {
	int id,size;
	int max_play =200;
	int max_send_once = 5;
	int pingpong = 0;
	int flag = 0;
	int recv = 0;
		
	MPI_Init(&argc, &argv);
	MPI_Comm_size(MPI_COMM_WORLD, &size);
	MPI_Comm_rank(MPI_COMM_WORLD, &id);

	while (pingpong<max_play) {
		if (id == 0) {
			int sendball=rand()%max_send_once+1;
			pingpong+=sendball;
			if (pingpong>max_play) {
				pingpong-=sendball;
				continue;
			}
			MPI_Send(&sendball, 1, MPI_INT, (id+1)%2, 0, MPI_COMM_WORLD);
			printf("this is player %d sending  %d pingpongs to player %d.(total pingpong:--%d) \n",id,sendball,(id+1)%2,pingpong);
		}
		else {
			recv = 0;
			MPI_Recv(&recv, 1, MPI_INT, (id+1)%2, 0, MPI_COMM_WORLD, MPI_STATUS_IGNORE);
			
			printf("this is player %d i received %d pingpongs from player 0 \n",id,recv);
			
		}
		
	}
	MPI_Finalize();
	return 0;
}