#include<stdio.h>
#include<stdlib.h>
struct node{
	int data;
	struct node* link;
}; 
//Adding the new node into the LinkList
void addNewNode(struct node *head, int a ){
	struct node *ptr, *temp;
	temp= (struct node*) malloc(sizeof(struct node));
	temp->data= a;
	temp->link= NULL;
	ptr= head;
	while(ptr->link!= NULL){
		ptr= ptr->link;
	}
	ptr->link= temp;
	printf("\n Node has successfull added to the LinkList:");
}
//Counting the total nodes in the LinkList
void countNodes(struct node *head){
	struct node* ptr;
	ptr= head;
	int count= 0;
	while(ptr!= NULL){
		count++;
		ptr= ptr->link;
	}
	printf("\n The total No of nodes is:%d \n",count);
}
//Printing the data in the each nodes
int getData(struct node* head){
	struct node* ptr;
	int i=0;
	ptr= head;
	while(ptr!= NULL){
		i++;
		printf("%d Node's data: %d \n",i, ptr->data);
		ptr= ptr->link;
	}
	}
		//Insertion of node at the beginning of the LinkList
	struct node* insertAtbeginning(struct node* head, int data){
		struct node* ptr;
		ptr= (struct node*) malloc(sizeof(struct node));
		ptr->link= head;
		ptr->data= data;
		head= ptr;
		return head;
	}
int main(){
	struct node *head, *current;
	head= (struct node*) malloc(sizeof(struct node));
	head->data= 1;
	head->link= NULL;
	addNewNode(head,2);
	//Adding 3 more nodes to the link list
	for(int i=3;i<=5;i++){
		addNewNode(head,i);
	}
	countNodes(head);
	 getData(head);
	 head=  insertAtbeginning(head,6);
	 countNodes(head);
	 getData(head);
	return 0;
}
