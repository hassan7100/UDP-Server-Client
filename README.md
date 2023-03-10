# UDP-Server-Client
Java code of a UDP server and a UDP client, see the readme file
#Server
The server binds to port number 7000, and keep on waiting for requests (packets) to be received from clients.
The client packet includes double values, which the server will send back to the client sorted in ascending order.
In addition, the server keeps a log file named log.txt, in which it logs the date/time info and info of clients addressing (IP address in dotted decimal notation and port number) and their sorted numbers.

#Client
The client reads the double values from the user, until the user enters -1 (positive doubles are only assumed to be entered).
It must send these double values to the server, then wait for the server response.
When the response is received, it prints the returned sorted numbers to the console.
