Project Title:	Lab 5 - Binary Tree & Hashing.

Project Description:
This program builds a cross reference listing of words from the getty.txt file. It references the number of times each word appears in the document, the line number and position where each word appears allowing us to make specific word queries. To do this, we insert these words into a binary search tree and do a in order traversal. If duplicate words are inserted, it doesnt not get inserted but a counter gets incremented. To make the cross reference table simple as possible however, we insert a list of 27 omitted words into a hash table that should not appear in the reference listing. To do this, we create a hash function that returns a hash of a key and insert the word into the hash location inside the hash table. The hash function defined in lab produces only 1 collision. When words are to be inserted into object binary tree, we check to see if the word appears in the hash table. If it appears in the hash table, the word is omitted out. This provides us with a simple cross reference index that allows querying.

Version or Date: 5.0 - 5/5/16

How to Start the Project: Driver.java

Author:	Chris Banci

User Instructions: 
Please run the driver file to run the program. 
To save the csis.txt log, remember to quit the query search by entering 0.

A minimal hash function is in the hash.java file for extra credit.