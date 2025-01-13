#include<iostream>
#include<stdlib.h>

struct Node{
	struct Node* prev;
	int data;
	struct Node* next;
};
//Adding the Node at the begining of the list
struct Node* insertAtbeg(struct Node* head){
	struct Node* temp= (struct Node*)malloc(sizeof(struct Node));
	temp->next= head;
	head->prev= temp;
	temp->prev= NULL;
	temp->data= 0;
    head= temp;
    printf("Node has successfully added at the beg\n");
	return head;
}
//Counting the total Nodes 
int countNodes(struct Node* head){
	if(head==NULL){
		printf("The List is empty:\n");
		return 0;
	}
	else {
		int count=0;
		struct Node* ptr= head;
		while(ptr!=NULL){
			count++;
			ptr=ptr->next;
		}
		printf("The total Nodes= %d\n",count);
		return count;
	}
}
//Adding the Node at the end of the list
  void addAtEnd(struct Node* head){
  	struct Node* temp= (struct Node*)malloc(sizeof(struct Node)); // Newly created Node.
  	struct Node* ptr= head; // To reach at the end.
  	while(ptr->next!= NULL){
  		ptr= ptr->next;
	  }
	  ptr->next= temp;
	  temp->prev= ptr;
	  temp->data= 3;
	  temp->next= NULL;
	printf("Node added at the End:\n");
  }  
 
//Adding the Node at cetrain position
struct Node* addAtCertain(struct Node* head,int position){
	int count=0;
	struct Node *temp, *ptr, *New;
	ptr= head;
	New= (struct Node*)malloc(sizeof(struct Node));
	 // jun position ma insert garni ho tyo vanda less time run huna paryo and we will store the pointer of the
	// previouse and the next position
	while(count<position)  {
	
	count++;
	temp= ptr;
	ptr= ptr->next;
}
  /*When we exit from the loop ptr will have the pointer of the next position and the temp will have the pointer of the previous position */
    New->prev= temp;
   	New->data= 10;
    New->next= ptr;
    temp->next= New;
    ptr->prev= New;
    printf("Node has successfully added at %d position:\n",position);
    return head;
}
// Printing the data from the each Node
void data(struct Node* head){
	printf("Function called to display data:\n");
	float data;
	struct Node* ptr= head;
	while(ptr->next!=NULL){
		ptr=ptr->next;
		data=ptr->data;
		printf("%0.1f\t",data);
	}
	printf("\n");
}
 int main(){
	//Creating a Node of a Doubly linked list
	struct Node* head= (struct Node*)malloc(sizeof(struct Node));
	head->prev= NULL;
	head->data= 1;
	head->next=NULL;
	head=insertAtbeg(head);
	countNodes(head);
	addAtEnd(head);
	countNodes(head);
	addAtEnd(head);
	addAtEnd(head);		
	addAtEnd(head);
	addAtEnd(head);		
	addAtEnd(head);
	countNodes(head);
	addAtCertain(head,7);
	countNodes(head);
	data(head);
	countNodes(head);
	return 0;
}
