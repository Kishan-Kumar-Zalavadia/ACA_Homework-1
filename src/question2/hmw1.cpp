#include <iostream>
#include <vector>
#include <fstream>
using namespace std;

int main(int argc, char *argv[])
{
    
    srand (time(NULL));
    
    int n=rand()%15+1; //The number of arrays
    //cout<< n <<"\n";
    ofstream myfile;
    myfile.open("output.txt");
srand (time(NULL));
    
    vector<int> myvector;
    for(int j=0; j<n; j++)
    {
       int elements = rand()%5+1; //length of each array
        
        for(int k=0;k<elements;k++)
        {
            myvector.push_back(rand()%100+1);  //value of each element in the array
        }
        
        sort(myvector.begin(), myvector.end());
       for(int k=0;k<elements;k++)
       {
           myfile<<myvector[k]<<" "; //value of each element in the array
       }
       myfile<<endl;
        myvector.clear();
    } //end of for j;
   cout <<"am here \n";

    return 0;
}
