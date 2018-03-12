# D0020E
1.Find github project here: https://github.com/adsamaz/D0020E
2.Install git on your computer.
3.Download project either with desktop app or git bash with 
“git clone https://github.com/adsamaz/D0020E.git” 
or is you have setup github with ssh 
“git@github.com:adsamaz/D0020E.git”.
(Probably better to learn about git and github and fork the project and then download and work on it).
4.Open project in eclipse or equivalent java IDE.
5.Project requires use of maven. Make sure that your chosen IDE downloads the required dependencies.
6.Setup arrowhead framework core services. One way is to use this docker file: https://forge.soa4d.org/frs/?group_id=58. To verify: check if you can access http://127.0.1.1:8080/managementtool/.
7.Edit ports and IPs in our project to match those that you will be using.
8.Run hateoas/Server to get the server running.
(The Server.java does not register the provider with Arrowhead. To do this, you will need to either use the ArrowheadProvider in its own main method and run it, or add it to the Server.java main method or constructor).
9.Run consumer/Consumer to get the consumer running.
DONE.
