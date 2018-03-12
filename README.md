# D0020E
System Guide
 1.Set up an arrowhead cloud:
The easiest way is by using the docker file which can be found here: https://forge.soa4d.org/frs/?group_id=58. Run the docker file using the command “docker compose up --build”. 

To verify: check if you can access http://127.0.1.1:8080/managementtool/.

2. Publish the provider onto arrowhead:
First clone/download the source code from here: https://github.com/adsamaz/D0020E.

Run the http() function of the ArrowheadProvider class in the arrowhead package with the ip of your arrowhead cloud. You can also unpublish a service by changing the parameters.

3.Start the server:
Run the Server class in the hateoas package.

 4.Have the consumer make a lookup request:
Start the Consumer class in the consumer package. Make sure the ip leads to your arrowhead cloud and that you specify the name of your provider/server that you just published in step 2. You can also run the Consumer locally without arrowhead by commenting away one line, see the code.

 5.Use the screwdriver!
You will see be given different self evident choices like “Press 1 to tighten all screws”. Make your choices and see the results printed out.
